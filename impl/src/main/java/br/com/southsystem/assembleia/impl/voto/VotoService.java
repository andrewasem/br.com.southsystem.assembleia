package br.com.southsystem.assembleia.impl.voto;

import br.com.southsystem.assembleia.impl.associado.AssociadoService;
import br.com.southsystem.assembleia.impl.pauta.PautaEntity;
import br.com.southsystem.assembleia.impl.pauta.PautaService;
import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import br.com.southsystem.assembleia.impl.sessao.SessaoService;
import br.com.southsystem.assembleia.impl.utils.MessageResource;
import br.com.southsystem.exception.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Service
@AllArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;

    private final AssociadoService associadoService;

    private final PautaService pautaService;

    private final SessaoService sessaoService;

    public Mono<VotoEntity> votar(VotoEntity votoEntity) {

        return votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(), votoEntity.getAssociadoId())
                .flatMap(verifyExistBlockToVote())
                .flatMap(count -> Mono.just(votoEntity)
                        .then(Mono.defer(() -> associadoService.findById(votoEntity.getAssociadoId())))
                        .then(Mono.defer(() -> pautaService.findById(votoEntity.getPautaId())))
                        .then(Mono.defer(() -> sessaoService.validatePautaWithSessao(votoEntity.getPautaId())))
                        .then(Mono.defer(() -> validateSessaoOpenedToPauta(votoEntity.getPautaId())))
                        .then(Mono.defer(() -> votoRepository.save(votoEntity)))
                        .then(Mono.defer(() -> pautaService.findById(votoEntity.getPautaId())
                                .flatMap(somaTotaisDeVotos(votoEntity))
                                .flatMap(pautaService::inserirOrAtualizar)
                                .then(Mono.defer(() -> Mono.just(votoEntity)))
                        )));
    }

    private Function<Integer, Mono<? extends Integer>> verifyExistBlockToVote() {
        return count -> {
            if (count > 0) { // já votou
                var message = MessageResource.getInstance().getMessage("assembleia.votacao.associado.ja.votou");
                return Mono.error(ExceptionUtils.buildBadRequestException(message));
            }
            return Mono.just(count);
        };
    }

    private Function<PautaEntity, Mono<? extends PautaEntity>> somaTotaisDeVotos(VotoEntity votoEntity) {
        return pautaEntity -> {
            if (Boolean.TRUE.equals(votoEntity.getVoto())) {
                pautaEntity.setTotalVotosSim(pautaEntity.getTotalVotosSim() + 1);
            } else {
                pautaEntity.setTotalVotosNao(pautaEntity.getTotalVotosNao() + 1);
            }
            return Mono.just(pautaEntity);
        };
    }

    private Mono<SessaoEntity> validateSessaoOpenedToPauta(Long pautaId) {

        var message = MessageResource.getInstance().getMessage("assembleia.sessao.fora.intervalo.de.tempo");

        return votoRepository.validateSessaoOpenedToPauta(pautaId)
                .switchIfEmpty(Mono.error(ExceptionUtils.buildBadRequestException(message)));
    }
}
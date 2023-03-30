package br.com.southsystem.assembleia.impl.sessao;

import br.com.southsystem.assembleia.impl.pauta.PautaEntity;
import br.com.southsystem.assembleia.impl.pauta.PautaService;
import br.com.southsystem.assembleia.impl.utils.MessageResource;
import br.com.southsystem.exception.errortype.GenericException;
import br.com.southsystem.exception.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    private final PautaService pautaService;

    public Mono<SessaoEntity> abrirSessao(SessaoEntity sessaoEntity) {

        return findPautaBydId(sessaoEntity.getPautaId())
                .then(validateExistSessaoOpenedToPautaId(sessaoEntity))
                .then(buildSessaoEntityOpened(sessaoEntity));
    }

    public Mono<SessaoEntity> validatePautaWithSessao(Long pautaId) {
        log.debug("Realizando busca de sessão por pautaId {}", pautaId);
        var message = MessageResource.getInstance().getMessage("assembleia.sessao.por.pauta.nao.encontrada");

        return sessaoRepository.findByPautaId(pautaId)
                .switchIfEmpty(ExceptionUtils.buildNotFoundException(message));
    }

    private Mono<SessaoEntity> buildSessaoEntityOpened(SessaoEntity sessaoEntity) {
        return Mono.defer(() -> {
            if (sessaoEntity.getTempoDeAbertura() == null) {
                sessaoEntity.setTempoDeAbertura(1);
            }
            var dataHoraInicio = LocalDateTime.now();

            sessaoEntity.setDataHoraInicio(LocalDateTime.now());
            sessaoEntity.setDataHoraFim(dataHoraInicio.plusMinutes(sessaoEntity.getTempoDeAbertura()));
            return sessaoRepository.save(sessaoEntity);
        });
    }

    private Mono<GenericException> validateExistSessaoOpenedToPautaId(SessaoEntity sessaoEntity) {
        return Mono.defer(() -> sessaoRepository.findByPautaId(sessaoEntity.getPautaId())
                .flatMap(se -> {
                    var message = MessageResource.getInstance().getMessage("assembleia.sessao.aberta.para.pauta");
                    return Mono.error(ExceptionUtils.buildBadRequestException(message));
                }));
    }

    private Mono<PautaEntity> findPautaBydId(Long pautaId) {
        log.debug("Realizando busca de pauta pelo identificador da pauta id {}", pautaId);
        return pautaService.findById(pautaId);
    }
}
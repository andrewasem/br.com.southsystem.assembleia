package br.com.southsystem.assembleia.impl.pauta;

import br.com.southsystem.assembleia.impl.utils.MessageResource;
import br.com.southsystem.assembleia.impl.utils.ServiceConstants;
import br.com.southsystem.exception.errortype.NoContentException;
import br.com.southsystem.exception.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Service
@AllArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    public Mono<PautaEntity> inserirOrAtualizar(PautaEntity pautaEntity) {
        return Mono.just(pautaEntity)
                .flatMap(setValorInicialParaTotaisDeVotos())
                .flatMap(pautaRepository ::save);
    }

    public Mono<PautaEntity> findById(Long id) {
        log.debug("Realizando Busca de pauta por id {}", id);
        var message = MessageResource.getInstance().getMessage(ServiceConstants.PAUTA_NAO_ENCONTRADA);

        return pautaRepository.findById(id)
                .switchIfEmpty(ExceptionUtils.buildNotFoundException(message));
    }

    public Flux<PautaEntity> findAll() {
        log.debug("Realizando Busca de pautas");
        var message = MessageResource.getInstance().getMessage(ServiceConstants.PAUTA_NAO_ENCONTRADA);

        return pautaRepository.findAll()
                .switchIfEmpty(Flux.error(NoContentException::new));
    }

    private Function<PautaEntity, Mono<? extends PautaEntity>> setValorInicialParaTotaisDeVotos() {
        return pautaEntity -> {
            if (pautaEntity.getTotalVotosNao() == null) {
                pautaEntity.setTotalVotosNao(0);
            }
            if (pautaEntity.getTotalVotosSim() == null) {
                pautaEntity.setTotalVotosSim(0);
            }
            return Mono.just(pautaEntity);
        };
    }
}
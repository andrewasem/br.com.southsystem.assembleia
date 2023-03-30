package br.com.southsystem.assembleia.impl.associado;

import br.com.southsystem.assembleia.impl.utils.MessageResource;
import br.com.southsystem.exception.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public Mono<AssociadoEntity> inserir(AssociadoEntity associadoEntity) {
        return associadoRepository.save(associadoEntity);
    }

    public Mono<AssociadoEntity> findById(Long idAssociado) {

        var message = MessageResource.getInstance().getMessage("assembleia.associado.nao.encontrado");

        return associadoRepository.findById(idAssociado)
                .switchIfEmpty(ExceptionUtils.buildNotFoundException(message));
    }
}
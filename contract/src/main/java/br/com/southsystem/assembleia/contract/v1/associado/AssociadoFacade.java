package br.com.southsystem.assembleia.contract.v1.associado;

import br.com.southsystem.assembleia.contract.v1.associado.mapper.AssociadoMapper;
import br.com.southsystem.assembleia.contract.v1.associado.model.request.AssociadoRequest;
import br.com.southsystem.assembleia.contract.v1.associado.model.response.AssociadoResponse;
import br.com.southsystem.assembleia.impl.associado.AssociadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AssociadoFacade {

    private final AssociadoService associadoService;

    public Mono<AssociadoResponse> inserir(AssociadoRequest associadoRequest) {
        var asssociadoEntity = AssociadoMapper.INSTANCE.requestToEntity(associadoRequest);

        return associadoService.inserir(asssociadoEntity)
                .map(AssociadoMapper.INSTANCE::entityToResponse);
    }
}

package br.com.southsystem.assembleia.contract.v1.voto;

import br.com.southsystem.assembleia.contract.v1.voto.mapper.VotoMapper;
import br.com.southsystem.assembleia.contract.v1.voto.model.request.VotoRequest;
import br.com.southsystem.assembleia.contract.v1.voto.model.response.VotoResponse;
import br.com.southsystem.assembleia.impl.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VotoFacade {

    private final VotoService votoService;

    public Mono<VotoResponse> votar(VotoRequest votoRequest) {
        var votoEntity = VotoMapper.INSTANCE.requestToEntity(votoRequest);

        return votoService.votar(votoEntity)
                .map(VotoMapper.INSTANCE::entityToResponse);
    }
}

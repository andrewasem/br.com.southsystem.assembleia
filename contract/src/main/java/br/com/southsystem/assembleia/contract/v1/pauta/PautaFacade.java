package br.com.southsystem.assembleia.contract.v1.pauta;

import br.com.southsystem.assembleia.contract.v1.pauta.mapper.PautaMapper;
import br.com.southsystem.assembleia.contract.v1.pauta.model.request.PautaRequest;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.assembleia.impl.pauta.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PautaFacade {

    private final PautaService pautaService;

    public Mono<PautaResponse> insert(PautaRequest pautaRequest) {
        var pautaEntity = PautaMapper.INSTANCE.requestToEntity(pautaRequest);

        return pautaService.inserirOrAtualizar(pautaEntity)
                .map(PautaMapper.INSTANCE::entityToResponse);
    }

    public Flux<PautaResponse> findAll() {
        return pautaService.findAll()
                .map(PautaMapper.INSTANCE::entityToResponse);
    }
}

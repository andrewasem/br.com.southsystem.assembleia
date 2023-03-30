package br.com.southsystem.assembleia.contract.v1.sessao;

import br.com.southsystem.assembleia.contract.v1.sessao.mapper.SessaoMapper;
import br.com.southsystem.assembleia.contract.v1.sessao.model.request.SessaoRequest;
import br.com.southsystem.assembleia.contract.v1.sessao.model.response.SessaoResponse;
import br.com.southsystem.assembleia.impl.sessao.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SessaoFacade {

    private final SessaoService sessaoService;

    public Mono<SessaoResponse> abrirSessao(SessaoRequest sessaoRequest) {
        var sessaoEntity = SessaoMapper.INSTANCE.requestToEntity(sessaoRequest);

        return sessaoService.abrirSessao(sessaoEntity)
                .map(SessaoMapper.INSTANCE::entityToResponse);
    }
}

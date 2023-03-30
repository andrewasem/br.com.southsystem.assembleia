package br.com.southsystem.assembleia.contract.v1.sessao.fixture;

import br.com.southsystem.assembleia.contract.v1.sessao.model.request.SessaoRequest;
import br.com.southsystem.assembleia.contract.v1.sessao.model.response.SessaoResponse;
import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class SessaoContractFixture {

    public SessaoRequest getSessaoRequest() {
        return SessaoRequest.builder()
                .pautaId(1L)
                .tempoDeAbertura(60)
                .build();
    }

    public SessaoResponse getSessaoResponse() {
        return SessaoResponse.builder()
                .pautaId(1L)
                .tempoDeAbertura(60)
                .dataHoraFim(LocalDateTime.of(2020, 1, 1, 23, 59, 59))
                .dataHoraInicio(LocalDateTime.of(2020, 1, 1, 22, 59, 59))
                .id(1L)
                .build();
    }

    public SessaoEntity getSessaoEntity() {
        return SessaoEntity.builder()
                .pautaId(1L)
                .tempoDeAbertura(60)
                .dataHoraFim(LocalDateTime.of(2020, 1, 1, 23, 59, 59))
                .dataHoraInicio(LocalDateTime.of(2020, 1, 1, 22, 59, 59))
                .id(1L)
                .build();
    }
}

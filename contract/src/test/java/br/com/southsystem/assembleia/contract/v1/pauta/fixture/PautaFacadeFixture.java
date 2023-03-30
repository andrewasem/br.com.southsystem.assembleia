package br.com.southsystem.assembleia.contract.v1.pauta.fixture;

import br.com.southsystem.assembleia.contract.v1.pauta.model.request.PautaRequest;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.assembleia.impl.pauta.PautaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PautaFacadeFixture {

    public PautaRequest getPautaRequest() {
        return PautaRequest.builder()
                .descricao("A")
                .build();
    }

    public PautaResponse getPautaResponse() {
        return PautaResponse.builder()
                .descricao("A")
                .id(1L)
                .build();
    }

    public PautaEntity getPautaEntity() {
        return PautaEntity.builder()
                .descricao("A")
                .id(1L)
                .build();
    }
}

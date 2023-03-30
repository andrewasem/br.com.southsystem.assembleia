package br.com.southsystem.assembleia.contract.v1.voto.fixture;

import br.com.southsystem.assembleia.contract.v1.voto.model.request.VotoRequest;
import br.com.southsystem.assembleia.contract.v1.voto.model.response.VotoResponse;
import br.com.southsystem.assembleia.impl.voto.VotoEntity;
import br.com.southsystem.assembleia.impl.voto.VotoTypeEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VotoContractFixture {

    public VotoRequest getVotoRequest() {
        return VotoRequest.builder()
                .pautaId(1L)
                .voto(VotoTypeEnum.SIM)
                .associadoId(1L)
                .build();
    }

    public VotoResponse getVotoResponse() {
        return VotoResponse.builder()
                .id(1L)
                .pautaId(1L)
                .associadoId(1L)
                .voto(VotoTypeEnum.SIM)
                .build();
    }

    public VotoEntity getVotoEntity() {
        return VotoEntity.builder()
                .id(1L)
                .pautaId(1L)
                .voto(true)
                .associadoId(1L)
                .build();
    }
}

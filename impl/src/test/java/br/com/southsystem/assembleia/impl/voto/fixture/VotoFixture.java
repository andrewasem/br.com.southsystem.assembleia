package br.com.southsystem.assembleia.impl.voto.fixture;

import br.com.southsystem.assembleia.impl.voto.VotoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VotoFixture {
    public VotoEntity getVotoEntity() {
        return VotoEntity.builder()
                .id(1L)
                .voto(true)
                .associadoId(1L)
                .pautaId(1L)
                .build();
    }
}

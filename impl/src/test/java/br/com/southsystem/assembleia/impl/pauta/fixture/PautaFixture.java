package br.com.southsystem.assembleia.impl.pauta.fixture;

import br.com.southsystem.assembleia.impl.pauta.PautaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PautaFixture {

    public PautaEntity getPautaEntity() {
        return PautaEntity.builder()
                .id(1L)
                .descricao("A")
                .totalVotosNao(1)
                .totalVotosSim(1)
                .build();
    }
}

package br.com.southsystem.assembleia.impl.associado.fixture;

import br.com.southsystem.assembleia.impl.associado.AssociadoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AssociadoFixture {

    public AssociadoEntity getAssociadoEntity() {
        return AssociadoEntity.builder()
                .id(1L)
                .nome("A")
                .build();
    }
}

package br.com.southsystem.assembleia.contract.v1.associado.fixture;

import br.com.southsystem.assembleia.contract.v1.associado.model.request.AssociadoRequest;
import br.com.southsystem.assembleia.contract.v1.associado.model.response.AssociadoResponse;
import br.com.southsystem.assembleia.impl.associado.AssociadoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AssociadoFacadeFixture {

    public AssociadoRequest getAssociadoRequest() {
        return AssociadoRequest.builder()
                .nome("A")
                .build();
    }

    public AssociadoResponse getAssociadoResponse() {
        return AssociadoResponse.builder()
                .nome("A")
                .id(1L)
                .build();
    }

    public AssociadoEntity getAssociadoEntity() {
        return AssociadoEntity.builder()
                .nome("A")
                .id(1L)
                .build();
    }
}

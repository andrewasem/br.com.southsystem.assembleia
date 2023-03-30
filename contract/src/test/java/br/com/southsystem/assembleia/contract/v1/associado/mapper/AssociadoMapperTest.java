package br.com.southsystem.assembleia.contract.v1.associado.mapper;


import br.com.southsystem.assembleia.contract.v1.associado.fixture.AssociadoFacadeFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssociadoMapperTest {

    @Test
    void testConverterAssociadoRequestToAssociadoEntityWithSuccess() {
        var associadoRequest = AssociadoFacadeFixture.getAssociadoRequest();

        var expectedAssociadoEntity = AssociadoFacadeFixture.getAssociadoEntity();

        var actualAssociadoEntity = AssociadoMapper.INSTANCE.requestToEntity(associadoRequest);

        assertEquals(expectedAssociadoEntity.getNome(), actualAssociadoEntity.getNome());
    }

    @Test
    void testConverterAssociadoEntityToAssociadoResponseWithSuccess() {
        var associadoEntity = AssociadoFacadeFixture.getAssociadoEntity();

        var associadoResponse = AssociadoFacadeFixture.getAssociadoResponse();

        var actualAssociadoResponse = AssociadoMapper.INSTANCE.entityToResponse(associadoEntity);

        assertEquals(associadoResponse, actualAssociadoResponse);
    }
}

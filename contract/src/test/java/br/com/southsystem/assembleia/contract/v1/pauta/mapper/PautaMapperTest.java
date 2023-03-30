package br.com.southsystem.assembleia.contract.v1.pauta.mapper;


import br.com.southsystem.assembleia.contract.v1.pauta.fixture.PautaFacadeFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PautaMapperTest {

    @Test
    void testConverterPautaRequestToPautaEntityWithSuccess() {
        var pautaRequest = PautaFacadeFixture.getPautaRequest();

        var expectedPautaEntity = PautaFacadeFixture.getPautaEntity();

        var actualPautaEntity = PautaMapper.INSTANCE.requestToEntity(pautaRequest);

        assertEquals(expectedPautaEntity.getDescricao(), actualPautaEntity.getDescricao());
    }

    @Test
    void testConverterPautaEntityToPautaResponseWithSuccess() {
        var pautaEntity = PautaFacadeFixture.getPautaEntity();

        var pautaResponse = PautaFacadeFixture.getPautaResponse();

        var actualPautaResponse = PautaMapper.INSTANCE.entityToResponse(pautaEntity);

        assertEquals(pautaResponse, actualPautaResponse);
    }
}

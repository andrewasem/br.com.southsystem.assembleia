package br.com.southsystem.assembleia.contract.v1.voto.mapper;

import br.com.southsystem.assembleia.contract.v1.voto.fixture.VotoFacadeFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VotoMapperTest {

    @Test
    void testConverterVotoRequestToVotoEntityWithSuccess() {
        var votoRequest = VotoFacadeFixture.getVotoRequest();

        var expectedVotoEntity = VotoFacadeFixture.getVotoEntity();
        expectedVotoEntity.setId(null);

        var actualVotoEntity = VotoMapper.INSTANCE.requestToEntity(votoRequest);

        assertEquals(expectedVotoEntity, actualVotoEntity);
    }

    @Test
    void testConverterVotoEntityToVotoResponseWithSuccess() {
        var votoEntity = VotoFacadeFixture.getVotoEntity();

        var votoResponse = VotoFacadeFixture.getVotoResponse();

        var actualVotoResponse = VotoMapper.INSTANCE.entityToResponse(votoEntity);

        assertEquals(votoResponse, actualVotoResponse);
    }
}

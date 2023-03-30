package br.com.southsystem.assembleia.contract.v1.sessao.mapper;


import br.com.southsystem.assembleia.contract.v1.sessao.fixture.SessaoFacadeFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessaoMapperTest {

    @Test
    void testConverterSessaoRequestToSessaoEntityWithSuccess() {
        var sessaoRequest = SessaoFacadeFixture.getSessaoRequest();

        var expectedSessaoEntity = SessaoFacadeFixture.getSessaoEntity();

        var actualSessaoEntity = SessaoMapper.INSTANCE.requestToEntity(sessaoRequest);

        assertEquals(expectedSessaoEntity.getPautaId(), actualSessaoEntity.getPautaId());
        assertEquals(expectedSessaoEntity.getTempoDeAbertura(), actualSessaoEntity.getTempoDeAbertura());
    }

    @Test
    void testConverterSessaoEntityToSessaoResponseWithSuccess() {
        var sessaoEntity = SessaoFacadeFixture.getSessaoEntity();

        var sessaoResponse = SessaoFacadeFixture.getSessaoResponse();

        var actualSessaoResponse = SessaoMapper.INSTANCE.entityToResponse(sessaoEntity);

        assertEquals(sessaoResponse, actualSessaoResponse);
    }
}

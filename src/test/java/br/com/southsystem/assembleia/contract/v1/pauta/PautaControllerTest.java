package br.com.southsystem.assembleia.contract.v1.pauta;

import br.com.southsystem.assembleia.contract.v1.pauta.fixture.PautaContractFixture;
import br.com.southsystem.assembleia.contract.v1.pauta.mapper.PautaMapper;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.assembleia.contract.v1.util.WebTestClientUtil;
import br.com.southsystem.assembleia.impl.pauta.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
@TestPropertySource("/test.properties")
class PautaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PautaRepository pautaRepository;

    @Test
    void mustSavePauta() {
        var pautaRequest = PautaContractFixture.getPautaRequest();
        var pautaEntity = PautaContractFixture.getPautaEntity();
        pautaEntity.setId(null);

        given(pautaRepository.save(pautaEntity))
                .willReturn(Mono.just(pautaEntity));

        var expectedPautaResponse = PautaMapper.INSTANCE.entityToResponse(pautaEntity);

        var actualPautaResponseEntityExchangeResult = WebTestClientUtil
                .buildPostWebClient(webTestClient, "/v1/pauta", pautaRequest, PautaResponse.class, MediaType.APPLICATION_JSON);

        assertEquals(HttpStatus.CREATED, actualPautaResponseEntityExchangeResult.getStatus());
        Assertions.assertEquals(expectedPautaResponse, actualPautaResponseEntityExchangeResult.getResponseBody());
    }
}

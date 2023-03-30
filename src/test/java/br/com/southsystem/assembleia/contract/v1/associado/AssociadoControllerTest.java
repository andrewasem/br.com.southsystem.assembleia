package br.com.southsystem.assembleia.contract.v1.associado;

import br.com.southsystem.assembleia.contract.v1.associado.fixture.AssociadoContractFixture;
import br.com.southsystem.assembleia.contract.v1.associado.mapper.AssociadoMapper;
import br.com.southsystem.assembleia.contract.v1.associado.model.response.AssociadoResponse;
import br.com.southsystem.assembleia.contract.v1.util.WebTestClientUtil;
import br.com.southsystem.assembleia.impl.associado.AssociadoRepository;
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
class AssociadoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AssociadoRepository associadoRepository;

    @Test
    void mustSaveAssociado() {
        var associadoRequest = AssociadoContractFixture.getAssociadoRequest();
        var associadoEntity = AssociadoContractFixture.getAssociadoEntity();
        associadoEntity.setId(null);

        given(associadoRepository.save(associadoEntity))
                .willReturn(Mono.just(associadoEntity));

        var expectedAssociadoResponse = AssociadoMapper.INSTANCE.entityToResponse(associadoEntity);

        var actualAssociadoResponseEntityExchangeResult = WebTestClientUtil
                .buildPostWebClient(webTestClient, "/v1/associado", associadoRequest, AssociadoResponse.class, MediaType.APPLICATION_JSON);

        assertEquals(HttpStatus.CREATED, actualAssociadoResponseEntityExchangeResult.getStatus());
        Assertions.assertEquals(expectedAssociadoResponse, actualAssociadoResponseEntityExchangeResult.getResponseBody());
    }
}

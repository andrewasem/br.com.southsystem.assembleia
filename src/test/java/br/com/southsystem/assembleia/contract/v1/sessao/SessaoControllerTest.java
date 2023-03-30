package br.com.southsystem.assembleia.contract.v1.sessao;

import br.com.southsystem.assembleia.contract.v1.pauta.fixture.PautaContractFixture;
import br.com.southsystem.assembleia.contract.v1.sessao.fixture.SessaoContractFixture;
import br.com.southsystem.assembleia.contract.v1.sessao.mapper.SessaoMapper;
import br.com.southsystem.assembleia.contract.v1.sessao.model.response.SessaoResponse;
import br.com.southsystem.assembleia.contract.v1.util.WebTestClientUtil;
import br.com.southsystem.assembleia.impl.pauta.PautaRepository;
import br.com.southsystem.assembleia.impl.sessao.SessaoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
@TestPropertySource("/test.properties")
class SessaoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SessaoRepository sessaoRepository;

    @MockBean
    private PautaRepository pautaRepository;

    @Test
    void mustSaveSessao() {
        var sessaoRequest = SessaoContractFixture.getSessaoRequest();
        var sessaoEntity = SessaoContractFixture.getSessaoEntity();
        var pautaEntity = PautaContractFixture.getPautaEntity();

        sessaoEntity.setId(null);

        given(sessaoRepository.save(any()))
                .willReturn(Mono.just(sessaoEntity));

        given(sessaoRepository.findByPautaId(sessaoEntity.getPautaId()))
                .willReturn(Mono.empty());

        given(pautaRepository.findById(1L))
                .willReturn(Mono.just(pautaEntity));

        var actualSessaoResponseEntityExchangeResult = WebTestClientUtil
                .buildPostWebClient(webTestClient, "/v1/sessao", sessaoRequest, SessaoResponse.class,
                        MediaType.APPLICATION_JSON);

        assertEquals(HttpStatus.CREATED, actualSessaoResponseEntityExchangeResult.getStatus());

        var expectedSuccessResponse = SessaoMapper.INSTANCE.entityToResponse(sessaoEntity);
        var actualSessionResponse = actualSessaoResponseEntityExchangeResult.getResponseBody();

        expectedSuccessResponse.setDataHoraFim(actualSessionResponse.getDataHoraFim());
        expectedSuccessResponse.setDataHoraInicio(actualSessionResponse.getDataHoraInicio());

        assertEquals(expectedSuccessResponse, actualSessionResponse);
    }
}

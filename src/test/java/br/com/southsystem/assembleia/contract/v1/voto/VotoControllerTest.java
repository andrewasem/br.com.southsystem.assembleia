package br.com.southsystem.assembleia.contract.v1.voto;

import br.com.southsystem.assembleia.contract.v1.associado.fixture.AssociadoContractFixture;
import br.com.southsystem.assembleia.contract.v1.pauta.fixture.PautaContractFixture;
import br.com.southsystem.assembleia.contract.v1.sessao.fixture.SessaoContractFixture;
import br.com.southsystem.assembleia.contract.v1.util.WebTestClientUtil;
import br.com.southsystem.assembleia.contract.v1.voto.fixture.VotoContractFixture;
import br.com.southsystem.assembleia.contract.v1.voto.mapper.VotoMapper;
import br.com.southsystem.assembleia.contract.v1.voto.model.response.VotoResponse;
import br.com.southsystem.assembleia.impl.associado.AssociadoRepository;
import br.com.southsystem.assembleia.impl.pauta.PautaRepository;
import br.com.southsystem.assembleia.impl.sessao.SessaoRepository;
import br.com.southsystem.assembleia.impl.voto.VotoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
@TestPropertySource("/test.properties")
class VotoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VotoRepository votoRepository;

    @MockBean
    private PautaRepository pautaRepository;

    @MockBean
    private AssociadoRepository associadoRepository;

    @MockBean
    private SessaoRepository sessaoRepository;

    @Test
    void mustSaveVoto() {
        var votoRequest = VotoContractFixture.getVotoRequest();
        var votoEntity = VotoContractFixture.getVotoEntity();
        var pautaEntity = PautaContractFixture.getPautaEntity();
        var associadoEntity = AssociadoContractFixture.getAssociadoEntity();
        var sessaoEntity = SessaoContractFixture.getSessaoEntity();

        votoEntity.setId(null);

        given(votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(), votoEntity.getAssociadoId()))
                .willReturn(Mono.just(0));

        given(pautaRepository.findById(1L))
                .willReturn(Mono.just(pautaEntity));

        given(pautaRepository.save(pautaEntity))
                .willReturn(Mono.just(pautaEntity));

        given(sessaoRepository.findByPautaId(sessaoEntity.getPautaId()))
                .willReturn(Mono.just(sessaoEntity));

        given(associadoRepository.findById(1L))
                .willReturn(Mono.just(associadoEntity));

        given(votoRepository.save(any()))
                .willReturn(Mono.just(votoEntity));

        given(votoRepository.validateSessaoOpenedToPauta(votoEntity.getPautaId()))
                .willReturn(Mono.just(sessaoEntity));

        var expectedVotoResponse = VotoMapper.INSTANCE.entityToResponse(votoEntity);

        var actualVotoResponseEntityExchangeResult = WebTestClientUtil
                .buildPostWebClient(webTestClient, "/v1/voto", votoRequest, VotoResponse.class, MediaType.APPLICATION_JSON);

        assertEquals(HttpStatus.CREATED, actualVotoResponseEntityExchangeResult.getStatus());
        Assertions.assertEquals(expectedVotoResponse, actualVotoResponseEntityExchangeResult.getResponseBody());
    }
}

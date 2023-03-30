package br.com.southsystem.assembleia.contract.v1.pauta;

import br.com.southsystem.assembleia.contract.v1.pauta.fixture.PautaFacadeFixture;
import br.com.southsystem.assembleia.contract.v1.pauta.mapper.PautaMapper;
import br.com.southsystem.assembleia.impl.pauta.PautaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PautaFacadeTest {

    @InjectMocks
    private PautaFacade pautaFacade;

    @Mock
    private PautaService pautaService;

    @Test
    void checksCallToInsertPautaEntityTest() {
        var pautaRequest = PautaFacadeFixture.getPautaRequest();

        var pautaEntity = PautaMapper.INSTANCE.requestToEntity(pautaRequest);

        var expectedPautaResponse = PautaMapper.INSTANCE.entityToResponse(pautaEntity);

        given(pautaService.inserirOrAtualizar(pautaEntity))
                .willReturn(Mono.just(pautaEntity));

        var actualPautaResponseMono = pautaFacade.insert(pautaRequest);

        StepVerifier.create(actualPautaResponseMono)
                .expectNext(expectedPautaResponse)
                .verifyComplete();

        then(pautaService).should(times(1))
                .inserirOrAtualizar(pautaEntity);
    }
}
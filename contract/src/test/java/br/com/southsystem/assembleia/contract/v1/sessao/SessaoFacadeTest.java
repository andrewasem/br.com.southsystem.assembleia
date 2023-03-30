package br.com.southsystem.assembleia.contract.v1.sessao;

import br.com.southsystem.assembleia.contract.v1.sessao.fixture.SessaoFacadeFixture;
import br.com.southsystem.assembleia.contract.v1.sessao.mapper.SessaoMapper;
import br.com.southsystem.assembleia.impl.sessao.SessaoService;
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
class SessaoFacadeTest {

    @InjectMocks
    private SessaoFacade sessaoFacade;

    @Mock
    private SessaoService sessaoService;

    @Test
    void checksCallToInsertPautaEntityTest() {
        var sessaoRequest = SessaoFacadeFixture.getSessaoRequest();

        var sessaoEntity = SessaoMapper.INSTANCE.requestToEntity(sessaoRequest);

        var expectedSessaoResponse = SessaoMapper.INSTANCE.entityToResponse(sessaoEntity);

        given(sessaoService.abrirSessao(sessaoEntity))
                .willReturn(Mono.just(sessaoEntity));

        var actualSessaoResponseMono = sessaoFacade.abrirSessao(sessaoRequest);

        StepVerifier.create(actualSessaoResponseMono)
                .expectNext(expectedSessaoResponse)
                .verifyComplete();

        then(sessaoService).should(times(1))
                .abrirSessao(sessaoEntity);
    }
}
package br.com.southsystem.assembleia.impl.sessao;


import br.com.southsystem.assembleia.impl.pauta.PautaService;
import br.com.southsystem.assembleia.impl.pauta.fixture.PautaFixture;
import br.com.southsystem.assembleia.impl.sessao.fixture.SessaoFixture;
import br.com.southsystem.exception.errortype.GenericException;
import br.com.southsystem.exception.utils.ExceptionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService sessaoService;

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaService pautaService;

    @Test
    void mustOpenSessaoEntityTest() {

        var sessaoEntity = SessaoFixture.getSessaoEntity();
        var pautaEntity = PautaFixture.getPautaEntity();

        var sessaoEntityCaptor = ArgumentCaptor.forClass(SessaoEntity.class);

        given(sessaoRepository.save(sessaoEntityCaptor.capture()))
                .willReturn(Mono.just(sessaoEntity));

        given(pautaService.findById(1L))
                .willReturn(Mono.just(pautaEntity));

        given(sessaoRepository.findByPautaId(1L))
                .willReturn(Mono.empty());

        var sessaoEntityActual = sessaoService.abrirSessao(sessaoEntity);

        StepVerifier.create(sessaoEntityActual)
                .expectNext(sessaoEntity)
                .verifyComplete();

        then(sessaoRepository).should(times(1))
                .save(sessaoEntity);

        then(pautaService).should(times(1))
                .findById(1L);
    }

    @Test
    void unsuccessfulToSaveSessao() {

        var sessaoEntity = SessaoFixture.getSessaoEntity();

        given(pautaService.findById(1L))
                .willReturn(ExceptionUtils.buildNotFoundException("Any"));

        var sessaoEntityActual = sessaoService.abrirSessao(sessaoEntity);

        StepVerifier.create(sessaoEntityActual)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(pautaService).should(times(1))
                .findById(1L);
    }
}
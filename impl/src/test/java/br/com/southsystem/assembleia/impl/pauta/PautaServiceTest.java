package br.com.southsystem.assembleia.impl.pauta;


import br.com.southsystem.assembleia.impl.pauta.fixture.PautaFixture;
import br.com.southsystem.exception.errortype.GenericException;
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

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    void mustSavePautaEntityTest() {

        var pautaEntity = PautaFixture.getPautaEntity();

        var pautaEntityMono = Mono.just(pautaEntity);

        given(pautaRepository.save(pautaEntity))
                .willReturn(pautaEntityMono);

        var pautaEntityActual = pautaService.inserirOrAtualizar(pautaEntity);

        StepVerifier.create(pautaEntityActual)
                .expectNext(pautaEntity)
                .verifyComplete();

        then(pautaRepository).should(times(1))
                .save(pautaEntity);
    }

    @Test
    void mustReturnedPautaEntityByIdTest() {

        var pautaEntity = PautaFixture.getPautaEntity();

        var pautaEntityMono = Mono.just(pautaEntity);

        given(pautaRepository.findById(1L))
                .willReturn(pautaEntityMono);

        var actualPautaEntityMono = pautaService.findById(1L);

        StepVerifier.create(actualPautaEntityMono)
                .expectNext(pautaEntity)
                .verifyComplete();

        then(pautaRepository).should(times(1))
                .findById(1L);
    }

    @Test
    void shouldReturnMonoErrorWhenFindByIdReturnEmptyAtFindPautaTest() {

        given(pautaRepository.findById(1L))
                .willReturn(Mono.empty());

        var pautaEntityMono = pautaService.findById(1L);

        StepVerifier.create(pautaEntityMono)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(pautaRepository).should(times(1)).findById(1L);
    }
}
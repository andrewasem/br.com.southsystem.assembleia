package br.com.southsystem.assembleia.impl.associado;


import br.com.southsystem.assembleia.impl.associado.fixture.AssociadoFixture;
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
class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    @Test
    void mustSaveAssociadoEntityTest() {

        var associadoEntity = AssociadoFixture.getAssociadoEntity();

        var associadoEntityMono = Mono.just(associadoEntity);

        given(associadoRepository.save(associadoEntity))
                .willReturn(associadoEntityMono);

        var associadoEntityActual = associadoService.inserir(associadoEntity);

        StepVerifier.create(associadoEntityActual)
                .expectNext(associadoEntity)
                .verifyComplete();

        then(associadoRepository).should(times(1)).save(associadoEntity);
    }

    @Test
    void mustReturnedAssociadoEntityByIdTest() {

        var associadoEntity = AssociadoFixture.getAssociadoEntity();

        var associadoEntityMono = Mono.just(associadoEntity);

        given(associadoRepository.findById(1L))
                .willReturn(associadoEntityMono);

        var actualAssociadoEntityMono = associadoService.findById(1L);

        StepVerifier.create(actualAssociadoEntityMono)
                .expectNext(associadoEntity)
                .verifyComplete();

        then(associadoRepository).should(times(1))
                .findById(1L);
    }

    @Test
    void shouldReturnMonoErrorWhenFindByIdReturnEmptyAtFindAssociadoTest() {

        given(associadoRepository.findById(1L))
                .willReturn(Mono.empty());

        var associadoEntityMono = associadoService.findById(1L);

        StepVerifier.create(associadoEntityMono)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(associadoRepository).should(times(1)).findById(1L);
    }
}
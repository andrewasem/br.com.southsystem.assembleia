package br.com.southsystem.assembleia.contract.v1.associado;

import br.com.southsystem.assembleia.contract.v1.associado.fixture.AssociadoFacadeFixture;
import br.com.southsystem.assembleia.contract.v1.associado.mapper.AssociadoMapper;
import br.com.southsystem.assembleia.impl.associado.AssociadoService;
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
class AssociadoFacadeTest {

    @InjectMocks
    private AssociadoFacade associadoFacade;

    @Mock
    private AssociadoService associadoService;

    @Test
    void checksCallToInsertAssociadoEntityTest() {
        var associadoRequest = AssociadoFacadeFixture.getAssociadoRequest();

        var associadoEntity = AssociadoMapper.INSTANCE.requestToEntity(associadoRequest);

        var expectedAssociadoResponse = AssociadoMapper.INSTANCE.entityToResponse(associadoEntity);

        given(associadoService.inserir(associadoEntity))
                .willReturn(Mono.just(associadoEntity));

        var actualAssociadoResponseMono = associadoFacade.inserir(associadoRequest);

        StepVerifier.create(actualAssociadoResponseMono)
                .expectNext(expectedAssociadoResponse)
                .verifyComplete();

        then(associadoService).should(times(1))
                .inserir(associadoEntity);
    }
}
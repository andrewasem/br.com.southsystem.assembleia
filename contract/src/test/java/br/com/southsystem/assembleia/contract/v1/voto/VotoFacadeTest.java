package br.com.southsystem.assembleia.contract.v1.voto;

import br.com.southsystem.assembleia.contract.v1.voto.fixture.VotoFacadeFixture;
import br.com.southsystem.assembleia.contract.v1.voto.mapper.VotoMapper;
import br.com.southsystem.assembleia.impl.voto.VotoService;
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
class VotoFacadeTest {

    @InjectMocks
    private VotoFacade votoFacade;

    @Mock
    private VotoService votoService;

    @Test
    void checksCallToInsertVotoEntityTest() {
        var votoRequest = VotoFacadeFixture.getVotoRequest();

        var votoEntity = VotoMapper.INSTANCE.requestToEntity(votoRequest);

        var expectedVotoResponse = VotoMapper.INSTANCE.entityToResponse(votoEntity);

        given(votoService.votar(votoEntity))
                .willReturn(Mono.just(votoEntity));

        var actualVotoResponseMono = votoFacade.votar(votoRequest);

        StepVerifier.create(actualVotoResponseMono)
                .expectNext(expectedVotoResponse)
                .verifyComplete();

        then(votoService).should(times(1))
                .votar(votoEntity);
    }
}
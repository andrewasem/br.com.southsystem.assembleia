package br.com.southsystem.assembleia.impl.voto;


import br.com.southsystem.assembleia.impl.associado.AssociadoService;
import br.com.southsystem.assembleia.impl.associado.fixture.AssociadoFixture;
import br.com.southsystem.assembleia.impl.pauta.PautaService;
import br.com.southsystem.assembleia.impl.pauta.fixture.PautaFixture;
import br.com.southsystem.assembleia.impl.sessao.SessaoService;
import br.com.southsystem.assembleia.impl.sessao.fixture.SessaoFixture;
import br.com.southsystem.assembleia.impl.voto.fixture.VotoFixture;
import br.com.southsystem.exception.errortype.GenericException;
import br.com.southsystem.exception.utils.ExceptionUtils;
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
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PautaService pautaService;

    @Mock
    private SessaoService sessaoService;

    @Test
    void mustCreatedVotoEntityTest() {

        var votoEntity = VotoFixture.getVotoEntity();
        var pautaEntity = PautaFixture.getPautaEntity();
        var associadoEntity = AssociadoFixture.getAssociadoEntity();
        var sessaoEntity = SessaoFixture.getSessaoEntity();

        given(votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                votoEntity.getAssociadoId()))
                .willReturn(Mono.just(0));

        given(associadoService.findById(votoEntity.getAssociadoId()))
                .willReturn(Mono.just(associadoEntity));

        given(pautaService.findById(votoEntity.getPautaId()))
                .willReturn(Mono.just(pautaEntity));

        given(votoRepository.save(votoEntity))
                .willReturn(Mono.just(votoEntity));

        given(sessaoService.validatePautaWithSessao(votoEntity.getPautaId()))
                .willReturn(Mono.just(sessaoEntity));

        given(votoRepository.validateSessaoOpenedToPauta(votoEntity.getPautaId()))
                .willReturn(Mono.just(sessaoEntity));

        given(pautaService.inserirOrAtualizar(pautaEntity))
                .willReturn(Mono.just(pautaEntity));

        var votoEntityActual = votoService.votar(votoEntity);

        StepVerifier.create(votoEntityActual)
                .expectNext(votoEntity)
                .verifyComplete();

        then(votoRepository).should(times(1))
                .verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                        votoEntity.getAssociadoId());

        then(associadoService).should(times(1))
                .findById(votoEntity.getAssociadoId());

        then(pautaService).should(times(2))
                .findById(votoEntity.getPautaId());

        then(pautaService).should(times(1))
                .inserirOrAtualizar(pautaEntity);

        then(sessaoService).should(times(1))
                .validatePautaWithSessao(votoEntity.getPautaId());

        then(votoRepository).should(times(1))
                .save(votoEntity);

    }

    @Test
    void unsuccessToCreatedVotoEntityBecauseNoExistPautaTest() {

        var votoEntity = VotoFixture.getVotoEntity();
        var pautaEntity = PautaFixture.getPautaEntity();
        var associadoEntity = AssociadoFixture.getAssociadoEntity();

        given(votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                votoEntity.getAssociadoId()))
                .willReturn(Mono.just(0));

        given(associadoService.findById(votoEntity.getAssociadoId()))
                .willReturn(Mono.just(associadoEntity));

        given(pautaService.findById(votoEntity.getPautaId()))
                .willReturn(ExceptionUtils.buildNotFoundException("Error"));

        var votoEntityActual = votoService.votar(votoEntity);

        StepVerifier.create(votoEntityActual)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(votoRepository).should(times(1))
                .verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                        votoEntity.getAssociadoId());

        then(associadoService).should(times(1))
                .findById(votoEntity.getAssociadoId());

        then(pautaService).should(times(1))
                .findById(votoEntity.getPautaId());

        then(pautaService).should(times(0))
                .inserirOrAtualizar(pautaEntity);

    }

    @Test
    void unsuccessToCreatedVotoEntityBecauseNoExistAssociadoTest() {

        var votoEntity = VotoFixture.getVotoEntity();
        var pautaEntity = PautaFixture.getPautaEntity();

        given(votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                votoEntity.getAssociadoId()))
                .willReturn(Mono.just(0));

        given(associadoService.findById(votoEntity.getAssociadoId()))
                .willReturn(ExceptionUtils.buildNotFoundException("Error"));

        var votoEntityActual = votoService.votar(votoEntity);

        StepVerifier.create(votoEntityActual)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(votoRepository).should(times(1))
                .verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                        votoEntity.getAssociadoId());

        then(associadoService).should(times(1))
                .findById(votoEntity.getAssociadoId());

        then(pautaService).should(times(0))
                .findById(votoEntity.getPautaId());

        then(pautaService).should(times(0))
                .inserirOrAtualizar(pautaEntity);
    }

    @Test
    void unsuccessToCreatedVotoEntityBecauseExistOneVotoToAssociadoTest() {

        var votoEntity = VotoFixture.getVotoEntity();
        var pautaEntity = PautaFixture.getPautaEntity();

        given(votoRepository.verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                votoEntity.getAssociadoId()))
                .willReturn(Mono.just(1));

        var votoEntityActual = votoService.votar(votoEntity);

        StepVerifier.create(votoEntityActual)
                .expectError(GenericException.class)
                .verifyThenAssertThat();

        then(votoRepository).should(times(1))
                .verificaSeAssociadoJaVotouNaPauta(votoEntity.getPautaId(),
                        votoEntity.getAssociadoId());

        then(associadoService).should(times(0))
                .findById(votoEntity.getAssociadoId());

        then(pautaService).should(times(0))
                .findById(votoEntity.getPautaId());

        then(pautaService).should(times(0))
                .inserirOrAtualizar(pautaEntity);
    }
}
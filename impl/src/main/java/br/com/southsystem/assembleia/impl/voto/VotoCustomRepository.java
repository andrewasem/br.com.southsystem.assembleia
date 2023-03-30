package br.com.southsystem.assembleia.impl.voto;

import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import reactor.core.publisher.Mono;

public interface VotoCustomRepository {

    Mono<Integer> verificaSeAssociadoJaVotouNaPauta(Long pautaId, Long associadoId);

    Mono<SessaoEntity> validateSessaoOpenedToPauta(Long pautaId);
}

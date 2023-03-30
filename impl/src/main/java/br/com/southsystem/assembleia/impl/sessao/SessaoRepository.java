package br.com.southsystem.assembleia.impl.sessao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SessaoRepository extends ReactiveCrudRepository<SessaoEntity, Long> {

    Mono<SessaoEntity> findByPautaId(Long pautaId);
}
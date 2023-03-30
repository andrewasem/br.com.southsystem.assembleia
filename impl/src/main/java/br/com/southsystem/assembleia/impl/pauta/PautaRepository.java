package br.com.southsystem.assembleia.impl.pauta;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends ReactiveCrudRepository<PautaEntity, Long> {

}
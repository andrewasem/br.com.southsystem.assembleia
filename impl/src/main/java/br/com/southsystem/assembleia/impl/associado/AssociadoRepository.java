package br.com.southsystem.assembleia.impl.associado;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends ReactiveCrudRepository<AssociadoEntity, Long> {

}
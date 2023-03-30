package br.com.southsystem.assembleia.impl.voto;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends ReactiveCrudRepository<VotoEntity, Long>, VotoCustomRepository {

}
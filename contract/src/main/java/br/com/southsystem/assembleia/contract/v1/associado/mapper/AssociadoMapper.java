package br.com.southsystem.assembleia.contract.v1.associado.mapper;

import br.com.southsystem.assembleia.contract.v1.associado.model.request.AssociadoRequest;
import br.com.southsystem.assembleia.contract.v1.associado.model.response.AssociadoResponse;
import br.com.southsystem.assembleia.impl.associado.AssociadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociadoMapper {

    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);

    @Mapping(target = "id", ignore = true)
    AssociadoEntity requestToEntity(AssociadoRequest associadoRequest);

    AssociadoResponse entityToResponse(AssociadoEntity associadoEntity);
}
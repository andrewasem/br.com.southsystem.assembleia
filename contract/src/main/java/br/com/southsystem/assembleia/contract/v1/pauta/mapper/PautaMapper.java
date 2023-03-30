package br.com.southsystem.assembleia.contract.v1.pauta.mapper;

import br.com.southsystem.assembleia.contract.v1.pauta.model.request.PautaRequest;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.assembleia.impl.pauta.PautaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalVotosSim", ignore = true)
    @Mapping(target = "totalVotosNao", ignore = true)
    PautaEntity requestToEntity(PautaRequest pautaRequest);

    PautaResponse entityToResponse(PautaEntity pautaEntity);
}
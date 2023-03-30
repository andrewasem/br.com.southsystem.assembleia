package br.com.southsystem.assembleia.contract.v1.voto.mapper;

import br.com.southsystem.assembleia.contract.v1.voto.model.request.VotoRequest;
import br.com.southsystem.assembleia.contract.v1.voto.model.response.VotoResponse;
import br.com.southsystem.assembleia.impl.voto.VotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "voto", expression = "java(votoRequest.getVoto() == null ? null : votoRequest.getVoto().getValue())")
    VotoEntity requestToEntity(VotoRequest votoRequest);

    @Mapping(target = "voto", expression = "java(br.com.southsystem.assembleia.impl.voto.VotoTypeEnum.fromValue(votoEntity.getVoto()))")
    VotoResponse entityToResponse(VotoEntity votoEntity);
}
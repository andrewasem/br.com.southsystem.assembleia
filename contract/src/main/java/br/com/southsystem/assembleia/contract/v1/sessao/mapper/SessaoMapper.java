package br.com.southsystem.assembleia.contract.v1.sessao.mapper;

import br.com.southsystem.assembleia.contract.v1.sessao.model.request.SessaoRequest;
import br.com.southsystem.assembleia.contract.v1.sessao.model.response.SessaoResponse;
import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessaoMapper {

    SessaoMapper INSTANCE = Mappers.getMapper(SessaoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataHoraInicio", ignore = true)
    @Mapping(target = "dataHoraFim", ignore = true)
    SessaoEntity requestToEntity(SessaoRequest sessaoRequest);

    SessaoResponse entityToResponse(SessaoEntity sessaoEntity);
}
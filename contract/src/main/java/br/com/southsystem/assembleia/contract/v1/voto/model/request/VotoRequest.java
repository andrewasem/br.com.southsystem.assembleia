package br.com.southsystem.assembleia.contract.v1.voto.model.request;

import br.com.southsystem.assembleia.impl.voto.VotoTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class VotoRequest {

    @ApiModelProperty(value = "Identificador do Associado", required = true, dataType = "java.lang.Long")
    @NotNull(message = "{assembleia.associado.id.nao.nulo}")
    private Long associadoId;

    @ApiModelProperty(value = "Identificador da Pauta", required = true, dataType = "java.lang.Long")
    @NotNull(message = "{assembleia.pauta.id.nao.nulo}")
    private Long pautaId;

    @ApiModelProperty(value = "Voto", required = true, dataType = "br.com.southsystem.assembleia.impl.votacao.VotoTypeEnum")
    @NotNull(message = "{assembleia.votacao.voto.nao.nulo}")
    private VotoTypeEnum voto;
}

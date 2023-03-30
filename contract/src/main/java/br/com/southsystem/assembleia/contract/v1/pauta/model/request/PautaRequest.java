package br.com.southsystem.assembleia.contract.v1.pauta.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PautaRequest {

    @ApiModelProperty(value = "Descrição da Pauta", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{assembleia.pauta.descricao.nao.nulo}")
    @Size(max = 100, message = "{assembleia.pauta.descricao.max.size}")
    private String descricao;
}

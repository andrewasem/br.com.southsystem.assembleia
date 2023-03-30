package br.com.southsystem.assembleia.contract.v1.associado.model.request;

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
public class AssociadoRequest {

    @ApiModelProperty(value = "Nome do Associado", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{assembleia.associado.nome.nao.nulo}")
    @Size(max = 100, message = "{assembleia.associado.nome.maximo.caracteres}")
    private String nome;
}

package br.com.southsystem.assembleia.contract.v1.associado.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoResponse {

    @ApiModelProperty(value = "Identificador do Associado", dataType = "java.lang.String")
    private Long id;

    @ApiModelProperty(value = "Nome do Associado", dataType = "java.lang.String")
    private String nome;

}
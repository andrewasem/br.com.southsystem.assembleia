package br.com.southsystem.assembleia.contract.v1.pauta.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponse {

    @ApiModelProperty(value = "Identificador da Pauta", dataType = "java.lang.Long")
    private Long id;

    @ApiModelProperty(value = "Descrição da Pauta", dataType = "java.lang.String")
    private String descricao;

    @ApiModelProperty(value = "Número total de votos sim", dataType = "java.lang.Integer")
    private Integer totalVotosSim;

    @ApiModelProperty(value = "Número total de votos não", dataType = "java.lang.Integer")
    private Integer totalVotosNao;
}

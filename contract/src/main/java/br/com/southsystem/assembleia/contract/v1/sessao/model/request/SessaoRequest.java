package br.com.southsystem.assembleia.contract.v1.sessao.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class SessaoRequest {

    @ApiModelProperty(value = "Tempo em minutos que a sessão deve permanecer aberta, não deve ser superior a 1 hora")
    @Max(value = 60, message = "{assembleia.sessao.tempo.maximo}")
    @Min(value = 1, message = "{assembleia.sessao.tempo.minimo}")
    private Integer tempoDeAbertura;

    @ApiModelProperty(value = "Identificador da Pauta", required = true, dataType = "java.lang.String")
    @NotNull(message = "{assembleia.pauta.id.nao.nulo}")
    private Long pautaId;
}

package br.com.southsystem.assembleia.contract.v1.voto.model.response;

import br.com.southsystem.assembleia.impl.voto.VotoTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponse {

    @Id
    private Long id;

    @ApiModelProperty(value = "Identificador da Pauta", dataType = "java.lang.Long")
    private Long pautaId;

    @ApiModelProperty(value = "Identificador do Associado", dataType = "java.lang.Long")
    private Long associadoId;

    @ApiModelProperty(value = "Tipo de voto realizado", dataType = "br.com.southsystem.assembleia.impl.votacao.VotoTypeEnum")
    private VotoTypeEnum voto;
}

package br.com.southsystem.assembleia.contract.v1.sessao.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoResponse {

    @Id
    private Long id;

    @ApiModelProperty(value = "Tempo máximo de abertura de sessão em minutos", dataType = "java.lang.Integer")
    private Integer tempoDeAbertura;

    @ApiModelProperty(value = "Identificador da Pauta", dataType = "java.lang.Long")
    private Long pautaId;

    @ApiModelProperty(value = "Data de inicio da sessão", example = "28/03/2023 00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @ApiModelProperty(value = "Data de fim da sessão", example = "28/03/2023 23:59:59")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraFim;
}

package br.com.southsystem.assembleia.impl.sessao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sessao")
public class SessaoEntity {

    @Id
    private Long id;

    private Integer tempoDeAbertura;

    private Long pautaId;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;
}
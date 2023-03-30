package br.com.southsystem.assembleia.impl.pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("pauta")
public class PautaEntity {

    @Id
    private Long id;

    private String descricao;

    private Integer totalVotosSim;

    private Integer totalVotosNao;
}
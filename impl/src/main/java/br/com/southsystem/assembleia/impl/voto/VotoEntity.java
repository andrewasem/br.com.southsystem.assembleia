package br.com.southsystem.assembleia.impl.voto;

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
@Table("voto")
public class VotoEntity {

    @Id
    private Long id;

    private Long pautaId;

    private Long associadoId;

    private Boolean voto;
}
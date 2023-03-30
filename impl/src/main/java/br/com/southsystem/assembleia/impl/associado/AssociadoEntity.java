package br.com.southsystem.assembleia.impl.associado;

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
@Table("associado")
public class AssociadoEntity {

    @Id
    private Long id;

    private String nome;
}
package br.com.southsystem.assembleia.impl.sessao.fixture;

import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class SessaoFixture {
    public SessaoEntity getSessaoEntity() {
        return SessaoEntity.builder()
                .id(1L)
                .dataHoraFim(LocalDateTime.MAX)
                .dataHoraInicio(LocalDateTime.MIN)
                .pautaId(1L)
                .tempoDeAbertura(60)
                .build();
    }
}

package br.com.southsystem.assembleia.impl.voto;

import br.com.southsystem.assembleia.impl.sessao.SessaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VotoCustomRepositoryImpl implements VotoCustomRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Mono<Integer> verificaSeAssociadoJaVotouNaPauta(Long pautaId, Long associadoId) {

        String sql = "  select " +
                "           count(sessao.*) " +
                "       from " +
                "           voto, " +
                "           sessao " +
                "       where " +
                "           voto.pauta_id = sessao.pauta_id" +
                "       and voto.associado_id = :associadoId " +
                "       and sessao.pauta_id = :pautaId ";
                //"       and now() between sessao.data_hora_inicio and sessao.data_hora_fim ";

        return r2dbcEntityTemplate.getDatabaseClient()
                .execute(sql)
                .as(Integer.class)
                .bind("associadoId", associadoId)
                .bind("pautaId", pautaId)
                .fetch()
                .first().log();
    }

    @Override
    public Mono<SessaoEntity> validateSessaoOpenedToPauta(Long pautaId) {

        String sql = "  select " +
                "          * " +
                "       from " +
                "           sessao " +
                "       where " +
                "       sessao.pauta_id = :pautaId " +
                "       and now() between sessao.data_hora_inicio and sessao.data_hora_fim ";

        return r2dbcEntityTemplate.getDatabaseClient()
                .execute(sql)
                .as(SessaoEntity.class)
                .bind("pautaId", pautaId)
                .fetch()
                .first().log();
    }
}

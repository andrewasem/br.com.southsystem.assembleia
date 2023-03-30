package br.com.southsystem.assembleia.contract.config.liquibase;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(entityOperationsRef = "masterEntityTemplate")
public class MasterConfig {

    @Value("${master-config.full-url}")
    private String urlPostgres;

    @Bean
    @Qualifier(value = "masterConnectionFactory")
    public ConnectionFactory masterConnectionFactory() {
        return ConnectionFactories.get(urlPostgres);
    }

    @Bean("masterEntityTemplate")
    public R2dbcEntityOperations masterEntityTemplate(@Qualifier("masterConnectionFactory") ConnectionFactory connectionFactory) {

        var strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        var databaseClient = DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();

        var populatorShemas = new ResourceDatabasePopulator(new ClassPathResource("scripts/schema.sql"));
        populatorShemas.execute(connectionFactory).subscribe();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }
}



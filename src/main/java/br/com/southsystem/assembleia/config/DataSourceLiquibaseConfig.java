package br.com.southsystem.assembleia.config;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@Slf4j
public class DataSourceLiquibaseConfig {

    private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";

    @Value("${spring.r2dbc.username}")
    private String user;

    @Value("${spring.r2dbc.password}")
    private String pass;

    @Value("${liquibase.url}")
    private String url;

    private static final String PUBLIC = "public";

    @Bean
    @DependsOn("masterEntityTemplate")
    public void runOnAllSchemas() {

        var dataSource = getDataSource();

        var liquibase = new SpringLiquibase();
        liquibase.setDefaultSchema(PUBLIC);
        liquibase.setLiquibaseSchema(PUBLIC);
        liquibase.setDataSource(dataSource);
        liquibase.setResourceLoader(new DefaultResourceLoader());
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");

        var parameters = new HashMap<String, String>();
        parameters.put("schema", PUBLIC);
        liquibase.setChangeLogParameters(parameters);

        try {
            liquibase.afterPropertiesSet();
        } catch (LiquibaseException e) {
            log.error(e.getMessage());
        }
    }

    private DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(ORG_POSTGRESQL_DRIVER);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(pass);
        return dataSourceBuilder.build();
    }

}

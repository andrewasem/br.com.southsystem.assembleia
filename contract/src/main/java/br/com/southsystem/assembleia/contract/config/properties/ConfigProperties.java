package br.com.southsystem.assembleia.contract.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Getter
public class ConfigProperties {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageResource());
        return bean;
    }

    @Bean
    public MessageSource messageResource() {
        var bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setBasename("classpath:messages");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        return bundleMessageSource;
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(DatabaseClient databaseClient) {
        return new R2dbcEntityTemplate(databaseClient);
    }
}

package br.com.southsystem.assembleia.contract.config.swagger;

import br.com.southsystem.assembleia.contract.config.properties.ConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    private final ConfigProperties configProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.southsystem.assembleia"))
                .paths(regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Assembléia service")
                .description("Service to manage the manager register")
                .version("v1")
                .contact(this.contact())
                .build();
    }

    private Contact contact() {
        return new Contact(
                "",
                "",
                "desenvservices@southsystem.com.br");
    }
}

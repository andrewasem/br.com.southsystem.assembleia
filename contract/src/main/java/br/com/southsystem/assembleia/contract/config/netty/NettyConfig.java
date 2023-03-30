package br.com.southsystem.assembleia.contract.config.netty;

import br.com.southsystem.assembleia.contract.config.properties.ConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;

import java.util.HashMap;

@Configuration
@AllArgsConstructor
public class NettyConfig {

    private final ConfigProperties configProperties;

    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        return new NettyReactiveWebServerFactory() {
            @Override
            public WebServer getWebServer(HttpHandler httpHandler) {
                var handlerMap = new HashMap<String, HttpHandler>();
                handlerMap.put(configProperties.getContextPath(), httpHandler);
                return super.getWebServer(new ContextPathCompositeHandler(handlerMap));
            }
        };
    }
}

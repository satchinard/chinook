package com.adn.chinook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 *
 * @author cagecfi
 */
@Component
public class TomcatWebServerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Value("${server.port}")
    private Integer port;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.setPort(port);
        factory.setContextPath("");
    }
}

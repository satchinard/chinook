//https://github.com/MarcGiffing/wicket-spring-boot
package com.adn.chinook.wicket;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author CAGECFI
 */
public class ChinookWicketApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder()
                .sources(ChinookWicketApplication.class)
                .run(args);
    }

}

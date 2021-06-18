package com.adn.chinook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.adn.chinook.entity"})
@EnableJpaRepositories(basePackages = {"com.adn.chinook.repository"})
@ComponentScan(basePackages = {"com.adn.chinook.config"})
@ComponentScan(basePackages = {"com.adn.chinook.controller"})
@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication
public class ChinookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChinookApplication.class, args);
    }

}

package com.godel.olizarovich.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.godel.olizarovich")
@ComponentScan(basePackages = {"com.godel.olizarovich.config", "com.godel.olizarovich.dao.access",
        "com.godel.olizarovich.services", "com.godel.olizarovich.serializers", "com.godel.olizarovich.controllers"})
@Import(SpringJdbcConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

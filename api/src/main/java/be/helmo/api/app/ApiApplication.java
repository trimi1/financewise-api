package be.helmo.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@EntityScan(basePackages = "be.helmo.api.infrastructure.model")
@ComponentScan(basePackages = {"be.helmo.api.infrastructure", "be.helmo.api.service", "be.helmo.api.controller", "be.helmo.api.security"})
@EnableJpaRepositories(basePackages = "be.helmo.api.infrastructure.repository")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

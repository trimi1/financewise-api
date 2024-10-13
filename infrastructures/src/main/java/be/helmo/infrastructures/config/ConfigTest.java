package be.helmo.infrastructures.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"be.helmo.infrastructures"})
@EnableJpaRepositories(basePackages = "be.helmo.infrastructures.repository")
@EntityScan(basePackages = "be.helmo.infrastructures.model")
public class ConfigTest {
    // This class is used to configure the test environment
}

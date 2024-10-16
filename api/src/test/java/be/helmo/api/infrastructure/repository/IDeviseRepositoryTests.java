package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IDeviseRepositoryTests {
    @Autowired
    private IDeviseRepository repository;

    @Test
    public void should_insert_devise() {
        Devise devise = new Devise("Euro");
        repository.save(devise);
        Optional<Devise> founderDevise = repository.findByDevise("Euro");
        assertTrue(founderDevise.isPresent());
        assertEquals(1, repository.count());
    }
}

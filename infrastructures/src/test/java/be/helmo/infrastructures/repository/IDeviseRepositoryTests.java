package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.config.ConfigTest;
import be.helmo.infrastructures.model.Devise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ConfigTest.class)
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
        repository.delete(founderDevise.get());
        repository.resetAutoIncrement();
    }
}

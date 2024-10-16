package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Quizz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IQuizzRepositoryTests {
    @Autowired
    private IQuizzRepository repository;

    @Test
    public void should_insert_quizz() {
        Quizz quizz = new Quizz("Finance #1");

        repository.save(quizz);
        Optional<Quizz> founded = repository.findByTitre("Finance #1");
        assertTrue(founded.isPresent());

        assertEquals("Finance #1", founded.get().getTitre());
    }
}

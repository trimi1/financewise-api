package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Quizz;
import be.helmo.api.infrastructure.model.RessourceQuizz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IRessourceQuizzRepositoryTests {
    @Autowired
    IRessourceQuizzRepository repository;
    @Autowired
    IQuizzRepository quizzRepository;

    @Test
    @Transactional
    public void should_insert_ressourceQuizz() {
        Quizz quizz = new Quizz("Finance #11");
        RessourceQuizz ressourceQuizz = new RessourceQuizz("A, B ou C ?", "A", quizz);

        quizzRepository.save(quizz);
        repository.save(ressourceQuizz);

        Optional<RessourceQuizz> founded = repository.findByQuestion("A, B ou C ?");
        assertTrue(founded.isPresent());

        assertEquals("A, B ou C ?", founded.get().getQuestion());
        assertEquals("A", founded.get().getAnswer());
        assertEquals("Finance #11", founded.get().getQuizz().getTitle());
    }
}

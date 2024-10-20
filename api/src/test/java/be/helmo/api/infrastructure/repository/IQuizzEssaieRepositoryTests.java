package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Quizz;
import be.helmo.api.infrastructure.model.QuizzEssaie;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IQuizzEssaieRepositoryTests {
    @Autowired
    private IQuizzEssaieRepository repository;
    @Autowired
    private IQuizzRepository quizzRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @Transactional
    public void should_insert_quizzessaie() {
        Role role = new Role("Tester");
        User user = new User("Robert", "Louis", "l.robert@student.helmo.be", "P4$$word", "4f6h7g", role);
        Quizz quizz = new Quizz("Finance #3");
        QuizzEssaie quizzEssaie = new QuizzEssaie(quizz, user, 50);

        roleRepository.save(role);
        utilisateurRepository.save(user);
        quizzRepository.save(quizz);
        repository.save(quizzEssaie);

        Optional<QuizzEssaie> founded = repository.findById(1);
        assertTrue(founded.isPresent());

        assertEquals("Finance #3", founded.get().getQuizz().getTitre());
        assertEquals("Robert", founded.get().getUtilisateur().getNom());
        assertEquals(50, founded.get().getScore());
    }

}

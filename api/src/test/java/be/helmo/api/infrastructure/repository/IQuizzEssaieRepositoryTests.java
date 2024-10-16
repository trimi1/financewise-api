package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Quizz;
import be.helmo.api.infrastructure.model.QuizzEssaie;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
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
    public void should_insert_quizzessaie() {
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Quizz quizz = new Quizz("Finance #1");
        QuizzEssaie quizzEssaie = new QuizzEssaie(quizz, utilisateur, 42);

        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        quizzRepository.save(quizz);
        repository.save(quizzEssaie);

        Optional<QuizzEssaie> founded = repository.findById(1);
        assertTrue(founded.isPresent());

        assertEquals("Finance #1", founded.get().getQuizz().getTitre());
        assertEquals("Manca", founded.get().getUtilisateur().getNom());
        assertEquals(42, founded.get().getScore());

        repository.delete(founded.get());
        repository.resetAutoIncrement();

        quizzRepository.delete(founded.get().getQuizz());
        quizzRepository.resetAutoIncrement();

        utilisateurRepository.delete(utilisateur);
        utilisateurRepository.resetAutoIncrement();

        roleRepository.delete(role);
        roleRepository.resetAutoIncrement();
    }
}

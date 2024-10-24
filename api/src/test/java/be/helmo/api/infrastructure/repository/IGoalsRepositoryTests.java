package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IGoalsRepositoryTests {
    @Autowired
    private IGoalsRepository goalsRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IDeviseRepository deviseRepository;

    @Test
    @Transactional
    public void should_insert_objectif() {
        Devise devise = new Devise("Yen");
        Role role = new Role("Contributor");
        User user = new User("Dupont", "Claire", "c.dupont@student.helmo.be", "P4$$word", "95a8ze", role);
        Objectif objectif = new Objectif("Lamborghini", 300000, LocalDate.of(2026, 6, 1).atStartOfDay(), devise, "Met 1 yen dans un pot tous les jours.", user);

        deviseRepository.save(devise);
        roleRepository.save(role);
        userRepository.save(user);
        goalsRepository.save(objectif);

        Optional<Objectif> founded = goalsRepository.findByName("Lamborghini");
        assertTrue(founded.isPresent());

        assertEquals("Lamborghini", founded.get().getName());
        assertEquals(300000, founded.get().getMontant());

        assertEquals(1, founded.get().getDeadline().getDayOfMonth());
        assertEquals(6, founded.get().getDeadline().getMonthValue());
        assertEquals(2026, founded.get().getDeadline().getYear());

        assertEquals("Yen", founded.get().getDevise().getDevise());
        assertEquals("Met 1 yen dans un pot tous les jours.", founded.get().getRecommendation());
        assertEquals("Claire", founded.get().getUser().getFirstName());
    }

}

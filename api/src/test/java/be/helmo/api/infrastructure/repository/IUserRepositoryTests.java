package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
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
public class IUserRepositoryTests {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @Transactional
    public void should_save_utilisateur() {
        Role role = new Role("Boss");
        User user = new User("Juve", "Matteo", "m.juve@student.helmo.be", "P4$$word", "a1b2c3", role);
        roleRepository.save(role);
        repository.save(user);
        Optional<User> founded = repository.findByEmail("m.juve@student.helmo.be");
        Optional<Role> foundedRole = roleRepository.findByRole("Boss");
        assertTrue(founded.isPresent());
        assertEquals(1, repository.count());

        assertEquals("Juve", founded.get().getNom());
        assertEquals("Matteo", founded.get().getPrenom());
        assertEquals("P4$$word", founded.get().getPassword());
        assertEquals("a1b2c3", founded.get().getCode());
        assertEquals("m.juve@student.helmo.be", founded.get().getEmail());
        assertEquals("Boss", founded.get().getRole().getRole());
    }

}

package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Information;
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
public class IInformationRepositoryTests {
    @Autowired
    private IInformationRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @Transactional
    public void should_insert_information() {
        Role role = new Role("Viewer");
        User user = new User("Bernard", "Julien", "j.bernard@student.helmo.be", "P4$$word", "m4loje", role);
        Information information = new Information("Tuto", "www.youtube.com", user);

        roleRepository.save(role);
        userRepository.save(user);
        repository.save(information);

        Optional<Information> founded = repository.findByTitle("Tuto");
        assertTrue(founded.isPresent());

        assertEquals("Tuto", founded.get().getTitle());
        assertEquals("www.youtube.com", founded.get().getLink());
        assertEquals("Julien", founded.get().getUtilisateur().getFirstName());
    }

}

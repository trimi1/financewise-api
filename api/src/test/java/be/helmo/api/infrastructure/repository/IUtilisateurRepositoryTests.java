package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
public class IUtilisateurRepositoryTests {
    @Autowired
    private IUtilisateurRepository repository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    public void should_save_utilisateur() {
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        roleRepository.save(role);
        repository.save(utilisateur);
        Optional<Utilisateur> founded = repository.findByEmail("m.manca@student.helmo.be");
        Optional<Role> foundedRole = roleRepository.findByRole("Admin");
        assertTrue(founded.isPresent());
        assertEquals(1, repository.count());

        assertEquals("Manca", founded.get().getNom());
        assertEquals("Mirko", founded.get().getPrenom());
        assertEquals("P4$$word", founded.get().getMotDePasse());
        assertEquals("m4loje", founded.get().getCode());
        assertEquals("m.manca@student.helmo.be", founded.get().getEmail());
        assertEquals("Admin", founded.get().getRole().getRole());
        repository.delete(founded.get());
        repository.resetAutoIncrement();
        roleRepository.delete(foundedRole.get());
        roleRepository.resetAutoIncrement();
    }
}

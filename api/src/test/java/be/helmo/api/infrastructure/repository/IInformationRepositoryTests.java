package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Information;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @Transactional
    public void should_insert_information() {
        Role role = new Role("Viewer");
        Utilisateur utilisateur = new Utilisateur("Bernard", "Julien", "j.bernard@student.helmo.be", "P4$$word", "m4loje", role);
        Information information = new Information("Tuto", "www.youtube.com", utilisateur);

        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        repository.save(information);

        Optional<Information> founded = repository.findByTitre("Tuto");
        assertTrue(founded.isPresent());

        assertEquals("Tuto", founded.get().getTitre());
        assertEquals("www.youtube.com", founded.get().getLien());
        assertEquals("Julien", founded.get().getUtilisateur().getPrenom());
    }

}

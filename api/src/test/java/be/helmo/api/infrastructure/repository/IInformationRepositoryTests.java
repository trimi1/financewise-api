package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Information;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
public class IInformationRepositoryTests {
    @Autowired
    private IInformationRepository repository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    public void should_insert_information() {
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Information information = new Information("Tuto", "www.youtube.com", utilisateur);

        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        repository.save(information);

        Optional<Information> founded = repository.findByTitre("Tuto");
        assertTrue(founded.isPresent());

        assertEquals("Tuto", founded.get().getTitre());
        assertEquals("www.youtube.com", founded.get().getLien());
        assertEquals("Mirko", founded.get().getUtilisateur().getPrenom());

        repository.delete(founded.get());
        repository.resetAutoIncrement();

        utilisateurRepository.save(utilisateur);
        utilisateurRepository.resetAutoIncrement();

        roleRepository.save(role);
        roleRepository.resetAutoIncrement();
    }
}

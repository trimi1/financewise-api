package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;

import be.helmo.api.infrastructure.model.Categorie;
import be.helmo.api.infrastructure.model.Devise;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class ICategorieRepositoryTests {
    @Autowired
    private ICategorieRepository repository;
    @Autowired
    private IDeviseRepository deviseRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @Transactional
    public void should_save_categorie() {
        Devise devise = new Devise("Euro");
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Categorie categorie = new Categorie("Voiture", 2000, devise, utilisateur);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        repository.save(categorie);

        Optional<Categorie> founded = repository.findByCategorie("Voiture");
        assertTrue(founded.isPresent());

        assertEquals("Euro", founded.get().getDevise().getDevise());
        assertEquals("Manca", founded.get().getUtilisateur().getNom());
        assertEquals("Voiture", founded.get().getCategorie());
    }
}

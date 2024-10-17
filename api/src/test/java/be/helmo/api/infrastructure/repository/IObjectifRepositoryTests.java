package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.BeforeEach;
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
public class IObjectifRepositoryTests {
    @Autowired
    private IObjectifRepository objectifRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IDeviseRepository deviseRepository;

    @Test
    @Transactional
    public void should_insert_objectif() {
        Devise devise = new Devise("Yen");
        Role role = new Role("Contributor");
        Utilisateur utilisateur = new Utilisateur("Dupont", "Claire", "c.dupont@student.helmo.be", "P4$$word", "95a8ze", role);
        Objectif objectif = new Objectif("Lamborghini", 300000, LocalDate.of(2026, 6, 1).atStartOfDay(), devise, "Met 1 yen dans un pot tous les jours.", utilisateur);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        objectifRepository.save(objectif);

        Optional<Objectif> founded = objectifRepository.findByNom("Lamborghini");
        assertTrue(founded.isPresent());

        assertEquals("Lamborghini", founded.get().getNom());
        assertEquals(300000, founded.get().getMontant());

        assertEquals(1, founded.get().getDateLimite().getDayOfMonth());
        assertEquals(6, founded.get().getDateLimite().getMonthValue());
        assertEquals(2026, founded.get().getDateLimite().getYear());

        assertEquals("Yen", founded.get().getDevise().getDevise());
        assertEquals("Met 1 yen dans un pot tous les jours.", founded.get().getRecommandation());
        assertEquals("Claire", founded.get().getUtilisateur().getPrenom());
    }

}

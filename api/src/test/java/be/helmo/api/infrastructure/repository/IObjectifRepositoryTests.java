package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
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
    public void should_insert_objectif() {
        Devise devise = new Devise("Euro");
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Objectif objectif = new Objectif("Ferrari", 215713, LocalDate.of(2025, 1, 1).atStartOfDay(), devise, "Met 1 euro dans un pot tous les jours.", utilisateur);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        objectifRepository.save(objectif);

        Optional<Objectif> founded = objectifRepository.findByNom("Ferrari");
        assertTrue(founded.isPresent());

        assertEquals("Ferrari", founded.get().getNom());
        assertEquals(215713, founded.get().getMontant());

        assertEquals(1, founded.get().getDateLimite().getDayOfMonth());
        assertEquals(1, founded.get().getDateLimite().getMonthValue());
        assertEquals(2025, founded.get().getDateLimite().getYear());

        assertEquals("Euro", founded.get().getDevise().getDevise());
        assertEquals("Met 1 euro dans un pot tous les jours.", founded.get().getRecommandation());
        assertEquals("Mirko", founded.get().getUtilisateur().getPrenom());

        objectifRepository.delete(founded.get());
        objectifRepository.resetAutoIncrement();

        deviseRepository.delete(founded.get().getDevise());
        deviseRepository.resetAutoIncrement();

        utilisateurRepository.delete(founded.get().getUtilisateur());
        utilisateurRepository.resetAutoIncrement();

        roleRepository.delete(founded.get().getUtilisateur().getRole());
        roleRepository.resetAutoIncrement();
    }
}

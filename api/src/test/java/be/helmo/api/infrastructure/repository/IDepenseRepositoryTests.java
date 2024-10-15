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
public class IDepenseRepositoryTests {
    @Autowired
    IDepenseRepository depenseRepository;
    @Autowired
    IDeviseRepository deviseRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IUtilisateurRepository utilisateurRepository;
    @Autowired
    ICategorieRepository categorieRepository;
    @Autowired
    IObjectifRepository objectifRepository;

    @Test
    public void should_insert_depense() {
        Devise devise = new Devise("Euro");
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Categorie categorie = new Categorie("Voiture", 2000, devise, utilisateur);
        Objectif objectif = new Objectif("Ferrari", 215713, LocalDate.of(2025, 1, 1).atStartOfDay(), devise, "Met 1 euro dans un pot tous les jours.", utilisateur);
        Depense depense = new Depense("Ferrari I", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, utilisateur);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        categorieRepository.save(categorie);
        objectifRepository.save(objectif);
        depenseRepository.save(depense);

        Optional<Depense> foundedDepense = depenseRepository.findByNom("Ferrari I");
        assertTrue(foundedDepense.isPresent());

        assertEquals(1, depenseRepository.count());
        assertEquals("Ferrari I", foundedDepense.get().getNom());
        assertEquals(1000, foundedDepense.get().getMontant());
        assertEquals("Euro", foundedDepense.get().getDevise().getDevise());

        assertEquals(13, foundedDepense.get().getDate().getDayOfMonth());
        assertEquals(11, foundedDepense.get().getDate().getMonthValue());
        assertEquals(2024, foundedDepense.get().getDate().getYear());

        assertEquals("Voiture", foundedDepense.get().getCategorie().getCategorie());
        assertEquals("Ferrari", foundedDepense.get().getObjectif().getNom());
        assertEquals("m.manca@student.helmo.be", foundedDepense.get().getUtilisateur().getEmail());

        depenseRepository.delete(foundedDepense.get());
        depenseRepository.resetAutoIncrement();

        categorieRepository.delete(foundedDepense.get().getCategorie());
        categorieRepository.resetAutoIncrement();

        objectifRepository.delete(foundedDepense.get().getObjectif());
        objectifRepository.resetAutoIncrement();

        deviseRepository.delete(foundedDepense.get().getDevise());
        deviseRepository.resetAutoIncrement();

        utilisateurRepository.delete(foundedDepense.get().getUtilisateur());
        utilisateurRepository.resetAutoIncrement();

        roleRepository.delete(foundedDepense.get().getUtilisateur().getRole());
        roleRepository.resetAutoIncrement();
    }
}

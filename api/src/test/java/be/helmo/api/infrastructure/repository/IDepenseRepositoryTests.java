package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IDepenseRepositoryTests {
    @Autowired
    IDepenseRepository depenseRepository;
    @Autowired
    IDeviseRepository deviseRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IUserRepository utilisateurRepository;
    @Autowired
    ICategorieRepository categorieRepository;
    @Autowired
    IObjectifRepository objectifRepository;

    @BeforeEach
    public void setUp() {
        depenseRepository.deleteAll();
        categorieRepository.deleteAll();
        objectifRepository.deleteAll();
        deviseRepository.deleteAll();
        utilisateurRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @Transactional
    public void should_insert_depense() {
        Devise devise = new Devise("Dollars");
        Role role = new Role("Co-Worker");
        User user = new User("Lemaire", "Nicolas", "n.lemaire@student.helmo.be", "P4$$word", "m4loje", role);
        Categorie categorie = new Categorie("Maison", 2000, devise, user);
        Objectif objectif = new Objectif("Ferrari", 215713, LocalDate.of(2025, 1, 1).atStartOfDay(), devise, "Met 1 euro dans un pot tous les jours.", user);
        Depense depense = new Depense("Ferrari I", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, user);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(user);
        categorieRepository.save(categorie);
        objectifRepository.save(objectif);
        depenseRepository.save(depense);

        Optional<Depense> foundedDepense = depenseRepository.findByName("Ferrari I");
        assertTrue(foundedDepense.isPresent());

        assertEquals(1, depenseRepository.count());
        assertEquals("Ferrari I", foundedDepense.get().getName());
        assertEquals(1000, foundedDepense.get().getMontant());
        assertEquals("Dollars", foundedDepense.get().getDevise().getDevise());

        assertEquals(13, foundedDepense.get().getDate().getDayOfMonth());
        assertEquals(11, foundedDepense.get().getDate().getMonthValue());
        assertEquals(2024, foundedDepense.get().getDate().getYear());

        assertEquals("Maison", foundedDepense.get().getCategorie().getName());
        assertEquals("Ferrari", foundedDepense.get().getObjectif().getName());
        assertEquals("n.lemaire@student.helmo.be", foundedDepense.get().getUtilisateur().getEmail());
    }

    @Test
    @Transactional
    public void should_filter_when_categories() {
        Devise devise = new Devise("Dollars");
        Role role = new Role("Co-Worker");
        User user = new User("Lemaire", "Nicolas", "n.lemaire@student.helmo.be", "P4$$word", "m4loje", role);
        Categorie categorie = new Categorie("Maison", 2000, devise, user);
        Categorie snCategorie = new Categorie("Voiture", 1500, devise, user);
        Objectif objectif = new Objectif("Ferrari", 215713, LocalDate.of(2025, 1, 1).atStartOfDay(), devise, "Met 1 euro dans un pot tous les jours.", user);
        Depense depense = new Depense("Ferrari I", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, user);
        Depense depenseSn = new Depense("Ferrari II", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), snCategorie, objectif, user);
        Depense depenseTh = new Depense("Ferrari III", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, user);

        deviseRepository.save(devise);
        roleRepository.save(role);
        utilisateurRepository.save(user);
        categorieRepository.save(categorie);
        categorieRepository.flush();
        categorieRepository.save(snCategorie);
        categorieRepository.flush();

        objectifRepository.save(objectif);
        objectifRepository.flush();

        depenseRepository.save(depense);
        depenseRepository.flush();
        depenseRepository.save(depenseSn);
        depenseRepository.flush();
        depenseRepository.save(depenseTh);
        depenseRepository.flush();

        Optional<Depense> foundedDepense = depenseRepository.findByName("Ferrari I");
        assertTrue(foundedDepense.isPresent());

        Optional<Depense> foundedDepenseSn = depenseRepository.findByName("Ferrari II");
        assertTrue(foundedDepenseSn.isPresent());

        Optional<Depense> foundedDepenseTh = depenseRepository.findByName("Ferrari III");
        assertTrue(foundedDepenseTh.isPresent());

        List<Depense> depenses = depenseRepository.findByCategorie_Name("Maison");
        assertEquals(2, depenses.size());
        List<Depense> depenseList = depenseRepository.findByUser_IdAndCategorie_Name(user.getId(), "Maison");
        assertEquals(2, depenseList.size());
    }

    @Test
    @Transactional
    public void should_filter_by_id() {
        Devise devise = new Devise("Dollars");
        Role role = new Role("Co-Worker");
        User user = new User("Lemaire", "Nicolas", "n.lemaire@student.helmo.be", "P4$$word", "m4loje", role);
        User userSn = new User("Boucher", "Louis", "l.boucher@student.helmo.be", "P4$$word", "m4lojx", role);
        Categorie categorie = new Categorie("Maison", 2000, devise, user);
        Objectif objectif = new Objectif("Ferrari", 215713, LocalDate.of(2025, 1, 1).atStartOfDay(), devise, "Met 1 euro dans un pot tous les jours.", user);
        Depense depense = new Depense("Ferrari I", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, user);
        Depense depenseSn = new Depense("Ferrari II", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, userSn);
        Depense depenseTh = new Depense("Ferrari III", 1000, devise, LocalDate.of(2024, 11, 13).atStartOfDay(), categorie, objectif, user);

        deviseRepository.save(devise);
        deviseRepository.flush();
        roleRepository.save(role);
        roleRepository.flush();
        utilisateurRepository.save(user);
        utilisateurRepository.flush();
        utilisateurRepository.save(userSn);
        utilisateurRepository.flush();
        categorieRepository.save(categorie);
        objectifRepository.save(objectif);
        depenseRepository.save(depense);
        depenseRepository.flush();
        depenseRepository.save(depenseSn);
        depenseRepository.flush();
        depenseRepository.save(depenseTh);
        depenseRepository.flush();

        List<Depense> depenses = depenseRepository.findByUser_Id(user.getId());
        assertEquals(2, depenses.size());
        List<Depense> depensesUser = depenseRepository.findByUser_IdAndCategorie_Name(user.getId(), "Maison");
        assertEquals(2, depenses.size());
    }
}

package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.HasAmis;
import be.helmo.api.infrastructure.model.Permission;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
public class IHasAmisRepositoryTests {
    @Autowired
    IHasAmisRepository hasAmisRepository;
    @Autowired
    IUtilisateurRepository utilisateurRepository;
    @Autowired
    IPermissionRepository permissionRepository;
    @Autowired
    IRoleRepository roleRepository;

    @Test
    public void should_insert_ihasamis() {
        Role role = new Role("Admin");
        Utilisateur utilisateur = new Utilisateur("Manca", "Mirko", "m.manca@student.helmo.be", "P4$$word", "m4loje", role);
        Utilisateur utilisateur2 = new Utilisateur("Maghe", "Alexandre", "m.maghe@student.helmo.be", "P4$$word", "95a8ze", role);
        Permission permission = new Permission("Voir", "Voit les dépenses.");
        HasAmis hasAmis = new HasAmis(utilisateur, utilisateur2, permission);

        roleRepository.save(role);
        utilisateurRepository.save(utilisateur);
        utilisateurRepository.save(utilisateur2);
        permissionRepository.save(permission);
        hasAmisRepository.save(hasAmis);

        Optional<HasAmis> foundedHasAmis = hasAmisRepository.findById(1);
        assertTrue(foundedHasAmis.isPresent());

        assertEquals("m.manca@student.helmo.be", foundedHasAmis.get().getUtilisateur().getEmail());
        assertEquals("m.maghe@student.helmo.be", foundedHasAmis.get().getAmis().getEmail());
        assertEquals("Voir", foundedHasAmis.get().getPermission().getPermission());

        hasAmisRepository.delete(foundedHasAmis.get());
        hasAmisRepository.resetAutoIncrement();

        permissionRepository.delete(foundedHasAmis.get().getPermission());
        permissionRepository.resetAutoIncrement();

        utilisateurRepository.delete(foundedHasAmis.get().getUtilisateur());
        utilisateurRepository.delete(foundedHasAmis.get().getAmis());
        utilisateurRepository.resetAutoIncrement();

        roleRepository.delete(role);
        roleRepository.resetAutoIncrement();
    }
}

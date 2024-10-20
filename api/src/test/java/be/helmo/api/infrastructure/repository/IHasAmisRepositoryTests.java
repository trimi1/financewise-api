package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.HasAmis;
import be.helmo.api.infrastructure.model.Permission;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IHasAmisRepositoryTests {
    @Autowired
    IHasAmisRepository hasAmisRepository;
    @Autowired
    IUserRepository utilisateurRepository;
    @Autowired
    IPermissionRepository permissionRepository;
    @Autowired
    IRoleRepository roleRepository;


    @Test
    @Transactional
    public void should_insert_ihasamis() {
        Role role = new Role("Editor");
        User user = new User("Martin", "Sophie", "s.martin@student.helmo.be", "P4$$word", "m4loje", role);
        User user2 = new User("Giraud", "Thomas", "t.giraud@student.helmo.be", "P4$$word", "95a8ze", role);
        Permission permission = new Permission("Modifier", "Peut modifier les dépenses.");
        HasAmis hasAmis = new HasAmis(user, user2, permission);

        roleRepository.save(role);
        utilisateurRepository.save(user);
        utilisateurRepository.save(user2);
        permissionRepository.save(permission);
        hasAmisRepository.save(hasAmis);

        Optional<HasAmis> foundedHasAmis = hasAmisRepository.findById(1);
        assertTrue(foundedHasAmis.isPresent());

        assertEquals("s.martin@student.helmo.be", foundedHasAmis.get().getUtilisateur().getEmail());
        assertEquals("t.giraud@student.helmo.be", foundedHasAmis.get().getAmis().getEmail());
        assertEquals("Modifier", foundedHasAmis.get().getPermission().getPermission());
    }

}

package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Permission;
import org.junit.jupiter.api.BeforeEach;
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
public class IPermissionRepositoryTests {
    @Autowired
    IPermissionRepository repository;


    @Test
    @Transactional
    public void should_insert_permission() {
        Permission permission = new Permission("Modifier", "Modifier les dépenses");

        repository.save(permission);
        Optional<Permission> founded = repository.findByPermission("Modifier");
        assertTrue(founded.isPresent());

        assertEquals("Modifier", founded.get().getPermission());
        assertEquals("Modifier les dépenses", founded.get().getDescription());
    }

}

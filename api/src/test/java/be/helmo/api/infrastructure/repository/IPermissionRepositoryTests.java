package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;
import be.helmo.api.infrastructure.model.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
public class IPermissionRepositoryTests {
    @Autowired
    IPermissionRepository repository;

    @Test
    public void should_insert_permission() {
        Permission permission = new Permission("Voir", "Voir les dépenses");

        repository.save(permission);
        Optional<Permission> founded = repository.findByPermission("Voir");
        assertTrue(founded.isPresent());

        assertEquals("Voir", founded.get().getPermission());
        assertEquals("Voir les dépenses", founded.get().getDescription());

        repository.delete(founded.get());
        repository.resetAutoIncrement();
    }
}

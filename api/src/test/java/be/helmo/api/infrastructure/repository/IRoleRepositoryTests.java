package be.helmo.api.infrastructure.repository;


import be.helmo.api.app.ApiApplication;

import be.helmo.api.infrastructure.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class IRoleRepositoryTests {

    @Autowired
    private IRoleRepository roleRepository;

    @Test
    public void should_save_and_delete_role() {
        // Given
        Role role = new Role("Admin");
        roleRepository.save(role);
        Optional<Role> foundedRole = roleRepository.findByRole("Admin");
        assertTrue(foundedRole.isPresent());
        assertEquals("Admin", foundedRole.get().getRole());
        roleRepository.delete(foundedRole.get());
        assertEquals(0, roleRepository.count());
    }
}

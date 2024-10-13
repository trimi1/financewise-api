package be.helmo.infrastructures.repository;


import be.helmo.infrastructures.config.ConfigTest;
import be.helmo.infrastructures.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ConfigTest.class)
public class IRoleRepositoryTests {

    @Autowired
    private IRoleRepository roleRepository;

    @Test
    public void testFindById() {
        // Given
        Role role = new Role("Admin");
        roleRepository.save(role);

        // When
        Optional<Role> foundRole = roleRepository.findById(1);

        // Then
        assertTrue(foundRole.isPresent());
        // When
        roleRepository.delete(foundRole.get());

        // Then
        Optional<Role> optionalRole = roleRepository.findById(1);
        assertTrue(optionalRole.isEmpty());
        roleRepository.resetRoleIdCounter();
    }

    @Test
    public void Should_delete_role() {
        // Given
        Role role = new Role("Admin");
        roleRepository.save(role);
        Optional<Role> foundedRole = roleRepository.findByRole("Admin");
        assertTrue(foundedRole.isPresent());
        assertEquals("Admin", foundedRole.get().getRole());
        roleRepository.delete(foundedRole.get());
        roleRepository.resetRoleIdCounter();
    }
}

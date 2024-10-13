package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    // This method is used to find a role by its name
    Optional<Role> findByRole(String role);

    // This method is used to reset the counter of the role id
    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_role', RESEED, 0)", nativeQuery = true)
    void resetRoleIdCounter();
}

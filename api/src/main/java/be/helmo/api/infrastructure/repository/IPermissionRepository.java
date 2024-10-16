package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

    Optional<Permission> findByPermission(String voir);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_permission', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();

}

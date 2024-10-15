package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
}

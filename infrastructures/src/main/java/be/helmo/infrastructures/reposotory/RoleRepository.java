package be.helmo.infrastructures.reposotory;

import be.helmo.infrastructures.model.Role;
import org.springframework.data.repository.CrudRepository;
public interface RoleRepository extends CrudRepository<Role, Integer> {

}

package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviseRepository extends JpaRepository<Devise, Integer> {
}

package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepenseRepository extends JpaRepository<Depense, Integer> {
}

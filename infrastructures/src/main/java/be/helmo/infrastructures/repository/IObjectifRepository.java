package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObjectifRepository extends JpaRepository<Objectif, Integer> {
}

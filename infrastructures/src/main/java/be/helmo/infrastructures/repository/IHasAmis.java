package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.HasAmis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHasAmis extends JpaRepository<HasAmis, Integer> {
}

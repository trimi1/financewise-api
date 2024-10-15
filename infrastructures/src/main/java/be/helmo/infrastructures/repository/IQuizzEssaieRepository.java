package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.QuizzEssaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizzEssaieRepository extends JpaRepository<QuizzEssaie, Integer> {
}

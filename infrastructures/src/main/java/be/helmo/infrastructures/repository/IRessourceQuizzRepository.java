package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.RessourceQuizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRessourceQuizzRepository extends JpaRepository<RessourceQuizz, Integer> {
}

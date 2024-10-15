package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizzRepository extends JpaRepository<Quizz, Integer> {
}

package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.RessourceQuizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRessourceQuizzRepository extends JpaRepository<RessourceQuizz, Integer> {

    Optional<RessourceQuizz> findByQuestion(String question);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_ressourcequizz', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

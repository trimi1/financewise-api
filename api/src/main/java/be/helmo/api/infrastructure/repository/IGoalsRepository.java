package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGoalsRepository extends JpaRepository<Objectif, Integer> {

    Optional<Objectif> findByName(String nom);

    List<Objectif> findObjectifsByUser_Id(int idUser);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_objectif', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

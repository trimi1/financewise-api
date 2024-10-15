package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Depense;
import be.helmo.infrastructures.model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IDepenseRepository extends JpaRepository<Depense, Integer> {

    Optional<Depense> findByNom(String name);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_depense', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

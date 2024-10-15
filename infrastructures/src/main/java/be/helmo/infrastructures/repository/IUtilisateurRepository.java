package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_utilisateur', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

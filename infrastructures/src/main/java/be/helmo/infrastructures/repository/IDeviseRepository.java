package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IDeviseRepository extends JpaRepository<Devise, Integer> {

    Optional<Devise> findByDevise(String name);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_devise', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

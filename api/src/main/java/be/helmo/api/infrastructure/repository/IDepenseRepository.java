package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepenseRepository extends JpaRepository<Depense, Integer> {

    Optional<Depense> findByName(String name);
    List<Depense> findByCategorie_Name(String name);
    List<Depense> findByUser_Id(int id);
    List<Depense> findByCategorie_NameAndObjectif_IdOrderByCategorieName(String categoryName, int objectifId);//todo: à remodifier si necessaire pour avoir la bonne requete

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_depense', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

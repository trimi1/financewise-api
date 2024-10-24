package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_categorie', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();

    List<Category> findByUser_Id(int id);
}

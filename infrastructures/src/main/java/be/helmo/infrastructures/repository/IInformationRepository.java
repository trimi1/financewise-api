package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInformationRepository extends JpaRepository<Information, Integer> {
}

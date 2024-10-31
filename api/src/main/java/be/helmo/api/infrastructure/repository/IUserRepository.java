package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLastNameAndFirstName(String lastName, String firstName);

    @Query(value = "SELECT code FROM ue25_26_user", nativeQuery = true)
    List<String> findAllCodes();

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_user', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

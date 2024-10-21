package be.helmo.api.infrastructure.repository;

import be.helmo.api.infrastructure.model.HasFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IHasFriendsRepository extends JpaRepository<HasFriends, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DBCC CHECKIDENT ('ue25_26_has_friends', RESEED, 0)", nativeQuery = true)
    void resetAutoIncrement();
}

package RuslanCode.germanAppV2.repositories;

import RuslanCode.germanAppV2.model.UserVerbLink;
import RuslanCode.germanAppV2.model.Verb;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserVerbLinkRepository extends JpaRepository<UserVerbLink, Long> {
    @Query("SELECT u.id FROM UserVerbLink u WHERE u.userID = :userID")
    List<Long> findListOfLinksByUserID(@Param("userID") Long userID);

    @Query("SELECT u.verbID FROM UserVerbLink u WHERE u.id = :listOfLinksIDs")
    List<Long> findListOfVerbsIdByListOfLinksID(@Param("listOfLinksIDs") List<Long> listOfLinksIDs);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserVerbLink u WHERE u.verbID = :idToDelete")
    void deleteByVerbId(@Param("idToDelete") Long idToDelete);

    @Query("SELECT u.verbID FROM UserVerbLink u WHERE u.userID = :userID")
    List<Long> findListOfVerbsIdByUserID(Long userID);

    Optional<UserVerbLink> findByVerbIDAndUserID(Long id, Long authorisedUserID);

    Optional<UserVerbLink> findByVerbID(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserVerbLink u WHERE u.verbID = :idToDelete and u.userID = :authorisedUserID")
    void deleteLinkByVerbIDAndUserID(Long idToDelete, Long authorisedUserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserVerbLink u WHERE u.userID = :userID")
    void deleteAllVerbLinksByUserID(Long userID);
}

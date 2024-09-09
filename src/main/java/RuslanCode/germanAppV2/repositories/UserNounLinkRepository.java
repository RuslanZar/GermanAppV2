package RuslanCode.germanAppV2.repositories;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.model.UserNounLink;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserNounLinkRepository extends JpaRepository<UserNounLink, Long> {
    @Query("SELECT u.id FROM UserNounLink u WHERE u.userID = :userID")
    List<Long> findListOfLinksByUserID(@Param("userID") Long userID);

    @Query("SELECT u.nounID FROM UserNounLink u WHERE u.id = :listOfLinksIDs")
    List<Long> findListOfNounsIdByListOfLinksID(@Param("listOfLinksIDs") List<Long> listOfLinksIDs);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserNounLink u WHERE u.nounID = :idToDelete")
    void deleteByNounId(@Param("idToDelete") Long idToDelete);

    @Query("SELECT u.nounID FROM UserNounLink u WHERE u.userID = :userID")
    List<Long> findListOfNounsIdByUserID(Long userID);

    @Query("SELECT n FROM UserNounLink n WHERE n.userID = :userId ORDER BY n.score ASC")
    List<UserNounLink> getLinkListByNumber(@Param("userId") Long userId, Pageable pageable);

    Optional<UserNounLink> findByNounIDAndUserID(Long id, Long authorisedUserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserNounLink u WHERE u.nounID = :idToDelete and u.userID = :authorisedUserID")
    void deleteLinkByNounIDAndUserID(Long idToDelete, Long authorisedUserID);

    @Query("SELECT u.nounID FROM UserNounLink u WHERE u.nounID = :id")
    Optional<Object> findByNounID(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserNounLink u WHERE u.userID = :userID")
    void deleteAllNounLinksByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("UPDATE UserNounLink e SET e.score = e.score + 0.5 WHERE e.userID = :authorisedUserID AND e.nounID IN (:list)")
    void addScoreToNouns(@Param("authorisedUserID") Long authorisedUserID, @Param("list") List<Long> list);

    @Transactional
    @Modifying
    @Query("UPDATE UserNounLink e SET e.score = e.score - 0.5 WHERE e.userID = :authorisedUserID AND e.nounID IN (:list)")
    void removeScoreToNouns(@Param("authorisedUserID") Long authorisedUserID, @Param("list") List<Long> list);

}

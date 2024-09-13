package RuslanCode.germanAppV2.repositories.phrases;

import RuslanCode.germanAppV2.model.phrase.UserPhraseLink;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPhraseLinkRepository extends JpaRepository<UserPhraseLink, Long> {
    @Query("SELECT u.id FROM UserPhraseLink u WHERE u.userID = :userID")
    List<Long> findListOfLinksByUserID(@Param("userID") Long userID);

    @Query("SELECT u.phraseID FROM UserPhraseLink u WHERE u.id = :listOfLinksIDs")
    List<Long> findListOfPhrasesIdByListOfLinksID(@Param("listOfLinksIDs") List<Long> listOfLinksIDs);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserPhraseLink u WHERE u.phraseID = :idToDelete")
    void deleteByPhraseId(@Param("idToDelete") Long idToDelete);

    @Query("SELECT u.phraseID FROM UserPhraseLink u WHERE u.userID = :userID")
    List<Long> findListOfPhrasesIdByUserID(Long userID);

    Optional<UserPhraseLink> findByPhraseIDAndUserID(Long id, Long authorisedUserID);

    Optional<UserPhraseLink> findByPhraseID(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserPhraseLink u WHERE u.phraseID = :idToDelete and u.userID = :authorisedUserID")
    void deleteLinkByPhraseIDAndUserID(Long idToDelete, Long authorisedUserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserPhraseLink u WHERE u.userID = :userID")
    void deleteAllPhraseLinksByUserID(Long userID);
}

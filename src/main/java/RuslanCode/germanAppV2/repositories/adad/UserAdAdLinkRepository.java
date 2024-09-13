package RuslanCode.germanAppV2.repositories.adad;

import RuslanCode.germanAppV2.model.adad.UserAdAdLink;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAdAdLinkRepository extends JpaRepository<UserAdAdLink, Long> {

    @Query("SELECT u.id FROM UserAdAdLink u WHERE u.userID = :userID")
    List<Long> findListOfLinksByUserID(@Param("userID") Long userID);

    @Query("SELECT u.adAdId FROM UserAdAdLink u WHERE u.id = :listOfLinksIDs")
    List<Long> findListOfAdAdIdByListOfLinksID(@Param("listOfLinksIDs") List<Long> listOfLinksIDs);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserAdAdLink u WHERE u.adAdId = :idToDelete")
    void deleteByAdAdId(@Param("idToDelete") Long idToDelete);

    @Query("SELECT u.adAdId FROM UserAdAdLink u WHERE u.userID = :userID")
    List<Long> findListOfAdAdsIdByUserID(Long userID);

    @Query("SELECT n FROM UserAdAdLink n WHERE n.userID = :userId ORDER BY n.score ASC")
    List<UserAdAdLink> getLinkListByNumber(@Param("userId") Long userId, Pageable pageable);

    Optional<UserAdAdLink> findByAdAdIdAndUserID(Long id, Long authorisedUserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserAdAdLink u WHERE u.adAdId = :idToDelete and u.userID = :authorisedUserID")
    void deleteLinkByAdAdIDAndUserID(Long idToDelete, Long authorisedUserID);

    @Query("SELECT u.adAdId FROM UserAdAdLink u WHERE u.adAdId = :id")
    Optional<Object> findByAdAdID(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserAdAdLink u WHERE u.userID = :userID")
    void deleteAllAdAdLinksByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("UPDATE UserAdAdLink e SET e.score = e.score + 0.5 WHERE e.userID = :authorisedUserID AND e.adAdId IN (:list)")
    void addScoreToAdAds(@Param("authorisedUserID") Long authorisedUserID, @Param("list") List<Long> list);

    @Transactional
    @Modifying
    @Query("UPDATE UserAdAdLink e SET e.score = e.score - 0.5 WHERE e.userID = :authorisedUserID AND e.adAdId IN (:list)")
    void removeScoreFromAdAds(@Param("authorisedUserID") Long authorisedUserID, @Param("list") List<Long> list);

}
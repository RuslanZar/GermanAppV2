package RuslanCode.germanAppV2.repositories.adad;

import RuslanCode.germanAppV2.model.adad.AdAd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AdAdRepository extends JpaRepository<AdAd, Long> {


    @Query("SELECT n FROM AdAd n WHERE n.id IN :listOfAd_ADIDs")
    List<AdAd> findAllByIDList(List<Long> listOfAd_ADIDs);

    Optional<AdAd> findByGerman(String noun);

    @Transactional
    @Modifying
    @Query("DELETE FROM AdAd u WHERE u.id =:listToDelete")
    void deleteAllByListOfIDs(List<Long> listToDelete);

    @Query("SELECT n.id FROM AdAd n WHERE n.id IN :listOfAd_AdIDs")
    List<Long> geAllAdAdIDsByUserID(Long listOfAd_AdIDs);

//    @Query("SELECT n FROM Noun n ORDER BY n.score ASC")
//    List<Noun> getNounListByNumber(Pageable pageable);

    @Query("SELECT COUNT(n) FROM UserAdAdLink n WHERE n.userID = :authorisedUserID")
    int getNumberOfAdAdsByUserID(Long authorisedUserID);

    @Query("SELECT n FROM AdAd n WHERE n.id IN :list")
    List<AdAd> getAdAdsByListOfIDs(List<Long> list);
}
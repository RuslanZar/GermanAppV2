package RuslanCode.germanAppV2.repositories;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.model.UserNounLink;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface NounsRepository extends JpaRepository<Noun, Long> {


    @Query("SELECT n FROM Noun n WHERE n.id IN :listOfNounsIDs")
    List<Noun> findAllByIDList(List<Long> listOfNounsIDs);

    Optional<Noun> findByGerman(String noun);

    @Transactional
    @Modifying
    @Query("DELETE FROM Noun u WHERE u.id =:listToDelete")
    void deleteAllByListOfIDs(List<Long> listToDelete);

    @Query("SELECT n.id FROM Noun n WHERE n.id IN :listOfNounsIDs")
    List<Long> geAllNounsIDsByUserID(Long listOfNounsIDs);

//    @Query("SELECT n FROM Noun n ORDER BY n.score ASC")
//    List<Noun> getNounListByNumber(Pageable pageable);

    @Query("SELECT COUNT(n) FROM UserNounLink n WHERE n.userID = :authorisedUserID")
    int getNumberOfNounsByUserID(Long authorisedUserID);

    @Query("SELECT n FROM Noun n WHERE n.id IN :list")
    List<Noun> getNounsByListOfIDs(List<Long> list);
}

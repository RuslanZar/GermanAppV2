package RuslanCode.germanAppV2.repositories.phrases;

import RuslanCode.germanAppV2.model.phrase.Phrase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {


    @Query("SELECT n FROM Phrase n WHERE n.id IN :listOfPhrasesIDs")
    List<Phrase> findAllByIDList(List<Long> listOfPhrasesIDs);

    Optional<Phrase> findByGerman(String phrase);

    @Query("SELECT n FROM Phrase n ORDER BY n.score ASC")
    List<Phrase> getPhraseListByNumber(Pageable pageable);

    @Query("SELECT COUNT(n) FROM UserPhraseLink n WHERE n.userID = :authorisedUserID")
    int getNumberOfPhrasesByUserID(Long authorisedUserID);
}

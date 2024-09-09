package RuslanCode.germanAppV2.repositories;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.model.Verb;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VerbRepository extends JpaRepository<Verb, Long> {


    @Query("SELECT n FROM Verb n WHERE n.id IN :listOfVerbIDs")
    List<Verb> findAllByIDList(List<Long> listOfVerbIDs);

    Optional<Verb> findByInfinitiv(String verb);

    @Query("SELECT n FROM Verb n ORDER BY n.score ASC")
    List<Verb> getVerbListByNumber(Pageable pageable);

    @Query("SELECT COUNT(n) FROM UserVerbLink n WHERE n.userID = :authorisedUserID")
    int getNumberOfVerbsByUserID(Long authorisedUserID);
}
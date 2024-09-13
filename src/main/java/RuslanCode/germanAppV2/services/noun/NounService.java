package RuslanCode.germanAppV2.services.noun;

import RuslanCode.germanAppV2.model.noun.Noun;
import RuslanCode.germanAppV2.repositories.noun.NounsRepository;
import RuslanCode.germanAppV2.repositories.noun.UserNounLinkRepository;
import RuslanCode.germanAppV2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NounService {
    private final NounsRepository nounsRepository;
    private final UserNounLinkService userNounLinkService;

    public void saveNoun(Noun newNoun) {
        Noun savedNoun = nounsRepository.save(newNoun);
        userNounLinkService.saveNewUserWordLink(savedNoun);
    }

    public List<Noun> geAllNounsByUserID(Long authorisedUserID) {

        List<Long> listOfNounIDs = userNounLinkService.findListOfNounIDsByUserId(authorisedUserID);
        return nounsRepository.findAllByIDList(listOfNounIDs);
    }

    public void deleteNounByNounId(Long id) {
        if (!userNounLinkService.checkExistenceOfLink(id)) {
            nounsRepository.deleteById(id);
        }
    }

    public void deleteAllByListOfIDs(List<Long> listToDelete) {
        listToDelete.forEach(e -> {
            if (!userNounLinkService.checkExistenceOfLink(e)) {
                nounsRepository.deleteById(e);
            }
        });
    }

    public boolean checkExistenceOfNoun(Noun noun) {
        return nounsRepository.findByGerman(noun.getGerman()).isPresent();
    }

    public Noun findNounByGerman(String german) {
        return nounsRepository.findByGerman(german).get();
    }

    public List<Noun> getAll() {
        return nounsRepository.findAll();
    }

    public List<Noun> getAllByListOfIDs(List<Long> list) {
        return nounsRepository.findAllByIDList(list);
    }


    public String normalizeString(String word) {
        String[] words = word.trim().split("\\s+");
        return words[0].substring(0, 1).toLowerCase() + words[0].substring(1)
                +
                " "
                + words[1].substring(0, 1).toUpperCase() + words[1].substring(1);
    }

    public Noun normalizeNoun(Noun newNoun) {
        newNoun.setGerman(normalizeString(newNoun.getGerman()));
        newNoun.setPlural(normalizeString(newNoun.getPlural()));
        return newNoun;
    }

    public int getNumberOfNounsByUserID(Long authorisedUserID) {
        return nounsRepository.getNumberOfNounsByUserID(authorisedUserID);
    }
}

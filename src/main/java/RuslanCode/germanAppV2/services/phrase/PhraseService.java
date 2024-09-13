package RuslanCode.germanAppV2.services.phrase;

import RuslanCode.germanAppV2.model.phrase.Phrase;
import RuslanCode.germanAppV2.repositories.phrases.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final UserPhraseLinkService userPhraseLinkService;


    public void savePhrase(Phrase newPhrase) {
        Phrase savedPhrase = phraseRepository.save(newPhrase);
        userPhraseLinkService.saveNewUserPhraseLink(savedPhrase);
    }

    public List<Phrase> geAllPhrasesByUserID(Long authorisedUserID) {

        List<Long> listOfPhrasesIDs = userPhraseLinkService.findListOfPhrasesByUserId(authorisedUserID);
        return phraseRepository.findAllByIDList(listOfPhrasesIDs);
    }

    public void deletePhraseByPhraseId(Long id) {
        if (!userPhraseLinkService.checkExistenceOfLink(id)){
            phraseRepository.deleteById(id);
        }
    }

    public boolean checkExistenceOfPhrase(Phrase phrase) {
        return phraseRepository.findByGerman(phrase.getGerman()).isPresent();
    }

    public Phrase findPhraseByGerman(String german) {
        return phraseRepository.findByGerman(german).get();
    }

    public List<Phrase> getAll() {
        return phraseRepository.findAll();
    }

    public List<Phrase> getAllByListOfIDs(List<Long> list) {
        return phraseRepository.findAllByIDList(list);
    }

    public List<Phrase> getPhraseListByNumber(Pageable pageable) {
        return phraseRepository.getPhraseListByNumber(pageable);
    }

    public int getNumberOfPhrasesByUserID(Long authorisedUserID) {
        return phraseRepository.getNumberOfPhrasesByUserID(authorisedUserID);
    }
}

package RuslanCode.germanAppV2.services;

import RuslanCode.germanAppV2.model.Phrase;
import RuslanCode.germanAppV2.model.UserPhraseLink;
import RuslanCode.germanAppV2.repositories.UserPhraseLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPhraseLinkService {
    @Autowired
    private final UserPhraseLinkRepository userPhraseLinkRepository;
    private final UserService userService;

    public void saveNewUserPhraseLink(Phrase savedPhrase) {
        UserPhraseLink newLink = new UserPhraseLink();
        newLink.setUserID(userService.getAuthorisedUserID());
        newLink.setPhraseID(savedPhrase.getId());
        if (userPhraseLinkRepository.findByPhraseIDAndUserID(savedPhrase.getId(), userService.getAuthorisedUserID()).isEmpty()) {
            userPhraseLinkRepository.save(newLink);
        }
    }

    public List<Long> findListOfLinksByUserID(Long authorisedUserID) {
        List<Long> linksIDList = userPhraseLinkRepository.findListOfLinksByUserID(authorisedUserID);
        return linksIDList;
    }

    public void deleteLinkByPhraseID(Long id) {
        userPhraseLinkRepository.deleteByPhraseId(id);
    }

    public List<Long> findListOfPhrasesByListOfLinksID(List<Long> listOfLinksIDs) {
        return userPhraseLinkRepository.findListOfPhrasesIdByListOfLinksID(listOfLinksIDs);
    }

    public List<Long> findListOfPhrasesByUserId(Long phraseID) {
        return userPhraseLinkRepository.findListOfPhrasesIdByUserID(phraseID);
    }

    public boolean checkExistenceOfLink(Long id) {
        return userPhraseLinkRepository.findByPhraseID(id).isPresent();
    }

    public void deleteLinkByPhraseIDAndUserID(Long id, Long authorisedUserID) {
        userPhraseLinkRepository.deleteLinkByPhraseIDAndUserID(id,authorisedUserID);
    }

    public void deleteAllPhraseLinksByUserID(Long userId) {
        userPhraseLinkRepository.deleteAllPhraseLinksByUserID(userId);
    }
}

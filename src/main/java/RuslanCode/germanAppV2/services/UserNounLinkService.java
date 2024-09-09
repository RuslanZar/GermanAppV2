package RuslanCode.germanAppV2.services;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.repositories.NounsRepository;
import RuslanCode.germanAppV2.repositories.UserNounLinkRepository;
import RuslanCode.germanAppV2.model.UserNounLink;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserNounLinkService {
    @Autowired
    private final UserNounLinkRepository userNounLinkRepository;
    private final UserService userService;
    private final NounsRepository nounsRepository;

    public void saveNewUserWordLink(Noun savedNoun) {
        UserNounLink newLink = new UserNounLink();
        newLink.setUserID(userService.getAuthorisedUserID());
        newLink.setNounID(savedNoun.getId());
        newLink.setScore(0.0);
        if (userNounLinkRepository.findByNounIDAndUserID(savedNoun.getId(), userService.getAuthorisedUserID()).isEmpty()) {
            userNounLinkRepository.save(newLink);
        }
    }

    public List<Noun> getNounListByNumber(Pageable pageable) {
        List<UserNounLink> userNounLinks = userNounLinkRepository.getLinkListByNumber(userService.getAuthorisedUserID(), pageable);
        List<Long> listOfNounsIDs = userNounLinks.stream()
                .map(UserNounLink::getNounID)
                .collect(Collectors.toList());

        return nounsRepository.getNounsByListOfIDs(listOfNounsIDs);
    }

    public List<Long> findListOfLinksByUserID(Long authorisedUserID) {
        List<Long> linksIDList = userNounLinkRepository.findListOfLinksByUserID(authorisedUserID);
        return linksIDList;
    }

    public void deleteLinkByNounID(Long id) {
        userNounLinkRepository.deleteByNounId(id);
    }

    public List<Long> findListOfNounsByListOfLinksID(List<Long> listOfLinksIDs) {
        return userNounLinkRepository.findListOfNounsIdByListOfLinksID(listOfLinksIDs);
    }

    public List<Long> findListOfNounIDsByUserId(Long userID) {
        return userNounLinkRepository.findListOfNounsIdByUserID(userID);
    }

    public void deleteLinkByNounIDAndUserID(Long id, Long authorisedUserID) {
        userNounLinkRepository.deleteLinkByNounIDAndUserID(id, authorisedUserID);
    }

    public boolean checkExistenceOfLink(Long id) {
        return userNounLinkRepository.findByNounID(id).isPresent();
    }

    public void deleteAllNounLinksByUserID(Long userID) {
        userNounLinkRepository.deleteAllNounLinksByUserID(userID);
    }

    public void addScoreToNouns(List<Long> list) {
        userNounLinkRepository.addScoreToNouns(userService.getAuthorisedUserID(),list);
    }

    public void removeScoreToNouns(List<Long> list) {
        userNounLinkRepository.removeScoreToNouns(userService.getAuthorisedUserID(),list);
    }
}

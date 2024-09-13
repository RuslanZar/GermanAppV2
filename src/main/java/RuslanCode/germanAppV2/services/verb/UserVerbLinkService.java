package RuslanCode.germanAppV2.services.verb;

import RuslanCode.germanAppV2.model.verb.UserVerbLink;
import RuslanCode.germanAppV2.model.verb.Verb;
import RuslanCode.germanAppV2.repositories.phrases.UserPhraseLinkRepository;
import RuslanCode.germanAppV2.repositories.verb.UserVerbLinkRepository;
import RuslanCode.germanAppV2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVerbLinkService {
    @Autowired
    private final UserPhraseLinkRepository userPhraseLinkRepository;
    private final UserService userService;
    private final UserVerbLinkRepository userVerbLinkRepository;

    public void saveNewUserVerbLink(Verb savedVerb) {
        UserVerbLink newLink = new UserVerbLink();
        newLink.setUserID(userService.getAuthorisedUserID());
        newLink.setVerbID(savedVerb.getId());
        if (userVerbLinkRepository.findByVerbIDAndUserID(savedVerb.getId(), userService.getAuthorisedUserID()).isEmpty()) {
            userVerbLinkRepository.save(newLink);
        }
    }

    public List<Long> findListOfLinksByUserID(Long authorisedUserID) {
        List<Long> linksIDList = userVerbLinkRepository.findListOfLinksByUserID(authorisedUserID);
        return linksIDList;
    }

    public void deleteLinkByVerbID(Long id) {
        userVerbLinkRepository.deleteByVerbId(id);
    }

    public List<Long> findListOfVerbsByListOfLinksID(List<Long> listOfLinksIDs) {
        return userVerbLinkRepository.findListOfVerbsIdByListOfLinksID(listOfLinksIDs);
    }

    public boolean checkExistenceOfLink(Long id) {
        return userVerbLinkRepository.findByVerbID(id).isPresent();
    }

    public void deleteLinkByVerbIDAndUserID(Long id, Long authorisedUserID) {
        userVerbLinkRepository.deleteLinkByVerbIDAndUserID(id,authorisedUserID);
    }

    public void deleteAllVerbLinksByUserID(Long userId) {
        userVerbLinkRepository.deleteAllVerbLinksByUserID(userId);
    }

    public List<Long> findListOfVerbsByUserId(Long userId) {
        return userVerbLinkRepository.findListOfVerbsIdByUserID(userId);
    }
}

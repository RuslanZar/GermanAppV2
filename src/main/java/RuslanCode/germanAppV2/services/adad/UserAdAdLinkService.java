package RuslanCode.germanAppV2.services.adad;

import RuslanCode.germanAppV2.model.adad.AdAd;
import RuslanCode.germanAppV2.model.adad.UserAdAdLink;
import RuslanCode.germanAppV2.repositories.adad.AdAdRepository;
import RuslanCode.germanAppV2.repositories.adad.UserAdAdLinkRepository;
import RuslanCode.germanAppV2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAdAdLinkService {
    @Autowired
    private final UserAdAdLinkRepository userAdjektivAdverbLinkRepository;
    private final UserService userService;
    private final AdAdRepository adjektivAdverbRepository;

    public void saveNewUserAdAdLink(AdAd savedAdAd) {
        UserAdAdLink newLink = new UserAdAdLink();
        newLink.setUserID(userService.getAuthorisedUserID());
        newLink.setAdAdId(savedAdAd.getId());
        newLink.setScore(0.0);
        if (userAdjektivAdverbLinkRepository.findByAdAdIdAndUserID(savedAdAd.getId(), userService.getAuthorisedUserID()).isEmpty()) {
            userAdjektivAdverbLinkRepository.save(newLink);
        }
    }

    public List<AdAd> getAdAdListByNumber(Pageable pageable) {
        List<UserAdAdLink> userAdAdLinks = userAdjektivAdverbLinkRepository.getLinkListByNumber(userService.getAuthorisedUserID(), pageable);
        List<Long> listOfAdAdsIDs = userAdAdLinks.stream()
                .map(UserAdAdLink::getAdAdId)
                .collect(Collectors.toList());

        return adjektivAdverbRepository.getAdAdsByListOfIDs(listOfAdAdsIDs);
    }

    public List<Long> findListOfLinksByUserID(Long authorisedUserID) {
        return userAdjektivAdverbLinkRepository.findListOfLinksByUserID(authorisedUserID);
    }

    public void deleteLinkByAdAdID(Long id) {
        userAdjektivAdverbLinkRepository.deleteByAdAdId(id);
    }

    public List<Long> findListOfAdAdsByListOfLinksID(List<Long> listOfLinksIDs) {
        return userAdjektivAdverbLinkRepository.findListOfAdAdIdByListOfLinksID(listOfLinksIDs);
    }

    public List<Long> findListOfAdAdsIDsByUserId(Long userID) {
        return userAdjektivAdverbLinkRepository.findListOfAdAdsIdByUserID(userID);
    }

    public void deleteLinkByAdAdIDAndUserID(Long id, Long authorisedUserID) {
        userAdjektivAdverbLinkRepository.deleteLinkByAdAdIDAndUserID(id, authorisedUserID);
    }

    public boolean checkExistenceOfLink(Long id) {
        return userAdjektivAdverbLinkRepository.findByAdAdID(id).isPresent();
    }

    public void deleteAllAdAdLinksByUserID(Long userID) {
        userAdjektivAdverbLinkRepository.deleteAllAdAdLinksByUserID(userID);
    }

    public void addScoreToAdAds(List<Long> list) {
        userAdjektivAdverbLinkRepository.addScoreToAdAds(userService.getAuthorisedUserID(),list);
    }

    public void removeScoreFromAdAds(List<Long> list) {
        userAdjektivAdverbLinkRepository.removeScoreFromAdAds(userService.getAuthorisedUserID(),list);
    }
}

package RuslanCode.germanAppV2.services.adad;

import RuslanCode.germanAppV2.model.adad.AdAd;
import RuslanCode.germanAppV2.repositories.adad.AdAdRepository;
import RuslanCode.germanAppV2.repositories.noun.UserNounLinkRepository;
import RuslanCode.germanAppV2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdAdService {
    private final AdAdRepository adAdRepository;
    private final UserAdAdLinkService userAdAdLinkService;

    public void saveAdAd(AdAd newAdAd) {
        AdAd savedAdAd = adAdRepository.save(newAdAd);
        userAdAdLinkService.saveNewUserAdAdLink(savedAdAd);
    }

    public List<AdAd> geAllAdAdsByUserID(Long authorisedUserID) {

        List<Long> listOfAdAdIDs = userAdAdLinkService.findListOfAdAdsIDsByUserId(authorisedUserID);
        return adAdRepository.findAllByIDList(listOfAdAdIDs);
    }

    public void deleteAdAdByAdAdId(Long id) {
        if (!userAdAdLinkService.checkExistenceOfLink(id)) {
            adAdRepository.deleteById(id);
        }
    }

    public void deleteAllByListOfIDs(List<Long> listToDelete) {
        listToDelete.forEach(e -> {
            if (!userAdAdLinkService.checkExistenceOfLink(e)) {
                adAdRepository.deleteById(e);
            }
        });
    }

    public boolean checkExistenceOfAdAd(AdAd adad) {
        return adAdRepository.findByGerman(adad.getGerman()).isPresent();
    }

    public AdAd findAdAdByGerman(String german) {
        return adAdRepository.findByGerman(german).get();
    }

    public List<AdAd> getAll() {
        return adAdRepository.findAll();
    }

    public List<AdAd> getAllByListOfIDs(List<Long> list) {
        return adAdRepository.findAllByIDList(list);
    }


    public String normalizeString(String word) {
        word = word.trim();
        return word.substring(0, 1).toLowerCase() + word.substring(1);
    }

    public int getNumberOfAdAdsByUserID(Long authorisedUserID) {
        return adAdRepository.getNumberOfAdAdsByUserID(authorisedUserID);
    }
}

package RuslanCode.germanAppV2.services.verb;

import RuslanCode.germanAppV2.model.verb.Verb;
import RuslanCode.germanAppV2.repositories.verb.VerbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerbService {
    private final VerbRepository verbRepository;
    private final UserVerbLinkService userVerbLinkService;

    public void saveVerb(Verb newVerb) {
        Verb savedVerb = verbRepository.save(newVerb);
        userVerbLinkService.saveNewUserVerbLink(savedVerb);
    }

    public List<Verb> geAllVerbsByUserID(Long authorisedUserID) {

        List<Long> listOfVerbIDs = userVerbLinkService.findListOfVerbsByUserId(authorisedUserID);
        return verbRepository.findAllByIDList(listOfVerbIDs);
    }

    public void deleteVerbByVerbId(Long id) {
        if (!userVerbLinkService.checkExistenceOfLink(id)){
            verbRepository.deleteById(id);
        }
    }

    public boolean checkExistenceOfVerb(Verb verb) {
        return verbRepository.findByInfinitiv(verb.getInfinitiv()).isPresent();
    }

    public Verb findVerbByGerman(String infinitiv) {
        return verbRepository.findByInfinitiv(infinitiv).get();
    }

    public List<Verb> getALl() {
        return verbRepository.findAll();
    }

    public List<Verb> getAllByListOfIDs(List<Long> list) {
        return verbRepository.findAllByIDList(list);
    }

    public List<Verb> getVerbListByNumber(Pageable pageable) {
        return verbRepository.getVerbListByNumber(pageable);
    }

    public int getNumberOfVerbsByUserID(Long authorisedUserID) {
        return verbRepository.getNumberOfVerbsByUserID(authorisedUserID);
    }
}

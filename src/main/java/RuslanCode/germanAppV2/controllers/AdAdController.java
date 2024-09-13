package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.adad.AdAd;
import RuslanCode.germanAppV2.services.adad.AdAdService;
import RuslanCode.germanAppV2.services.adad.UserAdAdLinkService;
import RuslanCode.germanAppV2.services.UserService;
import RuslanCode.germanAppV2.utilities.menu.Menu;
import RuslanCode.germanAppV2.utilities.ProjectDataReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdAdController {
    private final AdAdService adAdAdService;
    private final UserAdAdLinkService userAdAdLinkService;
    private final UserService userService;

    @GetMapping("/adad")
    public String showAdAdPage(Model model) throws IOException {
        log.info("Accessed Adad page controller");
        List<AdAd> listOfAdAd = adAdAdService.geAllAdAdsByUserID(userService.getAuthorisedUserID());
        model.addAttribute("menuList", Menu.getMenuList())
                .addAttribute("version", ProjectDataReader.getVersion())
                .addAttribute("userName", userService.getAuthorisedUserName())
                .addAttribute("listOfAdAd", listOfAdAd);
        return "adadsPage";
    }

    @PostMapping("/saveNewAdAd")
    public String saveAdAd(@RequestBody AdAd newAdAd) {
        log.info("save new Adad controller accessed");
        newAdAd.setGerman(adAdAdService.normalizeString(newAdAd.getGerman()));
        newAdAd.setRussian(adAdAdService.normalizeString(newAdAd.getRussian()));
        //Check existence of adad in BD according to german translation
        if (!adAdAdService.checkExistenceOfAdAd(newAdAd)) {
            adAdAdService.saveAdAd(newAdAd);
        } else {
            newAdAd = adAdAdService.findAdAdByGerman(newAdAd.getGerman());
            userAdAdLinkService.saveNewUserAdAdLink(newAdAd);
        }
        return "adadsPage";
    }

    @DeleteMapping("/deleteAdAd")
    public ResponseEntity<String> deleteAdAd(@RequestBody Map<String, Long> body) {
        log.info("delete Adad controller accessed by {}", userService.getAuthorisedUserName());
        Long id = body.get("id");
        userAdAdLinkService.deleteLinkByAdAdIDAndUserID(id, userService.getAuthorisedUserID());
        adAdAdService.deleteAdAdByAdAdId(id);
        return ResponseEntity.ok("AdAd deleted successfully by " + userService.getAuthorisedUserName());
    }

    @PatchMapping("/addScoreToAdAds")
    public ResponseEntity<String> addScoreToAdAds(@RequestBody List<Long> list) {
        log.info("addScoreToAdAds controller accessed by {}", userService.getAuthorisedUserName());
        if (!list.isEmpty()) userAdAdLinkService.addScoreToAdAds(list);
        return ResponseEntity.ok("Score was modified successfully");
    }

    @PatchMapping("/removeScoreFromAdAds")
    public ResponseEntity<String> removeScoreFromAdAds(@RequestBody List<Long> list) {
        log.info("removeScoreFromAdAds controller accessed by {}", userService.getAuthorisedUserName());
        if (!list.isEmpty()) userAdAdLinkService.removeScoreFromAdAds(list);
        return ResponseEntity.ok("Score was modified successfully");
    }
}

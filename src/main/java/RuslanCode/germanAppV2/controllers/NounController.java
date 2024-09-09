package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.services.NounService;
import RuslanCode.germanAppV2.services.UserNounLinkService;
import RuslanCode.germanAppV2.services.UserService;
import RuslanCode.germanAppV2.utilities.Menu;
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
public class NounController {

    private final NounService nounService;
    private final UserNounLinkService userNounLinkService;
    private final UserService userService;

    @GetMapping("/nouns")
    public String showNounsPage(Model model) throws IOException {
        model.addAttribute("menuList", Menu.getMenuList());
        model.addAttribute("version", ProjectDataReader.getVersion());
        model.addAttribute("userName", userService.getAuthorisedUserName());
        List<Noun> listOfNouns = nounService.geAllNounsByUserID(userService.getAuthorisedUserID());
        model.addAttribute("listOfNoun", listOfNouns);
        log.info("Accessed user nouns page controller");
        return "nounsPage";
    }

    @PostMapping("/saveNewNoun")
    public String saveNoun(@RequestBody Noun newNoun) {
        log.info("save new Noun controller accessed");
        newNoun = nounService.normalizeNoun(newNoun);
        //Check existence of noun in BD according to german translation
        if (!nounService.checkExistenceOfNoun(newNoun)) {
            nounService.saveNoun(newNoun);
        } else {
            newNoun = nounService.findNounByGerman(newNoun.getGerman());
            userNounLinkService.saveNewUserWordLink(newNoun);
        }
        return "nounsPage";
    }

    @DeleteMapping("/deleteNoun")
    public ResponseEntity<String> deleteWord(@RequestBody Map<String, Long> body) {
        log.info("delete Noun controller accessed by {}", userService.getAuthorisedUserName());
        Long id = body.get("id");
        userNounLinkService.deleteLinkByNounIDAndUserID(id, userService.getAuthorisedUserID());
        nounService.deleteNounByNounId(id);
        return ResponseEntity.ok("Noun deleted successfully by " + userService.getAuthorisedUserName());
    }

    @PatchMapping("/addScoreToNouns")
    public ResponseEntity<String> addScoreToNouns(@RequestBody List <Long> list){
        log.info("addScoreToNouns controller accessed by {}", userService.getAuthorisedUserName());
        if (!list.isEmpty()) userNounLinkService.addScoreToNouns(list);
        return ResponseEntity.ok("Score was modified successfully");
    }

    @PatchMapping("/removeScoreToNouns")
    public ResponseEntity<String> removeScoreToNouns(@RequestBody List <Long> list){
        log.info("removeScoreToNouns controller accessed by {}", userService.getAuthorisedUserName());
        if (!list.isEmpty()) userNounLinkService.removeScoreToNouns(list);
        return ResponseEntity.ok("Score was modified successfully");
    }
}

package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.model.Phrase;
import RuslanCode.germanAppV2.model.UserNounLink;
import RuslanCode.germanAppV2.services.*;
import RuslanCode.germanAppV2.utilities.Menu;
import RuslanCode.germanAppV2.utilities.ProjectDataReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PhraseController {

    private final UserService userService;
    private final PhraseService phraseService;
    private final UserPhraseLinkService userPhraseLinkService;

    @GetMapping("/phrases")
    public String showPhrasePage(Model model) throws IOException {
        model.addAttribute("menuList", Menu.getMenuList());
        model.addAttribute("version", ProjectDataReader.getVersion());
        model.addAttribute("userName", userService.getAuthorisedUserName());
        List<Phrase> listOfPhrases = phraseService.geAllPhrasesByUserID(userService.getAuthorisedUserID());
        model.addAttribute("listOfPhrases", listOfPhrases);
        log.info("Accessed user nouns page controller");
        return "phrasesPage";
    }

    @PostMapping("/saveNewPhrase")
    public String savePhrase(@RequestBody Phrase newPhrase) {
        log.info("save new Phrase controller accessed");
        if (!phraseService.checkExistenceOfPhrase(newPhrase)) {
            phraseService.savePhrase(newPhrase);
        } else {
            newPhrase = phraseService.findPhraseByGerman(newPhrase.getGerman());
            userPhraseLinkService.saveNewUserPhraseLink(newPhrase);
        }
        return "phrasesPage";
    }

    @DeleteMapping("/deletePhrase")
    public ResponseEntity<String> deletePhrase(@RequestBody Map<String, Long> body) {
        log.info("delete Phrase controller accessed");
        Long id = body.get("id");
        userPhraseLinkService.deleteLinkByPhraseIDAndUserID(id, userService.getAuthorisedUserID());
        phraseService.deletePhraseByPhraseId(id);
        return ResponseEntity.ok("Phrase deleted successfully");
    }
}

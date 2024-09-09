package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.Exercise;
import RuslanCode.germanAppV2.model.Noun;
import RuslanCode.germanAppV2.model.Phrase;
import RuslanCode.germanAppV2.model.Verb;
import RuslanCode.germanAppV2.services.*;
import RuslanCode.germanAppV2.utilities.Menu;
import RuslanCode.germanAppV2.utilities.ProjectDataReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final UserService userService;
    private final NounService nounService;
    private final VerbService verbService;
    private final PhraseService phraseService;
    private final UserNounLinkService userNounLinkService;

    @GetMapping("/exerciseGeneration")
    public String exerciseGeneration(Model model) throws IOException {
        model.addAttribute("userName", userService.getAuthorisedUserName());
        model.addAttribute("version", ProjectDataReader.getVersion());
        log.info("exerciseGeneration controller is triggered by {}", userService.getAuthorisedUserName());
        return "exerciseGeneration";
    }

    @GetMapping("/generatedExercise")
    public String generateExercise(Model model, String exerciseType, int number) throws IOException {
        log.info("generatedExercise controller is triggered by {}", userService.getAuthorisedUserName());
        model.addAttribute("menuList", Menu.getMenuList());
        model.addAttribute("version", ProjectDataReader.getVersion());
        model.addAttribute("userName", userService.getAuthorisedUserName());
        Pageable pageable = PageRequest.of(0, number);
        if (exerciseType.equals("Nomen")) {
            List<Noun> nounList = userNounLinkService.getNounListByNumber(pageable);
            model.addAttribute("nounList", nounList);
            model.addAttribute("exerciseType", exerciseType);
            model.addAttribute("number", number);
            return "generatedNounsExercise";
        } else if (exerciseType.equals("Verben")) {
            List<Verb> verbList = verbService.getVerbListByNumber(pageable);
            model.addAttribute("verbList", verbList);
            model.addAttribute("exerciseType", exerciseType);
            model.addAttribute("number", number);
            return "generatedVerbsExercise";
        } else {
            List<Phrase> phraseList = phraseService.getPhraseListByNumber(pageable);
            model.addAttribute("phraseList", phraseList);
            model.addAttribute("exerciseType", exerciseType);
            model.addAttribute("number", number);
            return "generatedPhrasesExercise";
        }
    }
}

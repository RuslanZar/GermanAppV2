package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.services.NounService;
import RuslanCode.germanAppV2.services.PhraseService;
import RuslanCode.germanAppV2.services.UserService;
import RuslanCode.germanAppV2.services.VerbService;
import RuslanCode.germanAppV2.utilities.Menu;
import RuslanCode.germanAppV2.utilities.ProjectDataReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserHomePageController {

    private final UserService userService;
    private final NounService nounService;
    private final VerbService verbService;
    private final PhraseService phraseService;

    @GetMapping("/profile")
    public String userProfile(Model model) throws IOException {
        model.addAttribute("menuList", Menu.getMenuList())
                .addAttribute("version", ProjectDataReader.getVersion())
                .addAttribute("userName", userService.getAuthorisedUserName())
                .addAttribute("userID", userService.getAuthorisedUserID())
                .addAttribute("nounNumber", nounService
                        .getNumberOfNounsByUserID(userService.getAuthorisedUserID()))
                .addAttribute("verbNumber", verbService
                        .getNumberOfVerbsByUserID(userService.getAuthorisedUserID()))
                .addAttribute("phraseNumber", phraseService
                        .getNumberOfPhrasesByUserID(userService.getAuthorisedUserID()));
        return "userProfilePage";
    }
}

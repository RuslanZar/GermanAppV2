package RuslanCode.germanAppV2.controllers;


import RuslanCode.germanAppV2.model.Verb;
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
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VerbController {

    private final UserService userService;
    private final VerbService verbService;
    private final UserVerbLinkService userVerbLinkService;

    @GetMapping("/verbs")
    public String showVerbsPage(Model model) throws IOException {
        model.addAttribute("menuList", Menu.getMenuList());
        model.addAttribute("version", ProjectDataReader.getVersion());
        model.addAttribute("userName", userService.getAuthorisedUserName());
        List<Verb> listOfVerbs = verbService.geAllVerbsByUserID(userService.getAuthorisedUserID());
        model.addAttribute("listOfVerbs", listOfVerbs);
        log.info("Accessed user verbs page controller");
        return "verbenPage";
    }

    @PostMapping("/saveNewVerb")
    public String saveVerb(@RequestBody Verb newVerb) {
        log.info("save new Verb controller accessed");
        if (!verbService.checkExistenceOfVerb(newVerb)) {
            verbService.saveVerb(newVerb);
        } else {
            newVerb = verbService.findVerbByGerman(newVerb.getInfinitiv());
            userVerbLinkService.saveNewUserVerbLink(newVerb);
        }
        return "verbenPage";
    }

    @DeleteMapping("/deleteVerb")
    public ResponseEntity<String> deleteVerb(@RequestBody Map<String, Long> body) {
        log.info("delete Verb controller accessed");
        Long id = body.get("id");
        userVerbLinkService.deleteLinkByVerbIDAndUserID(id, userService.getAuthorisedUserID());
        verbService.deleteVerbByVerbId(id);
        return ResponseEntity.ok("Verb deleted successfully");
    }
}

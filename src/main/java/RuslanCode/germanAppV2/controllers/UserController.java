package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.MyUser;
import RuslanCode.germanAppV2.services.*;
import RuslanCode.germanAppV2.services.noun.NounService;
import RuslanCode.germanAppV2.services.noun.UserNounLinkService;
import RuslanCode.germanAppV2.services.phrase.UserPhraseLinkService;
import RuslanCode.germanAppV2.services.verb.UserVerbLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserPhraseLinkService userPhraseLinkService;
    private final UserNounLinkService userNounLinkService;
    private final UserVerbLinkService userVerbLinkService;

    private final NounService nounService;

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public String createUser(@RequestBody MyUser newUser, RedirectAttributes redirectAttributes) {
        log.info("Accessed register user controller");
        if (!userService.checkUserExistence(newUser)) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userService.save(newUser);
            return "{\n" + "\t\"status\": \"success\"\n" + "}";
        } else {
            return "{\n" + "\t\"status\": \"failed\",\n" + "\t\"message\": \"user " + newUser.getUsername() + " already exists\"\n" + "}";
        }
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody MyUser user) {
        log.info("Accessed delete user page controller");
        //Check weather you a trying to delete yourself
        Long userID = user.getId();
        if (!Objects.equals(userService.getAuthorisedUserID(), userID)) {
            List<Long> nounIdListToDelete = userNounLinkService.findListOfNounIDsByUserId(userID);
            userNounLinkService.deleteAllNounLinksByUserID(userID);
            nounService.deleteAllByListOfIDs(nounIdListToDelete);
            userPhraseLinkService.deleteAllPhraseLinksByUserID(userID);
            userVerbLinkService.deleteAllVerbLinksByUserID(userID);

            userService.deleteMyUserByID(userID);
        }
        return "adminHomePage";
    }

    @PostMapping("/updatePassword")
    private void updatePassword(@RequestBody String newPass){
        userService.updatePassword(passwordEncoder.encode(newPass));
    }
}

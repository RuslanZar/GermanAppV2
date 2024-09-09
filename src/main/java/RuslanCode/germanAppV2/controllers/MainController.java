package RuslanCode.germanAppV2.controllers;

import RuslanCode.germanAppV2.model.MyUser;
import RuslanCode.germanAppV2.services.UserService;
import RuslanCode.germanAppV2.utilities.ProjectDataReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/profile")
    public String adminPageController(Model model) throws IOException {
        log.info("Accessed admin home page controller by {}", userService.getAuthorisedUserName());
        model.addAttribute("version", ProjectDataReader.getVersion());
        model.addAttribute("userName", userService.getAuthorisedUserName());
        model.addAttribute("allUserList", userService.getAllUsers());
        return "adminHomePage";
    }

    @GetMapping("/login")
    public String login() {
//        userService.saveAdmin(passwordEncoder.encode("111"));
        log.info("Accessed login endpoint by {}", userService.getAuthorisedUserName());
        return "custom_login";
    }

    @GetMapping("/redirect")
    public String redirect() {
        log.info("Accessed redirect endpoint by {}", userService.getAuthorisedUserName());
        if (userService.getAuthorisedUserRoles().stream().anyMatch(role -> role.toString().equals("ROLE_USER"))) {
            return "redirect:/nouns";
        }
        if (userService.getAuthorisedUserRoles().stream().anyMatch(role -> role.toString().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/profile";
        }
        return "redirect:/nouns";
    }
}

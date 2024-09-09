package RuslanCode.germanAppV2.services;

import RuslanCode.germanAppV2.model.MyUser;
import RuslanCode.germanAppV2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }

    }

    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

    public String getAuthorisedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Long getAuthorisedUserID() {
        return userRepository.findByUsername(getAuthorisedUserName()).get().getId();
    }

    public Collection<? extends GrantedAuthority> getAuthorisedUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities();
    }

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public MyUser save(MyUser newUser) {
        return userRepository.save(newUser);
    }

    public void deleteMyUserByID(Long id) {
        userRepository.deleteById(id);
    }

    public boolean checkUserExistence(MyUser newUser) {
        return userRepository.findByUsername(newUser.getUsername()).isPresent();
    }

    public void updatePassword(String newPass) {
        userRepository.updatePassword(newPass, getAuthorisedUserID());
    }

    public void saveAdmin(String password) {
        userRepository.saveAdmin(password);
    }
}

package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;

import java.security.Principal;


@Controller
@RequestMapping("/api/user")
public class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public ResponseEntity<User> get(Principal principal) {
        return ResponseEntity.ok(userRepository.findByName(principal.getName() ).get() );

    }

}

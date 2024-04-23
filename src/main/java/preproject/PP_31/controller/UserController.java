package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.Role;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;
import preproject.PP_31.service.UserService;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public  UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public String get(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByName(principal.getName() ).get() );

        return "info";
    }

    @GetMapping()
    public String start() {
        return "start";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll() );
        return "all";
    }

    @GetMapping("/new")
    public String addGet(Model model) {
        model.addAttribute("user", new User() );

        return "new";
    }

    @PostMapping("/new")
    public String addPost(@ModelAttribute("user") User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER") ) );
        userService.add(user);

        return "redirect:/all";
    }

    @GetMapping("/edit")
    public String updateUserGet(Model model, @RequestParam(value="id", required = false) Long id){
        model.addAttribute("user", userService.get(id) );

        return "edit";
    }

    @PostMapping("/edit")
    public String updateUserPost(@ModelAttribute User user, @RequestParam(value="id", required = false) Long id) {
        userService.update(user, id);
        return "redirect:/all";
    }

    @GetMapping("/delete")
    public String deleteGet(Model model, Long id) {
        model.addAttribute("id", id );

        return "delete";
    }

    @PostMapping("/delete")
    public String deletePost(Long id) {
        userService.delete(id);

        return "redirect:/all";
    }

}

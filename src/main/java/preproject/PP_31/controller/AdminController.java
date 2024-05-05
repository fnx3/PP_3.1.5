package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;
import preproject.PP_31.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/new")
    public String addPost(@ModelAttribute("user") User user) {
        userService.add(user);

        return "redirect:/admin";
    }
    @PostMapping("/edit")
    public String updateUserPost(@ModelAttribute User user, @RequestParam(value="id", required = false) Long id) {
        userService.update(user, id);

        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deletePost(Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }

    @GetMapping()
    public String admin(Model model, Principal principal) {

        model.addAttribute("user", userRepository.findByName(principal.getName() ).get() );
        model.addAttribute("table", userRepository.findAll() ); // Все пользователи
        model.addAttribute("listRoles", userService.listRoles() ); // Все роли

        return "adminBootstrap";
    }

}

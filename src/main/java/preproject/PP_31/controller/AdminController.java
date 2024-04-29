package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.Role;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;
import preproject.PP_31.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

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

//    @GetMapping()
//    public String start() {
//        return "start";
//    }

    @GetMapping()
    public String admin(Model model, Principal principal, Authentication authentication) {
        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).map(auth -> auth.replaceAll("ROLE_", "") )
                .collect(Collectors.toSet() );

        model.addAttribute("userRoles", String.join(" ", roles) );
        model.addAttribute("userName", principal.getName() );

        return "newBootstrap";
    }

    @GetMapping("/info")
    public String get(Model model, Principal principal) {
        model.addAttribute("user", userRepository
                .findByName(principal.getName() ).get() );

        return "info";
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

        return "redirect:/admin/all";
    }

    @GetMapping("/edit")
    public String updateUserGet(Model model, @RequestParam(value="id", required = false) Long id){
        model.addAttribute("user", userService.get(id) );

        return "edit";
    }

    @PostMapping("/edit")
    public String updateUserPost(@ModelAttribute User user, @RequestParam(value="id", required = false) Long id) {
        userService.update(user, id);
        return "redirect:/admin/all";
    }

    @GetMapping("/delete")
    public String deleteGet(Model model, Long id) {
        model.addAttribute("id", id );

        return "delete";
    }

    @PostMapping("/delete")
    public String deletePost(Long id) {
        userService.delete(id);

        return "redirect:/admin/all";
    }

}

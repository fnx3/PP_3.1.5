package preproject.PP_31.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import preproject.PP_31.model.Role;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminView (Model model) {
        model.addAttribute("roleUser", new Role(1L,"ROLE_USER") );
        model.addAttribute("roleAdmin", new Role(2L,"ROLE_ADMIN") );

        return "admin";
    }

}

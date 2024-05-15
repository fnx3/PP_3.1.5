package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.Role;
import preproject.PP_31.model.User;
import preproject.PP_31.service.RoleService;
import preproject.PP_31.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User> > showAllUsers() {
        return ResponseEntity.ok(userService.getAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id) );
    }

    @PostMapping
    public void newUser (@RequestBody User user) {
        userService.add(user);
    }

    @PutMapping
    public void editUser(@RequestBody User user, @RequestBody Set<Role> roles) {
        user.setRoles(roles);
        userService.update(user, user.getId() );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userService.getCurrentUser(principal) );
    }

}

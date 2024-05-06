package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    UserRepository userRepository;

    @Autowired
    public AdminRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<List<User> > showAllUsers() {
        return ResponseEntity.ok(userRepository.findAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id).get() );
    }

    @PostMapping
    public ResponseEntity<User> newUser (@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user) );
    }

    @PutMapping
    public void editUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userRepository.findByName(principal.getName() ).get() );
    }

}

package preproject.PP_31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    UserRepository userRepository;

    @Autowired
    public AdminRESTController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User> > showAllUsers() {
        return ResponseEntity.ok(userRepository.findAll() );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id).get() );
    }

    @PostMapping("/users")
    public ResponseEntity<User> newUser (@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user) );
    }

    @PutMapping("/users")
    public void editUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}

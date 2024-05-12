package preproject.PP_31.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
       return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword() ) );
        userRepository.save(user);
    }

    public void update(User user, Long id) {
        user.setPassword(passwordEncoder.encode(user.getPassword() ) );
        user.setId(id);
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}

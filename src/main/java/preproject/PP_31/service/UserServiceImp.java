package preproject.PP_31.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preproject.PP_31.model.Role;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.RoleRepository;
import preproject.PP_31.repositories.UserRepository;


import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository; this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
       return userRepository.getById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void update(User user, Long id) {
        user.setId(id);
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}

package preproject.PP_31.service;

import preproject.PP_31.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    User get(Long id);

    User getCurrentUser(Principal principal);

    List<User> getAll();

    void add(User user);

    void update(User user, Long id);

    void delete(Long id);

}

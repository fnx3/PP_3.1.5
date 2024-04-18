package preproject.PP_31.service;

import preproject.PP_31.model.User;

import java.util.List;

public interface UserService {

    User get(Long id);
    List<User> getAll();

    void add(User user);

    void update(User user, Long id);
    void delete(Long id);

}

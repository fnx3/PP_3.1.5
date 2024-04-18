package preproject.PP_31.dao;

import preproject.PP_31.model.User;

import java.util.List;

public interface UserDao {

    User get(Long id);
    List<User> getAll();

    void add(User user);

    void update(User user, Long id);
    void delete(Long id);

}

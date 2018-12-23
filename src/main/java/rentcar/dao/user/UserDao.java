package rentcar.dao.user;

import java.util.List;

import rentcar.model.User;

public interface UserDao {

    User findById(int id);

    User findByLogin(String login);

    void save(User user);

    void deleteByLogin(String login);

    List<User> getAll();

    List<User> getByRole(int roleId);
}


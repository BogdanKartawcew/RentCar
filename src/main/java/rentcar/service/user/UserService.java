package rentcar.service.user;

import java.util.List;

import rentcar.model.support.User;

public interface UserService {

    User findById(int id);

    User findByLogin(String login);

    void save(User user);

    void update(User user);

    void deleteByLogin(String login);

    List<User> getAll();

    boolean isLoginUnique(Integer id, String login);

    List<User> getByRole(int roleId);

    long countAllByPage();

    List<User> getConfirmedByPage(int pageNumber, int rowsOnPage);
}
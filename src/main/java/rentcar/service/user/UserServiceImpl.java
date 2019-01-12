package rentcar.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rentcar.dao.user.UserDao;
import rentcar.model.User;
import rentcar.model.UserImage;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserImageService userImageService;

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);
    }

    public void update(User user) {
        User entity = userDao.findById(user.getId());
        if (entity != null) {
            entity.setLogin(user.getLogin());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setSex(user.getSex());
            entity.setRoles(user.getRoles());
            entity.setRole();
        }
    }

    public void deleteByLogin(String login) {
        userDao.deleteByLogin(login);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public boolean isLoginUnique(Integer id, String login) {
        User user = findByLogin(login);
        //return (user == null || ((id != null) && (user.getId() == id)));
        return (user == null || user.getId().equals(id));
    }

    @Override
    public List<User> getByRole(int roleId) {
        return userDao.getByRole(roleId);
    }

    @Override
    public long countAllByPage() {
        return userDao.countAllByPage();
    }

    @Override
    public List<User> getConfirmedByPage(int pageNumber, int rowsOnPage) {
        return userDao.getConfirmedByPage(pageNumber, rowsOnPage);
    }
}

package rentcar.dao.user;

import rentcar.model.support.UserImage;


public interface UserImageDao {
    UserImage findByLogin(String login);

    void save(UserImage userImage);

    void deleteByLogin(String login);

    UserImage findById(int id);
}

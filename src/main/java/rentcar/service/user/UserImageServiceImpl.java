package rentcar.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.user.UserImageDao;
import rentcar.model.support.UserImage;

@Service("userImageService")
@Transactional
public class UserImageServiceImpl implements UserImageService {

    @Autowired
    UserImageDao userImageDao;

    @Override
    public UserImage findByLogin(String login) {
        return userImageDao.findByLogin(login);
    }

    @Override
    public void saveUserImage(UserImage userImage) {
        userImageDao.save(userImage);
    }

    @Override
    public void deleteUserImageByLogin(String login) {
        userImageDao.deleteByLogin(login);
    }

    @Override
    public void updateUserImage(UserImage userImage) {
        UserImage entity = userImageDao.findById(userImage.getId());
        if (entity != null) {
            entity.setId(userImage.getId());
            entity.setUserImage(userImage.getUserImage());
        }
    }
}

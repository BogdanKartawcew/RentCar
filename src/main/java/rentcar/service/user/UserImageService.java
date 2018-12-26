package rentcar.service.user;

import rentcar.model.support.UserImage;

public interface UserImageService {

    UserImage findByLogin(String login);

    void saveUserImage(UserImage userImage);

    void deleteUserImageByLogin(String login);

    void updateUserImage(UserImage userImage);
}

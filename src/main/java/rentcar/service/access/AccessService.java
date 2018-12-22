package rentcar.service.access;

import rentcar.model.User;

public interface AccessService {

    void mailUserAccessRequestSent(User user);

    void mailUserAccessGranted(User user);

    void mailRecruiterAccessGranted();

    void mailRecruiterAccessRemoved();

    void mailUserAccessRemoved(User user);


}

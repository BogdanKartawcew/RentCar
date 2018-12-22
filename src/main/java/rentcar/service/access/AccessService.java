package rentcar.service.access;

import rentcar.model.User;

import java.util.Map;

public interface AccessService {

    void mailUserAccessRequestSent(User user);

    void mailUserAccessGranted(User user);

    void mailRecruiterAccessGranted();

    void mailRecruiterAccessRemoved();

    void mailUserAccessRemoved(User user);
}

package rentcar.service.access;

import rentcar.model.User;

import java.util.HashMap;

public interface AccessService {

    void mailUser(String mailSubject, String link, String email , HashMap<String, Object> model);

    void deleteRecruiter(User user);

    void createRecruiter(User user);
}

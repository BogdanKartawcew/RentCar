package rentcar.service.user;

import java.util.HashSet;
import java.util.List;

import rentcar.model.support.Role;


public interface UserProfileService {

    Role findById(int id);

    Role findByType(String type);

    List<Role> getAll();

    HashSet<Role> getRoleSet(int roleId);
}

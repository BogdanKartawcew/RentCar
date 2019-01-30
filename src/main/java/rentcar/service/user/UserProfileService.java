package rentcar.service.user;

import java.util.HashSet;
import java.util.List;

import rentcar.model.Role;


public interface UserProfileService {

    Role findById(int id);

    Role findByType(String type);

    List<Role> getAll();

    HashSet<Role> getRoleSetById(int roleId);

    HashSet<Role> getRoleSetByType(String profileType);
}

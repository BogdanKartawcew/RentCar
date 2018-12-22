package rentcar.service.user;

import java.util.List;

import rentcar.model.Role;


public interface UserProfileService {

	Role findById(int id);

	Role findByType(String type);
	
	List<Role> getAll();
	
}

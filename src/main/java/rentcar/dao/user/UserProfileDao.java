package rentcar.dao.user;

import java.util.List;

import rentcar.model.Role;


public interface UserProfileDao {

	List<Role> findAll();
	
	Role findByType(String type);
	
	Role findById(int id);
}

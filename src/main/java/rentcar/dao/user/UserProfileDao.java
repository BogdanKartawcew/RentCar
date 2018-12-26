package rentcar.dao.user;

import java.util.List;

import rentcar.model.support.Role;


public interface UserProfileDao {

	List<Role> findAll();
	
	Role findByType(String type);
	
	Role findById(int id);
}

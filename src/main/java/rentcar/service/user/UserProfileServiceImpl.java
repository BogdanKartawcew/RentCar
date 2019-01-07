package rentcar.service.user;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rentcar.dao.user.UserProfileDao;
import rentcar.model.Role;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;

    @Override
    public Role findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Role findByType(String type) {
        return dao.findByType(type);
    }

    @Override
    public List<Role> getAll() {
        return dao.findAll();
    }

    @Override
    public HashSet<Role> getRoleSet(int roleId) {
        HashSet<Role> roleSet = new HashSet<>();
        Role role = findById(roleId);
        roleSet.add(role);
        return roleSet;
    }
}

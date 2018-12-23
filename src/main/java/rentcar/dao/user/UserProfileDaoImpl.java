package rentcar.dao.user;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import rentcar.dao.common.AbstractDao;
import rentcar.model.Role;


@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, Role> implements UserProfileDao {

    public Role findById(int id) {
        return getByKey(id);
    }

    public Role findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (Role) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<Role>) crit.list();
    }
}

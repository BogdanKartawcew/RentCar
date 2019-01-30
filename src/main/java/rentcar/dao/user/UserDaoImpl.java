package rentcar.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.User;

import static rentcar.propertiesenums.ControlersTexts.Constants.LOW_LOGIN;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }

    public User findByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(LOW_LOGIN, login));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc(LOW_LOGIN));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<User>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> getByRole (int roleId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("role", roleId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<User>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getConfirmedByPage(int pageNumber, int rowsOnPage) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc(LOW_LOGIN));
        criteria.add(Restrictions.not(Restrictions.in("role", new Integer[]{4})));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (pageNumber == 1) {
            criteria.setFirstResult(0);
        } else {
            criteria.setFirstResult((pageNumber-1) * rowsOnPage);
        }
        criteria.setMaxResults(rowsOnPage);
        return (List<User>) criteria.list();
    }

    @Override
    public long countAllByPage() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.not(Restrictions.in("role", new Integer[]{4})));
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(LOW_LOGIN, login));
        User user = (User) crit.uniqueResult();
        delete(user);
    }
}

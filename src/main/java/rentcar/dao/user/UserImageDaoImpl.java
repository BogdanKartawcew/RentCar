package rentcar.dao.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.UserImage;
import rentcar.service.user.UserService;

@Repository("userImageDao")
public class UserImageDaoImpl extends AbstractDao<Integer, UserImage> implements UserImageDao {

    @Autowired
    UserService userService;

    @Override
    public UserImage findByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", findIdByUserLogin(login)));
        UserImage userImage = (UserImage) crit.uniqueResult();
        return userImage;
    }

    @Override
    public UserImage findById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        UserImage userImage = (UserImage) crit.uniqueResult();
        return userImage;
    }

    @Override
    public void save(UserImage userImage) {
        persist(userImage);
    }

    @Override
    public void deleteByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", findIdByUserLogin(login)));
        UserImage userImage = (UserImage) crit.uniqueResult();
        delete(userImage);
    }

    private int findIdByUserLogin(String login) {
        return userService.findByLogin(login).getId();
    }
}

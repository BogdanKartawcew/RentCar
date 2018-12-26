package rentcar.dao.reservation;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.support.Availability;

import java.util.List;

@Repository("availabilityDao")
public class AvailabilityDaoImpl extends AbstractDao<Integer, Availability> implements AvailabilityDao {

    @Override
    public Availability findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void save(Availability availability) {
        persist(availability);
    }

    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("availabilityId", id));
        Availability availability = (Availability) crit.uniqueResult();
        delete(availability);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Availability> checkAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("startDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Availability>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Availability> listByCarId(Integer carId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("carId", carId));
        return (List<Availability>) crit.add(Restrictions.eq("carId", carId)).list();
    }

    /*@Override //example how to search for something by multiple 'where' conditions
    public int findTimeId(Integer carId, String startDate, String endDate) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("carId", carId));
        crit.add(Restrictions.eq("startDate", startDate));
        crit.add(Restrictions.eq("endDate", endDate));
        Availability carUnavailable = (Availability) crit.uniqueResult();
        return carUnavailable.getAvailabilityId();
    }*/
}

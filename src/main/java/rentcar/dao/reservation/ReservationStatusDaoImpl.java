package rentcar.dao.reservation;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.support.ReservationStatus;

import java.util.List;

@Repository("reservationStatusDao")
public class ReservationStatusDaoImpl extends AbstractDao<Integer, ReservationStatus> implements ReservationStatusDao {

    @Override
    public ReservationStatus findByStatusId(String statusId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("statusId", statusId));
        return (ReservationStatus) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReservationStatus> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("statusId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<ReservationStatus>) criteria.list();
    }

    @Override
    public void save(ReservationStatus reservationStatus) {
        persist(reservationStatus);
    }

    @Override
    public void delete(String id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("statusId", id));
        ReservationStatus reservationStatus = (ReservationStatus) crit.uniqueResult();
        delete(reservationStatus);
    }
}

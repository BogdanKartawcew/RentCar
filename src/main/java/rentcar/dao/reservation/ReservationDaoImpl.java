package rentcar.dao.reservation;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.Reservation;

import java.util.List;

@Repository("reservationDao")
public class ReservationDaoImpl extends AbstractDao<Integer, Reservation> implements ReservationDao {

    public Reservation findById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("reservationId", id));
        return (Reservation) crit.uniqueResult();
    }

    public void save(Reservation reservation) {
        persist(reservation);
    }

    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("reservationId", id));
        Reservation reservation = (Reservation) crit.uniqueResult();
        delete(reservation);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("startDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Reservation>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> getNotConfirmed() {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("confirmed", false)).addOrder(Order.asc("startDate"));
        return (List<Reservation>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> getRunning() {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("active", true)).addOrder(Order.asc("startDate"));
        return (List<Reservation>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> getFinished() {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("finished", true));
        return (List<Reservation>) criteria.list();
    }
}

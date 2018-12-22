package rentcar.dao.reservation;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.ReservationHistory;

import java.util.List;

@Repository("reservationHistoryDao")
public class ReservationHistoryDaoImpl extends AbstractDao<Integer, ReservationHistory> implements ReservationHistoryDao {
    @Override
    public ReservationHistory findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void save(ReservationHistory reservationHistory) {
        persist(reservationHistory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReservationHistory> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("timestamp"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<ReservationHistory>) criteria.list();
    }
}

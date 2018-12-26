package rentcar.dao.reservation;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.support.ReservationHistory;

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
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<ReservationHistory>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReservationHistory> getAllByPage(int pageNumber, int rowsOnPage) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (pageNumber == 1) {
            criteria.setFirstResult(0);
        } else {
            criteria.setFirstResult((pageNumber-1) * rowsOnPage);
        }
        criteria.setMaxResults(rowsOnPage);
        return (List<ReservationHistory>) criteria.list();
    }

    @Override
    public long countAllByPage() {
        Criteria criteriaCount = createEntityCriteria();
        criteriaCount.setProjection(Projections.rowCount());
        return (Long) criteriaCount.uniqueResult();
    }
}

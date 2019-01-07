package rentcar.dao.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.Client;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

    public Client findById(int id) {
        Client client = getByKey(id);
        return client;
    }

    public Client findByPesel(String pesel) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("pesel", pesel));
        Client client = (Client) crit.uniqueResult();
        return client;
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("clientFirstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Client> clients = (List<Client>) criteria.list();
        return clients;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> getAllByPage(int pageNumber, int rowsOnPage) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("clientFirstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (pageNumber == 1) {
            criteria.setFirstResult(0);
        } else {
            criteria.setFirstResult((pageNumber-1) * rowsOnPage);
        }
        criteria.setMaxResults(rowsOnPage);
        return (List<Client>) criteria.list();
    }

    @Override
    public long countAllByPage() {
        Criteria criteriaCount = createEntityCriteria();
        criteriaCount.setProjection(Projections.rowCount());
        return (Long) criteriaCount.uniqueResult();
    }

    public void save(Client client) {
        persist(client);
    }

    public void deleteByPesel(String pesel) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("pesel", pesel));
        Client client = (Client) crit.uniqueResult();
        delete(client);
    }
}

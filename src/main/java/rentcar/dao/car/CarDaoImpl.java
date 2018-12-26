package rentcar.dao.car;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.support.Car;

import java.util.List;

@Repository("carDao")
public class CarDaoImpl extends AbstractDao<Integer, Car> implements CarDao {

    public Car findById(int carId) {
        Car car = getByKey(carId);
        return car;
    }

    public Car findByVin(String vin) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("vin", vin));
        Car car = (Car) crit.uniqueResult();
        return car;
    }

    @Override
    public int getMileageById(int carId) {
        Car car = findById(carId);
        int mileage = car.getCarMileage();
        return mileage;
    }

    @SuppressWarnings("unchecked")
    public List<Car> getAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("carBrand"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Car> cars = (List<Car>) criteria.list();
        return cars;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Car> getAllByPage(int pageNumber, int rowsOnPage) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("carBrand"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (pageNumber == 1) {
            criteria.setFirstResult(0);
        } else {
            criteria.setFirstResult((pageNumber-1) * rowsOnPage);
        }
        criteria.setMaxResults(rowsOnPage);
        return (List<Car>) criteria.list();
    }

    @Override
    public long countAllByPage() {
        Criteria criteriaCount = createEntityCriteria();
        criteriaCount.setProjection(Projections.rowCount());
        return (Long) criteriaCount.uniqueResult();
    }

    public void save(Car car) {
        persist(car);
    }

    public void deleteByVin(String vin) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("vin", vin));
        Car car = (Car) crit.uniqueResult();
        delete(car);
    }
}

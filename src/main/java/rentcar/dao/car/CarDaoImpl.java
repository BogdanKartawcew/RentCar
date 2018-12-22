package rentcar.dao.car;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.Car;

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

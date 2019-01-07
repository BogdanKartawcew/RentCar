package rentcar.dao.car;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.CarImage;

@Repository("carImageDao")
public class CarImageDaoImpl extends AbstractDao<Integer, CarImage> implements CarImageDao{

    @Override
    public CarImage find(int carId, int number) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("carId", carId));
        crit.add(Restrictions.eq("number", number));
        return (CarImage) crit.uniqueResult();
    }

    @Override
    public void save(CarImage carImage) {
        persist(carImage);
    }

    @Override
    public void delete(int carId, int number) {
        CarImage carImage = find(carId, number);
        delete(carImage);
    }
}

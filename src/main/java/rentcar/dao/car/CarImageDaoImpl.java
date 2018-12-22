package rentcar.dao.car;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rentcar.dao.common.AbstractDao;
import rentcar.model.CarImage;

@Repository("carImageDao")
public class CarImageDaoImpl extends AbstractDao<Integer, CarImage> implements CarImageDao{


    @Override
    public CarImage findByVin(String vin) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("vin", vin));
        CarImage carImage = (CarImage) crit.uniqueResult();
        return carImage;
    }

    @Override
    public void save(CarImage carImage) {
        persist(carImage);
    }

    @Override
    public void deleteByVin(String vin) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("vin", vin));
        CarImage carImage = (CarImage) crit.uniqueResult();
        delete(carImage);
    }
}

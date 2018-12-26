package rentcar.dao.car;

import rentcar.model.support.CarImage;

public interface CarImageDao {

    CarImage findByVin(String vin);

    void save(CarImage carImage);

    void deleteByVin(String vin);
}

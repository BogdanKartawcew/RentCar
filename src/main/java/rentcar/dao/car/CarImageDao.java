package rentcar.dao.car;

import rentcar.model.CarImage;

public interface CarImageDao {

    CarImage find(int imageId, int number);

    void save(CarImage carImage);

    void delete(int imageId, int number);
}

package rentcar.service.car;

import rentcar.model.CarImage;
import rentcar.service.common.FileBucket;

public interface CarImageService {

    CarImage find(int carId, int number);

    void save(CarImage carImage);

    void update(FileBucket fileBucket, int carId, int number);

    void delete(int carId);
}

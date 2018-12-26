package rentcar.service.car;

import rentcar.model.support.CarImage;

public interface CarImageService {

    CarImage findByVin(String vin);

    void saveCarImage(CarImage carImage);

    void updateCarImage(CarImage carImage);

    void deleteCarImageByVin(String vin);
}

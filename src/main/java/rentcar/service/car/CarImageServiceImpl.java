package rentcar.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.car.CarImageDao;
import rentcar.model.support.CarImage;

@Service("carImageService")
@Transactional
public class CarImageServiceImpl implements CarImageService {
    @Autowired
    CarImageDao carImageDao;

    @Override
    public CarImage findByVin(String vin) {
        return carImageDao.findByVin(vin);
    }

    @Override
    public void saveCarImage(CarImage carImage) {
        carImageDao.save(carImage);
    }

    @Override
    public void updateCarImage(CarImage carImage) {
        CarImage entity = carImageDao.findByVin(carImage.getVin());
        if (entity != null) {
            entity.setVin(carImage.getVin());
            entity.setCarImage(carImage.getCarImage());
        }
    }

    @Override
    public void deleteCarImageByVin(String vin) {
        carImageDao.deleteByVin(vin);
    }
}

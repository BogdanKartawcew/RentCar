package rentcar.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rentcar.dao.car.CarImageDao;
import rentcar.model.CarImage;
import rentcar.service.common.FileBucket;

import java.io.IOException;

@Service("carImageService")
@Transactional
public class CarImageServiceImpl implements CarImageService {
    @Autowired
    CarImageDao carImageDao;

    @Autowired
    CarService carService;

    @Override
    public CarImage find(int carId, int number) {
        return carImageDao.find(carId, number);
    }

    @Override
    public void save(CarImage carImage) {
        carImageDao.save(carImage);
    }

    @Override
    public void update(FileBucket fileBucket, int carId, int number){

        CarImage carImage = new CarImage();
        try {
            MultipartFile multipartFile = fileBucket.getFile();
            carImage.setCarId(carId);
            carImage.setNumber(number);
            carImage.setCarImage(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println("Unexpected error during loading the car image");
        }

        CarImage entity = carImageDao.find(carId, number);
        if (entity != null) {
            entity.setCarId(carId);
            entity.setNumber(number);
            entity.setCarImage(carImage.getCarImage());
        }
    }

    @Override
    public void delete(int carId, int number) {
        carImageDao.delete(carId, number);
    }
}

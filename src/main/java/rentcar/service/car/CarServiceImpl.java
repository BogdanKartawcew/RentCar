package rentcar.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.car.CarDao;
import rentcar.model.Car;
import rentcar.model.CarImage;


import java.util.List;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CarImageService carImageService;

    @Override
    public Car findById(int carId) {
        return carDao.findById(carId);
    }

    @Override
    public Car findByVIN(String vin) {
        return carDao.findByVIN(vin);
    }


    @Override
    public int getMileageById(int carId) {
        return carDao.getMileageById(carId);
    }

    @Override
    public void save(Car car) {
        carDao.save(car);
        System.out.println("CARID:::::" + car.getCarId());
        Thread saveCarImageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                saveCarImage(car.getCarId());
            }
        });
        saveCarImageThread.start();
    }


    private void saveCarImage(int carId) {
        for (int i = 1; i <= imagesPerCar; i++) {
            CarImage carImage = new CarImage();
            carImage.setCarId(carId);
            carImage.setCarImage(null);
            carImage.setNumber(i);
            carImageService.save(carImage);
        }
    }

    @Override
    public void update(Car car) {
        Car entity = carDao.findById(car.getCarId());
        if (entity != null) {
            entity.setCarId(car.getCarId());
            entity.setCarBrand(car.getCarBrand());
            entity.setCarModel(car.getCarModel());
            entity.setCarFuel(car.getCarFuel());
            entity.setCarGearType(car.getCarGearType());
            entity.setCarHorsePower(car.getCarHorsePower());
            entity.setCarBodyType(car.getCarBodyType());
            entity.setCarDoorsNum(car.getCarDoorsNum());
            entity.setCarSeatsNum(car.getCarSeatsNum());
            entity.setCarAirCond(car.isCarAirCond());
            entity.setCarNavigation(car.isCarNavigation());
            entity.setVin(car.getVin());
            entity.setRegNum(car.getRegNum());
            entity.setCarYear(car.getCarYear());
            entity.setCarMileage(car.getCarMileage());
            entity.setCarVersion(car.getCarVersion());
            entity.setPrice(car.getPrice());
            entity.setCity(car.getCity());
        }
    }

    @Override
    public void delete(int carId) {
        carDao.delete(carId);
        carImageService.delete(carId);
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public boolean isVinUnique(Integer carId, String vin) {
        Car car = findByVIN(vin);
        return (car == null || ((carId != null) && (car.getCarId() == carId)));
    }

    @Override
    public long countAllByPage() {
        return carDao.countAllByPage();
    }

    @Override
    public List<Car> getAllByPage(int pageNumber, int rowsOnPage) {
        return carDao.getAllByPage(pageNumber, rowsOnPage);
    }

}

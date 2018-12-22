package rentcar.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.car.CarDao;
import rentcar.model.Car;


import java.util.List;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    public Car findByCarId(int carId) {
        return carDao.findById(carId);
    }

    public Car findByVin(String vin) {
        return carDao.findByVin(vin);
    }

    public int getMileageById(int carId) {
        return carDao.getMileageById(carId);
    }

    public void saveCar(Car car) {
        carDao.save(car);
    }

    public void updateCar(Car car) {
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
        }
    }

    public void deleteCarByVin(String vin) {
        carDao.deleteByVin(vin);
    }

    public List<Car> findAllCars() {
        return carDao.getAll();
    }

    public boolean isVinUnique(Integer carId, String vin) {
        Car car = findByVin(vin);
        return (car == null || ((carId != null) && (car.getCarId() == carId)));
    }

}

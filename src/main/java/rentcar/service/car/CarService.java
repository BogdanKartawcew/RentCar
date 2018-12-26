package rentcar.service.car;

import rentcar.model.support.Car;

import java.util.List;

public interface CarService {

    Car findByCarId(int carId);

    Car findByVin(String vin);

    int getMileageById(int carId);

    void saveCar(Car car);

    void updateCar(Car car);

    void deleteCarByVin(String vin);

    List<Car> findAllCars();

    boolean isVinUnique(Integer carId, String vin);

    long countAllByPage();

    List<Car> getAllByPage(int pageNumber, int rowsOnPage);

}
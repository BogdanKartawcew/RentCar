package rentcar.service.car;

import rentcar.model.Car;

import java.util.List;

public interface CarService {

    Car findById(int carId);

    Car findByVIN(String vin);

    int getMileageById(int carId);

    void save(Car car);

    void update(Car car);

    void delete(int carId);

    List<Car> getAll();

    boolean isVinUnique(Integer carId, String vin);

    long countAllByPage();

    List<Car> getAllByPage(int pageNumber, int rowsOnPage);

}
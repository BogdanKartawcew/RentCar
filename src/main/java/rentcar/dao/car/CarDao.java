package rentcar.dao.car;

import rentcar.model.Car;

import java.util.List;

public interface CarDao {

    Car findById(int carId);

    Car findByVin(String vin);

    int getMileageById(int carId);

    void save(Car car);

    void deleteByVin(String vin);

    List<Car> getAll();
}


package rentcar.dao.car;

import rentcar.model.Car;

import java.util.List;

public interface CarDao {

    Car findById(int carId);

    Car findByVIN(String vin);

    int getMileageById(int carId);

    void save(Car car);

    void delete(int carId);

    List<Car> getAll();

    List<Car> getAllByPage(int pageNumber, int rowsOnPage);

    long countAllByPage();
}


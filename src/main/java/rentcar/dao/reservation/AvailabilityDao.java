package rentcar.dao.reservation;

import rentcar.model.Availability;

import java.util.List;

public interface AvailabilityDao {

    Availability findById(Integer id);

    void save(Availability availability);

    void delete(int id);

    List<Availability> checkAll();

    List<Availability> listByCarId(Integer carId);
}

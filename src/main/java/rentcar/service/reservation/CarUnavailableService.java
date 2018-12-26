package rentcar.service.reservation;

import rentcar.model.support.Availability;
import rentcar.model.support.Reservation;

import java.util.List;

public interface CarUnavailableService {

    void save(Availability availability);

    void delete(int id);

    List<Availability> getAll();

    List<Availability> listByCarId(Integer carId);

    boolean[] checkAvailability(Reservation reservation);

    void update(Availability availability);

    String getTomorrow();

    Availability setCarUnavailble(Reservation reservation);
}



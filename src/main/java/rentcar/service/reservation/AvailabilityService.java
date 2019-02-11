package rentcar.service.reservation;

import rentcar.model.Availability;
import rentcar.model.Reservation;

import java.util.HashMap;
import java.util.List;

public interface AvailabilityService {

    void save(Availability availability);

    void delete(int id);

    List<Availability> getAll();

    List<Availability> listByCarId(Integer carId);

    boolean[] checkAvailability(Reservation reservation);

    void update(Availability availability);

    boolean containsFalse(boolean[] errors);

    String getTomorrow();

    Availability setCarUnavailble(Reservation reservation);

    boolean[] checkReservationOnEmpties(Reservation reservation);

    HashMap<String, String> getCalendarData();
}



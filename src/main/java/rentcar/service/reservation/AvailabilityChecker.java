package rentcar.service.reservation;

import rentcar.model.Reservation;

public interface AvailabilityChecker {
    boolean[] checkAvailability(Reservation reservation);
}

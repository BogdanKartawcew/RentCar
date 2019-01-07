package rentcar.service.reservation;

import rentcar.model.ReservationStatus;

import java.util.List;


public interface ReservationStatusService {

    void updte(ReservationStatus reservationStatus);

    void save(ReservationStatus reservationStatus);

    void delete(String statusId);

    List<ReservationStatus> getAll();
}

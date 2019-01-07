package rentcar.service.reservation;

import rentcar.model.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findById(int id);

    void save(Reservation reservation);

    void update(Reservation reservation);

    void delete(int reservationId);

    List<Reservation> getAll();

    List<Reservation> getNotConfirmed();

    List<Reservation> getRunning();

    List<Reservation> getFinished();

    void updateStatus(Reservation reservation, String command);

    void checkStatus(Reservation reservation);
}
package rentcar.dao.reservation;

import rentcar.model.Reservation;

import java.util.List;

public interface ReservationDao {

    Reservation findById(int id);

    void save(Reservation reservation);

    void delete(int id);

    List<Reservation> getAll();

    List<Reservation> getNotConfirmed();

    List<Reservation> getRunning();

    List<Reservation> getFinished();
}


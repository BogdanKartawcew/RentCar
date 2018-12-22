package rentcar.dao.reservation;

import rentcar.model.ReservationHistory;

import java.util.List;

public interface ReservationHistoryDao {

    ReservationHistory findById(Integer id);

    void save(ReservationHistory reservationHistory);

    List<ReservationHistory> getAll();
}

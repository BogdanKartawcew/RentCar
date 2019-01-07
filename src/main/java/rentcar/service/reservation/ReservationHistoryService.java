package rentcar.service.reservation;

import rentcar.model.Reservation;
import rentcar.model.ReservationHistory;

import java.util.List;

public interface ReservationHistoryService {

    ReservationHistory findById(int id);

    void save(ReservationHistory reservationHistory);

    List<ReservationHistory> getAll();

    void createReservationHistoryObject(Reservation reservation);

    long countAllByPage();

    List<ReservationHistory> getAllByPage(int pageNumber, int rowsOnPage);
}

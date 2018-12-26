package rentcar.dao.reservation;

import rentcar.model.support.ReservationStatus;

import java.util.List;

public interface ReservationStatusDao {

    ReservationStatus findByStatusId(String statusId);

    void save(ReservationStatus reservationStatus);

    void delete(String id);

    List<ReservationStatus> getAll();
}

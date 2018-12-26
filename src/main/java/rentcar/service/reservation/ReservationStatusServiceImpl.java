package rentcar.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.reservation.ReservationStatusDao;
import rentcar.model.support.ReservationStatus;

import java.util.List;


@Transactional
@Service("reservationStatusService")
public class ReservationStatusServiceImpl implements ReservationStatusService {

    @Autowired
    ReservationStatusDao reservationStatusDao;

    @Override
    public void save(ReservationStatus reservationStatus) {
        reservationStatusDao.save(reservationStatus);
    }

    @Override
    public void delete(String statusId) {
        reservationStatusDao.delete(statusId);
    }

    @Override
    public List<ReservationStatus> getAll() {
        return reservationStatusDao.getAll();
    }

    @Override
    public void updte(ReservationStatus reservationStatus) {
        ReservationStatus entity = reservationStatusDao.findByStatusId(reservationStatus.getStatusId());
        if (entity != null) {
            entity.setStatusId(reservationStatus.getStatusId());
            entity.setStatusDesc(reservationStatus.getStatusDesc());
        }
    }
}

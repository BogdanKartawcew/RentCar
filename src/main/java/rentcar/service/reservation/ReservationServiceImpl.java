package rentcar.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rentcar.dao.reservation.ReservationDao;
import rentcar.model.Reservation;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationHistoryService reservationHistoryService;

    @Override
    public Reservation findById(int id) {
        return reservationDao.findById(id);
    }

    @Override
    public void save(Reservation reservation) {
        reservation.setStatus("000");
        reservation.setActive(false);
        reservationDao.save(reservation);
        reservationHistoryService.createReservationHistoryObject(reservation);
    }

    @Override
    public void update(Reservation reservation) {

        Reservation entity = reservationDao.findById(reservation.getReservationId());
        System.out.println("BEFORE update: " + entity);
        if (entity != null) {
            entity.setReservationId(reservation.getReservationId());
            entity.setClientId(reservation.getClientId());
            entity.setCarId(reservation.getCarId());
            entity.setStartDate(reservation.getStartDate());
            entity.setStartTime(reservation.getStartTime());
            entity.setEndDate(reservation.getEndDate());
            entity.setEndTime(reservation.getEndTime());
            entity.setStatus(reservation.getStatus());
            entity.setConfirmed(reservation.isConfirmed());
            entity.setActive(reservation.isActive());
            entity.setFinished(reservation.isFinished());
        }
        System.out.println("AFTER update: "+reservation);
    }

    @Override
    public void delete(int reservationId) {
        reservationDao.delete(reservationId);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationDao.getAll();
    }

    @Override
    public List<Reservation> getNotConfirmed() {
        return reservationDao.getNotConfirmed();
    }

    @Override
    public List<Reservation> getRunning() {
        return reservationDao.getRunning();
    }

    @Override
    public List<Reservation> getFinished() {
        return reservationDao.getFinished();
    }


    /*
    Logic of statuses. By default it "000" adding during creating in void save.
    Parameters:
    edit
    confirm
    cancel
    end
    */
    @Override
    public void updateStatus(Reservation reservation, String parameter) {
        String currentStatus = reservation.getStatus();
        switch (parameter) {
            case "edit":
                switch (currentStatus) {
                    case "000":
                        reservation.setStatus("010");
                        break;
                    case "100":
                        reservation.setStatus("110");
                        break;
                    case "200":
                        reservation.setStatus("210");
                        break;
                }
                break;
            case "cancel":
                switch (currentStatus) {
                    case "000":
                        reservation.setStatus("001");
                        break;
                    case "100":
                        reservation.setStatus("101");
                        break;
                    case "110":
                        reservation.setStatus("111");
                        break;
                    case "200":
                        reservation.setStatus("201");
                        break;
                    case "210":
                        reservation.setStatus("211");
                        break;
                }
                break;
            case "confirm":
                switch (currentStatus) {
                    case "000":
                        reservation.setStatus("100");
                        break;
                    case "010":
                        reservation.setStatus("110");
                        break;
                }
                break;
            case "end":
                switch (currentStatus) {
                    case "200":
                        reservation.setStatus("300");
                        break;
                    case "210":
                        reservation.setStatus("310");
                        break;
                }
                break;
        }
        update(reservation);
        reservationHistoryService.createReservationHistoryObject(reservation);
    }

    @Override
    public void checkStatus(Reservation reservation) {
        System.out.println("++++++++++++++++Updating status");

        String stringDateTime = reservation.getStartDate() + " " + reservation.getStartTime();
        Date current = new Date();
        DateFormat ourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ourDate = null;
        try {
            ourDate = ourFormat.parse(stringDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean after = current.after(ourDate);

    }
}

package rentcar.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.controller.AbstractController;
import rentcar.dao.reservation.ReservationHistoryDao;
import rentcar.model.Car;
import rentcar.model.Reservation;
import rentcar.model.ReservationHistory;
import rentcar.model.User;
import rentcar.service.car.CarService;
import rentcar.service.user.UserService;

import java.sql.Timestamp;
import java.util.List;

@Service("reservationHistoryService")
@Transactional
public class ReservationHistoryServiceImpl extends AbstractController implements ReservationHistoryService {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    public ReservationHistoryDao reservationHistoryDao;

    @Override
    public ReservationHistory findById(int id) {
        return reservationHistoryDao.findById(id);
    }


    @Override
    public void save(ReservationHistory reservationHistory) {
        reservationHistoryDao.save(reservationHistory);
    }

    @Override
    public List<ReservationHistory> getAll() {
        return reservationHistoryDao.getAll();
    }

    @Override
    public void createReservationHistoryObject(Reservation reservation) {
        Car car = carService.findByCarId(reservation.getCarId());
        User user = userService.findByLogin(getActiveUser());
        ReservationHistory reservationHistory = new ReservationHistory();
        reservationHistory.setStatus(reservation.getStatus());
        reservationHistory.setReservationId(reservation.getReservationId());
        reservationHistory.setCarId(reservation.getCarId());
        reservationHistory.setClientId(reservation.getClientId());
        reservationHistory.setStartDate(reservation.getStartDate());
        reservationHistory.setStartTime(reservation.getStartTime());
        reservationHistory.setEndDate(reservation.getEndDate());
        reservationHistory.setEndTime(reservation.getEndTime());
        reservationHistory.setMileage(car.getCarMileage());
        reservationHistory.setUserId(user.getId());
        reservationHistory.setTimestamp(new Timestamp(System.currentTimeMillis()));
        save(reservationHistory);
    }
}

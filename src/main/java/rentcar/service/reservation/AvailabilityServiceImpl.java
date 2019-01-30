package rentcar.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.reservation.AvailabilityDao;
import rentcar.model.Availability;
import rentcar.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("availabilityService")
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private AvailabilityChecker availabilityChecker;

    @Override
    public void save(Availability availability) {
        availabilityDao.save(availability);
    }

    @Override
    public void update(Availability availability) {
        Availability entity = availabilityDao.findById(availability.getAvailabilityId());
        if (entity != null) {
            entity.setAvailabilityId(availability.getAvailabilityId());
            entity.setCarId(availability.getCarId());
            entity.setStartDate(availability.getStartDate());
            entity.setStartTime(availability.getStartTime());
            entity.setEndDate(availability.getEndDate());
            entity.setEndTime(availability.getEndTime());
        }
    }

    @Override
    public void delete(int id) {
        availabilityDao.delete(id);
    }

    @Override
    public List<Availability> getAll() {
        return availabilityDao.getAll();
    }

    @Override
    public List<Availability> listByCarId(Integer carId) {
        return availabilityDao.listByCarId(carId);
    }

    @Override
    public boolean containsFalse(boolean[] errors) {
        for (boolean error : errors) {
            if (!error) return true;
        }
        return false;
    }

    /*adding 12 hours to actual real time - to can book not faster than 12 hours later from actual time. It means that it's impossible to book a car and wait for it in the next hour.*/
    @Override
    public String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 12);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(calendar.getTime());
    }


    @Override
    public Availability setCarUnavailble(Reservation reservation) {
        Availability availability = new Availability();
        availability.setAvailabilityId(reservation.getReservationId());
        availability.setCarId(reservation.getCarId());
        availability.setStartDate(reservation.getStartDate());
        availability.setStartTime(reservation.getStartTime());
        availability.setEndDate(reservation.getEndDate());
        availability.setEndTime(reservation.getEndTime());
        return availability;
    }

    @Override
    public boolean[] checkAvailability(Reservation reservation) {
        return availabilityChecker.checkAvailability(reservation);
    }

    @Override
    public boolean[] checkReservationOnEmpties(Reservation reservation) {
        boolean[] result = new boolean[]{true, true, true, true, true, true}; //2 first positions always true. it's for "startTimeAvailable" and "endTimeAvailable"
        if (reservation.getStartDate() == null) {
            result[2] = false;
        }
        if (reservation.getEndDate() == null) {
            result[3] = false;
        }
        if (reservation.getStartTime() == null) {
            result[4] = false;
        }
        if (reservation.getEndTime() == null) {
            result[5] = false;
        }
        return result;
    }
}

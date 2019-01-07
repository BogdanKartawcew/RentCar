package rentcar.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.reservation.AvailabilityDao;
import rentcar.model.Availability;
import rentcar.model.Reservation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("carUnavailableService")
@Transactional
public class CarUnavailableServiceImpl implements CarUnavailableService {

    private final String dbDateFormat = "yyyy-MM-dd";
    private final String dbTimeFormat = "HH:mm:ss";
    private final Calendar openHours = toCalendar("05:59:59", dbTimeFormat);
    private final Calendar closeHours = toCalendar("22:00:01", dbTimeFormat);

    @Autowired
    private AvailabilityDao availabilityDao;

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

    public boolean[] checkAvailability(Reservation reservation) {

        boolean[] availibilityArray = new boolean[6]; /*startDateAvailable, endDateAvailable, startTimeAvailable, endTimeAvailable, startTimeInTime, endTimeInTime*/
        List<Availability> unavailablesList = listByCarId(reservation.getCarId());
        Calendar startDate = toCalendar(reservation.getStartDate().toString(), dbDateFormat);
        Calendar endDate = toCalendar(reservation.getEndDate().toString(), dbDateFormat);
        Calendar startTime = toCalendar(reservation.getStartTime().toString(), dbTimeFormat);
        Calendar endTime = toCalendar(reservation.getEndTime().toString(), dbTimeFormat);
        Calendar startTimestamp = createTimestamp(startDate, startTime);
        Calendar endTimestamp = createTimestamp(endDate, endTime);

        if (!unavailablesList.isEmpty()) {
            for (Availability car : unavailablesList) {
                if (!car.getAvailabilityId().equals(reservation.getReservationId())) {
                    Calendar startCal = createTimestamp(toCalendar(car.getStartDate().toString(), dbDateFormat), toCalendar(car.getStartTime().toString(), dbTimeFormat));
                    Calendar endCal = createTimestamp(toCalendar(car.getEndDate().toString(), dbDateFormat), toCalendar(car.getEndTime().toString(), dbTimeFormat));
                    endCal.add(Calendar.HOUR_OF_DAY, 3); //BUSINESS - to create a book after 3 hours car arrived.
                    availibilityArray[0] = !startTimestamp.before(startCal) && !startTimestamp.after(endCal);
                    availibilityArray[1] = !endTimestamp.before(startCal) && !endTimestamp.after(endCal);
                    if (availibilityArray[0])
                        availibilityArray[2] = !(!startTime.before(toCalendar(car.getStartTime().toString(), dbTimeFormat)) && !startTime.after(toCalendar(car.getEndTime().toString(), dbTimeFormat)));
                    if (availibilityArray[1])
                        availibilityArray[3] = !(!endTime.before(toCalendar(car.getStartTime().toString(), dbTimeFormat)) && !endTime.after(toCalendar(car.getEndTime().toString(), dbTimeFormat)));
                }
            }
        }
        availibilityArray[4] = !(startTime.after(openHours) && startTime.before(closeHours));
        availibilityArray[5] = !(endTime.after(openHours) && endTime.before(closeHours));
        return availibilityArray;
    }

    private Calendar toCalendar(String stringDate, String format) {
        if (stringDate == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        try {
            Timestamp newDate = Timestamp.valueOf(stringDate);
            calendar.setTime(newDate);
        } catch (Exception e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                calendar.setTime(simpleDateFormat.parse(stringDate));
            } catch (ParseException pe) {
                calendar = null;
            }
        }
        return calendar;
    }

    private Calendar createTimestamp(Calendar date, Calendar time) {
        Calendar timestamp = GregorianCalendar.getInstance();
        timestamp.set(Calendar.YEAR, date.get(Calendar.YEAR));
        timestamp.set(Calendar.MONTH, date.get(Calendar.MONTH));
        timestamp.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
        timestamp.set(Calendar.HOUR, time.get(Calendar.HOUR));
        timestamp.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        timestamp.set(Calendar.SECOND, time.get(Calendar.SECOND));
        return timestamp;
    }

    /*adding 12 hours to actual real time - to can book not faster than 12 hours later.*/
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
}

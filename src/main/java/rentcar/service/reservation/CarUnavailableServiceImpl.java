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

    @Autowired
    private AvailabilityDao carUnavailableDao;

    @Override
    public void save(Availability availability) {
        carUnavailableDao.save(availability);
    }

    @Override
    public void update(Availability availability) {

        Availability entity = carUnavailableDao.findById(availability.getAvailabilityId());
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
        carUnavailableDao.delete(id);
    }

    @Override
    public List<Availability> getAll() {
        return carUnavailableDao.checkAll();
    }

    @Override
    public List<Availability> listByCarId(Integer carId) {
        return carUnavailableDao.listByCarId(carId);
    }

    public boolean[] checkAvailability(Reservation reservation) {
        String dateFormat = "yyyy-MM-dd";
        String timeFormat = "HH:mm:ss";
        boolean[] availibility = new boolean[6]; /*startDateAvailable, endDateAvailable, startTimeAvailable, endTimeAvailable, startTimeInTime, endTimeInTime*/
        List<Availability> unavailablesList = listByCarId(reservation.getCarId());
        Calendar startDate = toCalendar(reservation.getStartDate().toString(), dateFormat);
        Calendar endDate = toCalendar(reservation.getEndDate().toString(), dateFormat);
        Calendar startTime = toCalendar(reservation.getStartTime().toString(), timeFormat);
        Calendar endTime = toCalendar(reservation.getEndTime().toString(), timeFormat);
        Calendar start = toCalendar("05:59:59", timeFormat);
        Calendar end = toCalendar("22:00:01", timeFormat);

        Calendar startTimestamp = createTimestamp(startDate, startTime);
        Calendar endTimestamp = createTimestamp(endDate, endTime);

        if (!unavailablesList.isEmpty()) {
            for (Availability car : unavailablesList) {
                if (!car.getAvailabilityId().equals(reservation.getReservationId())) {
                    Calendar startCal = createTimestamp(toCalendar(car.getStartDate().toString(), dateFormat), toCalendar(car.getStartTime().toString(), timeFormat));
                    Calendar endCal = createTimestamp(toCalendar(car.getEndDate().toString(), dateFormat), toCalendar(car.getEndTime().toString(), timeFormat));
                    endCal.add(Calendar.HOUR_OF_DAY, 3); //BUSINESS - to create a book after 3 hours car arrived.
                    availibility[0] = !startTimestamp.before(startCal) && !startTimestamp.after(endCal);
                    availibility[1] = !endTimestamp.before(startCal) && !endTimestamp.after(endCal);
                    if (availibility[0])
                        availibility[2] = !(!startTime.before(toCalendar(car.getStartTime().toString(), timeFormat)) && !startTime.after(toCalendar(car.getEndTime().toString(), timeFormat)));
                    if (availibility[1])
                        availibility[3] = !(!endTime.before(toCalendar(car.getStartTime().toString(), timeFormat)) && !endTime.after(toCalendar(car.getEndTime().toString(), timeFormat)));
                }
            }
        }
        availibility[4] = !(startTime.after(start) && startTime.before(end));
        availibility[5] = !(endTime.after(start) && endTime.before(end));
        return availibility;
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

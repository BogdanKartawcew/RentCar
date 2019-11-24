package rentcar.service.reservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rentcar.model.Availability;
import rentcar.model.Reservation;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Component("availabilityChecker")
class AvailabilityCheckerImpl implements AvailabilityChecker {

    @Autowired
    AvailabilityService availabilityService;

    private static final Logger log = LoggerFactory.getLogger(AvailabilityCheckerImpl.class.getName());

    private final String dbDateFormat = "yyyy-MM-dd";
    private final String dbTimeFormat = "HH:mm:ss";
    private final Calendar openHours = toCalendar(Time.valueOf("05:59:59"));
    private final Calendar closeHours = toCalendar(Time.valueOf("22:00:01"));
    //private final int businessReservedHours = 3; //BUSINESS - to create a book after 3 hours car arrived from previous rent.


    @Override
    public boolean[] checkAvailability(Reservation reservation) {
        /*in this method null values are NOT possible. all checks have made before enter this class.*/

        /*startTimeInTime, endTimeInTime, startDateAvailable, endDateAvailable, startTimeAvailable, endTimeAvailable*/
            /*need two arrays. correctArray is always correct.
            errorsArray - it is a working Array. and they are the same only at the beginning.*/
        boolean[] correctArray = new boolean[]{true, true, true, true, true, true};
        boolean[] errorsArray = new boolean[]{true, true, true, true, true, true};
        List<Availability> unavailablesList = new ArrayList<>();
        unavailablesList.add(0, getFictitiousRent(reservation.getCarId()));// add fictitious rent to the beginning of the list
        unavailablesList.addAll(availabilityService.listByCarId(reservation.getCarId())); //list is sorted by start date. from low to highest. it is very important for the method.

        Calendar newStartDate = toCalendar(reservation.getStartDate());
        Calendar newEndDate = toCalendar(reservation.getEndDate());
        Calendar newStartTime = toCalendar(reservation.getStartTime());
        Calendar newEndTime = toCalendar(reservation.getEndTime());

        //it's important to compare with working hours BEFORE adding businessReservedHours
        boolean isStartTimeInOpenHour = newStartTime.after(openHours) && newStartTime.before(closeHours);
        errorsArray[0] = isStartTimeInOpenHour;
        boolean isEndTimeInOpenHour = newEndTime.after(openHours) && newEndTime.before(closeHours);
        errorsArray[1] = isEndTimeInOpenHour;
        /*log.info("Profile : {}",profile);*/
        log.info("BUSINESS HOURS CHECKED");
        System.out.println("BUSINESS HOURS CHECKED");
        if (!isStartTimeInOpenHour || !isEndTimeInOpenHour) {
            return errorsArray;
        }

        if (unavailablesList.size() == 1) {
            System.out.println("LIST WAS EMPTY. THE FIRST RECORD WITH THIS CAR.");
            return correctArray;
        } else {
            //newStartTime.add(Calendar.HOUR_OF_DAY, -(businessReservedHours - 1)); //MINUS
            //newStartTime.add(Calendar.MINUTE, -(59));
            //newEndTime.add(Calendar.HOUR_OF_DAY, businessReservedHours - 1); //PLUS
            //newEndTime.add(Calendar.MINUTE, 59);
            Calendar newStartTimestamp = createTimestamp(newStartDate, newStartTime);
            Calendar newEndTimestamp = createTimestamp(newEndDate, newEndTime);

            //request can be from the update-form. so we need not to take into consideration
            System.out.println("BEFORE CHECKING FOR UPDATE REQUEST");
            for (Availability rent : unavailablesList) {
                System.out.println(rent);
            }
            for (Availability rent : unavailablesList) {
                if (rent.getAvailabilityId().equals(reservation.getReservationId())) {
                    System.out.println("THIS IS AN UPDATE REQUEST");
                    unavailablesList.remove(rent);
                    System.out.println("AFTER DELETING UPDATE");
                    break;
                }
            }
            System.out.println("AFTER CHECKING FOR UPDATE REQUEST");
            for (Availability rent : unavailablesList) {
                System.out.println(rent);
            }


            System.out.println("START CHECKS");
            for (int i = 0; i < unavailablesList.size() - 1; i++) {
                System.out.println("COMPARATION WITH i=" + i);
                if (unavailablesList.size() != 2 && i != unavailablesList.size() - 2) {
                    Availability reservtnBefore = unavailablesList.get(i);
                    System.out.println("carBefore:   " + reservtnBefore);
                    Availability reservtnAfter = unavailablesList.get(i + 1);
                    System.out.println("carAfter:    " + reservtnAfter);
                    Calendar reservtnBeforeEndDate = toCalendar(reservtnBefore.getEndDate());
                    Calendar reservtnBeforeEndTime = toCalendar(reservtnBefore.getEndTime());
                    Calendar reservtnAfterStartDate = toCalendar(reservtnAfter.getStartDate());
                    Calendar reservtnAfterStartTime = toCalendar(reservtnAfter.getStartTime());
                    Calendar beforeEndTimestamp = createTimestamp(reservtnBeforeEndDate, reservtnBeforeEndTime);
                    Calendar afterStartTimestamp = createTimestamp(reservtnAfterStartDate, reservtnAfterStartTime);

                    boolean isComparableRent = newStartDate.before(reservtnAfterStartDate);
                    if (isComparableRent) {
                        boolean startDateSame = newStartDate.equals(reservtnBeforeEndDate);
                        boolean endDateSame = newEndDate.equals(reservtnAfterStartDate);
                        if (startDateSame || endDateSame) {
                            System.out.println("SOME DATE THE SAME");
                            boolean startCorrect = newStartTimestamp.after(beforeEndTimestamp);
                            errorsArray[4] = startCorrect;
                            boolean endCorrect = newEndTimestamp.before(afterStartTimestamp);
                            errorsArray[5] = endCorrect;
                            if (!endCorrect || !startCorrect) {
                                System.out.println("===BAD HOURS===");
                                System.out.println(newStartTimestamp);
                                System.out.println(newEndTimestamp);
                                System.out.println(beforeEndTimestamp);
                                System.out.println(afterStartTimestamp);
                                return errorsArray;
                            } else {
                                return correctArray;
                            }

                        } else {
                            boolean startDateCorrect = newStartDate.after(reservtnBeforeEndDate) && newStartDate.before(reservtnAfterStartDate);
                            errorsArray[2] = startDateCorrect;
                            boolean endDateCorrect = newEndDate.before(reservtnAfterStartDate);
                            errorsArray[3] = endDateCorrect;
                            if (!startDateCorrect || !endDateCorrect) {
                                return errorsArray;
                            } else {
                                return correctArray;
                            }
                        }
                    }

                } else {
                    System.out.println("COMPARING WITH THE LAST POSITION ON THE LIST");
                    Availability cur = unavailablesList.get(unavailablesList.size() - 1);
                    Calendar curStartDate = toCalendar(cur.getStartDate());
                    Calendar curEndDate = toCalendar(cur.getEndDate());
                    Calendar curStartTime = toCalendar(cur.getStartTime());
                    Calendar curEndTime = toCalendar(cur.getEndTime());

                    boolean startDateSameStart = newStartDate.equals(curStartDate);
                    boolean startDateSameEnd = newStartDate.equals(curEndDate);
                    boolean endDateSameEnd = newEndDate.equals(curEndDate);
                    boolean endDateSameStart = newEndDate.equals(curStartDate);

                    if (startDateSameStart) {
                        System.out.println("startDateSameStart");
                        errorsArray[2] = false;
                        if (newEndDate.before(curEndDate)) {
                            errorsArray[3] = false;
                        }
                        return errorsArray;
                    }
                    if (startDateSameEnd) {
                        System.out.println("startDateSameEnd");
                        Calendar curEndTimestamp = createTimestamp(curEndDate, curEndTime);
                        errorsArray[4] = newStartTimestamp.after(curEndTimestamp);
                        return errorsArray;
                    }
                    if (endDateSameStart) {
                        System.out.println("endDateSameStart");
                        Calendar curStartTimestamp = createTimestamp(curStartDate, curStartTime);
                        errorsArray[5] = newEndTimestamp.before(curStartTimestamp);
                        return errorsArray;
                    }
                    if (endDateSameEnd) {
                        System.out.println("endDateSameEnd");
                        errorsArray[2] = false;
                        errorsArray[3] = false;
                        return errorsArray;
                    }

                    System.out.println("NO SIMILAR DATES");
                    boolean startDateAfterEnd = newStartDate.after(curEndDate);
                    boolean endDateBeforeStart = newEndDate.before(curStartDate);
                    if (endDateBeforeStart) {
                        return errorsArray;
                    } else {
                        System.out.println("END DATE AFTER START");
                        errorsArray[2] = startDateAfterEnd;
                        errorsArray[3] = !endDateBeforeStart;
                        return errorsArray;
                    }
                }
            }
        }
        return errorsArray;
    }

    private Availability getFictitiousRent(int carId) {
        Availability ghost = new Availability();
        ghost.setAvailabilityId(0);
        ghost.setCarId(carId);
        ghost.setStartDate(Date.valueOf("2010-01-01"));
        ghost.setStartTime(Time.valueOf("05:59:59"));
        ghost.setEndDate(Date.valueOf("2010-01-02"));
        ghost.setEndTime(Time.valueOf("22:00:01"));
        return ghost;
    }

    private Calendar toCalendar(Object object) {
        String format = null;
        String stringDate = object.toString();

        if (object instanceof Time) {
            format = dbTimeFormat;
        } else {
            format = dbDateFormat;
        }

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
        timestamp.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        timestamp.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        timestamp.set(Calendar.SECOND, time.get(Calendar.SECOND));
        return timestamp;
    }
}

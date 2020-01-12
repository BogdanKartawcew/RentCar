package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rentcar.controller.support.abstractcontrollers.AbstractReservationController;
import rentcar.model.Reservation;

import javax.validation.Valid;
import java.util.*;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class ReservationController extends AbstractReservationController {

    @RequestMapping(value = SUPPORT_RESERVATIONS_ALL, method = RequestMethod.GET)
    public String reservations(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("reservations", reservationService.getAll());
        model.addAttribute("checkAll", availabilityService.getAll());
        model.addAllAttributes(attributesList());
        model.addAllAttributes(attributesCRUDButtons());
        model.addAllAttributes(attributesForSupportHeader());
        return P_RESEVATION;
    }

    @RequestMapping(value = SUPPORT_RESERVATIONS_RUNNING, method = RequestMethod.GET)
    public String reservationRunning(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("running", reservationService.getRunning());
        model.addAllAttributes(attributesCRUDButtons());
        model.addAllAttributes(attributesForSupportHeader());
        return P_RESRUNNING;
    }


    @RequestMapping(value = SUPPORT_RESERVATIONS_NOTCONFIRMED, method = RequestMethod.GET)
    public String reservationNotConfirmed(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("notConfirmedReservations", reservationService.getNotConfirmed());
        model.addAllAttributes(attributesCRUDButtons());
        model.addAllAttributes(attributesForSupportHeader());
        return P_RESNOTCONFIRMED;
    }

    @RequestMapping(value = SUPPORT_RESERVATIONS_FINISHED, method = RequestMethod.GET)
    public String reservationFinished(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("finished", reservationService.getFinished());
        model.addAllAttributes(attributesCRUDButtons());
        model.addAllAttributes(attributesForSupportHeader());
        return P_RESFINISHED;
    }


    @RequestMapping(value = SUPPORT_RESERVATION_CREATE, method = RequestMethod.GET)
    public String createReservation(ModelMap model) {
        model.addAllAttributes(attributesCRUD(LOW_CREATE, false));
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATERESERVATION;
    }

    @RequestMapping(value = SUPPORT_RESERVATION_CREATE, method = RequestMethod.POST)
    public String createReservation(@Valid Reservation reservation, BindingResult result, ModelMap model) {

        String editcreate = LOW_CREATE;
        boolean[] reservationErrors = availabilityService.checkReservationOnEmpties(reservation);
        boolean hasReservationErrors = availabilityService.containsFalse(reservationErrors);
        if (hasReservationErrors) {
            System.out.println("SOME RESERVATION VALUE IS NULL");
            String res = "reservation";
            if (!reservationErrors[2]) {
                FieldError error = new FieldError(res, "startDate", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[3]) {
                FieldError error = new FieldError(res, "endDate", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[4]) {
                FieldError error = new FieldError(res, "startTime", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[5]) {
                FieldError error = new FieldError(res, "endTime", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            model.addAllAttributes(attributesCRUD(editcreate, true));
            model.addAttribute("reservation", reservation);
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATERESERVATION;
        } else {
            boolean[] availabilityErrors = availabilityService.checkAvailability(reservation); //returns array of booleans while checking is there any problem with availability
            boolean hasAvailibilityError = availabilityService.containsFalse(availabilityErrors); //checks the returned boolean array on existing of "false" values
            if (hasAvailibilityError) {
                for (int i = 0; i < availabilityErrors.length; i++) {
                    if (!availabilityErrors[i]) {
                        result.addError(getError(i, reservation));
                    }
                }
                System.out.println("ERRORS::: " + result);
                model.addAllAttributes(attributesCRUD(editcreate, true));
                model.addAttribute("reservation", reservation);
                model.addAllAttributes(attributesForSupportHeader());
                return P_CREATERESERVATION;
            }
        }

        reservationService.save(reservation);
        availabilityService.save(availabilityService.setCarUnavailble(reservation));
        model.addAllAttributes(attributesSuccess(new String[]{String.valueOf(reservation.getReservationId()), String.valueOf(reservation.getCarId())}, SUPPORT_RESERVATIONS_ALL, "success.res.crt", "but.reservations", null));
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_RESERVATION_EDIT, method = RequestMethod.GET)
    public String editReservation(@PathVariable int reservationId, ModelMap model) {
        model.addAllAttributes(attributesCRUD(LOW_EDIT, true));
        model.addAttribute(LOW_RESERVATION, reservationService.findById(reservationId));
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATERESERVATION;
    }

    //TODO - change these two metods into one common
    @RequestMapping(value = SUPPORT_RESERVATION_EDIT, method = RequestMethod.POST)
    public String editReservation(@Valid Reservation reservation, BindingResult result, ModelMap model) {
        System.out.println("RESERVATION AT THE START:" + reservation);
        String editcreate = LOW_EDIT;
        boolean[] reservationErrors = availabilityService.checkReservationOnEmpties(reservation);
        boolean hasReservationErrors = availabilityService.containsFalse(reservationErrors);
        if (hasReservationErrors) {
            System.out.println("SOME RESERVATION VALUE IS NULL");
            String res = "reservation";
            if (!reservationErrors[2]) {
                FieldError error = new FieldError(res, "startDate", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[3]) {
                FieldError error = new FieldError(res, "endDate", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[4]) {
                FieldError error = new FieldError(res, "startTime", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            if (!reservationErrors[5]) {
                FieldError error = new FieldError(res, "endTime", messageSource.getMessage("fill.in", new String[]{}, Locale.getDefault()));
                result.addError(error);
            }
            model.addAllAttributes(attributesCRUD(editcreate, true));
            model.addAttribute("reservation", reservation);
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATERESERVATION;
        } else {
            boolean[] availabilityErrors = availabilityService.checkAvailability(reservation); //returns array of booleans while checking is there any problem with availability
            boolean hasAvailibilityError = availabilityService.containsFalse(availabilityErrors); //checks the returned boolean array on existing of "false" values
            if (hasAvailibilityError) {
                for (int i = 0; i < availabilityErrors.length; i++) {
                    if (!availabilityErrors[i]) {
                        result.addError(getError(i, reservation));
                    }
                }
                System.out.println("ERRORS::: " + result);
                model.addAllAttributes(attributesCRUD(editcreate, true));
                model.addAttribute("reservation", reservation);
                model.addAllAttributes(attributesForSupportHeader());
                return P_CREATERESERVATION;
            }
        }

        reservationService.updateStatus(reservation, LOW_EDIT);
        availabilityService.update(availabilityService.setCarUnavailble(reservation));
        model.addAllAttributes(attributesSuccess(new String[]{String.valueOf(reservation.getReservationId()), String.valueOf(reservation.getCarId())}, SUPPORT_RESERVATIONS_ALL, "success.res.edt", "but.reservations", null));
        return P_SUCCESS;
    }


    //method return the error texts
    private FieldError getError(int index, Reservation reservation) {
        /*startDateAvailable, endDateAvailable, startTimeAvailable, endTimeAvailable, startTimeInTime, endTimeInTime*/
        System.out.println("GET ERRORS");
        String res = LOW_RESERVATION;
        switch (index) {
            case 0: {
                System.out.println("ERROR 0");
                return new FieldError(res, "startTime", messageSource.getMessage("non.inopenhours", new String[]{}, Locale.getDefault()));
            }
            case 1: {
                System.out.println("ERROR 1");
                return new FieldError(res, "endTime", messageSource.getMessage("non.inopenhours", new String[]{}, Locale.getDefault()));
            }
            case 2: {
                System.out.println("ERROR 2");
                return new FieldError(res, "startDate", messageSource.getMessage("non.available.startDate", new String[]{reservation.getStartDate().toString()}, Locale.getDefault()));
            }
            case 3: {
                System.out.println("ERROR 3");
                return new FieldError(res, "endDate", messageSource.getMessage("non.available.endDate", new String[]{reservation.getEndDate().toString()}, Locale.getDefault()));
            }
            case 4: {
                System.out.println("ERROR 4");
                return new FieldError(res, "startTime", messageSource.getMessage("non.available.startTime", new String[]{reservation.getStartTime().toString()}, Locale.getDefault()));
            }
            case 5: {
                System.out.println("ERROR 5");
                return new FieldError(res, "endTime", messageSource.getMessage("non.available.endTime", new String[]{reservation.getEndTime().toString()}, Locale.getDefault()));
            }
            default:
                return null;
        }
    }


    @RequestMapping(value = SUPPORT_RESERVATION_CANCEL, method = RequestMethod.GET)
    public String cancelReservation(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        reservationService.updateStatus(reservation, "cancel");
        availabilityService.delete(reservationId);
        reservationService.delete(reservationId);
        return COMMON_REDIRECT + SUPPORT_RESERVATIONS_ALL;
    }

    @RequestMapping(value = SUPPORT_RESERVATION_CONFIRM, method = RequestMethod.GET)
    public String confirmReservation(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        reservation.setConfirmed(true);
        reservationService.updateStatus(reservation, "confirm");
        return COMMON_REDIRECT + SUPPORT_RESERVATIONS_ALL;
    }


    //TODO
    /*@RequestMapping(value = SUPPORT_RESERVATION_END, method = RequestMethod.GET)
    public String endReservation(@PathVariable int reservationId, ModelMap model) {
        Reservation reservation = reservationService.findById(reservationId);
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        return P_ENDRESERVATION;
    }*/

    @RequestMapping(value = SUPPORT_RECALCULATE, method = RequestMethod.GET)
    public String recalculateStatuses() {
        for (Reservation reservation : reservationService.getAll())
            reservationService.checkStatus(reservation);
        return COMMON_REDIRECT + SUPPORT_RESERVATIONS_ALL;
    }

    @RequestMapping(value = SUPPORT_RESERVATIONHISTORY, method = RequestMethod.GET)
    public String history(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        model.addAllAttributes(universalPaginator(rowsOnPage, pageNumber, "reservationHistories"));
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAttribute("SUPPORT_RESERVATIONHISTORY_READY", SUPPORT_RESERVATIONHISTORY_READY);
        model.addAllAttributes(attributesForSupportHeader());
        return P_RESHISTORY;
    }


    @RequestMapping(value = SUPPORT_FINANCIALCALCULATIONS, method = RequestMethod.GET)
    public String financialCalculations(ModelMap model) {
        //TODO
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAllAttributes(attributesList());
        model.addAllAttributes(attributesForSupportHeader());
        return P_CALCULATIONS;
    }


}

package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rentcar.controller.support.abstractcontrollers.AbstractReservationController;
import rentcar.model.Reservation;
import rentcar.service.common.PaginatorService;
import rentcar.service.reservation.CarUnavailableService;
import rentcar.service.reservation.ReservationHistoryService;
import rentcar.service.reservation.ReservationService;
import rentcar.service.reservation.ReservationStatusService;

import javax.validation.Valid;
import java.util.*;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class ReservationController extends AbstractReservationController {
    @Autowired
    ReservationStatusService reservationStatusService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CarUnavailableService carUnavailableService;

    @Autowired
    ReservationHistoryService reservationHistoryService;

    @Autowired
    PaginatorService paginatorService;


    @RequestMapping(value = SUPPORT_RESERVATIONS_ALL, method = RequestMethod.GET)
    public String reservation(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("reservations", reservationService.getAll());
        model.addAttribute("checkAll", carUnavailableService.getAll());
        model.addAllAttributes(attributesList());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesCRUDButtons());
        return P_RESEVATION;
    }

    @RequestMapping(value = SUPPORT_RESERVATIONS_RUNNING, method = RequestMethod.GET)
    public String reservationRunning(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("running", reservationService.getRunning());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesCRUDButtons());
        return P_RESRUNNING;
    }


    @RequestMapping(value = SUPPORT_RESERVATIONS_NOTCONFIRMED, method = RequestMethod.GET)
    public String reservationNotConfirmed(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("notConfirmedReservations", reservationService.getNotConfirmed());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesCRUDButtons());
        return P_RESNOTCONFIRMED;
    }

    @RequestMapping(value = SUPPORT_RESERVATIONS_FINISHED, method = RequestMethod.GET)
    public String reservationFinished(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("finished", reservationService.getFinished());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesCRUDButtons());
        return P_RESFINISHED;
    }


    @RequestMapping(value = SUPPORT_RESERVATION_CREATE, method = RequestMethod.GET)
    public String createReservation(ModelMap model) {
        model.addAllAttributes(attributesCRUD("create", false));
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        return P_CREATERESERVATION;
    }

    @RequestMapping(value = SUPPORT_RESERVATION_CREATE, method = RequestMethod.POST)
    public String createReservation(@Valid Reservation reservation, BindingResult result, ModelMap model) {

        boolean[] errorsList = carUnavailableService.checkAvailability(reservation);
        boolean hasAvailibilityError = containsTrue(errorsList);
        if (checkAvailibilityErrors(reservation, result, model, result.hasErrors(), errorsList, hasAvailibilityError, "create")) {
            return P_CREATERESERVATION;
        }

        reservationService.save(reservation);
        carUnavailableService.save(carUnavailableService.setCarUnavailble(reservation));
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesSuccess(reservation, " has registered successfully"));
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_RESERVATION_EDIT, method = RequestMethod.GET)
    public String editReservation(@PathVariable int reservationId, ModelMap model) {
        model.addAllAttributes(attributesCRUD("edit", true));
        model.addAttribute("reservation", reservationService.findById(reservationId));
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        return P_CREATERESERVATION;
    }

    @RequestMapping(value = SUPPORT_RESERVATION_EDIT, method = RequestMethod.POST)
    public String editReservation(@Valid Reservation reservation, BindingResult result, ModelMap model, @PathVariable int reservationId) {

        boolean[] errorsList = carUnavailableService.checkAvailability(reservation);
        boolean hasAvailibilityError = containsTrue(errorsList);
        if (checkAvailibilityErrors(reservation, result, model, result.hasErrors(), errorsList, hasAvailibilityError, "edit")) {
            return P_CREATERESERVATION;
        }

        reservationService.updateStatus(reservation, "edit");
        carUnavailableService.update(carUnavailableService.setCarUnavailble(reservation));
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesSuccess(reservation, " has updated successfully"));
        return P_SUCCESS;
    }

    private boolean checkAvailibilityErrors(@Valid Reservation reservation, BindingResult result, ModelMap model, boolean hasGeneralError, boolean[] errorsList, boolean hasAvailibilityError, String editcreate) {
        if (hasAvailibilityError || hasGeneralError) {
            if (hasAvailibilityError) {
                for (int i = 0; i < errorsList.length; i++) {
                    if (errorsList[i]) result.addError(getError(i, reservation));
                }
            }
            model.addAttribute("reservation", reservation);
            model.addAllAttributes(attributesCRUD(editcreate, true));
            model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
            return true;
        }
        return false;
    }


    @RequestMapping(value = SUPPORT_RESERVATION_CANCEL, method = RequestMethod.GET)
    public String cancelReservation(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        reservationService.updateStatus(reservation, "cancel");
        carUnavailableService.delete(reservationId);
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
        long pageCount = reservationHistoryService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(SUPPORT_RESERVATIONHISTORY_READY, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("reservationHistories", reservationHistoryService.getAllByPage(pageNumber, rowsOnPage));
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAttribute("SUPPORT_RESERVATIONHISTORY_READY", SUPPORT_RESERVATIONHISTORY_READY);
        return P_RESHISTORY;
    }


    @RequestMapping(value = SUPPORT_FINANCIALCALCULATIONS, method = RequestMethod.GET)
    public String financialCalculations(ModelMap model) {
        //TODO
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAllAttributes(attributesForSupportHeader());
        model.addAllAttributes(attributesList());
        return P_CALCULATIONS;
    }



    /*=======================additional methods=============*/


    private FieldError getError(int index, Reservation reservation) {
        /*startDateAvailable, endDateAvailable, startTimeAvailable, endTimeAvailable, startTimeInTime, endTimeInTime*/
        String res = "reservation";
        switch (index) {
            case 0:
                return new FieldError(res, "startDate", messageSource.getMessage("non.available.startDate", new String[]{reservation.getStartDate().toString()}, Locale.getDefault()));
            case 1:
                return new FieldError(res, "endDate", messageSource.getMessage("non.available.endDate", new String[]{reservation.getEndDate().toString()}, Locale.getDefault()));
            case 2:
                return new FieldError(res, "startTime", messageSource.getMessage("non.available.startTime", new String[]{reservation.getStartTime().toString()}, Locale.getDefault()));
            case 3:
                return new FieldError(res, "endTime", messageSource.getMessage("non.available.endTime", new String[]{reservation.getEndTime().toString()}, Locale.getDefault()));
            case 4:
                return new FieldError(res, "startTime", messageSource.getMessage("non.inopenhours", new String[]{}, Locale.getDefault()));
            case 5:
                return new FieldError(res, "endTime", messageSource.getMessage("non.inopenhours", new String[]{}, Locale.getDefault()));
            default:
                return null;
        }
    }

    private boolean containsTrue(boolean[] errors) {
        for (boolean error : errors) {
            if (error) return true;
        }
        return false;
    }


}

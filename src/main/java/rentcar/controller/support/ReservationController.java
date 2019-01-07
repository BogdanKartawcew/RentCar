package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.Car;
import rentcar.model.Client;
import rentcar.model.Reservation;
import rentcar.service.car.CarService;
import rentcar.service.client.ClientService;
import rentcar.service.common.PaginatorService;
import rentcar.service.reservation.CarUnavailableService;
import rentcar.service.reservation.ReservationHistoryService;
import rentcar.service.reservation.ReservationService;
import rentcar.service.reservation.ReservationStatusService;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public class ReservationController extends AbstractController {

    @Autowired
    ReservationStatusService reservationStatusService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ClientService clientService;

    @Autowired
    CarService carService;

    @Autowired
    CarUnavailableService carUnavailableService;

    @Autowired
    ReservationHistoryService reservationHistoryService;

    @Autowired
    PaginatorService paginatorService;


    @RequestMapping(value = {"/support/reservation"}, method = RequestMethod.GET)
    public String reservation(ModelMap model) {
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("reservations", reservationService.getAll());
        model.addAttribute("checkAll", carUnavailableService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/reservation";
    }

    @RequestMapping(value = {"/support/reservation/running"}, method = RequestMethod.GET)
    public String reservationRunning(ModelMap model) {
        model.addAttribute("running", reservationService.getRunning());
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/reservationrunning";
    }


    @RequestMapping(value = {"/support/reservation/notconfirmed"}, method = RequestMethod.GET)
    public String reservationNotConfirmed(ModelMap model) {
        model.addAttribute("notConfirmedReservations", reservationService.getNotConfirmed());
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/reservationnotconfirmed";
    }

    @RequestMapping(value = {"/support/reservation/finished"}, method = RequestMethod.GET)
    public String reservationFinished(ModelMap model) {
        model.addAttribute("finished", reservationService.getFinished());
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/reservationfinished";
    }


    @RequestMapping(value = {"/support/reservation/createreservation"}, method = RequestMethod.GET)
    public String createReservation(ModelMap model) {
        model.addAllAttributes(attributes("create", false));
        model.addAttribute("reservation", new Reservation());
        return "support/createreservation";
    }

    @RequestMapping(value = {"/support/reservation/createreservation"}, method = RequestMethod.POST)
    public String createReservation(@Valid Reservation reservation, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAllAttributes(attributes("create", true));
            return "support/createreservation";
        }

        boolean[] errors = carUnavailableService.checkAvailability(reservation);

        if (containsTrue(errors)) {
            for (int i = 0; i < errors.length; i++) {
                if (errors[i]) result.addError(getError(i, reservation));
            }
            model.addAllAttributes(attributes("create", true));
            return "support/createreservation";
        }

        reservationService.save(reservation);
        carUnavailableService.save(carUnavailableService.setCarUnavailble(reservation));//data to dao carUnavailable

        model.addAttribute("reservationgoto", "Go to <a href=" + "/support/reservation" + ">Reservations list</a>");
        model.addAttribute("reservationsuccess", "Reservation " + reservation.getReservationId() + " for carId " + reservation.getCarId() + " has registered successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/reservation/editreservation-{reservationId}"}, method = RequestMethod.GET)
    public String editReservation(@PathVariable int reservationId, ModelMap model) {
        model.addAllAttributes(attributes("edit", true));
        model.addAttribute("reservation", reservationService.findById(reservationId));
        return "support/createreservation";
    }

    @RequestMapping(value = {"/support/reservation/editreservation-{reservationId}"}, method = RequestMethod.POST)
    public String editReservation(@Valid Reservation reservation, BindingResult result, ModelMap model, @PathVariable int reservationId) {
        if (result.hasErrors()) {
            model.addAllAttributes(attributes("edit", true));
            return "support/createreservation";
        }

        boolean[] errors = carUnavailableService.checkAvailability(reservation);
        if (containsTrue(errors)) {
            for (int i = 0; i < errors.length; i++) {
                if (errors[i]) result.addError(getError(i, reservation));
            }
            model.addAllAttributes(attributes("edit", true));
            return "support/createreservation";
        }
        reservationService.updateStatus(reservation, "edit");
        carUnavailableService.update(carUnavailableService.setCarUnavailble(reservation));
        model.addAttribute("reservationgoto", "Go to <a href=" + "/support/reservation" + ">Reservations list</a>");
        model.addAttribute("reservationsuccess", "Reservation " + reservation.getReservationId() + " for carId " + reservation.getCarId() + " has updated successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/reservation/cancelreservation-{reservationId}"}, method = RequestMethod.GET)
    public String cancelReservation(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        reservationService.updateStatus(reservation, "cancel");
        carUnavailableService.delete(reservationId);
        reservationService.delete(reservationId);
        return "redirect:/support/reservation";
    }

    @RequestMapping(value = {"/support/reservation/confirmreservation-{reservationId}"}, method = RequestMethod.GET)
    public String confirmReservation(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        reservation.setConfirmed(true);
        reservationService.updateStatus(reservation, "confirm");
        return "redirect:/support/reservation";
    }

    @RequestMapping(value = {"/support/reservation/recalculatestatuses"}, method = RequestMethod.GET)
    public String recalculateStatuses() {
        for (Reservation reservation : reservationService.getAll())
            reservationService.checkStatus(reservation);
        return "redirect:/support/reservation";
    }

    @RequestMapping(value = {"/support/reservation/reservationhistory-{pageNumber}per{rowsOnPage}"}, method = RequestMethod.GET)
    public String history(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        long pageCount = reservationHistoryService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        String link = "/support/reservation/reservationhistory-";
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(link, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("reservationHistories", reservationHistoryService.getAllByPage(pageNumber, rowsOnPage));
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/reservationhistory";
    }


    @RequestMapping(value = {"/support/reservation/financialcalculations"}, method = RequestMethod.GET)
    public String financialCalculations(ModelMap model) {
        //TODO
        model.addAttribute("statuses", reservationStatusService.getAll());
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/financialcalculations";
    }



    /*=======================additional methods=============*/


    private HashMap getCarsMap() {
        HashMap<Integer, Car> carsMap = new HashMap<>();
        for (Car car : carService.getAll()) {
            carsMap.put(car.getCarId(), car);
        }
        return carsMap;
    }

    private HashMap getClientsMap() {
        HashMap<Integer, Client> clientsMap = new HashMap<>();
        for (Client client : clientService.findAllClients()) {
            clientsMap.put(client.getClientId(), client);
        }
        return clientsMap;
    }

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

    private ModelMap attributes(String action, boolean error) {
        ModelMap model = new ModelMap();
        model.addAttribute("tomorrow", carUnavailableService.getTomorrow());
        model.addAttribute("carsMap", getCarsMap());
        model.addAttribute("clientsMap", getClientsMap());
        if (error) model.addAttribute("error", true);
        model.addAttribute(action, true);
        model.addAttribute("loggedinuser", getActiveUser());
        return model;
    }
}

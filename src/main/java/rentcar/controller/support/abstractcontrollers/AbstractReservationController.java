package rentcar.controller.support.abstractcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import rentcar.controller.AbstractController;
import rentcar.model.Car;
import rentcar.model.Client;

import java.util.HashMap;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public abstract class AbstractReservationController extends AbstractController {


    protected ModelMap attributesList() {
        ModelMap model = new ModelMap();
        model.addAttribute("SUPPORT_RESERVATION_CREATE", SUPPORT_RESERVATION_CREATE);
        model.addAttribute("SUPPORT_RESERVATIONS_NOTCONFIRMED", SUPPORT_RESERVATIONS_NOTCONFIRMED);
        model.addAttribute("SUPPORT_RESERVATIONS_RUNNING", SUPPORT_RESERVATIONS_RUNNING);
        model.addAttribute("SUPPORT_RESERVATIONS_FINISHED", SUPPORT_RESERVATIONS_FINISHED);
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAttribute("SUPPORT_FINANCIALCALCULATIONS", SUPPORT_FINANCIALCALCULATIONS);
        model.addAttribute("SUPPORT_RESERVATIONHISTORY_PAGES", SUPPORT_RESERVATIONHISTORY_PAGES);
        return model;
    }

    protected ModelMap attributesCRUDButtons() {
        ModelMap model = new ModelMap();
        model.addAttribute("SUPPORT_RESERVATION_CANCEL_READY", SUPPORT_RESERVATION_CANCEL_READY);
        model.addAttribute("SUPPORT_RESERVATION_END_READY", SUPPORT_RESERVATION_END_READY);
        model.addAttribute("SUPPORT_RESERVATION_CONFIRM_READY", SUPPORT_RESERVATION_CONFIRM_READY);
        model.addAttribute("SUPPORT_RESERVATION_EDIT_READY", SUPPORT_RESERVATION_EDIT_READY);
        model.addAttribute("SUPPORT_RECALCULATE", SUPPORT_RECALCULATE);
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL); //back to reservations
        return model;
    }

    protected ModelMap attributesCRUD(String editcreate, boolean error) {
        ModelMap model = new ModelMap();
        model.addAttribute("tomorrow", availabilityService.getTomorrow());
        model.addAttribute(LOW_CARS + MAP, getCarsMap());
        model.addAttribute(LOW_CLIENTS + MAP, getClientsMap());
        if (error) {
            model.addAttribute(LOW_ERROR, true);
        }
        model.addAttribute(editcreate, true);
        return model;
    }

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
}

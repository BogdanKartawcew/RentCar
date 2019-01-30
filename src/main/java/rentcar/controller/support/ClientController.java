package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractClientController;
import rentcar.model.Client;

import javax.validation.Valid;
import java.util.Locale;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class ClientController extends AbstractClientController {

    @RequestMapping(value = SUPPORT_CLIENTS, method = RequestMethod.GET)
    public String getClients(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        model.addAllAttributes(universalPaginator(rowsOnPage, pageNumber, "clients"));
        model.addAttribute("SUPPORT_CLIENTS_READY", SUPPORT_CLIENTS_READY);
        model.addAttribute("SUPPORT_CLIENT_CREATE", SUPPORT_CLIENT_CREATE);
        model.addAttribute("SUPPORT_CLIENT_DELETE_READY", SUPPORT_CLIENT_DELETE_READY);
        model.addAttribute("SUPPORT_CLIENT_EDIT_READY", SUPPORT_CLIENT_EDIT_READY);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CLIENTS;
    }

    @RequestMapping(value = SUPPORT_CLIENT_CREATE, method = RequestMethod.GET)
    public String createClient(ModelMap model) {
        Client client = new Client();
        model.addAllAttributes(basicAttributes(LOW_CREATE, client, LOW_CLIENT, false));
        model.addAttribute("SUPPORT_CLIENTS_PAGES", SUPPORT_CLIENTS_PAGES);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECLIENT;
    }

    @RequestMapping(value = SUPPORT_CLIENT_CREATE, method = RequestMethod.POST)
    public String createClient(@Valid Client client, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAllAttributes(basicAttributes(LOW_CREATE, client, LOW_CLIENT, true));
            return P_CREATECLIENT;
        }

        if (!clientService.isPeselUnique(client.getClientId(), client.getPesel())) {
            FieldError error = new FieldError(LOW_CLIENT, LOW_PESEL, messageSource.getMessage("non.unique.pesel", new String[]{client.getPesel()}, Locale.getDefault()));
            result.addError(error);
            model.addAllAttributes(basicAttributes(LOW_CREATE, client, LOW_CLIENT, true));
            return P_CREATECLIENT;
        }

        clientService.saveClient(client);
        model.addAllAttributes(attributesSuccess(new String[]{client.getClientFirstName(), client.getClientLastName()}, SUPPORT_CLIENTS_PAGES, "success.client.crt", "but.clients", null));
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_CLIENT_EDIT, method = RequestMethod.GET)
    public String editCLient(@PathVariable String pesel, ModelMap model) {
        Client client = clientService.findByPesel(pesel);
        model.addAllAttributes(basicAttributes(LOW_EDIT, client, LOW_CLIENT, false));
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECLIENT;
    }

    @RequestMapping(value = SUPPORT_CLIENT_EDIT, method = RequestMethod.POST)
    public String editClient(@Valid Client client, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAllAttributes(basicAttributes(LOW_EDIT, client, LOW_CLIENT, true));
            return P_CREATECLIENT;
        }
        clientService.updateClient(client);
        model.addAllAttributes(attributesSuccess(new String[]{client.getClientFirstName(), client.getClientLastName()}, SUPPORT_CLIENTS_PAGES, "success.client.upd", "but.clients", null));
        return P_SUCCESS;
    }

    @RequestMapping(value = SUPPORT_CLIENT_DELETE, method = RequestMethod.GET)
    public String deleteClient(@PathVariable String pesel) {
        clientService.deleteClientByPesel(pesel);
        return COMMON_REDIRECT + SUPPORT_CLIENTS_PAGES;
    }
}

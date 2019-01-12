package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractClientController;
import rentcar.model.Client;
import rentcar.service.client.ClientService;
import rentcar.service.common.PaginatorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Locale;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class ClientController extends AbstractClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    PaginatorService paginatorService;

    @RequestMapping(value = SUPPORT_CLIENTS, method = RequestMethod.GET)
    public String getClients(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        long pageCount = clientService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(SUPPORT_CLIENTS_READY, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("clients", clientService.getAllByPage(pageNumber, rowsOnPage));
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
        model.addAttribute("client", client);
        model.addAttribute("create", true);
        model.addAttribute("SUPPORT_CLIENTS_PAGES", SUPPORT_CLIENTS_PAGES);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECLIENT;
    }

    @RequestMapping(value = SUPPORT_CLIENT_CREATE, method = RequestMethod.POST)
    public String createClient(@Valid Client client, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("create", true);
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATECLIENT;
        }

        if (!clientService.isPeselUnique(client.getClientId(), client.getPesel())) {
            FieldError error = new FieldError("client", "pesel", messageSource.getMessage("non.unique.pesel", new String[]{client.getPesel()}, Locale.getDefault()));
            model.addAttribute("error", true);
            model.addAttribute("create", true);
            model.addAllAttributes(attributesForSupportHeader());
            result.addError(error);
            return P_CREATECLIENT;
        }

        clientService.saveClient(client);

        model.addAttribute("clientgoto", "Go to <a href=" + SUPPORT_CLIENTS_PAGES + ">Clients list</a>");
        model.addAttribute("clientsuccess", "Client " + client.getClientFirstName() + " " + client.getClientLastName() + " has registered successfully");
        model.addAllAttributes(attributesForSupportHeader());
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_CLIENT_EDIT, method = RequestMethod.GET)
    public String getClientPut(@PathVariable String pesel, ModelMap model) {
        Client client = clientService.findByPesel(pesel);
        model.addAttribute("client", client);
        model.addAttribute("edit", true);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECLIENT;
    }

    @RequestMapping(value = SUPPORT_CLIENT_EDIT, method = RequestMethod.POST)
    public String putClient(@Valid Client client, BindingResult result,
                            ModelMap model, @PathVariable String pesel) {

        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATECLIENT;
        }
        clientService.updateClient(client);
        model.addAttribute("clientgoto", "Go to <a href=" + SUPPORT_CLIENTS_PAGES + ">Clients list</a>");
        model.addAttribute("clientsuccess", "Client " + client.getClientFirstName() + " " + client.getClientLastName() + " has updated successfully");
        model.addAllAttributes(attributesForSupportHeader());
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_CLIENT_DELETE, method = RequestMethod.GET)
    public String deleteClient(@PathVariable String pesel) {
        clientService.deleteClientByPesel(pesel);
        return COMMON_REDIRECT + SUPPORT_CLIENTS_PAGES;
    }
}

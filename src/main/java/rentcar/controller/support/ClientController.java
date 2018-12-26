package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.support.Client;
import rentcar.service.client.ClientService;
import rentcar.service.common.PaginatorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public class ClientController extends AbstractController{

    @Autowired
    ClientService clientService;

    @Autowired
    PaginatorService paginatorService;

    @RequestMapping(value = {"/support/clients-{pageNumber}per{rowsOnPage}"}, method = RequestMethod.GET)
    public String getClients(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        long pageCount = clientService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        String link = "/support/reservation/clients-";
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(link, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("clients", clientService.getAllByPage(pageNumber, rowsOnPage));
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/clients";
    }

    @RequestMapping(value = {"/support/clients/createclient"}, method = RequestMethod.GET)
    public String getClientPost(ModelMap model) {
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createclient";
    }

    @RequestMapping(value = {"/support/clients/createclient"}, method = RequestMethod.POST)
    public String postClient(@Valid Client client, BindingResult result,
                             ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createclient";
        }

        if (!clientService.isPeselUnique(client.getClientId(), client.getPesel())) {
            FieldError error = new FieldError("client", "pesel", messageSource.getMessage("non.unique.pesel", new String[]{client.getPesel()}, Locale.getDefault()));
            model.addAttribute("error", true);
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            result.addError(error);
            return "support/createclient";
        }

        clientService.saveClient(client);

        model.addAttribute("clientgoto", "Go to <a href=" + "/support/clients-1per15" + ">Clients list</a>");
        model.addAttribute("clientsuccess", "Client " + client.getClientFirstName() + " " + client.getClientLastName() + " has registered successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/clients/editclient-{pesel}"}, method = RequestMethod.GET)
    public String getClientPut(@PathVariable String pesel, ModelMap model) {
        Client client = clientService.findByPesel(pesel);
        model.addAttribute("client", client);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createclient";
    }

    @RequestMapping(value = {"/support/clients/editclient-{pesel}"}, method = RequestMethod.POST)
    public String putClient(@Valid Client client, BindingResult result,
                            ModelMap model, @PathVariable String pesel) {

        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createclient";
        }
        clientService.updateClient(client);
        model.addAttribute("clientgoto", "Go to <a href=" + "/support/clients-1per15" + ">Clients list</a>");
        model.addAttribute("clientsuccess", "Client " + client.getClientFirstName() + " " + client.getClientLastName() + " has updated successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/clients/deleteclient-{pesel}"}, method = RequestMethod.GET)
    public String deleteClient(@PathVariable String pesel) {
        clientService.deleteClientByPesel(pesel);
        return "redirect:/support/clients-1per15";
    }
}

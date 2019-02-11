package rentcar.controller.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.AbstractController;

import java.util.ArrayList;
import java.util.HashMap;

import static rentcar.propertiesenums.ControlersTexts.Constants.LOW_CARS;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
@PreAuthorize("permitAll()")
public class ByCarController extends AbstractController {

    @RequestMapping(value = CUSTOMER_BYCAR, method = RequestMethod.GET)
    public String byCarPage(ModelMap model) {
        HashMap<String, String> rents = new HashMap<>();
        rents.put("2019-02-08", "from 16:00");
        //  {"date": "2019-02-08", "title": "from 16:00"}

        model.addAttribute("rents", rents);
        model.addAllAttributes(getForCustomerNavBar());
        model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
        model.addAttribute(LOW_CARS, carService.getAll());
        return P_BYCAR;
    }
}

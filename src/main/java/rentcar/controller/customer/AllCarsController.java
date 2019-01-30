package rentcar.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.AbstractController;
import rentcar.service.car.CarService;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;


@Controller
@RequestMapping(COMMON_EMPTY)
@PreAuthorize("permitAll()")
public class AllCarsController extends AbstractController {

    @Autowired
    CarService carService;

    @RequestMapping(value = CUSTOMER_ALLCARS, method = RequestMethod.GET)
    public String allCarsPage(ModelMap model) {
        model.addAllAttributes(getForCustomerNavBar());
        model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
        model.addAttribute(LOW_CARS, carService.getAll());
        return P_ALLCARS;
    }
}

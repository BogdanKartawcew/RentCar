package rentcar.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.AbstractController;
import rentcar.service.car.CarService;


@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class AllCarsController extends AbstractController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/allcars", method = RequestMethod.GET)
    public String allCarsPage(ModelMap model) {
        model.addAttribute("cars", carService.getAll());
        return "customer/allcars";
    }
}

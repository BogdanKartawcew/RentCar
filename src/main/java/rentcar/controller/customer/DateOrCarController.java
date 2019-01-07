package rentcar.controller.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.AbstractController;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class DateOrCarController extends AbstractController {

    @RequestMapping(value = "/dateorcar-{city}", method = RequestMethod.GET)
    public String dateOrCarPage(ModelMap model, @PathVariable String city) {
        return "customer/dateorcar";
    }
}

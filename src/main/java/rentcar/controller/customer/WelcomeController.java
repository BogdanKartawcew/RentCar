package rentcar.controller.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.AbstractController;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
@PreAuthorize("permitAll()")
public class WelcomeController extends AbstractController {

    @RequestMapping(value = {COMMON_EMPTY, COMMON_WELCOME}, method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
        model.addAllAttributes(getForCustomerNavBar());
        return P_WELCOME;
    }
}

package rentcar.controller.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.AbstractController;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class ByCarController extends AbstractController {

    @RequestMapping(value = "/bycar", method = RequestMethod.GET)
    public String byCarPage(ModelMap model) {
        return "customer/bycar";
    }
}

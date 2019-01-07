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
public class ByDateController extends AbstractController {

    @RequestMapping(value = "/bydate", method = RequestMethod.GET)
    public String byDatePage(ModelMap model) {
        return "customer/bydate";
    }
}

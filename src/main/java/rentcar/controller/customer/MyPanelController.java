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
public class MyPanelController extends AbstractController {

    @RequestMapping(value = {"/mypanel"}, method = RequestMethod.GET)
    public String myPanelPage(ModelMap model) {
        return "customer/mypanel";
    }
}

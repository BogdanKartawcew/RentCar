package rentcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public class LogController extends AbstractController {

    @RequestMapping(value = {"/support/log"}, method = RequestMethod.GET)
    public String history(ModelMap model) {
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/log";
    }
}

package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractLogController;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class LogController extends AbstractLogController {

    @RequestMapping(value = SUPPORT_LOG, method = RequestMethod.GET)
    public String history(ModelMap model) {
        model.addAllAttributes(attributesForSupportHeader());
        return P_LOG;
    }
}

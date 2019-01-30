package rentcar.controller.support.abstractcontrollers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rentcar.controller.AbstractController;

import static rentcar.propertiesenums.Links.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public abstract class AbstractCarController extends AbstractController {

}

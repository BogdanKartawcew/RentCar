package rentcar.controller.support.abstractcontrollers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import rentcar.controller.AbstractController;
import rentcar.model.User;


import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Links.Constants.SUPPORT_USERIMAGE_SHOW_READY;

@Controller
@RequestMapping(COMMON_EMPTY)
public abstract class AbstractUserController extends AbstractController {

    protected ModelMap attributesErrorDuringCreation(User user, String editcreate)
    {
        ModelMap model = new ModelMap();
        model.addAttribute(LOW_ERROR, true);
        model.addAllAttributes(attributesBasic(user, editcreate));
        return model;
    }

    protected ModelMap attributesBasic(User user, String editcreate)
    {
        ModelMap model = new ModelMap();
        model.addAttribute(editcreate, true);
        model.addAttribute(LOW_USER, user);
        model.addAttribute("SUPPORT_USERS_PAGES", SUPPORT_USERS_PAGES);
        return model;
    }

    protected ModelMap attributesList()
    {
        ModelMap model = new ModelMap();
        model.addAttribute("SUPPORT_USER_CREATE", SUPPORT_USER_CREATE);
        model.addAttribute("SUPPORT_USER_ACCEPT_READY", SUPPORT_USER_ACCEPT_READY);
        model.addAttribute("SUPPORT_USER_EDIT_READY", SUPPORT_USER_EDIT_READY);
        model.addAttribute("SUPPORT_USER_DELETE_READY", SUPPORT_USER_DELETE_READY);
        model.addAttribute("SUPPORT_USERS_READY", SUPPORT_USERS_READY);
        model.addAttribute("SUPPORT_USERIMAGE_SHOW_READY", SUPPORT_USERIMAGE_SHOW_READY);
        return model;
    }
}

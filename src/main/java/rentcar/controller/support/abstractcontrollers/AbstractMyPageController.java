package rentcar.controller.support.abstractcontrollers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import rentcar.controller.AbstractController;
import rentcar.model.User;
import rentcar.service.common.FileBucket;

import static rentcar.propertiesenums.ControlersTexts.Constants.LOW_USER;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Links.Constants.SUPPORT_USERIMAGE_SHOW_READY;

@Controller
@RequestMapping(COMMON_EMPTY)
public abstract class AbstractMyPageController extends AbstractController {

    protected ModelMap attributesUpdateUser(User user)
    {
        ModelMap model = new ModelMap();
        model.addAttribute(LOW_USER, user);
        model.addAttribute("SUPPORT_MYPAGE", SUPPORT_MYPAGE_IMAGE);
        return model;
    }

    protected ModelMap attributesUploadUserImage(User user, FileBucket fileBucket)
    {
        ModelMap model = new ModelMap();
        model.addAttribute(LOW_USER, user);
        model.addAttribute("fileBucket", fileBucket);
        model.addAttribute("SUPPORT_MYPAGE_DATA", SUPPORT_MYPAGE_DATA);
        model.addAttribute("SUPPORT_MAIN", SUPPORT_MAIN);
        model.addAttribute("SUPPORT_USERIMAGE_SHOW_READY", SUPPORT_USERIMAGE_SHOW_READY);
        return model;
    }
}

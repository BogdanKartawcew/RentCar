package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractAccessController;
import rentcar.model.User;

import javax.validation.Valid;
import static rentcar.model.UserProfileType.Constants.*;
import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class AccessController extends AbstractAccessController {

    @RequestMapping(value = COMMON_ACCESSREQUEST, method = RequestMethod.GET)
    public String requestUserAccess(ModelMap model) {
        User user = new User();
        model.addAttribute(LOW_USER, user);
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        return P_ACCESSFORM;
    }

    @RequestMapping(value = COMMON_ACCESSREQUEST, method = RequestMethod.POST)
    public String requestUserAccess(@Valid User user, BindingResult result, ModelMap model) {
        if (!userService.isLoginUnique(user.getId(), user.getLogin())) {
            FieldError error = new FieldError(LOW_USER, LOW_LOGIN, createText("non.unique.login", new String[]{user.getLogin()}, null));
            result.addError(error);
            model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
            return P_ACCESSFORM;
        }
        userService.save(user);
        accessService.sendMailThread(user, "mail.user.requested", "txt.user.requested");
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        model.addAllAttributes(attributesSuccess(new String[]{user.getEmail()}, SUPPORT_MYPAGE_IMAGE, "success.request.sent", "but.request.sent", null));
        return P_SUCCESS;
    }

    @RequestMapping(value = SUPPORT_USER_ACCEPT, method = RequestMethod.GET)
    public String acceptUser(@PathVariable String login) {
        User user = userService.findByLogin(login);
        user.setRoles(userProfileService.getRoleSetByType(USER));
        userService.update(user);
        accessService.sendMailThread(user, "mail.user.confirmed", "txt.user.granted");
        return COMMON_REDIRECT + SUPPORT_USERS_PAGES;
    }


    @RequestMapping(value = COMMON_RECRUITER, method = RequestMethod.GET)
    public String requestRecruiterAccess(ModelMap model) {
        User user = new User();
        model.addAttribute(LOW_USER, user);
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        return P_RECRUITER;
    }

    @RequestMapping(value = COMMON_RECRUITER, method = RequestMethod.POST)
    public String requestRecruiterAccess(@Valid User user, BindingResult result, ModelMap model) {
        accessService.createRecruiter(user);
        model.addAllAttributes(attributesSuccess(new String[]{user.getEmail()}, COMMON_LOGIN, "success.request.sent", "but.login", null));
        return P_SUCCESS;
    }

    /*@RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String resetPassword(ModelMap model) {

        System.out.println("RESET METHOD");
        return "redirect:/login?logout";
    }*/
}

package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractAccessController;
import rentcar.model.User;
import rentcar.model.UserImage;
import rentcar.service.access.AccessService;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import javax.validation.Valid;
import java.util.HashMap;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class AccessController extends AbstractAccessController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    AccessService accessService;

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value = COMMON_ACCESSREQUEST, method = RequestMethod.GET)
    public String requestUserAccess(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return P_ACCESSFORM;
    }

    @RequestMapping(value = COMMON_ACCESSREQUEST, method = RequestMethod.POST)
    public String requestUserAccess(@Valid User user, BindingResult result, ModelMap model) {

        if (!userService.isLoginUnique(user.getId(), user.getLogin())) {
            model.addAttribute("error", true);
            model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
            return COMMON_REDIRECT + COMMON_ACCESSREQUEST;
        }
        final User forThreadCopyUser = user;
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String mailSubject = "RentCar inc. - access request is under confirmation";
                String link = "mailUserAccessRequestSent.txt";
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("user", forThreadCopyUser.getFirstName());
                model.put("login", forThreadCopyUser.getLogin());
                model.put("password", forThreadCopyUser.getPassword());
                String email = forThreadCopyUser.getEmail();
                accessService.mailUser(mailSubject, link, email, model);
            }
        });
        user.setRole();
        userService.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);
        sendMailThread.start();
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        model.addAttribute("usergoto", "<a href=" + SUPPORT_MYPAGE_IMAGE + ">Visit your profile page</a>");
        model.addAttribute("usersuccess", "Request has been sent. Please check your e-mail: " + user.getEmail());
        return P_SUCCESS;
    }

    @RequestMapping(value = SUPPORT_USER_ACCEPT, method = RequestMethod.GET)
    public String acceptUser(@PathVariable String login) {
        User user = userService.findByLogin(login);
        user.setRoles(userProfileService.getRoleSet(1)); //USER_ROLE
        user.setRole();
        userService.update(user);
        final User forThreadCopyUser = user;
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String mailSubject = "RentCar inc. - access granted";
                String link = "mailUserAccessGranted.txt";
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("user", forThreadCopyUser.getFirstName());
                String email = forThreadCopyUser.getEmail();
                accessService.mailUser(mailSubject, link, email, model);
            }
        });
        sendMailThread.start();
        return COMMON_REDIRECT + SUPPORT_USERS_PAGES;
    }


    @RequestMapping(value = COMMON_RECRUITER, method = RequestMethod.GET)
    public String requestRecruiterAccess(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        return P_RECRUITER;
    }

    @RequestMapping(value = COMMON_RECRUITER, method = RequestMethod.POST)
    public String requestRecruiterAccess(@Valid User user, BindingResult result, ModelMap model) {
        accessService.createRecruiter(user);
        model.addAttribute("usergoto", "<a href=" + COMMON_EMPTY + "Log in</a>");
        model.addAttribute("usersuccess", "Request has been sent. Please check your e-mail: " + user.getEmail());
        return P_SUCCESS;
    }

    /*@RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String resetPassword(ModelMap model) {

        System.out.println("RESET METHOD");
        return "redirect:/login?logout";
    }*/
}

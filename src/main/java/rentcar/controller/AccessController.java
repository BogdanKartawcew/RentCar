package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.User;
import rentcar.model.UserImage;
import rentcar.service.access.AccessService;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AccessController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    AccessService accessService;

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value = {"/accessrequest"}, method = RequestMethod.GET)
    public String requestUserAccess(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "support/accessform";
    }

    @RequestMapping(value = {"/accessrequest"}, method = RequestMethod.POST)
    public String requestUserAccess(@Valid User user, BindingResult result, ModelMap model) {

        if (!userService.isLoginUnique(user.getId(), user.getLogin())) {
            model.addAttribute("error", true);
            return "redirect:/accessrequest";
        }
        user.setRole();
        userService.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);

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

        sendMailThread.start();
        model.addAttribute("usergoto", "<a href=\"/support/mypage\">Visit your profile page</a>");
        model.addAttribute("usersuccess", "Request has been sent. Please check your e-mail: " + user.getEmail());
        return "support/success";
    }

    @RequestMapping(value = {"/support/admin/userslist/acceptuser-{login}"}, method = RequestMethod.GET)
    public String acceptUser(@PathVariable String login) {
        User user = userService.findByLogin(login);
        user.setRoles(userProfileService.getRoleSet(1));
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
        return "redirect:/support/admin/userslist";
    }



    @RequestMapping(value = {"/recruiter"}, method = RequestMethod.GET)
    public String requestRecruiterAccess(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "support/recruiter";
    }

    @RequestMapping(value = {"/recruiter"}, method = RequestMethod.POST)
    public String requestRecruiterAccess(@Valid User user, BindingResult result, ModelMap model) {
        accessService.createRecruiter(user);
        model.addAttribute("usergoto", "<a href=\"/\">Log in</a>");
        model.addAttribute("usersuccess", "Request has been sent. Please check your e-mail: " + user.getEmail());
        return "support/success";
    }

    /*@RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String resetPassword(ModelMap model) {

        System.out.println("RESET METHOD");
        return "redirect:/login?logout";
    }*/
}

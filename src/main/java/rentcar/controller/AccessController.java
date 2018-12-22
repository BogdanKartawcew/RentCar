package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.User;
import rentcar.model.UserImage;
import rentcar.service.access.AccessService;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AccessController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    AccessService accessService;


    @RequestMapping(value = {"/accessrequest"}, method = RequestMethod.GET)
    public String requestAccess(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "support/accessform";
    }

    @RequestMapping(value = {"/accessrequest"}, method = RequestMethod.POST)
    public String requestAccess(@Valid User user, BindingResult result, ModelMap model) {

        System.out.println("USER REQUESTING: " + user);

        if (!userService.isLoginUnique(user.getId(), user.getLogin())) {
            System.out.println("ERROR2");
            model.addAttribute("error", true);
            return "redirect:/accessrequest";
        }

        user.setRole();
        userService.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);
        accessService.mailUserAccessRequestSent(user);
        model.addAttribute("usergoto", "<a href=\"/support/mypage\">Visit your profile page</a>");
        model.addAttribute("usersuccess", "Request has been sent. Please check your e-mail: " + user.getEmail());
        return "support/success";
    }
}

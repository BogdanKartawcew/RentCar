package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.Role;
import rentcar.model.User;
import rentcar.model.UserImage;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value = {"/support/admin/userslist"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/userslist";
    }

    @ModelAttribute("roles")
    public List<Role> initializeProfiles() {
        return userProfileService.getAll();
    }

    @RequestMapping(value = {"/support/admin/userslist/createuser"}, method = RequestMethod.GET)
    public String createUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("create", true);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createuser";
    }

    @RequestMapping(value = {"/support/admin/userslist/createuser"}, method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult result, ModelMap model) {

        user.setRole();
        System.out.println("USER CREATING:" + user);
        if (result.hasErrors()) {
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createuser";
        }

        if (!userService.isLoginUnique(user.getId(), user.getLogin())) {
            FieldError error = new FieldError("user", "login", messageSource.getMessage("non.unique.login", new String[]{user.getLogin()}, Locale.getDefault()));
            model.addAttribute("error", true);
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            result.addError(error);
            return "support/createuser";
        }

        userService.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);
        model.addAttribute("usergoto", "Go to <a href=\"/support/admin/userslist\">Users list</a>");
        model.addAttribute("usersuccess", "User " + user.getFirstName() + " " + user.getLastName() + " has registered successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }

    @RequestMapping(value = {"/support/admin/userslist/edituser-{login}"}, method = RequestMethod.GET)
    public String updateUser(@PathVariable String login, ModelMap model) {
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        System.out.println("USER:::::::::::::::" + user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createuser";
    }

    @RequestMapping(value = {"/support/admin/userslist/edituser-{login}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String login) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "/support/admin/userslist/edituser-{login}";
        }
        userService.update(user);
        model.addAttribute("usergoto", "Go to <a href=\"/support/admin/userslist\">Users list</a>");
        model.addAttribute("usersuccess", "User " + user.getFirstName() + " " + user.getLastName() + " has updated successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }

    @RequestMapping(value = {"/support/userimage-{login}"}, method = RequestMethod.GET)
    public String showUserImage(@PathVariable String login, HttpServletResponse response) {
        UserImage userImage = userImageService.findByLogin(login);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(userImage.getUserImage());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "support/userslist";
    }

    @RequestMapping(value = {"/support/admin/userslist/deleteuser-{login}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String login) {
        userImageService.deleteUserImageByLogin(login);
        userService.deleteByLogin(login);
        return "redirect:/support/admin/userslist";
    }
}

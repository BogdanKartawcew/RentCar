package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractUserController;
import rentcar.model.Role;
import rentcar.model.User;
import rentcar.model.UserImage;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;


@Controller
@RequestMapping(COMMON_EMPTY)
public class UserController extends AbstractUserController {

    @RequestMapping(value = SUPPORT_USERS, method = RequestMethod.GET)
    public String listUsers(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        model.addAllAttributes(universalPaginator(rowsOnPage, pageNumber, RESCONFIRMED));
        model.addAttribute(RESNOTCONFIRMED, userService.getByRole(4));
        model.addAllAttributes(attributesList());
        model.addAllAttributes(attributesForSupportHeader());
        return P_USERS;
    }

    @ModelAttribute("roles")
    public List<Role> initializeProfiles() {
        return userProfileService.getAll();
    }

    @RequestMapping(value = SUPPORT_USER_CREATE, method = RequestMethod.GET)
    public String createUser(ModelMap model) {
        User user = new User();
        List<Role> roles = userProfileService.getAll();
        model.addAttribute(LOW_ROLES, roles);
        model.addAllAttributes(attributesBasic(user, LOW_CREATE));
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATEUSER;
    }

    @RequestMapping(value = SUPPORT_USER_CREATE, method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult result, ModelMap model) {

        System.out.println("USER CREATING:" + user);

        if (checkUserForm(user, result, model, result.hasErrors(), !userService.isLoginUnique(user.getId(), user.getLogin()), LOW_CREATE)) {
            return P_CREATEUSER;
        }

        userService.save(user);
        model.addAllAttributes(attributesSuccess(new String[]{user.getFirstName(), user.getLastName()}, SUPPORT_USERS_PAGES, "success.usr.crt", "but.users", null));
        return P_SUCCESS;
    }

    @RequestMapping(value = SUPPORT_USER_EDIT, method = RequestMethod.GET)
    public String editUser(@PathVariable String login, ModelMap model) {
        User user = userService.findByLogin(login);
        model.addAllAttributes(attributesBasic(user, LOW_EDIT));
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATEUSER;
    }

    @RequestMapping(value = SUPPORT_USER_EDIT, method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result, ModelMap model) {

        if (checkUserForm(user, result, model, result.hasErrors(), !userService.isLoginUnique(user.getId(), user.getLogin()), LOW_EDIT)) {
            System.out.println(result);
            return P_CREATEUSER;
        }
        userService.update(user);
        model.addAllAttributes(attributesSuccess(new String[]{user.getFirstName(), user.getLastName()}, SUPPORT_USERS_PAGES, "success.usr.upd", "but.users", null));
        return P_SUCCESS;
    }

    private boolean checkUserForm(@Valid User user, BindingResult result, ModelMap model, boolean hasErrors, boolean isLoginUnique, String editcreate) {
        if (hasErrors || isLoginUnique) {
            if (isLoginUnique) {
                FieldError error = new FieldError(LOW_USER, LOW_LOGIN, createText("non.unique.login", new String[]{user.getLogin()}, null));
                result.addError(error);
            }
            model.addAllAttributes(attributesErrorDuringCreation(user, editcreate));
            return true;
        }
        return false;
    }

    @RequestMapping(value = SUPPORT_USERIMAGE_SHOW, method = RequestMethod.GET)
    public String showUserImage(@PathVariable String login, HttpServletResponse response) {
        UserImage userImage = userImageService.findByLogin(login);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(userImage.getUserImage());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return P_USERS;
    }

    @RequestMapping(value = SUPPORT_USER_DELETE, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String login) {
        accessService.sendMailThread(userService.findByLogin(login), "RentCar inc. - user access is removed", "mailUserAccessRemoved.txt");
        userImageService.deleteUserImageByLogin(login);
        userService.deleteByLogin(login);
        return COMMON_REDIRECT + SUPPORT_USERS_PAGES;
    }
}

package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
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
import rentcar.service.access.AccessService;
import rentcar.service.common.PaginatorService;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;


@Controller
@RequestMapping(COMMON_EMPTY)
public class UserController extends AbstractUserController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    AccessService accessService;

    @Autowired
    PaginatorService paginatorService;

    @RequestMapping(value = SUPPORT_USERS, method = RequestMethod.GET)
    public String listUsers(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        long pageCount = userService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(SUPPORT_USERS_READY, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("confirmed", userService.getConfirmedByPage(pageNumber, rowsOnPage));
        model.addAttribute("notConfirmed", userService.getByRole(4));
        model.addAllAttributes(attributesList());
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
        model.addAttribute("roles", roles);
        model.addAllAttributes(attributesBasic(user, "create"));
        return P_CREATEUSER;
    }

    @RequestMapping(value = SUPPORT_USER_CREATE, method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult result, ModelMap model) {

        user.setRole();
        System.out.println("USER CREATING:" + user);

        if (checkUserForm(user, result, model, result.hasErrors(), !userService.isLoginUnique(user.getId(), user.getLogin()), "create")) {
            return P_CREATEUSER;
        }

        userService.save(user);
        model.addAllAttributes(attributesSave(user, " has registered successfully"));
        return P_SUCCESS;
    }

    @RequestMapping(value = SUPPORT_USER_EDIT, method = RequestMethod.GET)
    public String editUser(@PathVariable String login, ModelMap model) {
        User user = userService.findByLogin(login);
        model.addAllAttributes(attributesBasic(user, "edit"));
        return P_CREATEUSER;
    }

    @RequestMapping(value = SUPPORT_USER_EDIT, method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result, ModelMap model) {

        if (checkUserForm(user, result, model, result.hasErrors(), !userService.isLoginUnique(user.getId(), user.getLogin()), "edit")) {
            return P_CREATEUSER;
        }

        userService.update(user);
        model.addAllAttributes(attributesSave(user, " has updated successfully"));
        return P_SUCCESS;
    }

    private boolean checkUserForm(@Valid User user, BindingResult result, ModelMap model, boolean hasErrors, boolean isLoginUnique, String editcreate) {
        if (hasErrors || isLoginUnique) {
            if (isLoginUnique) {
                FieldError error = new FieldError("user", "login", messageSource.getMessage("non.unique.login", new String[]{user.getLogin()}, Locale.getDefault()));
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
        final User forThreadCopyUser = userService.findByLogin(login);
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String mailSubject = "RentCar inc. - user access is removed";
                String link = "mailUserAccessRemoved.txt";
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("user", forThreadCopyUser.getFirstName());
                model.put("login", forThreadCopyUser.getLogin());
                String email = forThreadCopyUser.getEmail();
                accessService.mailUser(mailSubject, link, email, model);
            }
        });
        sendMailThread.start();
        userImageService.deleteUserImageByLogin(login);
        userService.deleteByLogin(login);
        return COMMON_REDIRECT + SUPPORT_USERS_PAGES;
    }
}

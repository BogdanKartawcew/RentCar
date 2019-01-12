package rentcar.controller.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.controller.support.abstractcontrollers.AbstractMainController;

import java.util.Collection;
import java.util.Locale;

import static rentcar.model.UserProfileType.CUSTOMER;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class MainController extends AbstractMainController {

    @RequestMapping(value = SUPPORT_MAIN, method = RequestMethod.GET)
    public String supportpage(ModelMap model) {
        model.addAttribute("COMMON_LOGOUT", COMMON_LOGOUT);
        model.addAttribute("SUPPORT_MYPAGE", SUPPORT_MYPAGE_IMAGE);
        model.addAttribute("SUPPORT_LOG", SUPPORT_LOG);
        model.addAttribute("SUPPORT_CARS_PAGES", SUPPORT_CARS_PAGES);
        model.addAttribute("SUPPORT_CLIENTS_PAGES", SUPPORT_CLIENTS_PAGES);
        model.addAttribute("SUPPORT_RESERVATIONS_ALL", SUPPORT_RESERVATIONS_ALL);
        model.addAttribute("SUPPORT_USERS_PAGES", SUPPORT_USERS_PAGES);
        model.addAttribute("COMMON_WELCOME", COMMON_WELCOME);
        model.addAttribute("loggedinuser", getActiveUserLogin());
        return P_SUPPORT;
    }

    @RequestMapping(value = COMMON_ACCESSDENIED, method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAllAttributes(attributesForSupportHeader());
        model.addAttribute("SUPPORT_MAIN", SUPPORT_MAIN);
        model.addAttribute("COMMON_LOGOUT", COMMON_LOGOUT);
        return P_ACCESSDENIED;
    }

    @RequestMapping(value = COMMON_LOGIN, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            model.addAttribute("COMMON_RECRUITER", COMMON_RECRUITER);
            model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
            model.addAttribute("COMMON_ACCESSREQUEST", COMMON_ACCESSREQUEST);
            model.addAttribute("COMMON_WELCOME", COMMON_WELCOME);
            model.addAttribute("text", messageSource.getMessage("user.access.request", new String[]{}, Locale.getDefault()));
            model.addAttribute("button", "Register");
            model.addAttribute("headertext", "Haven't got an account?");
            return P_LOGIN;
        } else {
            return P_SUPPORT;
        }
    }

    @RequestMapping(value = COMMON_LOGOUT, method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isCustomer = authorities.contains(new SimpleGrantedAuthority("ROLE_" + CUSTOMER));
            persistentTokenBasedRememberMeServices.logout(request, response, authentication);
            SecurityContextHolder.getContext().setAuthentication(null);
            if (isCustomer) {
                return COMMON_REDIRECT + COMMON_WELCOME;
            }
        }
        return COMMON_REDIRECT + COMMON_LOGOUT_REDIRECT;
    }
}
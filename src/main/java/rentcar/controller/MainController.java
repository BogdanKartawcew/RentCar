package rentcar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentcar.model.User;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainController extends AbstractController {

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = {"/support", "/"}, method = RequestMethod.GET)
    public String supportpage(ModelMap model) {
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/supportpage";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/accessdenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        model.addAttribute("text", messageSource.getMessage("user.access.request", new String[]{}, Locale.getDefault()));
        model.addAttribute("button", "Register");
        model.addAttribute("headertext", "Haven't got an account?");
        if (isCurrentAuthenticationAnonymous()) {
            return "support/login";
        } else {
            return "redirect:/support";
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@Valid User user, BindingResult result, ModelMap model) {
        System.out.println("method POST");
        return "redirect:/support";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }


    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
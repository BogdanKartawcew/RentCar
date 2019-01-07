package rentcar.controller.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainController extends AbstractController {

    @RequestMapping(value = {"/support"}, method = RequestMethod.GET)
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
        if (isCurrentAuthenticationAnonymous()) {
            model.addAttribute("text", messageSource.getMessage("user.access.request", new String[]{}, Locale.getDefault()));
            model.addAttribute("button", "Register");
            model.addAttribute("headertext", "Haven't got an account?");
            return "support/login";
        } else {
            return "support/supportpage";
        }
    }


   /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@Valid User user, BindingResult result, ModelMap model) {
        System.out.println("method POST");
        return "redirect:/support";
    }*/

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isCustomer = authorities.contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
            persistentTokenBasedRememberMeServices.logout(request, response, authentication);
            SecurityContextHolder.getContext().setAuthentication(null);
            if (isCustomer) {
                System.out.println("RETURN TO WELCOME");
                return "redirect:/welcome";
            }
        }
        return "redirect:/login?logout";
    }
}
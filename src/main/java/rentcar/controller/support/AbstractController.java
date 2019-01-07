package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public abstract class AbstractController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    protected String getActiveUser() {
        try {
            String userName = null;
            Object activeUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (activeUser instanceof UserDetails) {
                userName = ((UserDetails) activeUser).getUsername();
            } else {
                userName = activeUser.toString();
            }
            return userName;
        } catch (NullPointerException e) {
            return "JUnitTestUser";
        }
    }

    public boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}

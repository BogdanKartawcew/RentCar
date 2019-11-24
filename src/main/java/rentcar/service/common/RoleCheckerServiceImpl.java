package rentcar.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.model.UserProfileType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import static rentcar.model.UserProfileType.Constants.CUSTOMER;
import static rentcar.propertiesenums.ControlersTexts.Constants.ROLE_;

@Service("roleCheckerService")
@Transactional
public class RoleCheckerServiceImpl implements RoleCheckerService{

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Override
    public boolean hasRole (HttpServletRequest request, HttpServletResponse response, String role)
    {
        boolean isRole = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            isRole = authorities.contains(new SimpleGrantedAuthority(ROLE_ + role));
            persistentTokenBasedRememberMeServices.logout(request, response, authentication);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return isRole;
    }
}

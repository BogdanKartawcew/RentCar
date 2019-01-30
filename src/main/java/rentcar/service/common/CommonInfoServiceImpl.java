package rentcar.service.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commonInfoService")
@Transactional
public class CommonInfoServiceImpl implements CommonInfoService{
    @Override
    public String getActiveUserLogin() {
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
}

package rentcar.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rentcar.model.Role;
import rentcar.service.user.UserProfileService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Role> {

    @Autowired
    UserProfileService userProfileService;

    /*converts role in createuser.jsp from ID to TYPE*/
    public Role convert(Object element) {
        int id = Integer.parseInt((String) element);
        return userProfileService.findById(id);
    }
}
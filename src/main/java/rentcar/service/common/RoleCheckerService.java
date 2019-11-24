package rentcar.service.common;

import rentcar.model.UserProfileType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RoleCheckerService {

    boolean hasRole(HttpServletRequest request, HttpServletResponse response, String role);
}

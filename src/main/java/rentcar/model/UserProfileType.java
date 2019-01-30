package rentcar.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {

    ;

    private final String userProfileType;

    public String getUserProfileType() {
        return userProfileType;
    }

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    @Override
    public String toString() {
        return userProfileType;
    }

    public static class Constants {
        public static final String USER = "USER";
        public static final String SUPERUSER = "SUPERUSER";
        public static final String ADMIN = "ADMIN";
        public static final String TEMP = "TEMP";
        public static final String CUSTOMER = "CUSTOMER";
    }

}

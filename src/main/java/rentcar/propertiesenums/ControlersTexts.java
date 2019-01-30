package rentcar.propertiesenums;

public enum ControlersTexts {
    ;

    private final String controlersText;

    public String getPages() {
        return controlersText;
    }

    ControlersTexts(final String pages) {
        this.controlersText = pages;
    }

    @Override
    public String toString() {
        return controlersText;
    }

    public static class Constants {
        public static final String FILEBUCKET = "fileBucket";
        public static final String LOW_CAR = "car";
        public static final String LOW_CARS = "cars";
        public static final String LOW_CLIENT = "client";
        public static final String LOW_CLIENTS = "clients";
        public static final String LOW_CREATE = "create";
        public static final String LOW_EDIT = "edit";
        public static final String LOW_ERROR = "error";
        public static final String LOW_NEXT = "next";
        public static final String LOW_LOGGEDUSER = "loggedinuser";
        public static final String LOW_LOGIN = "login";
        public static final String LOW_PASSWORD = "password";
        public static final String LOW_PESEL = "pesel";
        public static final String LOW_RESERVATION = "reservation";
        public static final String LOW_ROLES = "roles";
        public static final String LOW_SUCCESS = "success";
        public static final String LOW_USER = "user";
        public static final String LOW_VIN = "vin";
        public static final String MAP = "Map";
        public static final String RESCONFIRMED = "confirmed";
        public static final String RESHISTORIES = "reservationHistories";
        public static final String RESNOTCONFIRMED = "notConfirmed";
        public static final String ROLE_ = "ROLE_";
    }
}

package rentcar.propertiesenums;

public enum Links {

    ;

    private final String links;

    Links(final String links) {
        this.links = links;
    }

    public String getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return links;
    }

    public static class Constants {

        public static final String COMMON_ACCESSDENIED = "/accessdenied";
        public static final String COMMON_ACCESSREQUEST = "/accessrequest";
        public static final String COMMON_CARIMAGE_SHOW = "/carimage-{carId}-{imagenumber}";
        public static final String COMMON_CARIMAGE_SHOW_READY = "/carimage-";
        public static final String COMMON_EMPTY = "/";
        public static final String COMMON_LOGIN = "/login";
        public static final String COMMON_LOGOUT = "/logout";
        public static final String COMMON_LOGOUT_REDIRECT = "/login?logout";
        public static final String COMMON_RECRUITER = "/recruiter";
        public static final String COMMON_REDIRECT = "redirect:";
        public static final String COMMON_WELCOME = "/welcome";
        public static final String CUSTOMER_ABOUTUS = "/aboutus";
        public static final String CUSTOMER_ALLCARS = "/allcars";
        public static final String CUSTOMER_BYCAR = "/bycar";
        public static final String CUSTOMER_BYDATE = "/bydate";
        public static final String CUSTOMER_DATEORCAR = "/dateorcar-{city}";
        public static final String CUSTOMER_MYPANEL = "/mypanel";
        public static final String SUPPORT_CAR_CREATE = "/support/cars/createcar";
        public static final String SUPPORT_CAR_DELETE = "/support/cars/deletecar-{carId}";
        public static final String SUPPORT_CAR_DELETE_READY = "/support/cars/deletecar-";
        public static final String SUPPORT_CAR_EDIT = "/support/cars/editcar-{carId}";
        public static final String SUPPORT_CAR_EDIT_READY = "/support/cars/editcar-";
        public static final String SUPPORT_CARIMAGE_UPLOAD = "/support/cars/uploadcarimage-{carId}";
        public static final String SUPPORT_CARIMAGE_UPLOAD_READY = "/support/cars/uploadcarimage-";
        public static final String SUPPORT_CARS = "/support/cars-{pageNumber}per{rowsOnPage}";
        public static final String SUPPORT_CARS_PAGES = "/support/cars-1per15";
        public static final String SUPPORT_CARS_READY = "/support/cars-";
        public static final String SUPPORT_CLIENT_CREATE = "/support/clients/createclient";
        public static final String SUPPORT_CLIENT_DELETE = "/support/clients/deleteclient-{pesel}";
        public static final String SUPPORT_CLIENT_DELETE_READY = "/support/clients/deleteclient-";
        public static final String SUPPORT_CLIENT_EDIT = "/support/clients/editclient-{pesel}";
        public static final String SUPPORT_CLIENT_EDIT_READY = "/support/clients/editclient-";
        public static final String SUPPORT_CLIENTS = "/support/clients-{pageNumber}per{rowsOnPage}";
        public static final String SUPPORT_CLIENTS_PAGES = "/support/clients-1per15";
        public static final String SUPPORT_CLIENTS_READY = "/support/clients-";
        public static final String SUPPORT_FINANCIALCALCULATIONS = "/support/reservation/financialcalculations";
        public static final String SUPPORT_ADMIN_GHOST = "/support/admin";
        public static final String SUPPORT_LOG = "/support/log";
        public static final String SUPPORT_MAIN = "/support";
        public static final String SUPPORT_MYPAGE_DATA = "/support/mypage/data";
        public static final String SUPPORT_MYPAGE_IMAGE = "/support/mypage";
        public static final String SUPPORT_RECALCULATE = "/support/reservation/recalculatestatuses";
        public static final String SUPPORT_RESERVATION_CANCEL = "/support/reservation/cancelreservation-{reservationId}";
        public static final String SUPPORT_RESERVATION_CANCEL_READY = "/support/reservation/cancelreservation-";
        public static final String SUPPORT_RESERVATION_CONFIRM = "/support/reservation/confirmreservation-{reservationId}";
        public static final String SUPPORT_RESERVATION_CONFIRM_READY = "/support/reservation/confirmreservation-";
        public static final String SUPPORT_RESERVATION_CREATE = "/support/reservation/createreservation";
        public static final String SUPPORT_RESERVATION_EDIT = "/support/reservation/editreservation-{reservationId}";
        public static final String SUPPORT_RESERVATION_EDIT_READY = "/support/reservation/editreservation-";
        public static final String SUPPORT_RESERVATION_END = "/support/reservation/editreservation-{reservationId}";
        public static final String SUPPORT_RESERVATION_END_READY = "/support/reservation/editreservation-";
        public static final String SUPPORT_RESERVATIONHISTORY = "/support/reservation/reservationhistory-{pageNumber}per{rowsOnPage}";
        public static final String SUPPORT_RESERVATIONHISTORY_PAGES = "/support/reservation/reservationhistory-1per15";
        public static final String SUPPORT_RESERVATIONHISTORY_READY = "/support/reservation/reservationhistory-";
        public static final String SUPPORT_RESERVATIONS_ALL = "/support/reservation";
        public static final String SUPPORT_RESERVATIONS_FINISHED = "/support/reservation/finished";
        public static final String SUPPORT_RESERVATIONS_NOTCONFIRMED = "/support/reservation/notconfirmed";
        public static final String SUPPORT_RESERVATIONS_RUNNING = "/support/reservation/running";
        public static final String SUPPORT_USER_ACCEPT = "/support/admin/userslist/acceptuser-{login}";
        public static final String SUPPORT_USER_ACCEPT_READY = "/support/admin/userslist/acceptuser-";
        public static final String SUPPORT_USER_CREATE = "/support/admin/userslist/createuser";
        public static final String SUPPORT_USER_DELETE = "/support/admin/userslist/deleteuser-{login}";
        public static final String SUPPORT_USER_DELETE_READY = "/support/admin/userslist/deleteuser-";
        public static final String SUPPORT_USER_EDIT = "/support/admin/userslist/edituser-{login}";
        public static final String SUPPORT_USER_EDIT_READY = "/support/admin/userslist/edituser-";
        public static final String SUPPORT_USERIMAGE_SHOW = "/support/userimage-{login}";
        public static final String SUPPORT_USERIMAGE_SHOW_READY = "/support/userimage-";
        public static final String SUPPORT_USERS = "/support/admin/userslist-{pageNumber}per{rowsOnPage}";
        public static final String SUPPORT_USERS_PAGES = "/support/admin/userslist-1per15";
        public static final String SUPPORT_USERS_READY = "/support/admin/userslist-";

    }
}

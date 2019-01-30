package rentcar.propertiesenums;

public enum Pages {
    ;

    private final String pages;

    public String getPages() {
        return pages;
    }

    Pages(final String pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return pages;
    }

    public static class Constants {

        public static final String P_ABOUTUS = "customer/aboutus";
        public static final String P_ACCESSDENIED = "support/accessdenied";
        public static final String P_ACCESSFORM = "support/accessform";
        public static final String P_ALLCARS = "customer/allcars";
        public static final String P_BYCAR = "customer/bycar";
        public static final String P_BYDATE = "customer/bydate";
        public static final String P_CALCULATIONS = "support/financialcalculations";
        public static final String P_CARIMAGEUPDATE = "support/carimageupdate";
        public static final String P_CARS = "support/cars";
        public static final String P_CLIENTS = "support/clients";
        public static final String P_CREATECAR = "support/createcar";
        public static final String P_CREATECLIENT = "support/createclient";
        public static final String P_CREATERESERVATION = "support/createreservation";
        public static final String P_CREATEUSER = "support/createuser";
        public static final String P_DATEORCAR = "customer/dateorcar";
        public static final String P_ENDRESERVATION = "support/endreservation";
        public static final String P_LOG = "support/log";
        public static final String P_LOGIN = "support/login";
        public static final String P_MYPAGEDATA = "support/mypage-data";
        public static final String P_MYPAGEIMAGE = "support/mypage-image";
        public static final String P_MYPANEL = "customer/mypanel";
        public static final String P_RECRUITER = "support/recruiter";
        public static final String P_RESEVATION = "support/reservation";
        public static final String P_RESFINISHED = "support/reservationfinished";
        public static final String P_RESHISTORY = "support/reservationhistory";
        public static final String P_RESNOTCONFIRMED = "support/reservationnotconfirmed";
        public static final String P_RESRUNNING = "support/reservationrunning";
        public static final String P_SUCCESS = "support/success";
        public static final String P_SUPPORT = "support/supportpage";
        public static final String P_USERS = "support/userslist";
        public static final String P_WELCOME = "customer/welcome";
    }
}

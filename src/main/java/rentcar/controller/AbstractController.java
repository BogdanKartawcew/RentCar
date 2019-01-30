package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import rentcar.service.access.AccessService;
import rentcar.service.car.CarImageService;
import rentcar.service.car.CarService;
import rentcar.service.client.ClientService;
import rentcar.service.common.CommonInfoService;
import rentcar.service.common.PaginatorService;
import rentcar.service.reservation.AvailabilityService;
import rentcar.service.reservation.ReservationHistoryService;
import rentcar.service.reservation.ReservationService;
import rentcar.service.reservation.ReservationStatusService;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import java.util.ArrayList;
import java.util.Locale;


import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public abstract class AbstractController {

    @Autowired
    protected AccessService accessService;

    @Autowired
    protected ReservationStatusService reservationStatusService;

    @Autowired
    protected ReservationService reservationService;

    @Autowired
    protected AvailabilityService availabilityService;

    @Autowired
    protected UserProfileService userProfileService;

    @Autowired
    protected UserImageService userImageService;

    @Autowired
    protected CarImageService carImageService;

    @Autowired
    protected CarService carService;

    @Autowired
    protected UserService userService;

    @Autowired
    ReservationHistoryService reservationHistoryService;

    @Autowired
    protected ClientService clientService;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected PaginatorService paginatorService;

    @Autowired
    protected PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private CommonInfoService commonInfoService;

    protected String getActiveUserLogin() {
        return commonInfoService.getActiveUserLogin();
    }

    protected boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    protected ModelMap getForCustomerNavBar() {
        ModelMap model = new ModelMap();
        model.addAttribute("COMMON_LOGIN", COMMON_LOGIN);
        model.addAttribute("COMMON_WELCOME", COMMON_WELCOME);
        model.addAttribute("CUSTOMER_ALLCARS", CUSTOMER_ALLCARS);
        model.addAttribute("CUSTOMER_ABOUTUS", CUSTOMER_ABOUTUS);
        model.addAttribute("CUSTOMER_MYPANEL", CUSTOMER_MYPANEL);
        model.addAttribute("SUPPORT_MAIN", SUPPORT_MAIN);
        model.addAttribute("COMMON_LOGOUT", COMMON_LOGOUT);
        return model;
    }

    protected ModelMap basicAttributes(String action, Object obj, String objName, boolean error) {
        ModelMap model = new ModelMap();
        model.addAttribute(objName, obj);
        model.addAttribute(action, true);
        if (error) {
            model.addAttribute(LOW_ERROR, true);
        }
        return model;
    }

    protected ModelMap attributesForSupportHeader() {
        ModelMap model = new ModelMap();
        model.addAttribute("SUPPORT_MAIN", SUPPORT_MAIN);
        model.addAttribute("COMMON_LOGOUT", COMMON_LOGOUT);
        model.addAttribute(LOW_LOGGEDUSER, getActiveUserLogin());
        return model;
    }

    protected ModelMap attributesSuccess(String[] args, String link, String message, String buttonName, String locale) {
        ModelMap model = new ModelMap();
        model.addAttribute(LOW_NEXT, createButtonLinkJSP("but.next", link, buttonName, locale));
        model.addAttribute(LOW_SUCCESS, createText(message, args, locale));
        model.addAllAttributes(attributesForSupportHeader());
        return model;
    }

    protected String createButtonLinkJSP(String buttonName, String link, String linkName, String localeId) {
        return createText(buttonName, null, localeId) + "<a href=" + link + ">" +
                createText(linkName, null, localeId) + "</a>";
    }

    protected String createText(String properties, String[] args, String localeId) {
        Locale locale;
        if (localeId != null) {
            locale = Locale.forLanguageTag(localeId);
        } else {
            locale = Locale.getDefault();
        }
        return messageSource.getMessage(properties, args, locale);
    }


    protected ModelMap universalPaginator(int rowsOnPage, int pageNumber, String mainAttribute) {
        ModelMap model = new ModelMap();
        String link = null;
        long pageCount = 0;

        switch (mainAttribute) {
            case LOW_CARS: {
                pageCount = carService.countAllByPage();
                model.addAttribute(mainAttribute, carService.getAllByPage(pageNumber, rowsOnPage));
                link = SUPPORT_CARS_READY;
                break;
            }
            case LOW_CLIENTS: {
                pageCount = clientService.countAllByPage();
                model.addAttribute(mainAttribute, clientService.getAllByPage(pageNumber, rowsOnPage));
                link = SUPPORT_CLIENTS_READY;
                break;
            }
            case RESHISTORIES: {
                pageCount = reservationHistoryService.countAllByPage();
                model.addAttribute(mainAttribute, reservationHistoryService.getAllByPage(pageNumber, rowsOnPage));
                link = SUPPORT_RESERVATIONHISTORY_READY;
                break;
            }
            case RESCONFIRMED: {
                pageCount = userService.countAllByPage();
                model.addAttribute(mainAttribute, userService.getAllByPage(pageNumber, rowsOnPage));
                link = SUPPORT_USERS_READY;
                break;
            }
            default:
                System.out.println("Unexpected mainAttribute value");
        }

        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(link, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        return model;
    }





    /*For future use: it's a good implementation but works only with direct class's calls, while we have here only inteface-service implemantation.*/
    /*protected ModelMap universalPaginator(int rowsOnPage, int pageNumber, String mainAttribute) {
        ModelMap model = new ModelMap();
        String link = null;
        Class actClass = null;

        switch (mainAttribute) {
            case "cars": {
                actClass = CarServiceImpl.class;
                link = SUPPORT_CARS_READY;
                break;
            }
            case "clients": {
                actClass = ClientServiceImpl.class;
                link = SUPPORT_CLIENTS_READY;
                break;
            }
            case "reservationHistories": {
                actClass = ReservationHistoryServiceImpl.class;
                link = SUPPORT_RESERVATIONHISTORY_READY;
                break;
            }
            case "confirmed": {
                actClass = UserService.class;
                link = SUPPORT_USERS_READY;
                break;
            }
            default:
                System.out.println("Unexpected mainAttribute value");
        }


        model.addAttribute(mainAttribute, (List) abstractMethod(actClass, "getAllByPage", new Class[]{Integer.class, Integer.class}, new Integer[]{pageNumber, rowsOnPage}));
        long pageCount = (long) abstractMethod(actClass, "countAllByPage", null, null);
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(link, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        System.out.println("============THERE");
        return model;
    }

    protected Object abstractMethod(Class actClass, String aMethod, Class[] params, Object[] args) {
        System.out.println("START");
        try {
            Method method = actClass.getDeclaredMethod(aMethod, params);
            System.out.println("method: " + method);
            System.out.println("params: " + Arrays.toString(params));
            System.out.println("args: " + Arrays.toString(args));
            Object obj = actClass.newInstance();
            System.out.println("obj: " + obj);
            Object result = null;
            if (args != null) {
                System.out.println("args not null");
                System.out.println(method.invoke(obj, args));
                result = method.invoke(obj, args);
            } else {
                System.out.println("args null");
                System.out.println(method.invoke(obj));
                result = method.invoke(obj);
            }
            return result;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("ERROR IN ABSTRACT METHOD - PAGINATOR");
            e.printStackTrace();
        }
        return null;
    }*/
}

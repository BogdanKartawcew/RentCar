package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rentcar.controller.support.abstractcontrollers.AbstractCarController;
import rentcar.model.Car;
import rentcar.model.CarImage;
import rentcar.service.common.FileBucket;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class CarController extends AbstractCarController {

    @RequestMapping(value = SUPPORT_CARS, method = RequestMethod.GET)
    public String cars(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        model.addAllAttributes(universalPaginator(rowsOnPage, pageNumber, LOW_CARS));
        model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
        model.addAttribute("SUPPORT_CARS_READY", SUPPORT_CARS_READY);
        model.addAttribute("SUPPORT_CAR_CREATE", SUPPORT_CAR_CREATE);
        model.addAttribute("SUPPORT_CARIMAGE_UPLOAD_READY", SUPPORT_CARIMAGE_UPLOAD_READY);
        model.addAttribute("SUPPORT_CAR_DELETE_READY", SUPPORT_CAR_DELETE_READY);
        model.addAttribute("SUPPORT_CAR_EDIT_READY", SUPPORT_CAR_EDIT_READY);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CARS;
    }

    @RequestMapping(value = SUPPORT_CAR_CREATE, method = RequestMethod.GET)
    public String createCar(ModelMap model) {
        Car car = new Car();
        model.addAllAttributes(basicAttributes(LOW_CREATE, car, LOW_CAR, false));
        model.addAttribute("SUPPORT_CARS_PAGES", SUPPORT_CARS_PAGES);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECAR;
    }

    @RequestMapping(value = SUPPORT_CAR_CREATE, method = RequestMethod.POST)
    public String createCar(@Valid Car car, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAllAttributes(basicAttributes(LOW_CREATE, car, LOW_CAR, true));
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATECAR;
        }

        if (!carService.isVinUnique(car.getCarId(), car.getVin())) {
            FieldError error = new FieldError(LOW_CAR, LOW_VIN, messageSource.getMessage("non.unique.vin", new String[]{car.getVin()}, Locale.getDefault()));
            result.addError(error);
            model.addAllAttributes(basicAttributes(LOW_CREATE, car, LOW_CAR, true));
            model.addAllAttributes(attributesForSupportHeader());
            return P_CREATECAR;
        }
        carService.save(car);
        model.addAllAttributes(attributesSuccess(new String[]{car.getCarBrand(), car.getCarModel()}, SUPPORT_CARS_PAGES, "success.car.crt", "but.cars", null));
        return P_SUCCESS;
    }


    @RequestMapping(value = COMMON_CARIMAGE_SHOW, method = RequestMethod.GET)
    public String showCarImage(@PathVariable int carId, @PathVariable int imagenumber, HttpServletResponse response, ModelMap model) {
        CarImage carImage = carImageService.find(carId, imagenumber);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(carImage.getCarImage());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
        return P_CARS;
    }


    @RequestMapping(value = SUPPORT_CAR_EDIT, method = RequestMethod.GET)
    public String editCar(@PathVariable int carId, ModelMap model) {
        Car car = carService.findById(carId);
        model.addAllAttributes(basicAttributes(LOW_EDIT, car, LOW_CAR, false));
        model.addAllAttributes(attributesForSupportHeader());
        return P_CREATECAR;
    }

    @RequestMapping(value = SUPPORT_CAR_EDIT, method = RequestMethod.POST)
    public String editCar(@Valid Car car, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAllAttributes(basicAttributes(LOW_EDIT, car, LOW_CAR, true));
            return P_CREATECAR;
        }
        carService.update(car);
        model.addAllAttributes(attributesSuccess(new String[]{car.getCarBrand(), car.getCarModel()}, SUPPORT_CARS_PAGES, "success.car.upd", "but.cars", null));
        return P_SUCCESS;
    }


    @RequestMapping(value = SUPPORT_CARIMAGE_UPLOAD, method = RequestMethod.GET)
    public String uploadCarImage(@PathVariable int carId, ModelMap model) {
        model.clear();
        FileBucket fileModel = new FileBucket();
        Car car = carService.findById(carId);
        model.addAttribute(LOW_CAR, car);
        model.addAttribute(FILEBUCKET, fileModel);
        model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
        model.addAllAttributes(attributesForSupportHeader());
        return P_CARIMAGEUPDATE;
    }


    @RequestMapping(value = SUPPORT_CARIMAGE_UPLOAD, method = RequestMethod.POST)
    public String uploadCarImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int carId, @ModelAttribute("imagenumber") int imagenumber) {
        model.clear();
        if (result.hasErrors()) {
            return uploadCarImage(carId, model);
        } else {
            carImageService.update(fileBucket, carId, imagenumber);
            model.addAttribute("COMMON_CARIMAGE_READY", COMMON_CARIMAGE_SHOW_READY);
            return COMMON_REDIRECT + SUPPORT_CARIMAGE_UPLOAD_READY + carId;
        }
    }

    @RequestMapping(value = SUPPORT_CAR_DELETE, method = RequestMethod.GET)
    public String deleteCar(@PathVariable int carId) {
        carService.delete(carId);
        return COMMON_REDIRECT + SUPPORT_CARS_PAGES;
    }
}
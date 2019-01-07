package rentcar.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rentcar.model.Car;
import rentcar.model.CarImage;
import rentcar.service.common.FileBucket;
import rentcar.service.car.CarImageService;
import rentcar.service.car.CarService;
import rentcar.service.common.PaginatorService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public class CarController extends AbstractController {

    @Autowired
    CarService carService;

    @Autowired
    CarImageService carImageService;

    @Autowired
    PaginatorService paginatorService;


    @RequestMapping(value = {"/support/cars-{pageNumber}per{rowsOnPage}"}, method = RequestMethod.GET)
    public String cars(@PathVariable int pageNumber, @PathVariable int rowsOnPage, ModelMap model) {
        long pageCount = carService.countAllByPage();
        int pagesAmount = paginatorService.pagesAmountCounter(pageCount, rowsOnPage);
        String link = "/support/cars-";
        ArrayList<String> paginatorTags = paginatorService.getPaginatorTags(link, rowsOnPage, pagesAmount, pageNumber);
        model.addAttribute("pagesAmount", pagesAmount);
        model.addAttribute("paginatorTags", paginatorTags);
        model.addAttribute("cars", carService.getAllByPage(pageNumber, rowsOnPage));
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/cars";
    }

    @RequestMapping(value = {"/support/cars/createcar"}, method = RequestMethod.GET)
    public String newCar(ModelMap model) {
        Car car = new Car();
        model.addAttribute("car", car);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createcar";
    }

    @RequestMapping(value = {"/support/cars/createcar"}, method = RequestMethod.POST)
    public String createCar(@Valid Car car, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createcar";
        }

        if (!carService.isVinUnique(car.getCarId(), car.getVin())) {
            FieldError error = new FieldError("car", "vin", messageSource.getMessage("non.unique.vin", new String[]{car.getVin()}, Locale.getDefault()));
            model.addAttribute("error", true);
            model.addAttribute("create", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createcar";
        }
        carService.save(car);

        model.addAttribute("cargoto", "Go to <a href=" + "/support/cars-1per15" + ">Cars list</a>");
        model.addAttribute("carsuccess", "Car " + car.getCarBrand() + " " + car.getCarModel() + " has registered successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/carimage-{carId}-{imagenumber}"}, method = RequestMethod.GET)
    public String showCarImage(@PathVariable int carId, @PathVariable int imagenumber, HttpServletResponse response) {
        CarImage carImage = carImageService.find(carId, imagenumber);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(carImage.getCarImage());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "support/cars";
    }


    @RequestMapping(value = {"/support/cars/editcar-{carId}"}, method = RequestMethod.GET)
    public String editCar(@PathVariable int carId, ModelMap model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createcar";
    }

    @RequestMapping(value = {"/support/cars/editcar-{carId}"}, method = RequestMethod.POST)
    public String editCar(@Valid Car car, BindingResult result, ModelMap model, @PathVariable int carId) {

        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createcar";
        }
        carService.update(car);
        model.addAttribute("cargoto", "Go to <a href=" + "/support/cars-1per15" + ">Cars list</a>");
        model.addAttribute("carsuccess", "Car " + car.getCarBrand() + " " + car.getCarModel() + " has updated successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/cars/uploadcarimage-{carId}"}, method = RequestMethod.GET)
    public String uploadCarImage(@PathVariable int carId, ModelMap model) {
        model.clear();
        FileBucket fileModel = new FileBucket();
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/carimageupdate";
    }


    @RequestMapping(value = {"/support/cars/uploadcarimage-{carId}"}, method = RequestMethod.POST)
    public String uploadCarImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int carId, @ModelAttribute("imagenumber") int imagenumber) {
        model.clear();
        if (result.hasErrors()) {
            return uploadCarImage(carId, model);
        } else {
            carImageService.update(fileBucket, carId, imagenumber);
            return "redirect:/support/cars/uploadcarimage-" + carId;
        }
    }

    @RequestMapping(value = {"/support/cars/deletecar-{carId}-{imagenumber}"}, method = RequestMethod.GET)
    public String deleteCar(@PathVariable int carId, @PathVariable int imagenumber) {
        carImageService.delete(carId, imagenumber);
        carService.delete(carId);
        return "redirect:/support/cars-1per15";
    }
}
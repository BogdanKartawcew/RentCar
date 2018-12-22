package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rentcar.model.Car;
import rentcar.model.CarImage;
import rentcar.model.FileBucket;
import rentcar.service.car.CarImageService;
import rentcar.service.car.CarService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
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


    @RequestMapping(value = {"/support/cars"}, method = RequestMethod.GET)
    public String cars(ModelMap model) {
        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
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
        CarImage carImage = new CarImage();
        carImage.setVin(car.getVin());
        carImage.setCarImage(null);
        carImageService.saveCarImage(carImage);
        carService.saveCar(car);

        model.addAttribute("cargoto", "Go to <a href=" + "/support/cars" + ">Cars list</a>");
        model.addAttribute("carsuccess", "Car " + car.getCarBrand() + " " + car.getCarModel() + " has registered successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/cars/carimage-{vin}"}, method = RequestMethod.GET)
    public String showCarImage(@PathVariable String vin, HttpServletResponse response) {
        CarImage carImage = carImageService.findByVin(vin);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(carImage.getCarImage());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "support/cars";
    }


    @RequestMapping(value = {"/support/cars/editcar-{vin}"}, method = RequestMethod.GET)
    public String editCar(@PathVariable String vin, ModelMap model) {
        Car car = carService.findByVin(vin);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/createcar";
    }

    @RequestMapping(value = {"/support/cars/editcar-{vin}"}, method = RequestMethod.POST)
    public String editCar(@Valid Car car, BindingResult result, ModelMap model, @PathVariable String vin) {

        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/createcar";
        }
        carService.updateCar(car);
        model.addAttribute("cargoto", "Go to <a href=" + "/support/cars" + ">Cars list</a>");
        model.addAttribute("carsuccess", "Car " + car.getCarBrand() + " " + car.getCarModel() + " has updated successfully");
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/success";
    }


    @RequestMapping(value = {"/support/cars/uploadcarimage-{vin}"}, method = RequestMethod.GET)
    public String uploadCarImage(@PathVariable String vin, ModelMap model) {
        FileBucket fileModel = new FileBucket();
        Car car = carService.findByVin(vin);
        model.addAttribute("car", car);
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/carimageupdate";
    }


    @RequestMapping(value = {"/support/cars/uploadcarimage-{vin}"}, method = RequestMethod.POST)
    public String uploadCarImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable String vin) throws IOException {

        if (result.hasErrors()) {
            /*FileBucket fileModel = new FileBucket();
            Car car = carService.findByVin(vin);
            model.addAttribute("car", car);
            model.addAttribute("fileBucket", fileModel);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/carimageupdate";*/
            return uploadCarImage(vin, model);
        } else {
            Car car = carService.findByVin(vin);
            CarImage carImage = new CarImage();
            MultipartFile multipartFile = fileBucket.getFile();

            try {
                carImage.setVin(vin);
                carImage.setCarImage(multipartFile.getBytes());
                carImageService.updateCarImage(carImage);
            } catch (IOException e) {
            }
            model.addAttribute("carimagegoto", "Go to <a href=" + "/support/cars" + ">Cars list</a>");
            model.addAttribute("carsuccess", "Image to car " + car.getCarBrand() + " " + car.getCarModel() + " has been updated successfully");
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/success";
        }
    }

    @RequestMapping(value = {"/support/cars/deletecar-{vin}"}, method = RequestMethod.GET)
    public String deleteCar(@PathVariable String vin) {
        carImageService.deleteCarImageByVin(vin);
        carService.deleteCarByVin(vin);
        return "redirect:/support/cars";
    }
}
package rentcar.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CAR", uniqueConstraints = {@UniqueConstraint(columnNames = "CARID")})
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARID", unique = true, nullable = false)
    private Integer carId;

    @NotBlank(message = "Please fill in the brand")
    @Column(name = "BRAND")
    private String carBrand;

    @NotBlank(message = "Please fill in the model")
    @Column(name = "MODEL")
    private String carModel;

    @NotBlank(message = "Please choose the fuel")
    @Column(name = "FUEL")
    private String carFuel;

    @NotBlank(message = "Please choose gear type.")
    @Column(name = "GEAR_TYPE", nullable = false)
    private String carGearType;

    @NotNull(message = "Please fill in the power in PS (horsepower)")
    @Min(value = 40, message = "The power of '${validatedValue}' PS is too low. Are you sure?")
    @Column(name = "HORSE_POWER")
    private int carHorsePower;

    @NotBlank(message = "Please choose body type")
    @Column(name = "BODY_TYPE")
    private String carBodyType;

    @Min(value = 2, message = "Are you sure that vehicle has '${validatedValue}' door?")
    @Column(name = "DOORS_NUM", nullable = false)
    private int carDoorsNum;

    @Max(value = 9, message = "Are you sure that vehicle has '${validatedValue}' seats?")
    @Column(name = "SEATS_NUM", nullable = false)
    private int carSeatsNum;

    @Column(name = "AIR_COND")
    private boolean carAirCond;

    @Column(name = "NAVIGATION")
    private boolean carNavigation;

    /*================================= admin information ==============================*/

    @Pattern(regexp = "[A-Z0-9^IOQioq_]{11}\\d{6}", message = "It is not a VIN. Please check again")
    @Column(name = "VIN", unique = true, nullable = false)
    private String vin;

    @Column(name = "REG_NUM")
    private String regNum;

    @Max(value = 2025, message = "Are you sure that the vehicle is from ${0} carYear?")
    @Min(value = 2011, message = "Are you sure that the vehicle is from ${0} carYear?")
    @Column(name = "YEAR")
    private int carYear;

    @Column(name = "MILEAGE", nullable = false)
    private int carMileage;

    @Column(name = "VERSION")
    private String carVersion;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "CITY")
    private String city;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public String getCarGearType() {
        return carGearType;
    }

    public void setCarGearType(String carGearType) {
        this.carGearType = carGearType;
    }

    public int getCarHorsePower() {
        return carHorsePower;
    }

    public void setCarHorsePower(int carHorsePower) {
        this.carHorsePower = carHorsePower;
    }

    public String getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        this.carBodyType = carBodyType;
    }

    public int getCarDoorsNum() {
        return carDoorsNum;
    }

    public void setCarDoorsNum(int carDoorsNum) {
        this.carDoorsNum = carDoorsNum;
    }

    public int getCarSeatsNum() {
        return carSeatsNum;
    }

    public void setCarSeatsNum(int carSeatsNum) {
        this.carSeatsNum = carSeatsNum;
    }

    public boolean isCarAirCond() {
        return carAirCond;
    }

    public void setCarAirCond(boolean carAirCond) {
        this.carAirCond = carAirCond;
    }

    public boolean isCarNavigation() {
        return carNavigation;
    }

    public void setCarNavigation(boolean carNavigation) {
        this.carNavigation = carNavigation;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(String carVersion) {
        this.carVersion = carVersion;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carHorsePower == car.carHorsePower &&
                carDoorsNum == car.carDoorsNum &&
                carSeatsNum == car.carSeatsNum &&
                carAirCond == car.carAirCond &&
                carNavigation == car.carNavigation &&
                carYear == car.carYear &&
                carMileage == car.carMileage &&
                price == car.price &&
                Objects.equals(carId, car.carId) &&
                Objects.equals(carBrand, car.carBrand) &&
                Objects.equals(carModel, car.carModel) &&
                Objects.equals(carFuel, car.carFuel) &&
                Objects.equals(carGearType, car.carGearType) &&
                Objects.equals(carBodyType, car.carBodyType) &&
                Objects.equals(vin, car.vin) &&
                Objects.equals(regNum, car.regNum) &&
                Objects.equals(carVersion, car.carVersion) &&
                Objects.equals(city, car.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carBrand, carModel, carFuel, carGearType, carHorsePower, carBodyType, carDoorsNum, carSeatsNum, carAirCond, carNavigation, vin, regNum, carYear, carMileage, carVersion, price, city);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carFuel='" + carFuel + '\'' +
                ", carGearType='" + carGearType + '\'' +
                ", carHorsePower=" + carHorsePower +
                ", carBodyType='" + carBodyType + '\'' +
                ", carDoorsNum=" + carDoorsNum +
                ", carSeatsNum=" + carSeatsNum +
                ", carAirCond=" + carAirCond +
                ", carNavigation=" + carNavigation +
                ", vin='" + vin + '\'' +
                ", regNum='" + regNum + '\'' +
                ", carYear=" + carYear +
                ", carMileage=" + carMileage +
                ", carVersion='" + carVersion + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                '}';
    }
}


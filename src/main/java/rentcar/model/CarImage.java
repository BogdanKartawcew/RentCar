package rentcar.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "CAR_IMAGE", uniqueConstraints = {@UniqueConstraint(columnNames = "IMAGEID")})
public class CarImage {

    @Id
    @Column(name = "IMAGEID", unique = true, nullable = false)
    private int imageId;

    @Column(name = "CARID")
    private int carId;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "IMAGE")
    private byte[] carImage;

    @Column(name = "NUMBER")
    private int number;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    public byte[] getCarImage() {
        return carImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarImage carImage1 = (CarImage) o;
        return imageId == carImage1.imageId &&
                carId == carImage1.carId &&
                number == carImage1.number &&
                Arrays.equals(carImage, carImage1.carImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(imageId, carId, number);
        result = 31 * result + Arrays.hashCode(carImage);
        return result;
    }

    @Override
    public String toString() {
        return "CarImage{" +
                "imageId=" + imageId +
                ", carId=" + carId +
                ", carImage=" + Arrays.toString(carImage) +
                ", number=" + number +
                '}';
    }
}

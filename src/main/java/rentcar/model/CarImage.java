package rentcar.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "CAR_IMAGE", uniqueConstraints = {@UniqueConstraint(columnNames = "VIN")})
public class CarImage {

    @Id
    @Column(name = "VIN", unique = true, nullable = false)
    private String vin;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "CAR_IMAGE")
    private byte[] carImage;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    public byte[] getCarImage() {
        return carImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarImage carImage1 = (CarImage) o;
        return Objects.equals(vin, carImage1.vin) &&
                Arrays.equals(carImage, carImage1.carImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(vin);
        result = 31 * result + Arrays.hashCode(carImage);
        return result;
    }

    @Override
    public String toString() {
        return "CarImage{" +
                "vin='" + vin + '\'' +
                ", carImage=" + Arrays.toString(carImage) +
                '}';
    }
}

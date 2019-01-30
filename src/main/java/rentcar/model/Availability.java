package rentcar.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "AVAILABILITY", uniqueConstraints = {@UniqueConstraint(columnNames = "AVAILABILITYID")})
public class Availability implements Serializable {

    @Id
    @Column(name = "AVAILABILITYID", unique = true, nullable = false)
    private Integer availabilityId;

    //@NotNull
    @Column(name = "CARID", nullable = false)
    private Integer carId;

    //@NotNull
    @Column(name = "STARTDATE")
    private Date startDate;

    //@NotNull
    @Column(name = "STARTTIME")
    private Time startTime;

    //@NotNull
    @Column(name = "ENDDATE")
    private Date endDate;

    //@NotNull
    @Column(name = "ENDTIME")
    private Time endTime;

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability that = (Availability) o;
        return Objects.equals(availabilityId, that.availabilityId) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(availabilityId, carId, startDate, startTime, endDate, endTime);
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                '}';
    }
}

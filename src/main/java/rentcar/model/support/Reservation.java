package rentcar.model.support;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Created by a261711 on 2017-10-22.
 */
@Entity
@Table(name = "RECEIVED_RESERVATIONS", uniqueConstraints = {@UniqueConstraint(columnNames = "RESERVATIONID")})
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATIONID", unique = true, nullable = false)
    private Integer reservationId;

    @NotNull(message = "Please choose the client")
    @Column(name = "CLIENTID")
    private Integer clientId;

    @NotNull(message = "Please choose the car")
    @Column(name = "CARID")
    private Integer carId;

    @Column(name = "STARTDATE")
    @NotNull(message = "Please choose the start date")
    //@Future
    private Date startDate;

    @Column(name = "STARTTIME")
    @NotNull(message = "Please choose the end date")
    private Time startTime;

    @Column(name = "ENDDATE")
    @NotNull(message = "Please choose the end date")
    //@Future(message = "Please choose the date after start date at list for one day")
    private Date endDate;

    @Column(name = "ENDTIME")
    @NotNull(message = "Please choose the end date")
    private Time endTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "FINISHED")
    private boolean finished;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return confirmed == that.confirmed &&
                active == that.active &&
                finished == that.finished &&
                Objects.equals(reservationId, that.reservationId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, clientId, carId, startDate, startTime, endDate, endTime, status, confirmed, active, finished);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", clientId=" + clientId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", confirmed=" + confirmed +
                ", active=" + active +
                ", finished=" + finished +
                '}';
    }
}


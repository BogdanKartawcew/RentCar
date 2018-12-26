package rentcar.model.support;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "RESERVATIONHISTORY", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class ReservationHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "RESERVATIONID")
    private Integer reservationId;

    @Column(name = "CARID")
    private Integer carId;

    @Column(name = "CLIENTID")
    private Integer clientId;

    @Column(name = "STARTDATE")
    private Date startDate;

    @Column(name = "STARTTIME")
    private Time startTime;

    @Column(name = "ENDDATE")
    private Date endDate;

    @Column(name = "ENDTIME")
    private Time endTime;

    @Column(name = "MILEAGE")
    private int mileage;

    @Column(name = "FREETEXT")
    private String freeText;

    @Column(name = "USERID")
    private Integer userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP", nullable = false)
    private java.util.Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public java.util.Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationHistory that = (ReservationHistory) o;
        return mileage == that.mileage &&
                Objects.equals(id, that.id) &&
                Objects.equals(status, that.status) &&
                Objects.equals(reservationId, that.reservationId) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(freeText, that.freeText) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, reservationId, carId, clientId, mileage, startDate, startTime, endDate, endTime, freeText, userId, timestamp);
    }

    @Override
    public String toString() {
        return "ReservationHistory{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", reservationId=" + reservationId +
                ", carId=" + carId +
                ", clientId=" + clientId +
                ", mileage=" + mileage +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", freeText='" + freeText + '\'' +
                ", userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

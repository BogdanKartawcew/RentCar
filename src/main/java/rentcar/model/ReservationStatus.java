package rentcar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "RESERVATION_STATUS", uniqueConstraints = {@UniqueConstraint(columnNames = "STATUS_ID")})
public class ReservationStatus implements Serializable {

    @Id
    @Column(name = "STATUS_ID")
    private String statusId;

    @Column(name = "STATUS_DESC", nullable = false)
    private String statusDesc;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationStatus that = (ReservationStatus) o;
        return Objects.equals(statusId, that.statusId) &&
                Objects.equals(statusDesc, that.statusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusDesc);
    }

    @Override
    public String toString() {
        return "ReservationStatus{" +
                "statusId='" + statusId + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}

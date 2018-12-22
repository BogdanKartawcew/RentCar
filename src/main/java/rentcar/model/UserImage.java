package rentcar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "USER_IMAGE")
public class UserImage {
    @Id
    @Column(name = "ID", unique = true)
    private int id;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "USER_IMAGE")
    private byte[] userImage;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImage userImage1 = (UserImage) o;
        return id == userImage1.id &&
                Arrays.equals(userImage, userImage1.userImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(userImage);
        return result;
    }

    @Override
    public String toString() {
        return "UserImage{" +
                "id=" + id +
                ", userImage=" + Arrays.toString(userImage) +
                '}';
    }
}

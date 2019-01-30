package rentcar.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @NotNull(message = "PESEL can not be blank")
    @Size(min = 11, max = 11, message = "Pesel must have 11 integers. Please check again")
    @Column(name = "PESEL", unique = true)
    private String pesel;

    @NotNull(message = "First name can not be blank")
    @Column(name = "CLIENT_FIRST_NAME")
    private String clientFirstName;

    @NotNull(message = "Last name can not be blank")
    @Column(name = "CLIENT_LAST_NAME")
    private String clientLastName;

    @NotNull(message = "Email can not be blank")
    @Email(message = "It is not an email. Please check field again")
    @Column(name = "CLIENT_EMAIL")
    private String clientEmail;

    @NotNull(message = "Field can not be blank")
    @Column(name = "CLIENT_COMP_NAME")
    private String clientCompanyName;

    @NotNull(message = "Please choose a gender")
    @Column(name = "CLIENT_GENDER")
    private String clientGender;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", pesel='" + pesel + '\'' +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientCompanyName='" + clientCompanyName + '\'' +
                ", clientGender='" + clientGender + '\'' +
                '}';
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != null ? !clientId.equals(client.clientId) : client.clientId != null) return false;
        return pesel != null ? pesel.equals(client.pesel) : client.pesel == null;
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        return result;
    }
}


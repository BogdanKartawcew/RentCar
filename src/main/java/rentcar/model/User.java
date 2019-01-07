package rentcar.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please fill in a login")
    @Column(name = "LOGIN", unique = true)
    private String login;

    @NotEmpty(message = "Please fill in a password")
    @Column(name = "PASSWORD")
    private String password;

    @NotEmpty(message = "First name can not be blank")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty(message = "Last name can not be blank")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotEmpty(message = "Email can not be blank")
    @Email(message = "It is not an email. Please check field again")
    @Column(name = "EMAIL")
    private String email;

    @NotBlank(message = "Please choose a gender")
    @Column(name = "SEX")
    private String sex;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Collection<Role> roles = new ArrayList<>();


    @Column(name = "ROLE")
    private int role;

    /*========================getters/setters======================*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getRole() {
        return role;
    }

    public void setRole(/*int role*/) {
        int actualRole = 0;
        for (Role roles : getRoles()) {
            actualRole = roles.getId();
        }

        this.role = actualRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return role == user.role &&
                Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, email, sex, roles, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", roles=" + roles +
                ", role=" + role +
                '}';
    }
}

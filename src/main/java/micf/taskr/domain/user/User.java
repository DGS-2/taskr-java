package micf.taskr.domain.user;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String firstName;

    private String lastName;

    @OneToOne(targetEntity = UserAuth.class, mappedBy = "email")
    private String email;

    private Boolean is_using_login_token;

    public String getId() {
        return id;
    } 

    public void setId(String id) {
        this.id = id;
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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getToken(){
        return is_using_login_token;
    }

    public void setToken(Boolean token) {
        this.is_using_login_token = token;
    }
}
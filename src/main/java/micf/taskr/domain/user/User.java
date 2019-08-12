package micf.taskr.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
// import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
// import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotBlank(message = "Password field is required")
    private String password;

    @Transient
    private String confirmPassword;
    private Date create_At;
    private Date update_At;

    //OneToMany with Task
    // @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    // private List<Task> tasks = new ArrayList<>();


    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    /*
    UserDetails interface methods
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    public User() {
    }

    public User(Long id, String username, String firstName, String lastName, String password, String confirmPassword, Date create_At, Date update_At) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.create_At = create_At;
        this.update_At = update_At;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreate_At() {
        return this.create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return this.update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User confirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public User create_At(Date create_At) {
        this.create_At = create_At;
        return this;
    }

    public User update_At(Date update_At) {
        this.update_At = update_At;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword) && Objects.equals(create_At, user.create_At) && Objects.equals(update_At, user.update_At);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, password, confirmPassword, create_At, update_At);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", password='" + getPassword() + "'" +
            ", confirmPassword='" + getConfirmPassword() + "'" +
            ", create_At='" + getCreate_At() + "'" +
            ", update_At='" + getUpdate_At() + "'" +
            "}";
    }


    
}
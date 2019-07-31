package micf.taskr.domain.user;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    // @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @Valid
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private boolean isUsingLoginToken;


    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String password, String matchingPassword, String email, boolean isUsingLoginToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.isUsingLoginToken = isUsingLoginToken;
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

    public String getMatchingPassword() {
        return this.matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsUsingLoginToken() {
        return this.isUsingLoginToken;
    }

    public boolean getIsUsingLoginToken() {
        return this.isUsingLoginToken;
    }

    public void setIsUsingLoginToken(boolean isUsingLoginToken) {
        this.isUsingLoginToken = isUsingLoginToken;
    }

    public UserDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDto lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDto password(String password) {
        this.password = password;
        return this;
    }

    public UserDto matchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        return this;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    public UserDto isUsingLoginToken(boolean isUsingLoginToken) {
        this.isUsingLoginToken = isUsingLoginToken;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(password, userDto.password) && Objects.equals(matchingPassword, userDto.matchingPassword) && Objects.equals(email, userDto.email) && isUsingLoginToken == userDto.isUsingLoginToken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, password, matchingPassword, email, isUsingLoginToken);
    }

    @Override
    public String toString() {
        return "{" +
            " firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", password='" + getPassword() + "'" +
            ", matchingPassword='" + getMatchingPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", isUsingLoginToken='" + isIsUsingLoginToken() + "'" +
            "}";
    }

}
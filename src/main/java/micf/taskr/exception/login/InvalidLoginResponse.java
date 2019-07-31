package micf.taskr.exception.login;

import java.util.Objects;

public class InvalidLoginResponse {
    private String email;
    private String password; 

    public InvalidLoginResponse() {
    }

    public InvalidLoginResponse(String email, String password) {
        this.email = "Invalid Email";
        this.password = "Invalid Password";
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InvalidLoginResponse email(String email) {
        this.email = email;
        return this;
    }

    public InvalidLoginResponse password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InvalidLoginResponse)) {
            return false;
        }
        InvalidLoginResponse invalidLoginResponse = (InvalidLoginResponse) o;
        return Objects.equals(email, invalidLoginResponse.email) && Objects.equals(password, invalidLoginResponse.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
    
    
}
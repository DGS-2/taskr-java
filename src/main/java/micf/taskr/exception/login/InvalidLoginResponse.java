package micf.taskr.exception.login;

import java.util.Objects;

public class InvalidLoginResponse {
    private String username;
    private String password;


    public InvalidLoginResponse() {
    }

    public InvalidLoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InvalidLoginResponse username(String username) {
        this.username = username;
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
        return Objects.equals(username, invalidLoginResponse.username) && Objects.equals(password, invalidLoginResponse.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
    
}
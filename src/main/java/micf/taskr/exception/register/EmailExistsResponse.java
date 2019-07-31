package micf.taskr.exception.register;

public class EmailExistsResponse {

    private String email;

    public EmailExistsResponse() {
    }

    public EmailExistsResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailExistsResponse email(String email) {
        this.email = email;
        return this;
    }

}
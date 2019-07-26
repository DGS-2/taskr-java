package micf.taskr.domain.user;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(mappedBy="email")
    private User email;

    private String hash;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getEmail(){
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
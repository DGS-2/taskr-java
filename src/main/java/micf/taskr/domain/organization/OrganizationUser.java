package micf.taskr.domain.organization;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import micf.taskr.domain.user.User;

import java.util.List;

@Entity
@Table(name="org_entity_user")
public class OrganizationUser {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(targetEntity = OrganizationRoster.class, mappedBy = "id" )
    private String org_entity_roster_id;

    @OneToOne(targetEntity = User.class, mappedBy = "id")
    private String user_id;

    private String position;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRosterId() {
        return org_entity_roster_id;
    }

    public void setRosterId(String id) {
        this.org_entity_roster_id = id;
    }

    public String getUser(){
        return user_id;
    }

    public void setUser(String id) {
        this.user_id = id;
    }

    public String getUserPosition(){
        return position;
    }

    public void setUserPosition(String position) {
        this.position = position;
    }
}
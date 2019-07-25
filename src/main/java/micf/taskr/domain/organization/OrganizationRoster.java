package micf.taskr.domain.organization;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name="org_entity_roster")
public class OrganizationRoster {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(targetEntity = Organization.class, mappedBy = "id")
    private String org_entity_id;

    @OneToMany(targetEntity = OrganizationUser.class, mappedBy = "id")
    private List<OrganizationUser> org_entity_users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return org_entity_id;
    }

    public void setParentId(String id) {
        this.org_entity_id = id;
    }

    public List<OrganizationUser> getOrgUsers() {
        return org_entity_users;
    }

    public void setOrgUsers(List<OrganizationUser> users) {
        this.org_entity_users = users;
    }
}
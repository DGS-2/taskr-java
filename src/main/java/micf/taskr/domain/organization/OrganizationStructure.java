package micf.taskr.domain.organization;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="org_entity_structure")
public class OrganizationStructure {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(targetEntity = Organization.class, mappedBy = "id")
    private String parent_id;

    @OneToOne(targetEntity = Organization.class, mappedBy = "id")
    private String child_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parent_id;
    }

    public void setParentId(String id) {
        this.parent_id = id;
    }

    public String getChildId() {
        return child_id;
    }

    public void setChildId(String id) {
        this.child_id = id;
    }
}
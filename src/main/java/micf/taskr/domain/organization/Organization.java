package micf.taskr.domain.organization;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="org_entity")
public class Organization {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private String abbreviated;

    private String level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviatedName() {
        return abbreviated;
    }

    public void setAbbreviatedName(String abbreviated) {
        this.abbreviated = abbreviated;
    }

    public String getOrganizationLevel(){
        return level;
    }

    public void setOrganizationLevel(String level) {
        this.level = level;
    }
}
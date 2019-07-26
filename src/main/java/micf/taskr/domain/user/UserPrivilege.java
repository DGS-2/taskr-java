package micf.taskr.domain.user;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "user_privilege")
public class UserPrivilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<UserRole> roles;

    public UserPrivilege() {
        super();
    }

    public UserPrivilege(final String name) {
        super();
        this.name = name;
    }

    // Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<UserRole> roles) {
        this.roles = roles;
    }

    // Overrides

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserPrivilege other = (UserPrivilege) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
}
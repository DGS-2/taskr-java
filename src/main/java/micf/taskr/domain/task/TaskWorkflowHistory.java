package micf.taskr.domain.task;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_workflow_state_history")
public class TaskWorkflowHistory {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    private String completed_by;

    private Date completed_date;

    @OneToMany(mappedBy = "workflow_id") 
    private List<TaskWorkflowState> completed_state;


    public TaskWorkflowHistory() {
    }

    public TaskWorkflowHistory(String id, String completed_by, Date completed_date, List<TaskWorkflowState> completed_state) {
        this.id = id;
        this.completed_by = completed_by;
        this.completed_date = completed_date;
        this.completed_state = completed_state;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompleted_by() {
        return this.completed_by;
    }

    public void setCompleted_by(String completed_by) {
        this.completed_by = completed_by;
    }

    public Date getCompleted_date() {
        return this.completed_date;
    }

    public void setCompleted_date(Date completed_date) {
        this.completed_date = completed_date;
    }

    public List<TaskWorkflowState> getCompleted_state() {
        return this.completed_state;
    }

    public void setCompleted_state(List<TaskWorkflowState> completed_state) {
        this.completed_state = completed_state;
    }

    public TaskWorkflowHistory id(String id) {
        this.id = id;
        return this;
    }

    public TaskWorkflowHistory completed_by(String completed_by) {
        this.completed_by = completed_by;
        return this;
    }

    public TaskWorkflowHistory completed_date(Date completed_date) {
        this.completed_date = completed_date;
        return this;
    }

    public TaskWorkflowHistory completed_state(List<TaskWorkflowState> completed_state) {
        this.completed_state = completed_state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskWorkflowHistory)) {
            return false;
        }
        TaskWorkflowHistory taskWorkflowHistory = (TaskWorkflowHistory) o;
        return Objects.equals(id, taskWorkflowHistory.id) && Objects.equals(completed_by, taskWorkflowHistory.completed_by) && Objects.equals(completed_date, taskWorkflowHistory.completed_date) && Objects.equals(completed_state, taskWorkflowHistory.completed_state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, completed_by, completed_date, completed_state);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", completed_by='" + getCompleted_by() + "'" +
            ", completed_date='" + getCompleted_date() + "'" +
            ", completed_state='" + getCompleted_state() + "'" +
            "}";
    }
    
}
package micf.taskr.domain.task;

import java.util.Date;

import javax.persistence.*;

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

    @OneToMany(targetEntity = TaskWorkflowState.class, mappedBy = "id")
    private String completed_state;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompletedBy(){
        return completed_by;
    }

    public void setCompletedBy(String id) {
        this.completed_by = id;
    }

    public Date getCompletedDate() {
        return completed_date;
    }

    public void setCompletedDate(Date completedDate) {
        this.completed_date = completedDate;
    }

    public String getCompletedState() {
        return completed_state;
    }

    public void setCompletedState(String state) {
        this.completed_state = state;
    }
}
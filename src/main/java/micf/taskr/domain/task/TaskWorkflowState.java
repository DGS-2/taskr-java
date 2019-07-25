package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_thread")
public class TaskWorkflowState {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    // @OneToMany(mappedBy = "workflow_id")
    private String workflow_id;

    private String state;

    private Number percentComplete;

    public TaskWorkflowState() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkflowId() {
        return workflow_id;
    }

    public void setWorkflowId(String workflow_id) {
        this.workflow_id = workflow_id;
    }

    public String getState(){
        return state;
    }    

    public void setState(String state) {
        this.state = state;
    }

    public Number getCompletedValue() {
        return percentComplete;
    }

    public void setCompletedValue(Number percent) {
        this.percentComplete = percent;
    }
}
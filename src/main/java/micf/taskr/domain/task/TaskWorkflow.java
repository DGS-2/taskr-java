package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_thread")
public class TaskWorkflow {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(mappedBy = "task_id")
    private String task_id;

    @OneToMany
    @JoinColumn
    private TaskWorkflowState state;

    public TaskWorkflow() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
    }

    public TaskWorkflowState getState(){
        return state;
    }

    public void setState(TaskWorkflowState state) {
        this.state = state;
    }
}
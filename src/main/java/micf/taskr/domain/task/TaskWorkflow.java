package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="task_thread")
public class TaskWorkflow {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @ManyToOne(targetEntity = Task.class)
    private String task_id;

    @OneToMany(targetEntity = TaskWorkflowState.class)
    @JoinColumn(name="workflow_id")
    private Set<TaskWorkflowState> state = new HashSet<>();

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

    public Set<TaskWorkflowState> getState(){
        return state;
    }

    public void setState(Set<TaskWorkflowState> state) {
        this.state = state;
    }
}
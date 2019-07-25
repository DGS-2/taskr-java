package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="task_workflow")
public class TaskWorkflow {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(targetEntity = Task.class, mappedBy = "id")
    private String task_id;

    @OneToMany(targetEntity = TaskWorkflowState.class)
    private Set<TaskWorkflowHistory> history = new HashSet<>();

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

    public Set<TaskWorkflowHistory> getHistory(){
        return history;
    }

    public void setHistory(Set<TaskWorkflowHistory> history) {
        this.history = history;
    }
}
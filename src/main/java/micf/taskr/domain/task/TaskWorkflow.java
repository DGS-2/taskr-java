package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name="task_workflow")
public class TaskWorkflow {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(mappedBy = "taskWorkflow")
    private Task workflow_task_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "history", referencedColumnName = "id")
    private TaskWorkflowHistory history;

    public TaskWorkflow() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getTaskId() {
        return workflow_task_id;
    }

    public void setTaskId(Task workflow_task_id) {
        this.workflow_task_id = workflow_task_id;
    }

    public TaskWorkflowHistory getHistory(){
        return history;
    }

    public void setHistory(TaskWorkflowHistory history) {
        this.history = history;
    }
}
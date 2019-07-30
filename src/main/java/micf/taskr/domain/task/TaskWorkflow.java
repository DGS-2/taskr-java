package micf.taskr.domain.task;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

// import java.util.List;

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


    public TaskWorkflow() {
    }

    public TaskWorkflow(String id, Task workflow_task_id, TaskWorkflowHistory history) {
        this.id = id;
        this.workflow_task_id = workflow_task_id;
        this.history = history;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getWorkflow_task_id() {
        return this.workflow_task_id;
    }

    public void setWorkflow_task_id(Task workflow_task_id) {
        this.workflow_task_id = workflow_task_id;
    }

    public TaskWorkflowHistory getHistory() {
        return this.history;
    }

    public void setHistory(TaskWorkflowHistory history) {
        this.history = history;
    }

    public TaskWorkflow id(String id) {
        this.id = id;
        return this;
    }

    public TaskWorkflow workflow_task_id(Task workflow_task_id) {
        this.workflow_task_id = workflow_task_id;
        return this;
    }

    public TaskWorkflow history(TaskWorkflowHistory history) {
        this.history = history;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskWorkflow)) {
            return false;
        }
        TaskWorkflow taskWorkflow = (TaskWorkflow) o;
        return Objects.equals(id, taskWorkflow.id) && Objects.equals(workflow_task_id, taskWorkflow.workflow_task_id) && Objects.equals(history, taskWorkflow.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflow_task_id, history);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", workflow_task_id='" + getWorkflow_task_id() + "'" +
            ", history='" + getHistory() + "'" +
            "}";
    }
    
}
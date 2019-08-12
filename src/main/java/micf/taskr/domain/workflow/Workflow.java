package micf.taskr.domain.workflow;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import micf.taskr.domain.task.Task;
import micf.taskr.domain.user.User;

@Entity
@Table(name = "workflow")
public class Workflow {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String taskIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User created_by;

    private Date updated_at;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "workflow")
    @JsonIgnore
    private WorkflowHistory workflowHistory;

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;


    public Workflow() {
    }

    public Workflow(Long id, String taskIdentifier, User created_by, Date updated_at, WorkflowHistory workflowHistory, Task task) {
        this.id = id;
        this.taskIdentifier = taskIdentifier;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.workflowHistory = workflowHistory;
        this.task = task;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public User getCreated_by() {
        return this.created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public WorkflowHistory getWorkflowHistory() {
        return this.workflowHistory;
    }

    public void setWorkflowHistory(WorkflowHistory workflowHistory) {
        this.workflowHistory = workflowHistory;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Workflow id(Long id) {
        this.id = id;
        return this;
    }

    public Workflow taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public Workflow created_by(User created_by) {
        this.created_by = created_by;
        return this;
    }

    public Workflow updated_at(Date updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public Workflow workflowHistory(WorkflowHistory workflowHistory) {
        this.workflowHistory = workflowHistory;
        return this;
    }

    public Workflow task(Task task) {
        this.task = task;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Workflow)) {
            return false;
        }
        Workflow workflow = (Workflow) o;
        return Objects.equals(id, workflow.id) && Objects.equals(taskIdentifier, workflow.taskIdentifier) && Objects.equals(created_by, workflow.created_by) && Objects.equals(updated_at, workflow.updated_at) && Objects.equals(workflowHistory, workflow.workflowHistory) && Objects.equals(task, workflow.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskIdentifier, created_by, updated_at, workflowHistory, task);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", created_by='" + getCreated_by() + "'" +
            ", updated_at='" + getUpdated_at() + "'" +
            ", workflowHistory='" + getWorkflowHistory() + "'" +
            ", task='" + getTask() + "'" +
            "}";
    }
    

}
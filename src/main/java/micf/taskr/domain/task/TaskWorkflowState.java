package micf.taskr.domain.task;

import java.util.Objects;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_workflow_state")
public class TaskWorkflowState {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id; 

    @Column(updatable = false)
    private String taskSequence;

    // Status of workflow
    private String state;

    private Integer percentComplete;

    private Boolean is_approval_required;

    private String approver_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private TaskBacklog backlog;

    private String taskIdentifier;


    public TaskWorkflowState() {
    }

    public TaskWorkflowState(String id, String taskSequence, String state, Integer percentComplete, Boolean is_approval_required, String approver_id, TaskBacklog backlog, String taskIdentifier) {
        this.id = id;
        this.taskSequence = taskSequence;
        this.state = state;
        this.percentComplete = percentComplete;
        this.is_approval_required = is_approval_required;
        this.approver_id = approver_id;
        this.backlog = backlog;
        this.taskIdentifier = taskIdentifier;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskSequence() {
        return this.taskSequence;
    }

    public void setTaskSequence(String taskSequence) {
        this.taskSequence = taskSequence;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPercentComplete() {
        return this.percentComplete;
    }

    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }

    public Boolean isIs_approval_required() {
        return this.is_approval_required;
    }

    public Boolean getIs_approval_required() {
        return this.is_approval_required;
    }

    public void setIs_approval_required(Boolean is_approval_required) {
        this.is_approval_required = is_approval_required;
    }

    public String getApprover_id() {
        return this.approver_id;
    }

    public void setApprover_id(String approver_id) {
        this.approver_id = approver_id;
    }

    public TaskBacklog getBacklog() {
        return this.backlog;
    }

    public void setBacklog(TaskBacklog backlog) {
        this.backlog = backlog;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public TaskWorkflowState id(String id) {
        this.id = id;
        return this;
    }

    public TaskWorkflowState taskSequence(String taskSequence) {
        this.taskSequence = taskSequence;
        return this;
    }

    public TaskWorkflowState state(String state) {
        this.state = state;
        return this;
    }

    public TaskWorkflowState percentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
        return this;
    }

    public TaskWorkflowState is_approval_required(Boolean is_approval_required) {
        this.is_approval_required = is_approval_required;
        return this;
    }

    public TaskWorkflowState approver_id(String approver_id) {
        this.approver_id = approver_id;
        return this;
    }

    public TaskWorkflowState backlog(TaskBacklog backlog) {
        this.backlog = backlog;
        return this;
    }

    public TaskWorkflowState taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskWorkflowState)) {
            return false;
        }
        TaskWorkflowState taskWorkflowState = (TaskWorkflowState) o;
        return Objects.equals(id, taskWorkflowState.id) && Objects.equals(taskSequence, taskWorkflowState.taskSequence) && Objects.equals(state, taskWorkflowState.state) && Objects.equals(percentComplete, taskWorkflowState.percentComplete) && Objects.equals(is_approval_required, taskWorkflowState.is_approval_required) && Objects.equals(approver_id, taskWorkflowState.approver_id) && Objects.equals(backlog, taskWorkflowState.backlog) && Objects.equals(taskIdentifier, taskWorkflowState.taskIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskSequence, state, percentComplete, is_approval_required, approver_id, backlog, taskIdentifier);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", taskSequence='" + getTaskSequence() + "'" +
            ", state='" + getState() + "'" +
            ", percentComplete='" + getPercentComplete() + "'" +
            ", is_approval_required='" + isIs_approval_required() + "'" +
            ", approver_id='" + getApprover_id() + "'" +
            ", backlog='" + getBacklog() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            "}";
    }
    
    
}
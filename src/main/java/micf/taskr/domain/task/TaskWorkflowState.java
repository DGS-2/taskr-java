package micf.taskr.domain.task;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_workflow_state")
public class TaskWorkflowState {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id; 

    @ManyToOne
    private TaskWorkflowHistory workflow_id;

    private String state;

    private Integer percentComplete;

    private Boolean is_approval_required;

    private String approver_id;


    public TaskWorkflowState() {
    }

    public TaskWorkflowState(String id, TaskWorkflowHistory workflow_id, String state, Integer percentComplete, Boolean is_approval_required, String approver_id) {
        this.id = id;
        this.workflow_id = workflow_id;
        this.state = state;
        this.percentComplete = percentComplete;
        this.is_approval_required = is_approval_required;
        this.approver_id = approver_id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskWorkflowHistory getWorkflow_id() {
        return this.workflow_id;
    }

    public void setWorkflow_id(TaskWorkflowHistory workflow_id) {
        this.workflow_id = workflow_id;
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

    public TaskWorkflowState id(String id) {
        this.id = id;
        return this;
    }

    public TaskWorkflowState workflow_id(TaskWorkflowHistory workflow_id) {
        this.workflow_id = workflow_id;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskWorkflowState)) {
            return false;
        }
        TaskWorkflowState taskWorkflowState = (TaskWorkflowState) o;
        return Objects.equals(id, taskWorkflowState.id) && Objects.equals(workflow_id, taskWorkflowState.workflow_id) && Objects.equals(state, taskWorkflowState.state) && Objects.equals(percentComplete, taskWorkflowState.percentComplete) && Objects.equals(is_approval_required, taskWorkflowState.is_approval_required) && Objects.equals(approver_id, taskWorkflowState.approver_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflow_id, state, percentComplete, is_approval_required, approver_id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", workflow_id='" + getWorkflow_id() + "'" +
            ", state='" + getState() + "'" +
            ", percentComplete='" + getPercentComplete() + "'" +
            ", is_approval_required='" + isIs_approval_required() + "'" +
            ", approver_id='" + getApprover_id() + "'" +
            "}";
    }
    
}
package micf.taskr.domain.workflow;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import micf.taskr.domain.user.User;

@Entity
@Table(name = "workflow_item")
public class WorkflowItem {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Sequence in the history this item occured
    @Column(updatable = false, unique = true)
    private String historySequence;
    
    // Date status was changed
    private Date date_completed = new Date();

    // status name AWAITING_USER_ACTION/OPEN/CLOSED/etc.
    private String completedStatus;

    private Integer percentComplete;

    private boolean isApprovalRequired;

    private String taskIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User approvalAuthority;
    
    //ManyToOne with WorkflowHistory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_history_id", updatable = false, nullable = false)
    @JsonIgnore
    //
    private WorkflowHistory workflowHistory;


    public WorkflowItem() {
    }

    public WorkflowItem(Long id, String historySequence, Date date_completed, String completedStatus, Integer percentComplete, boolean isApprovalRequired, String taskIdentifier, User approvalAuthority, WorkflowHistory workflowHistory) {
        this.id = id;
        this.historySequence = historySequence;
        this.date_completed = date_completed;
        this.completedStatus = completedStatus;
        this.percentComplete = percentComplete;
        this.isApprovalRequired = isApprovalRequired;
        this.taskIdentifier = taskIdentifier;
        this.approvalAuthority = approvalAuthority;
        this.workflowHistory = workflowHistory;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistorySequence() {
        return this.historySequence;
    }

    public void setHistorySequence(String historySequence) {
        this.historySequence = historySequence;
    }

    public Date getDate_completed() {
        return this.date_completed;
    }

    public void setDate_completed(Date date_completed) {
        this.date_completed = date_completed;
    }

    public String getCompletedStatus() {
        return this.completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public Integer getPercentComplete() {
        return this.percentComplete;
    }

    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }

    public boolean isIsApprovalRequired() {
        return this.isApprovalRequired;
    }

    public boolean getIsApprovalRequired() {
        return this.isApprovalRequired;
    }

    public void setIsApprovalRequired(boolean isApprovalRequired) {
        this.isApprovalRequired = isApprovalRequired;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public User getApprovalAuthority() {
        return this.approvalAuthority;
    }

    public void setApprovalAuthority(User approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
    }

    public WorkflowHistory getWorkflowHistory() {
        return this.workflowHistory;
    }

    public void setWorkflowHistory(WorkflowHistory workflowHistory) {
        this.workflowHistory = workflowHistory;
    }

    public WorkflowItem id(Long id) {
        this.id = id;
        return this;
    }

    public WorkflowItem historySequence(String historySequence) {
        this.historySequence = historySequence;
        return this;
    }

    public WorkflowItem date_completed(Date date_completed) {
        this.date_completed = date_completed;
        return this;
    }

    public WorkflowItem completedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
        return this;
    }

    public WorkflowItem percentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
        return this;
    }

    public WorkflowItem isApprovalRequired(boolean isApprovalRequired) {
        this.isApprovalRequired = isApprovalRequired;
        return this;
    }

    public WorkflowItem taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public WorkflowItem approvalAuthority(User approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
        return this;
    }

    public WorkflowItem workflowHistory(WorkflowHistory workflowHistory) {
        this.workflowHistory = workflowHistory;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WorkflowItem)) {
            return false;
        }
        WorkflowItem workflowItem = (WorkflowItem) o;
        return Objects.equals(id, workflowItem.id) && Objects.equals(historySequence, workflowItem.historySequence) && Objects.equals(date_completed, workflowItem.date_completed) && Objects.equals(completedStatus, workflowItem.completedStatus) && Objects.equals(percentComplete, workflowItem.percentComplete) && isApprovalRequired == workflowItem.isApprovalRequired && Objects.equals(taskIdentifier, workflowItem.taskIdentifier) && Objects.equals(approvalAuthority, workflowItem.approvalAuthority) && Objects.equals(workflowHistory, workflowItem.workflowHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, historySequence, date_completed, completedStatus, percentComplete, isApprovalRequired, taskIdentifier, approvalAuthority, workflowHistory);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", historySequence='" + getHistorySequence() + "'" +
            ", date_completed='" + getDate_completed() + "'" +
            ", completedStatus='" + getCompletedStatus() + "'" +
            ", percentComplete='" + getPercentComplete() + "'" +
            ", isApprovalRequired='" + isIsApprovalRequired() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", approvalAuthority='" + getApprovalAuthority() + "'" +
            ", workflowHistory='" + getWorkflowHistory() + "'" +
            "}";
    }

}
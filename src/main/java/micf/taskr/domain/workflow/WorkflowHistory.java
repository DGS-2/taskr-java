package micf.taskr.domain.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "workflow_history")
public class WorkflowHistory {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer workflowSequence = 0;

    private String taskIdentifier;

    //OneToOne with Workflow
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_id", nullable = false)
    @JsonIgnore
    private Workflow workflow;

    //OneToMany with Workflow Item
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "workflowHistory", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private List<WorkflowItem> statusHistory = new ArrayList<>();


    public WorkflowHistory() {
    }

    public WorkflowHistory(Long id, Integer workflowSequence, String taskIdentifier, Workflow workflow, List<WorkflowItem> statusHistory) {
        this.id = id;
        this.workflowSequence = workflowSequence;
        this.taskIdentifier = taskIdentifier;
        this.workflow = workflow;
        this.statusHistory = statusHistory;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkflowSequence() {
        return this.workflowSequence;
    }

    public void setWorkflowSequence(Integer workflowSequence) {
        this.workflowSequence = workflowSequence;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public List<WorkflowItem> getStatusHistory() {
        return this.statusHistory;
    }

    public void setStatusHistory(List<WorkflowItem> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public WorkflowHistory id(Long id) {
        this.id = id;
        return this;
    }

    public WorkflowHistory workflowSequence(Integer workflowSequence) {
        this.workflowSequence = workflowSequence;
        return this;
    }

    public WorkflowHistory taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public WorkflowHistory workflow(Workflow workflow) {
        this.workflow = workflow;
        return this;
    }

    public WorkflowHistory statusHistory(List<WorkflowItem> statusHistory) {
        this.statusHistory = statusHistory;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WorkflowHistory)) {
            return false;
        }
        WorkflowHistory workflowHistory = (WorkflowHistory) o;
        return Objects.equals(id, workflowHistory.id) && Objects.equals(workflowSequence, workflowHistory.workflowSequence) && Objects.equals(taskIdentifier, workflowHistory.taskIdentifier) && Objects.equals(workflow, workflowHistory.workflow) && Objects.equals(statusHistory, workflowHistory.statusHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflowSequence, taskIdentifier, workflow, statusHistory);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", workflowSequence='" + getWorkflowSequence() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", workflow='" + getWorkflow() + "'" +
            ", statusHistory='" + getStatusHistory() + "'" +
            "}";
    }

}
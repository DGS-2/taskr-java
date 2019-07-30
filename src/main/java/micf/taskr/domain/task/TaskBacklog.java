package micf.taskr.domain.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "task_backlog")
public class TaskBacklog {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Integer workflowSequence = 0;
    private Integer messageSequence = 0;

    private String taskIdentifier;

    //OneToOne with a task
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;
    
    // Here we will store the backlog of each message in the message thread
    //OneToMany 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "backlog")
    private List<TaskThreadMessage> messageThread = new ArrayList<>();

    // Here we will store the backlog of each workflow update
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "backlog")
    private List<TaskWorkflowState> workflowState = new ArrayList<>();




    public TaskBacklog() {
    }

    public TaskBacklog(String id, Integer workflowSequence, Integer messageSequence, String taskIdentifier, Task task, List<TaskThreadMessage> messageThread, List<TaskWorkflowState> workflowState) {
        this.id = id;
        this.workflowSequence = workflowSequence;
        this.messageSequence = messageSequence;
        this.taskIdentifier = taskIdentifier;
        this.task = task;
        this.messageThread = messageThread;
        this.workflowState = workflowState;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWorkflowSequence() {
        return this.workflowSequence;
    }

    public void setWorkflowSequence(Integer workflowSequence) {
        this.workflowSequence = workflowSequence;
    }

    public Integer getMessageSequence() {
        return this.messageSequence;
    }

    public void setMessageSequence(Integer messageSequence) {
        this.messageSequence = messageSequence;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<TaskThreadMessage> getMessageThread() {
        return this.messageThread;
    }

    public void setMessageThread(List<TaskThreadMessage> messageThread) {
        this.messageThread = messageThread;
    }

    public List<TaskWorkflowState> getWorkflowState() {
        return this.workflowState;
    }

    public void setWorkflowState(List<TaskWorkflowState> workflowState) {
        this.workflowState = workflowState;
    }

    public TaskBacklog id(String id) {
        this.id = id;
        return this;
    }

    public TaskBacklog workflowSequence(Integer workflowSequence) {
        this.workflowSequence = workflowSequence;
        return this;
    }

    public TaskBacklog messageSequence(Integer messageSequence) {
        this.messageSequence = messageSequence;
        return this;
    }

    public TaskBacklog taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public TaskBacklog task(Task task) {
        this.task = task;
        return this;
    }

    public TaskBacklog messageThread(List<TaskThreadMessage> messageThread) {
        this.messageThread = messageThread;
        return this;
    }

    public TaskBacklog workflowState(List<TaskWorkflowState> workflowState) {
        this.workflowState = workflowState;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskBacklog)) {
            return false;
        }
        TaskBacklog taskBacklog = (TaskBacklog) o;
        return Objects.equals(id, taskBacklog.id) && Objects.equals(workflowSequence, taskBacklog.workflowSequence) && Objects.equals(messageSequence, taskBacklog.messageSequence) && Objects.equals(taskIdentifier, taskBacklog.taskIdentifier) && Objects.equals(task, taskBacklog.task) && Objects.equals(messageThread, taskBacklog.messageThread) && Objects.equals(workflowState, taskBacklog.workflowState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflowSequence, messageSequence, taskIdentifier, task, messageThread, workflowState);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", workflowSequence='" + getWorkflowSequence() + "'" +
            ", messageSequence='" + getMessageSequence() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", task='" + getTask() + "'" +
            ", messageThread='" + getMessageThread() + "'" +
            ", workflowState='" + getWorkflowState() + "'" +
            "}";
    }
    
    
    
}
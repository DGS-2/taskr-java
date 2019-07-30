package micf.taskr.domain.task;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
// import java.util.Set;
import java.util.Objects;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    // foreign key to task_classification table
    @NotBlank(message = "Classification cannot be left blank")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classification", referencedColumnName = "id")
    private TaskClassification classification;

    @NotBlank(message = "Title cannot be left blank")
    private String title;

    @NotBlank(message = "Description cannot be left blank")
    private String description;

    // foreign key to task_attachments table
    // private Set<TaskAttachment> attachments;

    @CreatedDate
    private Date createdDate;

    private Date updatedAt;

    @NotBlank(message = "Due date cannot be left blank")
    private Date dueDate;

    // needs to be a foreign key to Users table
    @CreatedBy
    private String createdBy;

    // This needs to be a foreign key to the Users table
    private String assignedTo;

    private String parentTask;

    private Integer priority;

    //ManyToOne with Backlog
    @Column(updatable = false)
    private String taskIdentifier;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "task")
    //
    private TaskBacklog backlog;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public Task() {
    }

    public Task(String id, TaskClassification classification, String title, String description, Date createdDate, Date updatedAt, Date dueDate, String createdBy, String assignedTo, String parentTask, Integer priority, String taskIdentifier, TaskBacklog backlog) {
        this.id = id;
        this.classification = classification;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.dueDate = dueDate;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.parentTask = parentTask;
        this.priority = priority;
        this.taskIdentifier = taskIdentifier;
        this.backlog = backlog;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskClassification getClassification() {
        return this.classification;
    }

    public void setClassification(TaskClassification classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getParentTask() {
        return this.parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public TaskBacklog getBacklog() {
        return this.backlog;
    }

    public void setBacklog(TaskBacklog backlog) {
        this.backlog = backlog;
    }

    public Task id(String id) {
        this.id = id;
        return this;
    }

    public Task classification(TaskClassification classification) {
        this.classification = classification;
        return this;
    }

    public Task title(String title) {
        this.title = title;
        return this;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public Task createdDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Task updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Task dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Task createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Task assignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Task parentTask(String parentTask) {
        this.parentTask = parentTask;
        return this;
    }

    public Task priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Task taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public Task backlog(TaskBacklog backlog) {
        this.backlog = backlog;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(classification, task.classification) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(createdDate, task.createdDate) && Objects.equals(updatedAt, task.updatedAt) && Objects.equals(dueDate, task.dueDate) && Objects.equals(createdBy, task.createdBy) && Objects.equals(assignedTo, task.assignedTo) && Objects.equals(parentTask, task.parentTask) && Objects.equals(priority, task.priority) && Objects.equals(taskIdentifier, task.taskIdentifier) && Objects.equals(backlog, task.backlog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classification, title, description, createdDate, updatedAt, dueDate, createdBy, assignedTo, parentTask, priority, taskIdentifier, backlog);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", classification='" + getClassification() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", assignedTo='" + getAssignedTo() + "'" +
            ", parentTask='" + getParentTask() + "'" +
            ", priority='" + getPriority() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", backlog='" + getBacklog() + "'" +
            "}";
    }

}
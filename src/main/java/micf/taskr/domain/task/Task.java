package micf.taskr.domain.task;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import micf.taskr.domain.user.User;
import micf.taskr.domain.workflow.Workflow;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String taskIdentifier;

    private String classification;

    @NotBlank(message = "Title cannot be left blank")
    private String title;

    @NotBlank(message = "Description cannot be left blank")
    private String description;

    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date due_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User created_by;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User assigned_to;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "task")
    @JsonIgnore
    private TaskThread message_thread;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
    @JsonIgnore
    private Workflow workflow;

    private Date updated_at;

    @PrePersist
    protected void onCreate(){
        this.created_date = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }



    public Task() {
    }

    public Task(Long id, String taskIdentifier, String classification, String title, String description, Date created_date, Date due_date, User created_by, User assigned_to, TaskThread message_thread, Workflow workflow, Date updated_at) {
        this.id = id;
        this.taskIdentifier = taskIdentifier;
        this.classification = classification;
        this.title = title;
        this.description = description;
        this.created_date = created_date;
        this.due_date = due_date;
        this.created_by = created_by;
        this.assigned_to = assigned_to;
        this.message_thread = message_thread;
        this.workflow = workflow;
        this.updated_at = updated_at;
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

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
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

    public Date getCreated_date() {
        return this.created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getDue_date() {
        return this.due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public User getCreated_by() {
        return this.created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public User getAssigned_to() {
        return this.assigned_to;
    }

    public void setAssigned_to(User assigned_to) {
        this.assigned_to = assigned_to;
    }

    public TaskThread getMessage_thread() {
        return this.message_thread;
    }

    public void setMessage_thread(TaskThread message_thread) {
        this.message_thread = message_thread;
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Task id(Long id) {
        this.id = id;
        return this;
    }

    public Task taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public Task classification(String classification) {
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

    public Task created_date(Date created_date) {
        this.created_date = created_date;
        return this;
    }

    public Task due_date(Date due_date) {
        this.due_date = due_date;
        return this;
    }

    public Task created_by(User created_by) {
        this.created_by = created_by;
        return this;
    }

    public Task assigned_to(User assigned_to) {
        this.assigned_to = assigned_to;
        return this;
    }

    public Task message_thread(TaskThread message_thread) {
        this.message_thread = message_thread;
        return this;
    }

    public Task workflow(Workflow workflow) {
        this.workflow = workflow;
        return this;
    }

    public Task updated_at(Date updated_at) {
        this.updated_at = updated_at;
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
        return Objects.equals(id, task.id) && Objects.equals(taskIdentifier, task.taskIdentifier) && Objects.equals(classification, task.classification) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(created_date, task.created_date) && Objects.equals(due_date, task.due_date) && Objects.equals(created_by, task.created_by) && Objects.equals(assigned_to, task.assigned_to) && Objects.equals(message_thread, task.message_thread) && Objects.equals(workflow, task.workflow) && Objects.equals(updated_at, task.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskIdentifier, classification, title, description, created_date, due_date, created_by, assigned_to, message_thread, workflow, updated_at);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", classification='" + getClassification() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", created_date='" + getCreated_date() + "'" +
            ", due_date='" + getDue_date() + "'" +
            ", created_by='" + getCreated_by() + "'" +
            ", assigned_to='" + getAssigned_to() + "'" +
            ", message_thread='" + getMessage_thread() + "'" +
            ", workflow='" + getWorkflow() + "'" +
            ", updated_at='" + getUpdated_at() + "'" +
            "}";
    }
    

}
package micf.taskr.domain.task;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

    private Integer TaskSequence = 0;

    private String taskIdentifier;

    //OneToOne with a task
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;
    //OneToMany 


    public TaskBacklog() {
    }

    public TaskBacklog(String id, Integer TaskSequence, String taskIdentifier, Task task) {
        this.id = id;
        this.TaskSequence = TaskSequence;
        this.taskIdentifier = taskIdentifier;
        this.task = task;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTaskSequence() {
        return this.TaskSequence;
    }

    public void setTaskSequence(Integer TaskSequence) {
        this.TaskSequence = TaskSequence;
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

    public TaskBacklog id(String id) {
        this.id = id;
        return this;
    }

    public TaskBacklog TaskSequence(Integer TaskSequence) {
        this.TaskSequence = TaskSequence;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskBacklog)) {
            return false;
        }
        TaskBacklog taskBacklog = (TaskBacklog) o;
        return Objects.equals(id, taskBacklog.id) && Objects.equals(TaskSequence, taskBacklog.TaskSequence) && Objects.equals(taskIdentifier, taskBacklog.taskIdentifier) && Objects.equals(task, taskBacklog.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, TaskSequence, taskIdentifier, task);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", TaskSequence='" + getTaskSequence() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", task='" + getTask() + "'" +
            "}";
    }
    
}
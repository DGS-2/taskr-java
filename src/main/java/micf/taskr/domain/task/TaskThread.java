package micf.taskr.domain.task;

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
@Table(name = "task_thread")
public class TaskThread {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer messageSequence = 0;

    private String taskIdentifier;

    //OneToOne with Task
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    //OneToMany with Messages
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "taskThread", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private List<TaskThreadMessage> taskMessages = new ArrayList<>();



    public TaskThread() {
    }

    public TaskThread(Long id, Integer messageSequence, String taskIdentifier, Task task, List<TaskThreadMessage> taskMessages) {
        this.id = id;
        this.messageSequence = messageSequence;
        this.taskIdentifier = taskIdentifier;
        this.task = task;
        this.taskMessages = taskMessages;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TaskThreadMessage> getTaskMessages() {
        return this.taskMessages;
    }

    public void setTaskMessages(List<TaskThreadMessage> taskMessages) {
        this.taskMessages = taskMessages;
    }

    public TaskThread id(Long id) {
        this.id = id;
        return this;
    }

    public TaskThread messageSequence(Integer messageSequence) {
        this.messageSequence = messageSequence;
        return this;
    }

    public TaskThread taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    public TaskThread task(Task task) {
        this.task = task;
        return this;
    }

    public TaskThread taskMessages(List<TaskThreadMessage> taskMessages) {
        this.taskMessages = taskMessages;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskThread)) {
            return false;
        }
        TaskThread taskThread = (TaskThread) o;
        return Objects.equals(id, taskThread.id) && Objects.equals(messageSequence, taskThread.messageSequence) && Objects.equals(taskIdentifier, taskThread.taskIdentifier) && Objects.equals(task, taskThread.task) && Objects.equals(taskMessages, taskThread.taskMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageSequence, taskIdentifier, task, taskMessages);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", messageSequence='" + getMessageSequence() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            ", task='" + getTask() + "'" +
            ", taskMessages='" + getTaskMessages() + "'" +
            "}";
    }
    
}
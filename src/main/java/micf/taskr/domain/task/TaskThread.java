package micf.taskr.domain.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="task_thread")
public class TaskThread {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(mappedBy = "messageThread") 
    private Task task_thread_id;

    @OneToMany(mappedBy = "thread_id")
    private List<TaskThreadMessage> messages;

    private String taskIdentifier;


    public TaskThread() {
    }

    public TaskThread(String id, Task task_thread_id, List<TaskThreadMessage> messages, String taskIdentifier) {
        this.id = id;
        this.task_thread_id = task_thread_id;
        this.messages = messages;
        this.taskIdentifier = taskIdentifier;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getTask_thread_id() {
        return this.task_thread_id;
    }

    public void setTask_thread_id(Task task_thread_id) {
        this.task_thread_id = task_thread_id;
    }

    public List<TaskThreadMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(List<TaskThreadMessage> messages) {
        this.messages = messages;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public TaskThread id(String id) {
        this.id = id;
        return this;
    }

    public TaskThread task_thread_id(Task task_thread_id) {
        this.task_thread_id = task_thread_id;
        return this;
    }

    public TaskThread messages(List<TaskThreadMessage> messages) {
        this.messages = messages;
        return this;
    }

    public TaskThread taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
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
        return Objects.equals(id, taskThread.id) && Objects.equals(task_thread_id, taskThread.task_thread_id) && Objects.equals(messages, taskThread.messages) && Objects.equals(taskIdentifier, taskThread.taskIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task_thread_id, messages, taskIdentifier);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", task_thread_id='" + getTask_thread_id() + "'" +
            ", messages='" + getMessages() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            "}";
    }
    
}
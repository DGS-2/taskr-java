package micf.taskr.domain.task;

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
@Table(name = "task_thread_message")
public class TaskThreadMessage {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false, unique = true)
    private String messageSequence;
    
    private Date date = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User message_from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User message_to;

    private String message;
    
    //ManyToOne with TaskThread
    @ManyToOne
    @JoinColumn(name = "task_thread_id", updatable = false, nullable = false)
    @JsonIgnore
    private TaskThread taskThread;

    @Column(updatable = false)
    private String taskIdentifier;



    public TaskThreadMessage() {
    }

    public TaskThreadMessage(Long id, String messageSequence, Date date, User message_from, User message_to, String message, TaskThread taskThread, String taskIdentifier) {
        this.id = id;
        this.messageSequence = messageSequence;
        this.date = date;
        this.message_from = message_from;
        this.message_to = message_to;
        this.message = message;
        this.taskThread = taskThread;
        this.taskIdentifier = taskIdentifier;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageSequence() {
        return this.messageSequence;
    }

    public void setMessageSequence(String messageSequence) {
        this.messageSequence = messageSequence;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getMessage_from() {
        return this.message_from;
    }

    public void setMessage_from(User message_from) {
        this.message_from = message_from;
    }

    public User getMessage_to() {
        return this.message_to;
    }

    public void setMessage_to(User message_to) {
        this.message_to = message_to;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TaskThread getTaskThread() {
        return this.taskThread;
    }

    public void setTaskThread(TaskThread taskThread) {
        this.taskThread = taskThread;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public TaskThreadMessage id(Long id) {
        this.id = id;
        return this;
    }

    public TaskThreadMessage messageSequence(String messageSequence) {
        this.messageSequence = messageSequence;
        return this;
    }

    public TaskThreadMessage date(Date date) {
        this.date = date;
        return this;
    }

    public TaskThreadMessage message_from(User message_from) {
        this.message_from = message_from;
        return this;
    }

    public TaskThreadMessage message_to(User message_to) {
        this.message_to = message_to;
        return this;
    }

    public TaskThreadMessage message(String message) {
        this.message = message;
        return this;
    }

    public TaskThreadMessage taskThread(TaskThread taskThread) {
        this.taskThread = taskThread;
        return this;
    }

    public TaskThreadMessage taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskThreadMessage)) {
            return false;
        }
        TaskThreadMessage taskThreadMessage = (TaskThreadMessage) o;
        return Objects.equals(id, taskThreadMessage.id) && Objects.equals(messageSequence, taskThreadMessage.messageSequence) && Objects.equals(date, taskThreadMessage.date) && Objects.equals(message_from, taskThreadMessage.message_from) && Objects.equals(message_to, taskThreadMessage.message_to) && Objects.equals(message, taskThreadMessage.message) && Objects.equals(taskThread, taskThreadMessage.taskThread) && Objects.equals(taskIdentifier, taskThreadMessage.taskIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageSequence, date, message_from, message_to, message, taskThread, taskIdentifier);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", messageSequence='" + getMessageSequence() + "'" +
            ", date='" + getDate() + "'" +
            ", message_from='" + getMessage_from() + "'" +
            ", message_to='" + getMessage_to() + "'" +
            ", message='" + getMessage() + "'" +
            ", taskThread='" + getTaskThread() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            "}";
    }
    

}
package micf.taskr.domain.task;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="task_thread_message")
public class TaskThreadMessage {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @ManyToOne
    private TaskThread thread_id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    private String messageTo;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "task_backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private TaskBacklog backlog;


    public TaskThreadMessage() {
    }

    public TaskThreadMessage(String id, TaskThread thread_id, String createdBy, Date createdDate, String messageTo, String message, TaskBacklog backlog) {
        this.id = id;
        this.thread_id = thread_id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.messageTo = messageTo;
        this.message = message;
        this.backlog = backlog;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskThread getThread_id() {
        return this.thread_id;
    }

    public void setThread_id(TaskThread thread_id) {
        this.thread_id = thread_id;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessageTo() {
        return this.messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TaskBacklog getBacklog() {
        return this.backlog;
    }

    public void setBacklog(TaskBacklog backlog) {
        this.backlog = backlog;
    }

    public TaskThreadMessage id(String id) {
        this.id = id;
        return this;
    }

    public TaskThreadMessage thread_id(TaskThread thread_id) {
        this.thread_id = thread_id;
        return this;
    }

    public TaskThreadMessage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public TaskThreadMessage createdDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public TaskThreadMessage messageTo(String messageTo) {
        this.messageTo = messageTo;
        return this;
    }

    public TaskThreadMessage message(String message) {
        this.message = message;
        return this;
    }

    public TaskThreadMessage backlog(TaskBacklog backlog) {
        this.backlog = backlog;
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
        return Objects.equals(id, taskThreadMessage.id) && Objects.equals(thread_id, taskThreadMessage.thread_id) && Objects.equals(createdBy, taskThreadMessage.createdBy) && Objects.equals(createdDate, taskThreadMessage.createdDate) && Objects.equals(messageTo, taskThreadMessage.messageTo) && Objects.equals(message, taskThreadMessage.message) && Objects.equals(backlog, taskThreadMessage.backlog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thread_id, createdBy, createdDate, messageTo, message, backlog);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", thread_id='" + getThread_id() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", messageTo='" + getMessageTo() + "'" +
            ", message='" + getMessage() + "'" +
            ", backlog='" + getBacklog() + "'" +
            "}";
    }
    
}
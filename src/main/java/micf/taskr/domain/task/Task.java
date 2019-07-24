package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    // foreign key to task_classification table
    @ManyToOne
    @JoinColumn
    private TaskClassification classification;

    private String title;

    private String description;

    // foreign key to task_attachments table
    private Set<TaskAttachment> attachments;

    @CreatedDate
    private Date createdDate;

    private Date dueDate;

    // needs to be a foreign key to Users table
    @CreatedBy
    private String createdBy;

    // This needs to be a foreign key to the Users table
    private String assignedTo;

    private String parentTask;

    @OneToOne
    @JoinColumn
    private TaskThread messageThread;

    public Task() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskClassification getClassification() {
        return classification;
    }

    public void setClassification(TaskClassification classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TaskAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<TaskAttachment> attachments) {
        this.attachments = attachments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getParentTask(){
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public TaskThread getMessageThread() {
        return messageThread;
    }

    public void setMessageThread(TaskThread messageThread) {
        this.messageThread = messageThread;
    }
}
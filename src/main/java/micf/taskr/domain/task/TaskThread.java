package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

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

    public TaskThread() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getTaskId() {
        return task_thread_id;
    }

    public void setTaskId(Task task_thread_id) {
        this.task_thread_id = task_thread_id;
    }

    public List<TaskThreadMessage> getThreadMessage() {
        return messages;
    }

    public void addThreadMessage(List<TaskThreadMessage> messages) {
        this.messages = messages;
    }
}
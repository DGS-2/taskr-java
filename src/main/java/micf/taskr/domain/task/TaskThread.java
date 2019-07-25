package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name="task_thread")
public class TaskThread {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(targetEntity = Task.class)
    private String task_id;

    @OneToMany
    @JoinColumn
    private Set<TaskThreadMessage> message;

    public TaskThread() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
    }

    public Set<TaskThreadMessage> getThreadMessage() {
        return message;
    }

    public void addThreadMessage(Set<TaskThreadMessage> message) {
        this.message = message;
    }
}
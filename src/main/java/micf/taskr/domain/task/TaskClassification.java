package micf.taskr.domain.task;

import java.util.Objects;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
// import javax.persistence.PrePersist;
// import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_classification")
public class TaskClassification {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(mappedBy = "classification")
    private Task classification;

    public TaskClassification() {}

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Task getClassification() {
        return classification;
    }

    public void setClassification(Task classification) {
        this.classification = classification;
    }

    private String taskIdentifier;


    public TaskClassification(String id, Task classification, String taskIdentifier) {
        this.id = id;
        this.classification = classification;
        this.taskIdentifier = taskIdentifier;
    }

    public String getTaskIdentifier() {
        return this.taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public TaskClassification id(String id) {
        this.id = id;
        return this;
    }

    public TaskClassification classification(Task classification) {
        this.classification = classification;
        return this;
    }

    public TaskClassification taskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskClassification)) {
            return false;
        }
        TaskClassification taskClassification = (TaskClassification) o;
        return Objects.equals(id, taskClassification.id) && Objects.equals(classification, taskClassification.classification) && Objects.equals(taskIdentifier, taskClassification.taskIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classification, taskIdentifier);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", classification='" + getClassification() + "'" +
            ", taskIdentifier='" + getTaskIdentifier() + "'" +
            "}";
    }

}
package micf.taskr.domain.task;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="task_classification")
public class TaskClassification {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "classification")
    private String classification;

    public TaskClassification() {}

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
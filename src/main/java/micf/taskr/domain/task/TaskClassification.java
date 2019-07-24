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
}
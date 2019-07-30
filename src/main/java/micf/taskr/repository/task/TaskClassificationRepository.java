package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskClassification;

@Repository
public interface TaskClassificationRepository extends JpaRepository<TaskClassification, Long> {
    
}
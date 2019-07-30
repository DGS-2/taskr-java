package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskWorkflowState;

@Repository
public interface TaskWorkflowStateRepository extends JpaRepository<TaskWorkflowState, Long> {
    
}
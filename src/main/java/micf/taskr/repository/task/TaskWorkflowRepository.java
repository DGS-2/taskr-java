package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskWorkflow;

@Repository
public interface TaskWorkflowRepository extends JpaRepository<TaskWorkflow, Long> {
    
}
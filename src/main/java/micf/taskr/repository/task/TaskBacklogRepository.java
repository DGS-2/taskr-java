package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskBacklog;

@Repository
public interface TaskBacklogRepository extends JpaRepository<TaskBacklog, Long> {
    
    TaskBacklog findByTaskIdentifier(String identifier);
}
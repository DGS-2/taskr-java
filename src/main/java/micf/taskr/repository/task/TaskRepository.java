package micf.taskr.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTaskIdentifier(String taskId);

    @Override
    List<Task> findAll();

    // List<Task> findByCreated_by(String username);

    // List<Task> findByAssigned_to(String username);
}
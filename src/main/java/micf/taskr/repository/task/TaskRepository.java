package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;

import micf.taskr.domain.task.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll();

    Task findById(String id);
}
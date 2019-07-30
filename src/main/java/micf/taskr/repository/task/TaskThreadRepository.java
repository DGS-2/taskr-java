package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskThread;

@Repository
public interface TaskThreadRepository extends JpaRepository<TaskThread, Long> {

}
package micf.taskr.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.TaskThreadMessage;

@Repository
public interface TaskThreadMessageRepository extends JpaRepository<TaskThreadMessage, Long> {

}
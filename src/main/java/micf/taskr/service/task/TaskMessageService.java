package micf.taskr.service.task;

import java.util.List;

import micf.taskr.domain.task.TaskThreadMessage;

public interface TaskMessageService {

    TaskThreadMessage findAll();

    List<TaskThreadMessage> findByBacklogId(String id);
}
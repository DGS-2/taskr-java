package micf.taskr.service.task;

import micf.taskr.domain.task.TaskThreadMessage;

public interface TaskMessageService {

    TaskThreadMessage findAll();

    TaskThreadMessage findByBacklogId(String id);
}
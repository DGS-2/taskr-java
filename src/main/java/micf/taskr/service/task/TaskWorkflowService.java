package micf.taskr.service.task;

import micf.taskr.domain.task.TaskWorkflowState;

public interface TaskWorkflowService {

    TaskWorkflowState findAll();
}
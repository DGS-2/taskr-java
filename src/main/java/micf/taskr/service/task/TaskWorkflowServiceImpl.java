package micf.taskr.service.task;

import java.util.List;

import org.springframework.stereotype.Service;

import micf.taskr.domain.task.TaskBacklog;
import micf.taskr.domain.task.TaskWorkflowState;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.repository.task.TaskBacklogRepository;
import micf.taskr.repository.task.TaskWorkflowStateRepository;

@Service
public class TaskWorkflowServiceImpl implements TaskWorkflowService {

    private final TaskWorkflowStateRepository taskWorkflowStateRepository;

    private final TaskBacklogRepository taskBacklogRepository;

    public TaskWorkflowServiceImpl(TaskWorkflowStateRepository taskWorkflowStateRepository, TaskBacklogRepository taskBacklogRepository) {
        this.taskBacklogRepository = taskBacklogRepository;
        this.taskWorkflowStateRepository = taskWorkflowStateRepository;
    }

    @Override
    public TaskWorkflowState findAll() {
        return null;
    }

    public TaskWorkflowState addWorkflowState(String taskIdentifier, TaskWorkflowState workflow) {
        try {
            // Exceptions: Task not found
            
            // Workflow State must be added to a specific task, task != null, TaskBacklog exists
            TaskBacklog  backlog = taskBacklogRepository.findByTaskIdentifier(taskIdentifier);
            // Set the TaskBacklog to WorkflowState
            workflow.setBacklog(backlog);
            // Task sequence = taskIdentifier-sequence to grab id witin the task, not the database id
            Integer currentSequence = backlog.getWorkflowSequence();        
            // Sequence must continue to increment
            currentSequence++;
            // Add sequenence to WorkflowState
            workflow.setTaskSequence(taskIdentifier + "-" + currentSequence);
            workflow.setTaskIdentifier(taskIdentifier);
            
            if(workflow.getState() == "" || workflow.getState() == null) {
                workflow.setState("WAITING_USER_ACTION");
            }
        } catch(Exception e) {
            throw new TaskNotFoundException("Task not found.");
        }

        return null;
    }

	public List<TaskWorkflowState> findByBacklogId(String backlog_id) {
		return taskWorkflowStateRepository.findByTaskIdentifier(backlog_id);
	}

    public TaskWorkflowState findWorkflowBySequence(String backlog_id, String workflow_id) {
        
        TaskBacklog backlog = taskBacklogRepository.findByTaskIdentifier(backlog_id);

        if(backlog == null) throw new TaskNotFoundException("Task not found.");

        TaskWorkflowState state = taskWorkflowStateRepository.findByTaskSequence(workflow_id);
        
        if(state == null) throw new TaskNotFoundException("Task not found.");

        if(!state.getTaskIdentifier().equals(backlog_id)) throw new TaskNotFoundException("Task not found.");

        return state;
    }

    public TaskWorkflowState updateWorkflowBySequence(TaskWorkflowState updatedState, String backlog_id, String workflow_id){
        TaskWorkflowState state = findWorkflowBySequence(backlog_id, workflow_id);

        state = updatedState;

        return taskWorkflowStateRepository.save(state);
    }

    public void deleteWorkflowBySeuqence(String backlog_id, String workflow_id) {
        TaskWorkflowState state = findWorkflowBySequence(backlog_id, workflow_id);

        taskWorkflowStateRepository.delete(state);
    }
}
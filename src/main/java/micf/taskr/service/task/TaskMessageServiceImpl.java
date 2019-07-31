package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.task.Task;
import micf.taskr.domain.task.TaskBacklog;
import micf.taskr.domain.task.TaskThreadMessage;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.repository.task.TaskBacklogRepository;
import micf.taskr.repository.task.TaskRepository;
import micf.taskr.repository.task.TaskThreadMessageRepository;

@Service
public class TaskMessageServiceImpl implements TaskMessageService {

    private final TaskThreadMessageRepository taskThreadMessageRepository;

    private final TaskRepository taskRepository;

    private final TaskBacklogRepository taskBacklogRepository;

    @Autowired
    public TaskMessageServiceImpl(TaskThreadMessageRepository taskThreadMessageRepository,
            TaskBacklogRepository taskBacklogRepository, TaskRepository taskRepository) {
        this.taskBacklogRepository = taskBacklogRepository;
        this.taskThreadMessageRepository = taskThreadMessageRepository;
        this.taskRepository = taskRepository;
    }

    public TaskThreadMessage addThreadMessage(String taskIdentifier, TaskThreadMessage message) {
        // Exceptions: Task not found

        // Thread Message must be added to a specific task, task != null, TaskBacklog exists
        TaskBacklog  backlog = taskBacklogRepository.findByTaskIdentifier(taskIdentifier);
        // Set the TaskBacklog to TaskThreadMessage
        message.setBacklog(backlog);
        // Task sequence = taskIdentifier-sequence to grab id witin the task, not the database id
        Integer currentSequence = backlog.getMessageSequence();        
        // Sequence must continue to increment
        currentSequence++;
        // Add sequenence to TaskThreadMessage
        message.setTaskSequence(taskIdentifier + "-" + currentSequence);
        message.setTaskIdentifier(taskIdentifier);
        
        return null;
    }

    @Override
    public TaskThreadMessage findAll() {
        return null;
    }

    @Override
    public List<TaskThreadMessage> findByBacklogId(String id) {
        Task task = taskRepository.findByTaskIdentifier(id);

        if(task == null) throw new TaskNotFoundException("Task with ID: '" + id + "' not found.");

        return taskThreadMessageRepository.findByTaskIdentifier(id);
    }

    
}
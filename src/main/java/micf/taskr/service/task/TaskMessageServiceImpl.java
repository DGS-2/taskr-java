package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.task.TaskBacklog;
import micf.taskr.domain.task.TaskThreadMessage;
import micf.taskr.repository.task.TaskBacklogRepository;
import micf.taskr.repository.task.TaskThreadMessageRepository;

@Service
public class TaskMessageServiceImpl implements TaskMessageService {

    private final TaskThreadMessageRepository taskThreadMessageRepository;

    private final TaskBacklogRepository taskBacklogRepository;

    @Autowired
    public TaskMessageServiceImpl(TaskThreadMessageRepository taskThreadMessageRepository,
            TaskBacklogRepository taskBacklogRepository) {
        this.taskBacklogRepository = taskBacklogRepository;
        this.taskThreadMessageRepository = taskThreadMessageRepository;
    }

    public TaskThreadMessage addThreadMessage(String taskIdentifier, TaskThreadMessage message) {
        // Exceptions: Task not found

        // Thread Message must be added to a specific task, task != null, TaskBacklog exists
        TaskBacklog  backlog = taskBacklogRepository.findByTaskIdentifier(taskIdentifier);
        // Set the TaskBacklog to TaskThreadMessage
        message.setBacklog(backlog);
        // Task sequence = taskIdentifier-sequence to grab id witin the task, not the database id
        Integer currentSequence = backlog.getTaskSequence();        
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
        return taskThreadMessageRepository.findByTaskIdentifier(id);
    }

    
}
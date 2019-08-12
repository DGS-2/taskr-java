package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.task.TaskThread;
import micf.taskr.domain.task.TaskThreadMessage;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.repository.task.TaskThreadMessageRepository;

@Service
public class TaskThreadMessageService {

    @Autowired
    private TaskThreadMessageRepository taskThreadMessageRepository;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    public TaskThreadMessage addMessageToThread(String taskIdentifier, TaskThreadMessage message, String username) {

        // Messages need to be added to the correct thread
        TaskThread messageThread = taskServiceImpl.findTaskByIdentifier(taskIdentifier, username).getMessage_thread();

        // System.out.println(messageThread);
        message.setTaskThread(messageThread);

        Integer ThreadSequence = messageThread.getMessageSequence();
        // Update message sequence
        ThreadSequence ++;

        messageThread.setMessageSequence(ThreadSequence);

        // Add sequence to message
        message.setMessageSequence(messageThread.getTaskIdentifier() + "-" + ThreadSequence);
        message.setMessage(message.getMessage());
        message.setTaskIdentifier(taskIdentifier);

        // Save the message
        return taskThreadMessageRepository.save(message);
    }

    public List<TaskThreadMessage> findThreadById(String taskIdentifier, String username) {
        taskServiceImpl.findTaskByIdentifier(taskIdentifier, username);

        return taskThreadMessageRepository.findByTaskIdentifier(taskIdentifier);
    }

    public TaskThreadMessage findMessageBySequence(String taskIdentifier, String message_sequence, String username) {

        // Search in the existing message thread
        TaskThreadMessage message = taskThreadMessageRepository.findByMessageSequence(message_sequence);

        if(message == null) {
            throw new TaskNotFoundException("Message could not be retrieved.");
        }

        if(!message.getTaskIdentifier().equals(taskIdentifier)) {
            throw new TaskNotFoundException("Message does not exist");
        }

        return message;
    }

    public TaskThreadMessage updateMessageBySequence(TaskThreadMessage updatedMessage, String taskIdentifier, String message_sequence, String username) {
        
        TaskThreadMessage message = findMessageBySequence(taskIdentifier, message_sequence, username);

        message = updatedMessage;

        return taskThreadMessageRepository.save(message);
    }

    public void deleteMessageBySequence(String taskIdentifier, String message_sequence, String username) {
        TaskThreadMessage message = findMessageBySequence(taskIdentifier, message_sequence, username);

        taskThreadMessageRepository.delete(message);
    }
}
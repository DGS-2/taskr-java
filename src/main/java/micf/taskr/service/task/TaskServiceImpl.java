package micf.taskr.service.task;


import micf.taskr.domain.user.User;
import micf.taskr.domain.workflow.Workflow;
import micf.taskr.domain.workflow.WorkflowHistory;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.exception.task.TaskTitleException;
import micf.taskr.repository.task.TaskRepository;
import micf.taskr.repository.task.TaskThreadRepository;
import micf.taskr.repository.user.UserRepository;
import micf.taskr.repository.workflow.WorkflowRepository;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.task.Task;
import micf.taskr.domain.task.TaskThread;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskThreadRepository taskThreadRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private WorkflowRepository workflowRepository;

    public Task createOrUpdate(Task task, String username) {

        User requestingUser = userRepository.findByUsername(username);        
        
        if(task.getId() != null) {            
            // Only task creator can update the task
            Task existingTask = taskRepository.findByTaskIdentifier(task.getTaskIdentifier());

            if( existingTask != null && ( !existingTask.getCreated_by().getId().equals( requestingUser.getId() ) ) ) {
                throw new TaskNotFoundException("Task with ID: '" + task.getTaskIdentifier() + "' is not associated with your account.");
            } else if(existingTask == null) {
                throw new TaskNotFoundException("Task with ID: '" + task.getTaskIdentifier() + "' cannot be updated. It does not exist.");
            }
        }

        try {

            task.setCreated_by(requestingUser);
            task.setTaskIdentifier(task.getTaskIdentifier());
            task.setClassification(task.getClassification());
            task.setDescription(task.getDescription());

            if(task.getAssigned_to() != null) {
                task.setAssigned_to(task.getAssigned_to());
            }

            if(task.getId() != null) {
                task.setMessage_thread(taskThreadRepository.findByTaskIdentifier(task.getTaskIdentifier()));
                task.setWorkflow(workflowRepository.findByTaskIdentifier(task.getTaskIdentifier()));
            }

            if(task.getId() == null) {               

                // Create new message thread
                TaskThread message_thread = new TaskThread();
                task.setMessage_thread(message_thread);
                message_thread.setTask(task);
                message_thread.setTaskIdentifier(task.getTaskIdentifier());

                // Create new workflow
                Workflow workflow = new Workflow();
                workflow.setCreated_by(requestingUser);
                workflow.setTaskIdentifier(task.getTaskIdentifier());
                workflow.setTask(task);
                task.setWorkflow(workflow);
                
                WorkflowHistory history = new WorkflowHistory();
                history.setWorkflow(workflow);
                history.setTaskIdentifier(task.getTaskIdentifier());
                workflow.setWorkflowHistory(history);

            }            

            return taskRepository.save(task);

        } catch(Exception e) {
            throw new TaskTitleException("Task with ID: '" + task.getTaskIdentifier() + "' already exists.");
        }
    }

    public Task findTaskByIdentifier(String taskIdentifier, String username) {
        
        User requestingUser = userRepository.findByUsername(username);

        Task task = taskRepository.findByTaskIdentifier(taskIdentifier);

        if(task == null) {
            throw new TaskTitleException("Task does not exist.");
        }

        if(task.getAssigned_to() != null) {
            if(!task.getAssigned_to().getId().equals(requestingUser.getId()) ) {
                throw new TaskNotFoundException("You do not have access to this task");
            }
        }

        if(!task.getCreated_by().getId().equals(requestingUser.getId()) ) {
            throw new TaskNotFoundException("You do not have access to this task");
        }

        return task;
    }
    
    // public List<Task> findAllCreatedTasks(String username) {
    //     return taskRepository.findByCreated_by(username);
    // }

    // public List<Task> findAllAssignedTasks(String username) {
    //     return taskRepository.findByAssigned_to(username);
    // }

    public void deleteTaskByIdentifier(String taskIdentifier, String username) {
        taskRepository.delete(findTaskByIdentifier(taskIdentifier, username));
    }
}
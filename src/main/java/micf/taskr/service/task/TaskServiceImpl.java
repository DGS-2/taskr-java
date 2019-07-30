package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import micf.taskr.domain.task.Task;
import micf.taskr.domain.task.TaskBacklog;
import micf.taskr.exception.task.TaskTitleException;
import micf.taskr.repository.task.TaskBacklogRepository;
import micf.taskr.repository.task.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskBacklogRepository taskBacklogRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskBacklogRepository taskBacklogRepository){
        this.taskRepository = taskRepository;
        this.taskBacklogRepository = taskBacklogRepository;
    }


    public Task saveOrUpdateTask(Task task) {
        try{
            if(task.getId() == null){
                TaskBacklog taskBacklog = new TaskBacklog();
                task.setBacklog(taskBacklog);
                taskBacklog.setTask(task);
                taskBacklog.setTaskIdentifier(task.getTaskIdentifier());
            } 
            if(task.getId() != null) {
                task.setBacklog(taskBacklogRepository.findByTaskIdentifier(task.getTaskIdentifier()));
            }
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskTitleException("Task '" + task.getId() + "' already exists");
        }        
    }

    @Override
    @Transactional
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task findById(String id) {
        Task task = taskRepository.findById(id);

        if(task == null) throw new TaskTitleException("Task '" + id + "' does not exist");

        return task;
    }

    public void deleteTaskById(String id) {
        Task task = taskRepository.findById(id);

        if(task == null) throw new TaskTitleException("Cannot delete Task with id: '" + id + "'. Task does not exist");

        taskRepository.delete(task);
    }

}
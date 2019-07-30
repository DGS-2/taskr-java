package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import micf.taskr.domain.task.*;
import micf.taskr.exception.task.TaskTitleException;
import micf.taskr.repository.task.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task saveOrUpdateTask(Task task) {
        try{
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
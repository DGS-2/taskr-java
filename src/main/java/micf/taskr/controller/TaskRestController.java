package micf.taskr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micf.taskr.domain.task.*;
import micf.taskr.service.task.TaskService;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private TaskService taskService;

    @Autowired
    public TaskRestController(TaskService theTaskService) {
        taskService = theTaskService;
    }

    // Get all tasks
    @GetMapping("/tasks")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    // Get task by id
    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable String taskId) {
        Task theTask = taskService.findById(taskId);
        
        if(theTask == null) {
            throw new RuntimeException("Task id not found - " + taskId);
        }

        return theTask;
    }
}
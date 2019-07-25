package micf.taskr.controller;

import micf.taskr.domain.task.*;
import micf.taskr.service.task.TaskServiceImpl;

// import java.util.List;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class TaskRestController {
    private TaskServiceImpl taskService;

    @Autowired
    public TaskRestController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/test")
    public String test(){
        return "Welcome";
    }

    // Get all tasks
    @GetMapping("/tasks")
    public Collection<Task> findAll() {
        return taskService.findAll();
    }

    // Get task by id
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable String id) {
        Task theTask = taskService.findById(id);
        
        if(theTask == null) {
            throw new RuntimeException("Task id not found - " + id);
        }

        return theTask;
    }
}
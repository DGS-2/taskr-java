package micf.taskr.controller;

import micf.taskr.domain.task.*;
import micf.taskr.service.task.TaskServiceImpl;
import micf.taskr.validation.MapValidationError;

// import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Validation
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private TaskServiceImpl taskService;

    // inject service into controller
    @Autowired
    public TaskRestController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    // autowire validation error map from BindingResult->result
    @Autowired MapValidationError mapValidationError;

    // Create or update task
    @PostMapping("/create-task")
    public ResponseEntity<?> createNewTask(@Valid @RequestBody Task task, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);

        if(errorMap != null) return errorMap;

        Task newTask = taskService.saveOrUpdateTask(task);
        return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
    }

    // Get task by id
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable String id) {
        Task theTask = taskService.findById(id);
        
        if(theTask == null) {
            throw new RuntimeException("Task id not found - " + id);
        }

        return theTask;
    }

    // Get all tasks
    @GetMapping("/tasks")
    public Iterable<Task> findAll() {
        return taskService.findAll();
    }

    // Delete Task

    // Update Task
    
}
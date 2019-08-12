package micf.taskr.controller;

import java.security.Principal;
// import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micf.taskr.validation.MapValidationError;
import micf.taskr.domain.task.Task;
import micf.taskr.service.task.TaskServiceImpl;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/task")
public class TaskRestController {
    
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private MapValidationError mapValidationError;

    @PostMapping(path = "")
    public ResponseEntity<?> createNewTask(@Valid @RequestBody Task task, BindingResult result, Principal principal) {
        
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        Task newTask = taskServiceImpl.createOrUpdate(task, principal.getName());
        return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{taskIdentifier}")
    public ResponseEntity<?> getTaskById(@PathVariable String taskIdentifier, Principal principal) {

        Task task = taskServiceImpl.findTaskByIdentifier(taskIdentifier, principal.getName());

        return new ResponseEntity<Task>(task, HttpStatus.OK); 
    }

    // @GetMapping(path = "/all-assigned")
    // public List<Task> getAllAssignedTasks(Principal principal) {
        // Bring in User and perform match with User.username and principal.getName()
        // then return tasks that assignedTo are equal to principal
    //     return taskServiceImpl.findAllAssignedTasks(principal.getName());
    // }

    // @GetMapping(path = "/all-created")
    // public List<Task> getAllCreatedTasks(Principal principal) {
    //     return taskServiceImpl.findAllCreatedTasks(principal.getName());
    // }

    @DeleteMapping(path = "/{taskIdentifier}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskIdentifier, Principal principal) {
        taskServiceImpl.deleteTaskByIdentifier(taskIdentifier, principal.getName());

        return new ResponseEntity<String>("Task was deleted successfully", HttpStatus.OK);
    }
}
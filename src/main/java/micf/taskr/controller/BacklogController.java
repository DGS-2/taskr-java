package micf.taskr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import micf.taskr.domain.task.TaskThreadMessage;
import micf.taskr.domain.task.TaskWorkflowState;
import micf.taskr.service.task.TaskMessageServiceImpl;
import micf.taskr.service.task.TaskWorkflowServiceImpl;
import micf.taskr.validation.MapValidationError;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BacklogController {

    private final TaskMessageServiceImpl taskMessageServiceImpl;

    private final TaskWorkflowServiceImpl taskWorkflowServiceImpl;

    private final MapValidationError mapValidationError;

    @Autowired
    public BacklogController(TaskMessageServiceImpl taskMessageServiceImpl, TaskWorkflowServiceImpl taskWorkflowServiceImpl, MapValidationError mapValidationError){
        this.taskMessageServiceImpl = taskMessageServiceImpl;
        this.taskWorkflowServiceImpl = taskWorkflowServiceImpl;        
        this.mapValidationError = mapValidationError;
    }

    // Workflow routes

    @PostMapping("/update-backlog-workflow/{backlog_id}")
    public ResponseEntity<?> addWorkflowToBacklog(@Valid @RequestBody TaskWorkflowState workflow,
    BindingResult result, @PathVariable String backlog_id) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskWorkflowState workflowState = taskWorkflowServiceImpl.addWorkflowState(backlog_id, workflow);

        return new ResponseEntity<TaskWorkflowState>(workflowState, HttpStatus.CREATED);
    }

    @GetMapping("/get-task-workflow/{backlog_id}")
    public Iterable<TaskWorkflowState> getTaskWorkflow(@PathVariable String backlog_id) {
        return taskWorkflowServiceImpl.findByBacklogId(backlog_id);
    }

    // Message Thread routes

    @PostMapping("/update-backlog-message/{backlog_id}")
    public ResponseEntity<?> addMessageToBacklog(@Valid @RequestBody TaskThreadMessage message,
    BindingResult result, @PathVariable String backlog_id) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskThreadMessage threadMessage = taskMessageServiceImpl.addThreadMessage(backlog_id, message);

        return new ResponseEntity<TaskThreadMessage>(threadMessage, HttpStatus.CREATED);
    }

    @GetMapping("/get-task-message-thread/{backlog_id}")
    public Iterable<TaskThreadMessage> getTaskMessages(@PathVariable String backlog_id) {
        return taskMessageServiceImpl.findByBacklogId(backlog_id);
    }
}
package micf.taskr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("/get-task-workflow-item/{backlog_id}/{workflow_id}")
    public ResponseEntity<?> getTaskWorkflowItem(@PathVariable String backlog_id, @PathVariable String workflow_id) {
        TaskWorkflowState state = taskWorkflowServiceImpl.findWorkflowBySequence(backlog_id, workflow_id);

        return new ResponseEntity<TaskWorkflowState>(state, HttpStatus.OK);
    }

    @PatchMapping("/update-task-workflow-item/{backlog_id}/{workflow_id}")
    public ResponseEntity<?> updateTaskWorkflowItem(@PathVariable String backlog_id, @PathVariable String workflow_id, @Valid @RequestBody TaskWorkflowState updatedState, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskWorkflowState state = taskWorkflowServiceImpl.updateWorkflowBySequence(updatedState, backlog_id, workflow_id);

        return new ResponseEntity<TaskWorkflowState>(state, HttpStatus.OK);
    }

    @DeleteMapping("/delete-task-workflow-item/{backlog_id}/{workflow_id}")
    public ResponseEntity<?> deteleTaskWorkflowItem(@PathVariable String backlog_id, @PathVariable String workflow_id){
        taskWorkflowServiceImpl.deleteWorkflowBySeuqence(backlog_id, workflow_id);

        return new ResponseEntity<String>("Workflow item '" + workflow_id + "' was deleted.", HttpStatus.OK);
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

    @GetMapping("/get-task-message/{backlog_id}/{message_id}")
    public ResponseEntity<?> getTaskMessage(@PathVariable String backlog_id, @PathVariable String message_id) {
        TaskThreadMessage message = taskMessageServiceImpl.findMessageBySequence(backlog_id, message_id);

        return new ResponseEntity<TaskThreadMessage>(message, HttpStatus.OK);
    }

    @PatchMapping("/update-task-message/{backlog_id}/{message_id}")
    public ResponseEntity<?> updateTaskMessage(@PathVariable String backlog_id, @PathVariable String message_id, @Valid @RequestBody TaskThreadMessage updatedMessage, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskThreadMessage message = taskMessageServiceImpl.updateMessageBySequence(updatedMessage, backlog_id, message_id);

        return new ResponseEntity<TaskThreadMessage>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete-task-message/{backlog_id}/{message_id}")
    public ResponseEntity<?> deleteTaskMessage(@PathVariable String backlog_id, @PathVariable String message_id){
        taskMessageServiceImpl.deleteMessageBySequence(backlog_id, message_id);

        return new ResponseEntity<String>("Message: '" + message_id + "' was deleted.", HttpStatus.OK);
    }

}
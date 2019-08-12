package micf.taskr.controller;

import java.security.Principal;
import java.util.List;

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

import micf.taskr.domain.workflow.WorkflowItem;
import micf.taskr.service.workflow.WorkflowItemService;
import micf.taskr.validation.MapValidationError;


@RestController
@RequestMapping("/api/workflow")
@CrossOrigin
public class WorkflowController {

    @Autowired
    private WorkflowItemService workflowService;

    @Autowired
    private MapValidationError mapValidationError;

    @PostMapping(path = "/{taskIdentifier}")
    public ResponseEntity<?> addStatetoWorkflow(@Valid @RequestBody WorkflowItem workflowItem, BindingResult result, @PathVariable String taskIdentifier, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if (errorMap != null) return errorMap;

        WorkflowItem newWorkflowState = workflowService.addStateChange(taskIdentifier, workflowItem, principal.getName());

        return new ResponseEntity<WorkflowItem>(newWorkflowState, HttpStatus.CREATED);
    }

    @GetMapping("/{taskIdentifier}")
    public List<WorkflowItem> getTaskWorkflow(@PathVariable String taskIdentifier, Principal principal) {
        return workflowService.findWorkflowById(taskIdentifier, principal.getName());
    }

    @GetMapping("/{taskIdentifier}/{workflow_sequence}")
    public ResponseEntity<?> getTaskWorkflow(@PathVariable String taskIdentifier, @PathVariable String workflow_sequence, Principal principal) {
        WorkflowItem workflowState = workflowService.findWorkflowByTaskSequence(taskIdentifier, workflow_sequence, principal.getName());

        return new ResponseEntity<WorkflowItem>(workflowState, HttpStatus.OK);
    }

    @PatchMapping("/{taskIdentifier}/{workflow_sequence}")
    public ResponseEntity<?> updateWorkflowItem(@Valid @RequestBody WorkflowItem workflowState, BindingResult result, @PathVariable String taskIdentifier, @PathVariable String workflow_sequence, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if (errorMap != null) return errorMap;

        WorkflowItem currentState = workflowService.updatedByTaskSequence(workflowState, taskIdentifier, workflow_sequence, principal.getName());

        return new ResponseEntity<WorkflowItem>(currentState, HttpStatus.OK);
    }

    @DeleteMapping("/{taskIdentifier}/{workflow_sequence}")
    public ResponseEntity<?> deleteWorkflowItem(@PathVariable String taskIdentifier, @PathVariable String workflow_sequence, Principal principal) {
        workflowService.deleteStateByProjectSequence(taskIdentifier, workflow_sequence, principal.getName());

        return new ResponseEntity<String>("Workflow item deleted successfully", HttpStatus.OK);
    }
}
package micf.taskr.service.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.workflow.WorkflowHistory;
import micf.taskr.domain.workflow.WorkflowItem;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.repository.workflow.WorkflowItemRepository;

@Service
public class WorkflowItemService {
    @Autowired
    private WorkflowItemRepository workflowItemRepository;

    @Autowired
    private WorkflowService workflowService;


    public WorkflowItem addStateChange(String taskIdentifier, WorkflowItem workflowItem, String username) {
        WorkflowHistory history = workflowService.findWorkflowByTaskIdentifier(taskIdentifier, username).getWorkflowHistory();
        
        if(history != null) {
            workflowItem.setWorkflowHistory(history);
        
            Integer Sequence = history.getWorkflowSequence();
            Sequence ++;
            
            history.setWorkflowSequence(Sequence);
            
            // Add sequence to workflow item
            
            workflowItem.setHistorySequence(history.getTaskIdentifier() + "-" + Sequence);
            
            workflowItem.setTaskIdentifier(taskIdentifier);
            
            return workflowItemRepository.save(workflowItem);
        }

        return null;
        
    }

    public List<WorkflowItem> findWorkflowById(String taskIdentifier, String username) {
        workflowService.findWorkflowByTaskIdentifier(taskIdentifier, username);

        return workflowItemRepository.findByTaskIdentifier(taskIdentifier);
    }

    public WorkflowItem findWorkflowByTaskSequence(String taskIdentifier, String sequence, String username) {

        // find current Workflow
        workflowService.findWorkflowByTaskIdentifier(taskIdentifier, username);

        // make sure workflow state exists
        WorkflowItem item = workflowItemRepository.findByHistorySequence(sequence);

        if(item == null) {
            throw new TaskNotFoundException("Workflow was not found.");
        }

        if(!item.getTaskIdentifier().equals(taskIdentifier)) {
            throw new TaskNotFoundException("Workflow does not exist.");
        }

        return item;
    }

    public WorkflowItem updatedByTaskSequence(WorkflowItem updatedWorkflow, String taskIdentifier, String sequence, String username) {
        WorkflowItem item = findWorkflowByTaskSequence(taskIdentifier, sequence, username);

        item = updatedWorkflow;

        return workflowItemRepository.save(item);
    }

    public void deleteStateByProjectSequence(String taskIdentifier, String sequence, String username) {
        WorkflowItem workflowState = findWorkflowByTaskSequence(taskIdentifier, sequence, username);
        workflowItemRepository.delete(workflowState);
    }
}
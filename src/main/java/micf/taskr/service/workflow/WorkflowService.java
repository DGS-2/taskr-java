package micf.taskr.service.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micf.taskr.domain.user.User;
import micf.taskr.domain.workflow.Workflow;
import micf.taskr.domain.workflow.WorkflowHistory;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.exception.task.TaskTitleException;
import micf.taskr.repository.user.UserRepository;
import micf.taskr.repository.workflow.WorkflowHistoryRepository;
import micf.taskr.repository.workflow.WorkflowRepository;

@Service
public class WorkflowService {
    
    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private WorkflowHistoryRepository workflowHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Workflow createOrUpdateWorkflow(Workflow workflow, String username) {
        
        User requestingUser = userRepository.findByUsername(username);

        if(workflow.getId() != null) {
            Workflow existingWorkflow = workflowRepository.findByTaskIdentifier(workflow.getTaskIdentifier());
            if(existingWorkflow != null && ( existingWorkflow.getCreated_by().getId().equals( requestingUser.getId() ))) {
                throw new TaskNotFoundException("Workflow is not associated with your account");
            } else if(existingWorkflow == null) {
                throw new TaskNotFoundException("Workflow no longer exists.");
            }
        }

        try {
            workflow.setCreated_by(requestingUser);
            workflow.setTaskIdentifier(workflow.getTaskIdentifier());

            if(workflow.getId() == null) {
                WorkflowHistory history = new WorkflowHistory();
                workflow.setWorkflowHistory(history);
                history.setWorkflow(workflow);
                history.setTaskIdentifier(workflow.getTaskIdentifier());
            }

            if(workflow.getId() != null) {
                workflow.setWorkflowHistory(workflowHistoryRepository.findByTaskIdentifier(workflow.getTaskIdentifier()));
            }

            return workflowRepository.save(workflow);

        }catch(Exception e) {
            throw new TaskTitleException("Workflow already exists");
        }
    }

    public Workflow findWorkflowByTaskIdentifier(String taskIdentifier, String username) {

        Workflow workflow = workflowRepository.findByTaskIdentifier(taskIdentifier);

        return workflow;
    }

    public Iterable<Workflow> findAllWorkflows(String username) {
        return workflowRepository.findAll();
    }

    public void deleteWorkflowByIdentifier(String taskIdentifier, String username) {
        workflowRepository.delete(findWorkflowByTaskIdentifier(taskIdentifier, username));
    }
}
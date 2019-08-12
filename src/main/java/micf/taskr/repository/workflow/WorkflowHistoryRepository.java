package micf.taskr.repository.workflow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.workflow.WorkflowHistory;

@Repository
public interface WorkflowHistoryRepository extends JpaRepository<WorkflowHistory, Long> {

    WorkflowHistory findByTaskIdentifier(String taskIdentifier);

    @Override
    List<WorkflowHistory> findAll();
}
package micf.taskr.repository.workflow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.workflow.WorkflowItem;

@Repository
public interface WorkflowItemRepository extends JpaRepository<WorkflowItem, Long> {
    List<WorkflowItem> findByTaskIdentifier(String taskIdentifier);

    WorkflowItem findByHistorySequence(String historySequence);

    @Override
    List<WorkflowItem> findAll();
}
package micf.taskr.repository.workflow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.workflow.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

    Workflow findByTaskIdentifier(String taskIdentifier);

    @Override
    List<Workflow> findAll();
}
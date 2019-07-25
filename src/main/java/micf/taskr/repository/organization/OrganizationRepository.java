package micf.taskr.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import micf.taskr.domain.organization.*;

import java.util.List;

public interface OrganizationRepository extends JpaRepository {
    List<Organization> findAll();

    Organization findById(String id);
}
package micf.taskr.service.organization;

import java.util.List;

import micf.taskr.domain.organization.*;

public interface OrganizationService {

    List<Organization> findAll();

    Organization findById(String id);
}
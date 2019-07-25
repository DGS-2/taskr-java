package micf.taskr.service.organization;

import java.util.List;

import micf.taskr.domain.organization.*;

public interface OrganizationService {

    public List<Organization> findAll();

    public Organization findById(String id);
}
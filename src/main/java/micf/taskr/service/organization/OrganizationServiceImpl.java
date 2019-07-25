package micf.taskr.service.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import micf.taskr.domain.organization.*;
import micf.taskr.repository.organization.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    @Override
    @Transactional
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    @Transactional
    public Organization findById(String id) {
        return organizationRepository.findById(id);
    }
}
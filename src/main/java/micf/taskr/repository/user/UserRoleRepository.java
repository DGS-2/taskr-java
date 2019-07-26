package micf.taskr.repository.user;

import micf.taskr.domain.user.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String name);

    @Override
    void delete(UserRole role);

}
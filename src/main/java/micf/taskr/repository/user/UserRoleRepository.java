package micf.taskr.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import micf.taskr.domain.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);

    @Override
    void delete(UserRole role);
}
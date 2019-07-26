package micf.taskr.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import micf.taskr.domain.user.*;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findById(String id);
}
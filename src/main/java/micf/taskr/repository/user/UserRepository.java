package micf.taskr.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import micf.taskr.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findById(String id);

    User findByEmail(String email);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);
}
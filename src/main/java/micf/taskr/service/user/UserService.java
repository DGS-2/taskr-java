package micf.taskr.service.user;

import java.util.List;

import micf.taskr.domain.user.*;

public interface UserService {

    public List<User> findAll();

    public User findById(String id);
}
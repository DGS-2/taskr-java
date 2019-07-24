package micf.taskr.service.task;

import java.util.List;

import micf.taskr.domain.task.*;

public interface TaskService {

    public List<Task> findAll();

    public Task findById(String id);
}
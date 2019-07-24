package micf.taskr.dao.task;

import java.util.List;

import micf.taskr.domain.task.*;

public interface TaskDAO {
    public List<Task> findAll();

    public Task findById(String id);
}
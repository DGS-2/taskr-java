package micf.taskr.service.task;

import java.util.List;

import micf.taskr.domain.task.*;

public interface TaskService {

    List<Task> findAll();

    Task findById(String id);
}
package micf.taskr.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import micf.taskr.domain.task.*;
import micf.taskr.dao.task.*;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO theTaskDAO) {
        this.taskDAO = theTaskDAO;
    }

    @Override
    @Transactional
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    @Transactional
    public Task findById(String id) {
        return taskDAO.findById(id);
    }
}
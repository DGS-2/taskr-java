package micf.taskr.dao.task;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import micf.taskr.domain.task.*;

@Repository
public class TaskDAOHibernateImpl implements TaskDAO {


    // Entity mangeer
    private EntityManager entityManager;

    @Autowired
    public TaskDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Task> findAll() {
        // get current Hibernate session
        Session currSession = entityManager.unwrap(Session.class);

        // setup the query
        Query<Task> theQuery = 
            currSession.createQuery("from Task", Task.class);

        // execute query
        List<Task> tasks = theQuery.getResultList();

        // return list of tasks
        return tasks;
    }

    @Override
    public Task findById(String id) {
        // get current session
        Session currSession = entityManager.unwrap(Session.class);

        // build the query
        Task theTask =
            currSession.get(Task.class, id);

        // return result
        return theTask;
    }
}
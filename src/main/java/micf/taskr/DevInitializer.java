package micf.taskr;

import micf.taskr.repository.task.*;
import micf.taskr.service.task.TaskServiceImpl;
import micf.taskr.service.user.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevInitializer implements ApplicationRunner {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public DevInitializer() {}

    @Override
    public void run(ApplicationArguments arg0) {
        
    }
}
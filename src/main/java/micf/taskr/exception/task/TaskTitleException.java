package micf.taskr.exception.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskTitleException extends RuntimeException{

    private static final long serialVersionUID = 5861310537366287163L;

    public TaskTitleException(String message) {
        super(message);
    }

}
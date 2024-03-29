package micf.taskr.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import micf.taskr.exception.register.EmailExistsException;
import micf.taskr.exception.register.EmailExistsResponse;
import micf.taskr.exception.task.TaskNotFoundException;
import micf.taskr.exception.task.TaskNotFoundExceptionResponse;
import micf.taskr.exception.task.TaskTitleException;
import micf.taskr.exception.task.TaskTitleExceptionResponse;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    public final ResponseEntity<Object> handleTaskTitleException(TaskTitleException ex, WebRequest request) {
        TaskTitleExceptionResponse exceptionResponse = new TaskTitleExceptionResponse(ex.getMessage());

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request) {
        TaskNotFoundExceptionResponse exceptionResponse = new TaskNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEmailExistsException(EmailExistsException ex, WebRequest request) {
        EmailExistsResponse exceptionResponse = new EmailExistsResponse(ex.getMessage());

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
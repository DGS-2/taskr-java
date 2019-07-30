package micf.taskr.exception.task;

import java.util.Objects;

public class TaskNotFoundExceptionResponse {

    private String TaskNotFound;

    public TaskNotFoundExceptionResponse(String TaskNotFound) {
        this.TaskNotFound = TaskNotFound;
    }

    public String getTaskNotFound() {
        return this.TaskNotFound;
    }

    public void setTaskNotFound(String TaskNotFound) {
        this.TaskNotFound = TaskNotFound;
    }

    public TaskNotFoundExceptionResponse TaskNotFound(String TaskNotFound) {
        this.TaskNotFound = TaskNotFound;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskNotFoundExceptionResponse)) {
            return false;
        }
        TaskNotFoundExceptionResponse taskNotFoundExceptionResponse = (TaskNotFoundExceptionResponse) o;
        return Objects.equals(TaskNotFound, taskNotFoundExceptionResponse.TaskNotFound);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(TaskNotFound);
    }

    @Override
    public String toString() {
        return "{" +
            " TaskNotFound='" + getTaskNotFound() + "'" +
            "}";
    }


}
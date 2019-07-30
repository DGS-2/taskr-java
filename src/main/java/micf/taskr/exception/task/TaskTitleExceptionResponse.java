package micf.taskr.exception.task;


public class TaskTitleExceptionResponse {
    
    private String taskTitle;

    public TaskTitleExceptionResponse(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String title) {
        this.taskTitle = title;
    }
}
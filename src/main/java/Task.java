import java.util.Objects;


public class Task {

    private int taskID;
    private String taskStartDate;
    private String taskContent;
    private String taskStatus;

    public int getTaskID() {
        return taskID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    public String getTaskStartDate() {
        return taskStartDate;
    }
    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }
    public String getTaskContent() {
        return taskContent;
    }
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        String tempStatus = taskStatus.toLowerCase();
        if ((tempStatus.equals("in progress")) || (tempStatus.equals("completed")))
            this.taskStatus = taskStatus;
        else
            this.taskStatus = "Created";
    }

    public Task(int checkedId, String taskStartDate, String taskContent) {
        this.setTaskID(checkedId);
        this.setTaskStartDate(taskStartDate);  //format yyyy-mm-dd?
        this.setTaskContent(taskContent);
        this.setTaskStatus("Created");
    }

    public Task(int taskID, String taskStartDate, String taskContent, String taskStatus) {
        this.taskID = taskID;
        this.taskStartDate = taskStartDate;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
    }
    public String toString() {
        return (""+getTaskID()+'\t'+getTaskStartDate()+'\t'+getTaskStatus()+'\t'+getTaskContent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return taskID == task.taskID &&
                Objects.equals(taskStartDate, task.taskStartDate) &&
                Objects.equals(taskContent, task.taskContent) &&
                Objects.equals(taskStatus, task.taskStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskID, taskStartDate, taskContent, taskStatus);
    }
}

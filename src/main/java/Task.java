import java.time.LocalDate;
import java.util.Objects;

/**
 * <h1>Task class</h1>
 * Task class provides all necessary functionality
 * for dealing with Task class objects.
 *
 * @author  Michal Krupka, Karolina Szatanik
 * @version 1.0
 */


public class Task {

    private int taskID;
    private LocalDate taskStartDate;
    private String taskContent;
    private Status taskStatus;


    public Task(int checkedId, LocalDate taskStartDate, String taskContent) {
        this.setTaskID(checkedId);
        this.setTaskStartDate(taskStartDate);  //format yyyy-mm-dd?
        this.setTaskContent(taskContent);
        this.setTaskStatus(Status.CREATED);
    }

    public Task(int checkedId, String taskContent) {
        this.setTaskID(checkedId);
        this.setTaskStartDate(LocalDate.now());
        this.setTaskContent(taskContent);
        this.setTaskStatus(Status.CREATED);
    }

    public Task(int taskID, LocalDate taskStartDate, String taskContent, Status taskStatus) {
        this.taskID = taskID;
        this.taskStartDate = taskStartDate;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
    }

    public int getTaskID() {
        return taskID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    public LocalDate getTaskStartDate() {
        return taskStartDate;
    }
    public void setTaskStartDate(LocalDate taskStartDate) {
        this.taskStartDate = taskStartDate;
    }
    public String getTaskContent() {
        return taskContent;
    }
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
    public Status getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", taskStartDate='" + taskStartDate + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
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

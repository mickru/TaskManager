import java.text.DateFormat;
import java.util.Date;


public class Task {

    private static int subsID = 0;
    private int taskID;
    private String taskStartDate;
    private String taskContent;
    private String taskStatus;

    {
        subsID++;
    }

    public int getTaskID() {
        return taskID;
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

    public Task(String taskStartDate, String taskContent) {
        this.taskID = subsID;
        this.setTaskStartDate(taskStartDate);  //format dd-mm-yyyy?
        this.setTaskContent(taskContent);
        this.setTaskStatus("Created");
    }

    public String toString() {
        return (""+getTaskID()+'\t'+getTaskStartDate()+'\t'+getTaskStatus()+'\t'+getTaskContent());
    }
}

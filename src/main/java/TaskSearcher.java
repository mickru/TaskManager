import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskSearcher {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static ArrayList<Task> foundTasks = new ArrayList<>();

    public static void searchTaskByWord(String word) {
        for(Task task : listOfTasks){
            if(task.getTaskContent().toLowerCase().contains(word.toLowerCase())){
                foundTasks.add(task);
            }
        }
    }

    public static void searchTaskByDate(String date) {
        for(Task task : listOfTasks){
            if(task.getTaskStartDate().equals(date))
                foundTasks.add(task);
        }
    }

    public static void searchTaskByDateRange(String start, String end){
        Date startDate = new Date();
        Date endDate = new Date();
        Date taskDate = new Date();
        startDate = dateParse(start);
        endDate = dateParse(end);

        for(Task task : listOfTasks) {
            taskDate = dateParse(task.getTaskStartDate());

            if(taskDate.equals(startDate) || taskDate.equals(endDate))
                foundTasks.add(task);

            if(taskDate.after(startDate) && taskDate.before(endDate))
                foundTasks.add(task);
        }
    }

    private static Date dateParse(String date) {
        Date parsedDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            parsedDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Incorrect date type. It must be dd-MM-yyyy");
        }
        return parsedDate;
    }
}

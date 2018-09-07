import java.time.LocalDate;
import java.util.ArrayList;


public class TaskSearcher {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static ArrayList<Task> foundTasks = new ArrayList<>();

    public static ArrayList<Task> searchTaskByWord(String word, ArrayList<Task> listOfTasks) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for(Task task : listOfTasks){
            if(task.getTaskContent().toLowerCase().contains(word.toLowerCase())){
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public static ArrayList<Task> searchTaskByDate(LocalDate date, ArrayList<Task> listOfTasks) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for(Task task : listOfTasks){
            if(task.getTaskStartDate().isEqual(date))
                foundTasks.add(task);
        }
        return foundTasks;
    }

    public static void searchTaskByDateRange(LocalDate start, LocalDate end){

        for(Task task : listOfTasks) {

            if(task.getTaskStartDate().isAfter(start) && task.getTaskStartDate().isBefore(end))
                foundTasks.add(task);

        }
    }

}

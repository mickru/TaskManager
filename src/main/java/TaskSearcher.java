import java.util.ArrayList;

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

    public void searchTaskByDate(String date) {
        for(Task task : listOfTasks){
            if(task.getTaskStartDate().equals(date))
                foundTasks.add(task);
        }
    }
}

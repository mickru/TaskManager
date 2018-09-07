import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskSearcherTest {

    @Test
    public void searchTaskByWord() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        Task task1 = new Task(1, LocalDate.of(2012,1,1),"aaa",Status.COMPLETED);
        Task task2 = new Task(2, LocalDate.of(2015,1,1),"bbb",Status.COMPLETED);

        listOfTasks.add(task1);
        listOfTasks.add(task2);

        String toBeFound = "a";
        ArrayList<Task> expectedResult = new ArrayList<>();
        expectedResult.add(task1);
        assertArrayEquals(expectedResult.toArray(),TaskSearcher.searchTaskByWord(toBeFound, listOfTasks).toArray());
    }

    @Test
    public void searchTaskByDate() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        Task task1 = new Task(1, LocalDate.of(2012,1,1),"aaa",Status.COMPLETED);
        Task task2 = new Task(2, LocalDate.of(2015,1,1),"bbb",Status.COMPLETED);

        listOfTasks.add(task1);
        listOfTasks.add(task2);
        LocalDate toBeFound = LocalDate.of(2015,1,1);
        ArrayList<Task> expectedResult = new ArrayList<>();
        expectedResult.add(task2);
        assertArrayEquals(expectedResult.toArray(),TaskSearcher.searchTaskByDate(toBeFound, listOfTasks).toArray());
    }

}

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.Assert.*;

public class TaskDatabaseTest {

    @Test
    public void readAllFromDb() {
        Task task1 = new Task(1, LocalDate.of(2012,1,1),"aaa",Status.COMPLETED);
        Task task2 = new Task(2, LocalDate.of(2015,1,1),"aaa",Status.COMPLETED);
        TaskDatabase database = new TaskDatabase();
        database.dropTable();


        database.createNewTableIfNoExists();
        database.insertTask(task1);
        database.insertTask(task2);
        database.selectAll();

        ArrayList<Task> expectedResult = new ArrayList<>();
        expectedResult.add(task1);
        expectedResult.add(task2);

        ArrayList<Task> resultFromDB = new ArrayList<>();
        resultFromDB = database.readAllFromDb();
        assertArrayEquals(expectedResult.toArray(),resultFromDB.toArray());
    }
}
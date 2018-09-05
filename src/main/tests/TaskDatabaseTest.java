import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskDatabaseTest {

    @Test
    public void readAllFromDb() {
        Task task1 = new Task("01-01-2019","dom");
        Task task2 = new Task("01-09-2019","ddddom");
        TaskDatabase database = new TaskDatabase();
        database.dropTable();


        database.createNewTable();
        database.insertTask(task1);
        database.insertTask(task2);

        ArrayList<Task> expectedResult = new ArrayList<>();
        expectedResult.add(task1);
        expectedResult.add(task2);

        ArrayList<Task> resultFromDB = new ArrayList<>();
        resultFromDB = database.readAllFromDb();
        assertArrayEquals(expectedResult.toArray(),resultFromDB.toArray());
    }
}
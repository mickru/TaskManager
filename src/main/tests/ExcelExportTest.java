import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExcelExportTest {

    @Test
    public void exportToExcel() {
        ArrayList<Task> input_list = new ArrayList<Task>();
        Task task1 = new Task(1, LocalDate.of(2012,1,1),"aaa",Status.COMPLETED);
        Task task2 = new Task(2, LocalDate.of(2015,1,1),"aaa",Status.COMPLETED);
         input_list.add(task1);
         input_list.add(task2);

        try {
            ExcelExport.exportToExcel(input_list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
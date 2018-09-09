import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelExport {
    public static void exportToExcel(ArrayList<Task> tasksList) throws IOException {

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow((short) 0);

        row.createCell(0).setCellValue("taskID");
        row.createCell(1).setCellValue("task start date");
        row.createCell(2).setCellValue("task content");
        row.createCell(3).setCellValue("task status");

        short taskNo = 0;

        for (Task task: tasksList){
            row = sheet.createRow(++taskNo);
            row.createCell(0).setCellValue(task.getTaskID());
            row.createCell(1).setCellValue(task.getTaskStartDate().toString());
            row.createCell(2).setCellValue(task.getTaskContent());
            row.createCell(3).setCellValue(task.getTaskStatus().toString());
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("workbook.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        wb.write(fileOut);
        fileOut.close();
    }
}

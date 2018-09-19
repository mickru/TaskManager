import java.util.ArrayList;

/**
 * <h1>PrintToScreen class</h1>
 * PrintToScreen class provides all necessary functionality
 * for printing out the results of search methods from TaskDatabase class.
 *
 * @author  Michal Krupka
 * @version 1.0
 */


public class PrintToScreen {

    int idLength;
    int contentLength;
    int statusLength;

    final int ID_LENGTH = 3;
    final int DATE_LENGTH = 10;
    final int CONTENT_LENGTH = 18;
    final int STATUS_LENGTH = 11;


    public StringBuilder generateBlank() {
        StringBuilder filledBlank = new StringBuilder();
        for (int i = 0; i < ID_LENGTH; i++)
            filledBlank.append(" ");
        filledBlank.append('|');
        for (int i = 0; i < DATE_LENGTH; i++)
            filledBlank.append(" ");
        filledBlank.append('|');
        for (int i = 0; i < CONTENT_LENGTH; i++)
            filledBlank.append(" ");
        filledBlank.append('|');
        for (int i = 0; i < STATUS_LENGTH; i++)
            filledBlank.append(" ");
        filledBlank.append('|');
        return filledBlank;
    }

    public StringBuilder generateHeadline(StringBuilder filledBlank) {
        filledBlank.replace(Math.abs(ID_LENGTH/2)-1,(Math.abs(ID_LENGTH/2)-1)+2,"ID");
        filledBlank.replace(ID_LENGTH+(Math.abs(DATE_LENGTH/2))-1,(ID_LENGTH+(Math.abs(DATE_LENGTH/2))-1)+4,"Date");
        filledBlank.replace(ID_LENGTH+DATE_LENGTH+1+Math.abs(CONTENT_LENGTH/2)-1,(ID_LENGTH+DATE_LENGTH+1
                +Math.abs(CONTENT_LENGTH/2)-1)+5,"To do");
        filledBlank.replace(ID_LENGTH+DATE_LENGTH+1+CONTENT_LENGTH+1+Math.abs(STATUS_LENGTH/2)-1,
                (ID_LENGTH+DATE_LENGTH+1+CONTENT_LENGTH+1+Math.abs(STATUS_LENGTH/2)-1)+6,"Status");
        return filledBlank;
    }

    public void printHeadline() {
        StringBuilder filledBlank = generateBlank();
        StringBuilder headline = generateHeadline(filledBlank);
        System.out.println(headline);
        for (int i = 0; i < (ID_LENGTH+1+DATE_LENGTH+1+CONTENT_LENGTH+1+STATUS_LENGTH+1); i++)
            System.out.print('-');
        System.out.println();
    }

    public void printRow(int id, String date, String content, Status status) {
            StringBuilder filledBlank = generateBlank();
            String idString = Integer.toString(id);
            idLength = idString.length();
            filledBlank.replace(0,idLength,idString);
            filledBlank.replace(ID_LENGTH+1,(ID_LENGTH+1)+10,date);
            contentLength = content.length();
            filledBlank.replace(ID_LENGTH+1+DATE_LENGTH+1,(ID_LENGTH+1+DATE_LENGTH+1)+Math.min(contentLength,CONTENT_LENGTH),
                    content.substring(0,Math.min(contentLength,CONTENT_LENGTH)));
            statusLength = status.toString().length();
            filledBlank.replace(ID_LENGTH+1+DATE_LENGTH+1+CONTENT_LENGTH+1,(ID_LENGTH+1+DATE_LENGTH+1+CONTENT_LENGTH+1)
                    +Math.min(statusLength,STATUS_LENGTH),status.toString().substring(0,Math.min(statusLength,STATUS_LENGTH)));
            System.out.print(filledBlank+"\n");
            if ((contentLength > CONTENT_LENGTH))
                printContinuedContent(contentLength, content);
        }

    public void printTable(ArrayList<Task> arrayList) {
        printHeadline();
        for (Task task : arrayList){
            this.printRow(task.getTaskID(), task.getTaskStartDate().toString(), task.getTaskContent(), task.getTaskStatus());
        }

    }

    public void printContinuedContent(int contentLength, String content) {
        int count;
        if ((contentLength % CONTENT_LENGTH) == 0)
            count = contentLength / CONTENT_LENGTH;
        else
            count = Math.abs(contentLength/CONTENT_LENGTH)+1;
        for (int i = 1; i < count; i++) {
            StringBuilder filledBlank = generateBlank();
            filledBlank.replace(ID_LENGTH+DATE_LENGTH+2,Math.min(ID_LENGTH+DATE_LENGTH+2+CONTENT_LENGTH, ID_LENGTH+DATE_LENGTH+2+(contentLength-(i*CONTENT_LENGTH))),
                    content.substring(i*CONTENT_LENGTH,Math.min((i*CONTENT_LENGTH)+CONTENT_LENGTH, contentLength)));
            System.out.print(filledBlank+"\n");
        }
    }


    }




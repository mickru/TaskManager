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
    final int CONTENT_LENGTH = 20;
    final int STATUS_LENGTH = 11;


    public StringBuilder generateBlank() {
        /**
         * <h2>generateBlank()</h2>
         * This method returns an empty row to be used in a table of tasks that is to be generated further.
         * Blank row  has 4 columns and their width depends on the constants in the PrintToScreen class.
         */

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
        /**
         * <h2>generateHeadline(StringBuilder filledBlank)</h2>
         * Using a blank row generated in generateBlank() method, this method returns a filled in row
         * to be used as a headline in a table of tasks that is to be generated further.
         */

        filledBlank.replace(Math.abs(ID_LENGTH/2)-1,(Math.abs(ID_LENGTH/2)-1)+2,"ID");
        filledBlank.replace(ID_LENGTH+(Math.abs(DATE_LENGTH/2))-1,(ID_LENGTH+(Math.abs(DATE_LENGTH/2))-1)+4,"Date");
        filledBlank.replace(ID_LENGTH+DATE_LENGTH+1+Math.abs(CONTENT_LENGTH/2)-1,(ID_LENGTH+DATE_LENGTH+1
                +Math.abs(CONTENT_LENGTH/2)-1)+5,"To do");
        filledBlank.replace(ID_LENGTH+DATE_LENGTH+1+CONTENT_LENGTH+1+Math.abs(STATUS_LENGTH/2)-1,
                (ID_LENGTH+DATE_LENGTH+1+CONTENT_LENGTH+1+Math.abs(STATUS_LENGTH/2)-1)+6,"Status");
        return filledBlank;
    }

    public void printHeadline() {
        /**
         * <h2>printHeadline()</h2>
         * This method prints out on the screen a headline returned by
         * generateHeadline(StringBuilder filledBlank) method. I will be a headline
         * to be used in a table of tasks that is to be generated further.
         */

        StringBuilder filledBlank = generateBlank();
        StringBuilder headline = generateHeadline(filledBlank);
        System.out.println(headline);
        for (int i = 0; i < (ID_LENGTH+1+DATE_LENGTH+1+CONTENT_LENGTH+1+STATUS_LENGTH+1); i++)
            System.out.print('-');
        System.out.println();
    }

    public String getHeadline() {
        /**
         * <h2>getHeadline()</h2>
         * This method returns  a headline returned by
         * generateHeadline(StringBuilder filledBlank) method. I will be a headline
         * to be used in a table of tasks that is to be generated further.
         */

        String headlineWithDecoreation;
        StringBuilder filledBlank = generateBlank();
        StringBuilder headline = generateHeadline(filledBlank);

        headlineWithDecoreation = headline.toString();

        headlineWithDecoreation = headlineWithDecoreation + "\n";

        for (int i = 0; i < (ID_LENGTH+1+DATE_LENGTH+1+CONTENT_LENGTH+1+STATUS_LENGTH+1); i++)
            headlineWithDecoreation = headlineWithDecoreation + ('-');

        return headlineWithDecoreation;
//
    }


    public String getAllRowsContent(int id, String date, String content, Status status) {
        /**
         * <h2>getAllRowsContent(int id, String date, String content, Status status)</h2>
         * This method returns a row to be used in a table of tasks.
         * A row contain one task.
         */

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
     //   System.out.print(filledBlank+"\n");
      //  if ((contentLength > CONTENT_LENGTH))
        //    printContinuedContent(contentLength, content);

        return filledBlank.toString();
    }


    public void printRow(int id, String date, String content, Status status) {
        /**
         * <h2>printRow(int id, String date, String content, Status status)</h2>
         * This method prints out on the screen a row to be used in a table of tasks.
         * A row contain one task.
         */

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
        /**
         * <h2>printTable(ArrayList<Task> arrayList)</h2>
         * This method prints out on the screen a table of tasks.
         * A table contains of a headline and row(s) with task(s).
         */

        printHeadline();
        for (Task task : arrayList){
            this.printRow(task.getTaskID(), task.getTaskStartDate().toString(), task.getTaskContent(), task.getTaskStatus());
        }

    }

    public void printContinuedContent(int contentLength, String content) {
        /**
         * <h2>printContinuedContent(int contentLength, String content)</h2>
         * This method prints out on the screen a row with continued content,
         * if the content length is longer than the constant defined in
         * PrintToScreen class.
         * Other columns in the row are blank.
         */
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




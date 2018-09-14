import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class DataReader {
    private String errorMessage;

    public LocalDate readDate() {
        Scanner scanner = new Scanner(System.in);
        String stringDate;
        do {
            stringDate = scanner.nextLine();
        } while (!(isDateFormat(stringDate)));
        return dateParse(stringDate);
    }

    private boolean isDateFormat(String inputString) {
        return (isCorrectFormat(inputString) && isNumbers(inputString) && isExistDay(inputString));
    }

    //this method return true if string is in format xxxx-xx-xx
    private boolean isCorrectFormat(String inputString){
        errorMessage = "Date format error. Write the date in format yyyy-MM-dd. Try again.";
        String[] splitString = inputString.split("-");
        if(splitString.length == 3 ) {
            if(splitString[0].length() == 4 && splitString[1].length() == 2 && splitString[2].length() == 2) {
                return true;
            }
        }
        System.out.println(errorMessage);
        return false;
    }

    private boolean isNumbers (String inputString){
        errorMessage = "The input are not numbers. Try again.";
        String[] splitInputString = inputString.split("-");
            for(String string : splitInputString){
                try{
                    Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    System.out.println(errorMessage);
                    return false;
                }
            }
        return true;
    }
    private boolean isExistDay(String date){
        errorMessage = "There is no such day " + date+". Try again.";
        String [] arrayOfDate = date.split("-");
        try {
            LocalDate.of(Integer.parseInt(arrayOfDate[0]), Integer.parseInt(arrayOfDate[1]), Integer.parseInt(arrayOfDate[2]));
        } catch (DateTimeException e) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    private LocalDate dateParse(String date){
        int year = 0;
        int month = 0;
        int day = 0;

        String[] splitStringDate = date.split("-");
            year = Integer.parseInt(splitStringDate[0]);
            month = Integer.parseInt(splitStringDate[1]);
            day = Integer.parseInt(splitStringDate[2]);
        return LocalDate.of(year, month, day);
    }

    public int readTaskID() {
        errorMessage = "The input are not numbers. Try again.";
        String stringNumber;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                stringNumber = scanner.nextLine();
                Integer.parseInt(stringNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (true);
        return Integer.parseInt(stringNumber);
    }

    public String readTaskContent() {
        String taskContent;
        do {
            Scanner scanner = new Scanner(System.in);
            taskContent = scanner.nextLine();
        } while (isEmptyTaskContent(taskContent));
        return taskContent;
    }

    private boolean isEmptyTaskContent(String text) {
        errorMessage = "You did not enter the content of task. Try again. ";
        int spacesCounter = 0;
            for (Character eachChar : text.toCharArray())
                if (eachChar.toString().equals(" "))
                    spacesCounter++;
            if (spacesCounter == text.length()) {
                System.out.println(errorMessage);
                return true;
            }
        return false;
    }

    public Status readStatus() {
        String inputStatus;
        Status status = null;
        do{
            Scanner scanner = new Scanner(System.in);
            inputStatus = scanner.nextLine().toLowerCase();
            status = chooseStatus(inputStatus);
        } while (!(inputStatus.equals("created") || inputStatus.equals("in progress") || inputStatus.equals("completed")));
        return status;
    }

    private Status chooseStatus(String inputStatus){
        errorMessage = "There is no such status of task. Choose next task statuses: \n- created; \n- in progress; \n- completed";
        Status status = null;
        switch (inputStatus){
            case ("created"): status = Status.CREATED; break;
            case ("in progress"): status = Status.IN_PROGRESS; break;
            case ("completed"): status = Status.COMPLETED; break;
            default:
                System.out.println(errorMessage); break;
        }
        return status;
    }
}

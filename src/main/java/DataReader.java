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
        return LocalDate.parse(stringDate);
    }

    private boolean isDateFormat(String inputString) {
        try {
            LocalDate.parse(inputString);
        }
        catch (DateTimeException ex ) {
            errorMessage = "You entered incorrect date format or day doesn't exist. Try again. ";
            System.out.println(errorMessage);
            return false;
        }
        return true;
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

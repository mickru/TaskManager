import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * CorrectDataReader class implements 4 methods to read Users input as a String,
 * until it is entered in correct format, and return specific type.
 * @author Nazarii Ihnat, Karolina Szatan.
 */
public class CorrectDataReader {
    private String errorMessage;

    /**
     * Reads input to receive it in correct format (yyyy-mm-dd),
     * validates given day or, if User press Enter button, return today's date.
     * @return LocalDate.
     */

    public static void main(String[] args) {
        CorrectDataReader correctDataReader = new CorrectDataReader();
        System.out.println(correctDataReader.readCorrectStatus());
    }

    public LocalDate readCorrectDate() {
        Scanner scanner = new Scanner(System.in);
        String stringDate;
        do {
            stringDate = scanner.nextLine();
            if(stringDate.equals("")) {
                System.out.println("Inputted date is " + LocalDate.now());
                return LocalDate.now();
            }
        } while (!(isDateFormat(stringDate)));
        return LocalDate.parse(stringDate);
    }

    private boolean isDateFormat(String inputString) {
        try {
            LocalDate.parse(inputString);
        }
        catch (DateTimeException ex ) {
            errorMessage = "You entered incorrect date format or day doesn't exist. Press enter to input today's date.  ";
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    /**
     * Reads input to receive numbers only.
     * @return int.
     */
    public int readCorreckID() {
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

    /**
     * Reads input until it is not empty or doesn't contain only spaces.
     * @return String.
     */
    public String readCorrectTaskContent() {
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

    /**
     * Reads input until receives expected keywords:
     * - created;
     * - in progress;
     * - completed;
     * Return CREATED status if user press Enter button.
     * @return Status enums.
     */
    public Status readCorrectStatus() {
        String inputStatus;
        Status status = null;
        do{
            Scanner scanner = new Scanner(System.in);
            inputStatus = scanner.nextLine().toLowerCase();
            if(inputStatus.equals("")) {
                System.out.println("Inputted status is " + Status.CREATED);
                return Status.COMPLETED;
            }
            status = chooseStatus(inputStatus);
        } while (!(inputStatus.equals("created") || inputStatus.equals("in progress") || inputStatus.equals("completed")));
        return status;
    }

    private Status chooseStatus(String inputStatus){
        errorMessage = "There is no such status of task. Choose next task statuses: " +
                       "\n- created; \n- in progress; \n- completed \nPress enter to input default status (created).";
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

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //Example of command line working with program. DL, 04.09.2018
        Scanner command = new Scanner(System.in);

        CorrectDataReader reader = new CorrectDataReader();

        TaskDatabase database = new TaskDatabase();
        database.createNewTableIfNoExists();
        System.out.println("Database has been established");

        boolean running = true;

        while(running){

            System.out.println("Enter command: ");

            switch(command.nextLine()){

                case "exit":
                    System.out.println("Application Closed");
                    running = false;
                    break;

                case "addTask":
                    System.out.println("Add Task");
                    System.out.println("Enter start date of the task (RRRR-MM-DD)");
                    LocalDate taskDate = reader.readCorrectDate();
                    System.out.println("Enter your task content");
                    String taskContent = reader.readCorrectTaskContent();
                    int taskID = database.checkNextId();
                    Task task1 = new Task (taskID, taskDate, taskContent);
                    database.insertTask(task1);
                    System.out.println("Your task has been created");

                case "help":
                    System.out.println("\"addTask\" adds new task.");
                    System.out.println("\"exit\" - closes program");
                    break;

                default:
                    System.out.println("Command not recognized. Type \"help\" to get info on available commands");
                    break;
            }
        }
        command.close();
    }
}

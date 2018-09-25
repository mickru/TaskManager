import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * <h1>Main class</h1>
 * Main class for whole project
 * contains functionality for User
 *
 * @author  Dominik Lyczek
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) throws IOException {

        //Example of command line working with program. DL, 04.09.2018
        Scanner command = new Scanner(System.in);

        CorrectDataReader reader = new CorrectDataReader();

        TaskDatabase database = new TaskDatabase();
        database.createNewTableIfNoExists();
        System.out.println("Database has been established");

        boolean running = true;
        int taskIDtoUpdate;
        String taskContent;
        int updatedRowsCount;

        while(running){

            System.out.println("Enter command: ");


            switch(command.nextLine().toLowerCase()){

                case "99":
                case "exit":
                    System.out.println("Application Closed");
                    running = false;
                    break;
                case "1":
                case "addtask":
                    System.out.println("Add Task");
                    System.out.println("Enter start date of the task (RRRR-MM-DD)");
                    LocalDate taskDate = reader.readCorrectDate();
                    System.out.println("Enter your task content");
                    taskContent = reader.readCorrectTaskContent();
                    if (taskContent == null || taskDate == null ) {
                         System.out.println("Adding task cancelled!");
                        break;
                    }
                    int taskID = database.checkNextId();
                    Task task1 = new Task (taskID, taskDate, taskContent);
                    database.insertTask(task1);
                    System.out.println("Your task has been created");
                    break;
                case "2":
                case "selectall":
                    System.out.println("All your tasks are:");
                    database.selectAll();
                    break;
                case "3":
                case "updatedate":
                    System.out.println("Write ID to update:");
                    taskIDtoUpdate = reader.readCorreckID();
                    System.out.println("Enter new date of the task (RRRR-MM-DD)");
                    LocalDate taskDateToUpdate = reader.readCorrectDate();
                    updatedRowsCount = database.updateDate( taskIDtoUpdate, taskDateToUpdate.toString() );
                    if ( updatedRowsCount > 0 ) {
                    System.out.println("ID: " + taskIDtoUpdate + " date updated to: " + taskDateToUpdate.toString());
                }
                break;
                case "4":
                case "updatecontent":
                    System.out.println("Write ID to update:");
                    taskIDtoUpdate = reader.readCorreckID();
                    System.out.println("Enter new 'TO DO' of the task");
                    taskContent = reader.readCorrectTaskContent();
                    updatedRowsCount = database.updateContent( taskIDtoUpdate, taskContent );
                    if ( updatedRowsCount > 0 ) {
                        System.out.println("ID: " + taskIDtoUpdate + " 'TO DO' updated to: " + taskContent);
                    }
                else {
                        System.out.println("0 rows updated!");
                }
                    break;
                case "5":
                case "updatestatus":
                    System.out.println("Write ID to update:");
                    taskIDtoUpdate = reader.readCorreckID();
                    System.out.println("Enter new Status");
                    Status newStatus= reader.readCorrectStatus();
                    updatedRowsCount = database.updateStatus( taskIDtoUpdate, newStatus.toString() );
                    if ( updatedRowsCount > 0 ) {
                        System.out.println("ID: " + taskIDtoUpdate + " status updated to status: " + newStatus.toString() );
                    }
                    else {
                        System.out.println("0 rows updated!");
                    }
                    break;
                case "6":
                case "searchstatus":
                    System.out.println("Write Status to be searched:");
                    Status searchedStatus= reader.readCorrectStatus();
                    database.searchStatus ( searchedStatus.toString() );
                    break;
                case "7":
                case "searchdate":
                    System.out.println("Write Date to be searched:");
                    LocalDate taskDateToSearch = reader.readCorrectDate();
                    database.searchDate ( taskDateToSearch.toString() );
                    break;
                case "8":
                case "searchdaterange":
                    System.out.println("Write date from which to search:");
                    LocalDate taskDateToSearchFrom = reader.readCorrectDate();
                    System.out.println("Write date to which to search:");
                    LocalDate taskDateToSearchTo = reader.readCorrectDate();
                    database.searchDateRange ( taskDateToSearchFrom.toString(), taskDateToSearchTo.toString() );
                    break;
                case "create":
                case "createtable":
                    database.createNewTableIfNoExists();
                    System.out.println("TABLE created!");
                    break;
                case "drop":
                case "droptable":
                    database.dropTable();
                    System.out.println("TABLE removed!");
                    break;

                case "h":
                case "help":
                    System.out.println("\"1 or addTask\" adds new task.");
                    System.out.println("\"2 or selectall\" displays all tasks.");
                    System.out.println("\"3 or updatedate\" is for updating selected by ID task Date.");
                    System.out.println("\"4 or updatecontent\" is for updating selected by ID task 'TO DO'.");
                    System.out.println("\"5 or updatestatus\" is for updating selected by ID task Status.");
                    System.out.println("\"6 or searchstatus\" is for displaying all rows with given Status.");
                    System.out.println("\"7 or searchdate\" is for displaying all rows with given Date.");
                    System.out.println("\"8 or searchdaterange\" is for displaying all rows between two dates (including them).");
                    System.out.println("\"99 or exit\" - closes program");
                    break;

                default:
                    System.out.println("Command not recognized. Type \"help\" to get info on available commands");
                    break;
            }
            System.out.println("");
        }
        command.close();
    }
}

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //Example of command line working with program. DL, 04.09.2018
        Scanner command = new Scanner(System.in);

        System.out.println("Enter command: ");
        boolean running = true;

        while(running){

            switch(command.nextLine()){

                case "start":
                    TaskDatabase database = new TaskDatabase();
                    database.createNewTableIfNoExists();
                    System.out.println("Database started!");
                    break;

                case "stop":
                    System.out.println("Machine stopped.");
                    break;

                case "exit":
                    System.out.println("Application Closed");
                    running = false;
                    break;


                case "help":
                    System.out.println("\"start\" starts.");
                    System.out.println("\"stop\" stops.");
                    System.out.println("\"exit\" - closes program");
                    break;

                default:
                    System.out.println("Command not recognized!");
                    break;
            }
        }
        command.close();
    }

}

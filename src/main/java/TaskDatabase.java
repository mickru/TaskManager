import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <h1>TaskDatabase class</h1>
 * TaskDatabase class provides all necessary functionality
 * for communicating with SQL database file.
 *
 * @author  Michal Krupka, Karolina Szatan
 * @version 1.0
 */

public class TaskDatabase {

    private static String url = "jdbc:sqlite:testdb.db";
    private static Connection conn;

    {
        /**
         * <h2>Connection block</h2>
         * This initial block tries to connect to .db file in the location
         * indicated in 'url' param. If it fails, a new file is created.
         */

        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeSql(String sql) {
        try (
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTableIfNoExists() {
        /**
         * <h2>createNewTableIfNoExists()</h2>
         * This method creates a new table with id, date, content, status
         * columns, unless such table already exists.
         */
         String sql = "CREATE TABLE IF NOT EXISTS TaskList (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	date text NOT NULL,\n"
                + "	content text NOT NULL, \n"
                + "	status text NOT NULL\n"
                + ");";
        executeSql(sql);
    }

    public static void dropTable() {
        String sql = "Drop table TaskList";
        executeSql(sql);
    }


    public void insertTask(Task task) {
        /**
         * <h2>insertTask(Task task)</h2>
         * This method adds a new task to the table, containing id, date,
         * content, status params.
         */

        this.insert(task.getTaskID(),task.getTaskStartDate().toString(),task.getTaskContent(), task.getTaskStatus().toString());
    }

    public void insert(int taskID, String taskStartDate, String taskContent, String taskStatus) {
        /**
         * <h2>insert(int taskID, String taskStartDate, String taskContent, String taskStatus)</h2>
         * This method is used by insertTask() and has the params drawn from the object Task class.
         */

        String sql = "INSERT INTO TaskList (id, date, content, status) VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, taskID);
            pstmt.setString(2, taskStartDate);
            pstmt.setString(3, taskContent);
            pstmt.setString(4, taskStatus);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll(){
        /**
         * <h2>selectAll()</h2>
         * This method prints out on the screen all tasks saved in the .db file in form of a list.
         */
        String sql = "SELECT id, date, content, status FROM TaskList";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            PrintToScreen print = new PrintToScreen();
            print.printHeadline();

            while (rs.next()) {
                print.printRow(rs.getInt("id"),rs.getString("date"), rs.getString("content"), Status.valueOf(rs.getString("status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> readAllFromDb(){
        ArrayList<Task> result = new ArrayList<Task>();
        String sql = "SELECT id, date, content, status FROM TaskList";
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(rs.getInt("id") , LocalDate.parse(rs.getString("date")),
                        rs.getString("content"), Status.valueOf(rs.getString("status")));
                result.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }

    public int checkNextId(){
        /**
         * <h2>checkNextId()</h2>
         * This method checks the last id stored in the .db file - the next
         * int number is meant to be used for creation of a new object Task.
         */
        String sql = "SELECT id FROM TaskList";
        int counter = 1;
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }

    public void updateDate(int id, String date) {
        /**
         * <h2>updateDate(int id, String date)</h2>
         * This method updates a date in the task record with indicated id.
         */
        String sql = "UPDATE TaskList SET date = ? "
                + "WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateContent(int id, String content) {
        /**
         * <h2>updateContent(int id, String content)</h2>
         * This method updates a content in the task record with indicated id.
         */
        String sql = "UPDATE TaskList SET content = ? "
                + "WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, content);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStatus(int id, String status) {
        /**
         * <h2>updateStatus(int id, String status)</h2>
         * This method updates a status in the task record with indicated id.
         */
        String sql = "UPDATE TaskList SET status = ? "
                + "WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchStatus(String stat){
        /**
         * <h2>searchStatus(String stat)</h2>
         * This method is searching for task with the given status in the task database file, and prints them out.
         */
        String sql = "SELECT id, date, content, status FROM TaskList ";

        PrintToScreen print = new PrintToScreen();
        print.printHeadline();

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int count = 0;
            String statMod = stat.toUpperCase();
            while (rs.next()) {
                if ((statMod.equals(rs.getString("status")))) {
                    print.printRow(rs.getInt("id"),rs.getString("date"), rs.getString("content"), Status.valueOf(rs.getString("status")));
                    count++;
                }
            }
            System.out.println("Number of tasks with status '"+statMod+"': "+count+"\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchDate(String date){
        /**
         * <h2>searchDate(String date)</h2>
         * This method is searching for task with the given date in the task database file, and prints them out.
         */
        String sql = "SELECT id, date, content, status FROM TaskList ";

        PrintToScreen print = new PrintToScreen();
        print.printHeadline();

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int count = 0;
            while (rs.next()) {
                if ((date.equals(rs.getString("date")))) {
                    print.printRow(rs.getInt("id"),rs.getString("date"), rs.getString("content"), Status.valueOf(rs.getString("status")));
                    count++;
                }
            }
            System.out.println("Number of tasks with date '"+date+"': "+count+"\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchDateRange(String dateFrom, String dateTo){
        /**
         * <h2>searchDateRange(String dateFrom, String dateTo)</h2>
         * This method is searching for task within the given date range (including date FROM and date TO)
         * in the task database file, and prints them out.
         */
        String sql = "SELECT id, date, content, status FROM TaskList";

        PrintToScreen print = new PrintToScreen();
        print.printHeadline();

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            String dateFromMod = dateFrom.replace("-","");
            int dateFromModInt = Integer.parseInt(dateFromMod);
            String dateToMod = dateTo.replace("-","");
            int dateToModInt = Integer.parseInt(dateToMod);
            String databaseDateString;
            String databaseDateMod;
            int databaseDateInt;
            int count = 0;

            while (rs.next()) {
                databaseDateString = rs.getString("date");
                databaseDateMod = databaseDateString.replace("-","");
                databaseDateInt = Integer.parseInt(databaseDateMod);
                if ((databaseDateInt >= dateFromModInt) && (databaseDateInt <= dateToModInt)) {
                    print.printRow(rs.getInt("id"),rs.getString("date"), rs.getString("content"), Status.valueOf(rs.getString("status")));
                count++;
                }
            }
            System.out.println("Number of tasks from '"+dateFrom+"' to '"+dateTo+"': "+count+"\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

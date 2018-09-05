import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>TaskDatabase class</h1>
 * TaskDatabase class provides all necessary functionality
 * for communicating with SQL database file.
 *
 * @author  Michal Krupka, Karolina Szatanik
 * @version 1.0
 */

public class TaskDatabase {

    private static String url = "jdbc:sqlite:testdb.db";
    private static Connection conn;

    private static int taskID;
    private static String taskStartDate;
    private static String taskContent;
    private static String taskStatus;


    {
        /**
         * <h2>Connection block</h2>
         * This initial block tries to connect to .db file in the location
         * indicated in 'url' param. If it fails, a new file is created.
         * @author  Michal Krupka
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

    public static void createNewTable() {
        /**
         * <h2>createNewTable()</h2>
         * This method creates a new table with id, date, content, status
         * columns, unless such table already exists.
         * @author  Michal Krupka
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
         * @author  Michal Krupka
         */

        taskID = task.getTaskID();
        taskStartDate = task.getTaskStartDate();
        taskContent = task.getTaskContent();
        taskStatus = task.getTaskStatus();
        this.insert(taskID, taskStartDate, taskContent, taskStatus);
    }

    public void insert(int taskID, String taskStartDate, String taskContent, String taskStatus) {
        /**
         * <h2>insert(int taskID, String taskStartDate, String taskContent, String taskStatus)</h2>
         * This method is used by insertTask() and has the params drawn from the object Task class.
         * @author  Michal Krupka
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
         * @author  Michal Krupka
         */
        System.out.println("ID"+"\t"+"Start date"+"\t"+"What to do"+"\t"+"Status");
        String sql = "SELECT id, date, content, status FROM TaskList";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("date") + "\t" +
                        rs.getString("content") + "\t" +
                        rs.getString("status"));
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
                Task task = new Task(rs.getInt("id") , rs.getString("date") , rs.getString("content"), rs.getString("status"));
                result.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }

    public int checkLastId(){
        /**
         * <h2>checkLastId()</h2>
         * This method checks the last id stored in the .db file - the next
         * int number is meant to be used for creation of a new object Task.
         * @author  Michal Krupka
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
         * @author  Michal Krupka
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
         * @author  Michal Krupka
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
         * @author  Michal Krupka
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

}

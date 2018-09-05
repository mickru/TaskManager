import java.sql.*;

public class TaskDatabase {

    private static String url = "jdbc:sqlite:testdb.db";
    private static Connection conn;

    private static int taskID;
    private static String taskStartDate;
    private static String taskContent;
    private static String taskStatus;

    {
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS TaskList (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	date text NOT NULL,\n"
                + "	content text NOT NULL, \n"
                + "	status text NOT NULL\n"
                + ");";
        try (
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTask(Task task) {
        taskID = task.getTaskID();
        taskStartDate = task.getTaskStartDate();
        taskContent = task.getTaskContent();
        taskStatus = task.getTaskStatus();
        this.insert(taskID, taskStartDate, taskContent, taskStatus);
    }

    public void insert(int taskID, String taskStartDate, String taskContent, String taskStatus) {

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

}

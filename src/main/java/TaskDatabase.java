import java.sql.*;

public class TaskDatabase {

    public static String url = "jdbc:sqlite:testdb.db";

    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

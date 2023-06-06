package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crypto";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "semih58307";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("yuRRRRR");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}

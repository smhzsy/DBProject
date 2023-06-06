package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int userID;
    private double balance;

    public Account(int userID, double balance) {
        this.userID = userID;
        this.balance = balance;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static Account getAccountByUserID(int userID) {
        Account account = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Account WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("Balance");
                account = new Account(userID, balance);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static void addToAccount(int userID, double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Account SET Balance = Balance + ? WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromAccount(int userID, double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Account SET Balance = Balance - ? WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

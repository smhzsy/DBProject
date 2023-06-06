package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Wallet {
    private int userID;
    private int coinID;
    private double amount;

    public Wallet(int userID, int coinID, double amount) {
        this.userID = userID;
        this.coinID = coinID;
        this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCoinID() {
        return coinID;
    }

    public void setCoinID(int coinID) {
        this.coinID = coinID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static Wallet getWalletByUserID(int userID) {
        Wallet wallet = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Wallet WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int coinID = resultSet.getInt("CoinID");
                double amount = resultSet.getDouble("Amount");
                wallet = new Wallet(userID, coinID, amount);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }

    public static Wallet getWalletByCoinID(int coinID) {
        Wallet wallet = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Wallet WHERE CoinID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, coinID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                double amount = resultSet.getDouble("Amount");
                wallet = new Wallet(userID, coinID, amount);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }

    public static void addToWallet(int userID, int coinID, double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Wallet SET Amount = Amount + ? WHERE UserID = ? AND CoinID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, userID);
            statement.setInt(3, coinID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromWallet(int userID, int coinID, double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Wallet SET Amount = Amount - ? WHERE UserID = ? AND CoinID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, userID);
            statement.setInt(3, coinID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
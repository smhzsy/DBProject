package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Exchange {
    public static void buyCoin(int userID, int coinID, double amount) {
        double coinPrice = getCoinPrice(coinID);
        double accountBalance = getAccountBalance(userID);
        if (accountBalance >= coinPrice * amount) {
            Account.removeFromAccount(userID, coinPrice * amount);
            Wallet.addToWallet(userID, coinID, amount);
            Transaction.createTransaction(userID, 0, coinID, amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public static void sellCoin(int userID, int coinID, double amount) {
        double coinPrice = getCoinPrice(coinID);
        double walletBalance = getWalletBalance(userID, coinID);
        if (walletBalance >= amount) {
            Account.addToAccount(userID, coinPrice * amount);
            Wallet.removeFromWallet(userID, coinID, amount);
            Transaction.createTransaction(0, userID, coinID, amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public static double getCoinPrice(int coinID) {
        double price = 0.0;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT Price FROM Coin WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, coinID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble("Price");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public static double getAccountBalance(int userID) {
        double balance = 0.0;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT Balance FROM Account WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("Balance");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public static double getWalletBalance(int userID, int coinID) {
        double balance = 0.0;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT Amount FROM Wallet WHERE UserID = ? AND CoinID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            statement.setInt(2, coinID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("Amount");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    public static List<Wallet> getUserWallets(int userID) {
        List<Wallet> wallets = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Wallet WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int walletUserID = resultSet.getInt("userID");
                int coinID = resultSet.getInt("coinID");
                double amount = resultSet.getDouble("amount");

                Wallet wallet = new Wallet(walletUserID, coinID, amount);
                wallets.add(wallet);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wallets;
    }
    
    public static double getCoinAmountInWallet(int userID, int coinID) {
        double amount = 0.0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT amount FROM Wallet WHERE userID = ? AND coinID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, coinID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                amount = resultSet.getDouble("amount");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;
    }
}
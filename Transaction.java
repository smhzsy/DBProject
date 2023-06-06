package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
    private int ID;
    private int senderID;
    private int receiverID;
    private int coinID;
    private double amount;
    private Date date;

    public Transaction(int ID, int senderID, int receiverID, int coinID, double amount, Date date) {
        this.ID = ID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.coinID = coinID;
        this.amount = amount;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void createTransaction(int senderID, int receiverID, int coinID, double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Transaction (SenderID, ReceiverID, CoinID, Amount, Date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, senderID);
            statement.setInt(2, receiverID);
            statement.setInt(3, coinID);
            statement.setDouble(4, amount);
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

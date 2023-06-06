package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int ID;
    private String name;
    private String email;
    private String password;

    public User(int ID, String name, String email, String password) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByID(int userID) {
        User user = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM User WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                user = new User(id, name, email, password);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM User WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String userEmail = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                user = new User(id, name, userEmail, password);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(User user) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO User (ID, Name, Email, Password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getID());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

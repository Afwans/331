package DAO;
import util.DBConnection;
import java.sql.*;

public class UserDAO {

    // SIGN UP / REGISTER NEW USER
    public static void signUp(String username, String password, String role) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password); // Plain text for now (Hashing recommended for real projects)
            stmt.setString(3, role);

            stmt.executeUpdate();
            ErrorHandler.showSuccess("User registered successfully!");

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to register user (maybe username already exists)", e);
        }
    }

    // LOGIN / AUTHENTICATION
    public static String login(String username, String password) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ErrorHandler.showSuccess("Login successful! Welcome " + username);
                return rs.getString("Role"); // Return user role
            } else {
                ErrorHandler.showWarning("Invalid username or password");
                return null;
            }
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to login user", e);
            return null;
        }
    }
}

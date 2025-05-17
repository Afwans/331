package DAO;
import util.DBConnection;
import java.sql.*;

public class StaffDAO {

    // INSERT STAFF
    public static void insertStaff(int id, String fname, String lname, String role, String street, String city, String state, String zip) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Staff (StaffID, StaffFName, StaffLName, StaffRole, StaffCampusStreet, StaffCampusCity, StaffCampusState, StaffCampusZip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, fname);
            stmt.setString(3, lname);
            stmt.setString(4, role);
            stmt.setString(5, street);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setString(8, zip);

            stmt.executeUpdate();
            ErrorHandler.showSuccess("Staff added successfully!");

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to add staff", e);
        }
    }

    // UPDATE STAFF
    public static void updateStaff(int id, String role, String street, String city, String state, String zip) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Staff SET StaffRole = ?, StaffCampusStreet = ?, StaffCampusCity = ?, StaffCampusState = ?, StaffCampusZip = ? WHERE StaffID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, role);
            stmt.setString(2, street);
            stmt.setString(3, city);
            stmt.setString(4, state);
            stmt.setString(5, zip);
            stmt.setInt(6, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Staff updated successfully!");
            } else {
                ErrorHandler.showWarning("Staff not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to update staff", e);
        }
    }

    // DELETE STAFF
    public static void deleteStaff(int id) {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM Staff WHERE StaffID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Staff deleted successfully!");
            } else {
                ErrorHandler.showWarning("Staff not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to delete staff", e);
        }
    }

    // SEARCH STAFF BY ID
    public static void getStaffById(int id) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Staff WHERE StaffID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("👤 Staff Found:");
                System.out.println("ID: " + rs.getInt("StaffID"));
                System.out.println("Name: " + rs.getString("StaffFName") + " " + rs.getString("StaffLName"));
                System.out.println("Role: " + rs.getString("StaffRole"));
            } else {
                ErrorHandler.showWarning("Staff not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search staff", e);
        }
    }

    // VIEW ALL STAFF
    public static void getAllStaff() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Staff";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📋 All Staff:");
            while (rs.next()) {
                System.out.println(rs.getInt("StaffID") + ": " + rs.getString("StaffFName") + " " + rs.getString("StaffLName") + " - " + rs.getString("StaffRole"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch staff records", e);
        }
    }
}

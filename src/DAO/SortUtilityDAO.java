package DAO;
import util.DBConnection;
import java.sql.*;

public class SortUtilityDAO {

    // SORT MEMBERS BY COLUMN
    public static void sortMembers(String sortBy) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Member ORDER BY " + sortBy;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("🔽 Members sorted by " + sortBy + ":");
            while (rs.next()) {
                System.out.println(rs.getInt("MemberID") + ": " + rs.getString("MemberFName") + " " + rs.getString("MemberLName"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to sort members", e);
        }
    }

    // SORT BOOKS BY COLUMN
    public static void sortBooks(String sortBy) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Book ORDER BY " + sortBy;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("🔽 Books sorted by " + sortBy + ":");
            while (rs.next()) {
                System.out.println(rs.getString("TitleISBN") + ": " + rs.getString("TitleDescription") + " by " + rs.getString("TitleAuthors"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to sort books", e);
        }
    }

    // SORT BORROW RECORDS BY COLUMN
    public static void sortBorrowRecords(String sortBy) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Borrow ORDER BY " + sortBy;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("🔽 Borrow Records sorted by " + sortBy + ":");
            while (rs.next()) {
                System.out.println("BookID: " + rs.getInt("BookID") +
                        " | Borrowed: " + rs.getString("BorrowDate") +
                        " | Due: " + rs.getString("DueDate"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to sort borrow records", e);
        }
    }

    // SORT STAFF BY ROLE
    public static void sortStaffByRole() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Staff ORDER BY StaffRole";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("🔽 Staff sorted by Role:");
            while (rs.next()) {
                System.out.println(rs.getInt("StaffID") + ": " + rs.getString("StaffFName") + " " + rs.getString("StaffLName") + " - " + rs.getString("StaffRole"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to sort staff records", e);
        }
    }
}

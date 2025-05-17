package DAO;
import util.DBConnection;
import java.sql.*;

public class ReportDAO {

    // REPORT: Overdue Books
    public static void getOverdueBooks() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Borrow WHERE OverdueStatus = 'Overdue'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📚 Overdue Books:");
            while (rs.next()) {
                System.out.println("BookID: " + rs.getInt("BookID") +
                        " | MemberID: " + rs.getInt("MemberID") +
                        " | Fine: $" + rs.getDouble("FineAmount"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch overdue books", e);
        }
    }

    // REPORT: Borrow History of a Member
    public static void getBorrowHistoryByMember(int memberID) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Borrow WHERE MemberID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();

            System.out.println("📖 Borrow History of MemberID: " + memberID);
            while (rs.next()) {
                System.out.println("BookID: " + rs.getInt("BookID") +
                        " | Borrowed: " + rs.getString("BorrowDate") +
                        " | Returned: " + rs.getString("DateReturned"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch borrow history of member", e);
        }
    }

    // REPORT: Borrow History Within Date Range
    public static void getBorrowHistoryByDateRange(String startDate, String endDate) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Borrow WHERE BorrowDate BETWEEN ? AND ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            System.out.println("📅 Borrow Records from " + startDate + " to " + endDate);
            while (rs.next()) {
                System.out.println("BookID: " + rs.getInt("BookID") +
                        " | MemberID: " + rs.getInt("MemberID") +
                        " | Borrowed: " + rs.getString("BorrowDate"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch borrow history by date range", e);
        }
    }

    // REPORT: Active vs Expired Members
    public static void getActiveAndExpiredMembers() {
        Connection conn = DBConnection.getConnection();
        String sqlActive = "SELECT * FROM Member WHERE ExpirationDate >= CURDATE()";
        String sqlExpired = "SELECT * FROM Member WHERE ExpirationDate < CURDATE()";

        try {
            Statement stmt = conn.createStatement();

            System.out.println("🟢 Active Members:");
            ResultSet rsActive = stmt.executeQuery(sqlActive);
            while (rsActive.next()) {
                System.out.println(rsActive.getInt("MemberID") + ": " + rsActive.getString("MemberFName") + " " + rsActive.getString("MemberLName"));
            }

            System.out.println("🔴 Expired Members:");
            ResultSet rsExpired = stmt.executeQuery(sqlExpired);
            while (rsExpired.next()) {
                System.out.println(rsExpired.getInt("MemberID") + ": " + rsExpired.getString("MemberFName") + " " + rsExpired.getString("MemberLName"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch active/expired members", e);
        }
    }
}

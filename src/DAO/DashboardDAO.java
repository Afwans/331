package DAO;
import util.DBConnection;
import java.sql.*;

public class DashboardDAO {

    // TOTAL BOOKS
    public static int getTotalBooks() {
        return getCount("SELECT COUNT(*) FROM Book", "fetch total books");
    }

    // TOTAL ACTIVE MEMBERS
    public static int getTotalActiveMembers() {
        return getCount("SELECT COUNT(*) FROM Member WHERE ExpirationDate >= CURDATE()", "fetch active members");
    }

    // TOTAL EXPIRED MEMBERS
    public static int getTotalExpiredMembers() {
        return getCount("SELECT COUNT(*) FROM Member WHERE ExpirationDate < CURDATE()", "fetch expired members");
    }

    // TOTAL OVERDUE BOOKS
    public static int getTotalOverdueBooks() {
        return getCount("SELECT COUNT(*) FROM Borrow WHERE OverdueStatus = 'Overdue'", "fetch overdue books");
    }

    // GENERIC COUNTER WITH ERROR HANDLER
    private static int getCount(String sql, String context) {
        Connection conn = DBConnection.getConnection();
        int count = 0;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to " + context, e);
        }

        return count;
    }
}

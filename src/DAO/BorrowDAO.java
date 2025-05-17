package DAO;
import util.DBConnection;
import java.sql.*;
import java.time.LocalDate;

public class BorrowDAO {

    // RECORD BORROW TRANSACTION
    public static void borrowBook(int bookID, int memberID, String titleISBN, String borrowDate, String dueDate) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Borrow (BookID, MemberID, TitleISBN, BorrowDate, DueDate, OverdueStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookID);
            stmt.setInt(2, memberID);
            stmt.setString(3, titleISBN);
            stmt.setString(4, borrowDate);
            stmt.setString(5, dueDate);
            stmt.setString(6, "Not Returned");

            stmt.executeUpdate();
            ErrorHandler.showSuccess("Book borrowed successfully!");

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to borrow book", e);
        }
    }

    // RETURN BOOK WITH FINE CALCULATION
    public static void returnBook(int bookID, String returnDate) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Borrow SET DateReturned = ?, OverdueStatus = ?, FineAmount = ? WHERE BookID = ?";

        try {
            String status = "Returned";
            double fine = 0;

            String checkDueSql = "SELECT DueDate FROM Borrow WHERE BookID = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkDueSql);
            checkStmt.setInt(1, bookID);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String dueDate = rs.getString("DueDate");
                LocalDate due = LocalDate.parse(dueDate);
                LocalDate returned = LocalDate.parse(returnDate);

                if (returned.isAfter(due)) {
                    status = "Overdue";
                    long daysLate = java.time.temporal.ChronoUnit.DAYS.between(due, returned);
                    fine = daysLate * 2.00;
                }
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, returnDate);
            stmt.setString(2, status);
            stmt.setDouble(3, fine);
            stmt.setInt(4, bookID);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Book returned successfully!");
                if (fine > 0) {
                    ErrorHandler.showWarning("Fine Due: $" + fine);
                }
            } else {
                ErrorHandler.showWarning("Book not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to return book", e);
        }
    }

    // VIEW ALL BORROW RECORDS
    public static void getAllBorrows() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Borrow";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📦 All Borrow Records:");
            while (rs.next()) {
                System.out.println("BookID: " + rs.getInt("BookID") +
                        " | MemberID: " + rs.getInt("MemberID") +
                        " | Borrowed: " + rs.getString("BorrowDate") +
                        " | Due: " + rs.getString("DueDate") +
                        " | Returned: " + rs.getString("DateReturned") +
                        " | Status: " + rs.getString("OverdueStatus") +
                        " | Fine: $" + rs.getDouble("FineAmount"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch borrow records", e);
        }
    }
}

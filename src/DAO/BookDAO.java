package DAO;
import util.DBConnection;
import java.sql.*;

public class BookDAO {

    // INSERT BOOK
    public static void insertBook(String isbn, String description, String binding, String language,
                                  String edition, String subject, String authors, String status) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Book (TitleISBN, TitleDescription, TitleBinding, TitleLanguage, TitleEdition, SubjectAreas, TitleAuthors, BookStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);
            stmt.setString(2, description);
            stmt.setString(3, binding);
            stmt.setString(4, language);
            stmt.setString(5, edition);
            stmt.setString(6, subject);
            stmt.setString(7, authors);
            stmt.setString(8, status);

            stmt.executeUpdate();
            ErrorHandler.showSuccess("Book inserted successfully!");

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to insert book", e);
        }
    }

    // UPDATE BOOK
    public static void updateBook(String isbn, String description, String status) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Book SET TitleDescription = ?, BookStatus = ? WHERE TitleISBN = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, description);
            stmt.setString(2, status);
            stmt.setString(3, isbn);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Book updated successfully!");
            } else {
                ErrorHandler.showWarning("Book not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to update book", e);
        }
    }

    // DELETE BOOK
    public static void deleteBook(String isbn) {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM Book WHERE TitleISBN = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Book deleted successfully!");
            } else {
                ErrorHandler.showWarning("Book not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to delete book", e);
        }
    }

    // SEARCH BOOK BY ISBN
    public static void getBookByISBN(String isbn) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Book WHERE TitleISBN = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("📖 Book Found:");
                System.out.println("ISBN: " + rs.getString("TitleISBN"));
                System.out.println("Title: " + rs.getString("TitleDescription"));
                System.out.println("Author: " + rs.getString("TitleAuthors"));
                System.out.println("Status: " + rs.getString("BookStatus"));
            } else {
                ErrorHandler.showWarning("Book not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search book", e);
        }
    }

    // VIEW ALL BOOKS
    public static void getAllBooks() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Book";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📚 All Books:");
            while (rs.next()) {
                System.out.println(rs.getString("TitleISBN") + ": " + rs.getString("TitleDescription") + " by " + rs.getString("TitleAuthors") + " (" + rs.getString("BookStatus") + ")");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch books", e);
        }
    }
}

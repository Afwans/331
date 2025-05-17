package util;
import DAO.ErrorHandler;
import java.sql.*;

public class SearchUtilityDAO {

    // SEARCH MEMBERS BY NAME OR PHONE
    public static void searchMember(String keyword) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Member WHERE MemberFName LIKE ? OR MemberLName LIKE ? OR PhoneNumber LIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            stmt.setString(3, keyword);

            ResultSet rs = stmt.executeQuery();

            System.out.println("🔍 Matching Members:");
            while (rs.next()) {
                System.out.println(rs.getInt("MemberID") + ": " + rs.getString("MemberFName") + " " + rs.getString("MemberLName"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search members", e);
        }
    }

    // SEARCH BOOKS BY TITLE, AUTHOR OR ISBN
    public static void searchBook(String keyword) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Book WHERE TitleDescription LIKE ? OR TitleAuthors LIKE ? OR TitleISBN LIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            stmt.setString(3, keyword);

            ResultSet rs = stmt.executeQuery();

            System.out.println("🔍 Matching Books:");
            while (rs.next()) {
                System.out.println(rs.getString("TitleISBN") + ": " + rs.getString("TitleDescription") + " by " + rs.getString("TitleAuthors"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search books", e);
        }
    }

    // SEARCH STAFF BY NAME OR ROLE
    public static void searchStaff(String keyword) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Staff WHERE StaffFName LIKE ? OR StaffLName LIKE ? OR StaffRole LIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            stmt.setString(3, keyword);

            ResultSet rs = stmt.executeQuery();

            System.out.println("🔍 Matching Staff:");
            while (rs.next()) {
                System.out.println(rs.getInt("StaffID") + ": " + rs.getString("StaffFName") + " " + rs.getString("StaffLName") + " - " + rs.getString("StaffRole"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search staff", e);
        }
    }
}

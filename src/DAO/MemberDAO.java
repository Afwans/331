package DAO;
import util.DBConnection;
import java.sql.*;

public class MemberDAO {

    // INSERT MEMBER
    public static void insertMember(int memberID, String fname, String lname, String phone, String expirationDate, String memberType,
                                    int ssn, String homeStreet, String homeCity, String homeState, String homeZip,
                                    String campusStreet, String campusCity, String campusState, String campusZip) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Member (MemberID, MemberFName, MemberLName, PhoneNumber, ExpirationDate, MemberType, SSNNum, HomeStreet, HomeCity, HomeState, HomeZip, CampusStreet, CampusCity, CampusState, CampusZip) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberID);
            stmt.setString(2, fname);
            stmt.setString(3, lname);
            stmt.setString(4, phone);
            stmt.setString(5, expirationDate);
            stmt.setString(6, memberType);
            stmt.setInt(7, ssn);
            stmt.setString(8, homeStreet);
            stmt.setString(9, homeCity);
            stmt.setString(10, homeState);
            stmt.setString(11, homeZip);
            stmt.setString(12, campusStreet);
            stmt.setString(13, campusCity);
            stmt.setString(14, campusState);
            stmt.setString(15, campusZip);

            stmt.executeUpdate();
            ErrorHandler.showSuccess("Member inserted successfully!");
            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to insert member", e);
        }
    }

    // UPDATE MEMBER
    public static void updateMember(int memberID, String fname, String lname, String phone, String expirationDate, String memberType,
                                    int ssn, String homeStreet, String homeCity, String homeState, String homeZip,
                                    String campusStreet, String campusCity, String campusState, String campusZip) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Member SET MemberFName = ?, MemberLName = ?, PhoneNumber = ?, ExpirationDate = ?, MemberType = ?, " +
                "SSNNum = ?, HomeStreet = ?, HomeCity = ?, HomeState = ?, HomeZip = ?, " +
                "CampusStreet = ?, CampusCity = ?, CampusState = ?, CampusZip = ? WHERE MemberID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, phone);
            stmt.setString(4, expirationDate);
            stmt.setString(5, memberType);
            stmt.setInt(6, ssn);
            stmt.setString(7, homeStreet);
            stmt.setString(8, homeCity);
            stmt.setString(9, homeState);
            stmt.setString(10, homeZip);
            stmt.setString(11, campusStreet);
            stmt.setString(12, campusCity);
            stmt.setString(13, campusState);
            stmt.setString(14, campusZip);
            stmt.setInt(15, memberID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                ErrorHandler.showSuccess("Member updated successfully!");
            } else {
                ErrorHandler.showWarning("Member not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to update member", e);
        }
    }

    // DELETE MEMBER
    public static void deleteMember(int memberID) {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM Member WHERE MemberID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ErrorHandler.showSuccess("Member deleted successfully!");
            } else {
                ErrorHandler.showWarning("Member not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to delete member", e);
        }
    }

    // SEARCH MEMBER BY ID
    public static void getMemberById(int memberID) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Member WHERE MemberID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("👤 Member Found:");
                System.out.println("ID: " + rs.getInt("MemberID"));
                System.out.println("Name: " + rs.getString("MemberFName") + " " + rs.getString("MemberLName"));
                System.out.println("Phone: " + rs.getString("PhoneNumber"));
                System.out.println("Expires: " + rs.getString("ExpirationDate"));
                System.out.println("Type: " + rs.getString("MemberType"));
                System.out.println("SSN: " + rs.getInt("SSNNum"));
                System.out.println("🏠 Home Address: " + rs.getString("HomeStreet") + ", " + rs.getString("HomeCity") + ", " + rs.getString("HomeState") + " " + rs.getString("HomeZip"));
                System.out.println("🏫 Campus Address: " + rs.getString("CampusStreet") + ", " + rs.getString("CampusCity") + ", " + rs.getString("CampusState") + " " + rs.getString("CampusZip"));
            } else {
                ErrorHandler.showWarning("Member not found.");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to search member", e);
        }
    }

    // VIEW ALL MEMBERS
    public static void getAllMembers() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Member";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📋 All Members:");
            while (rs.next()) {
                System.out.println(rs.getInt("MemberID") + ": " + rs.getString("MemberFName") + " " + rs.getString("MemberLName") + " (" + rs.getString("MemberType") + ")");
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch members", e);
        }
    }

    // VIEW ALL FACULTY
    public static void getAllFaculty() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Member WHERE MemberType = 'Faculty'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("👨‍🏫 All Faculty Members:");
            while (rs.next()) {
                System.out.println(rs.getInt("MemberID") + ": " + rs.getString("MemberFName") + " " + rs.getString("MemberLName"));
            }

            conn.close();
        } catch (SQLException e) {
            ErrorHandler.showError("Failed to fetch faculty members", e);
        }
    }
}

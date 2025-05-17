public class Main {
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("📚 LIBRARY MANAGEMENT SYSTEM (CONSOLE)");
        System.out.println("=======================================\n");

        // DASHBOARD
        System.out.println("===== DASHBOARD =====");
        System.out.println("📚 Total Books: " + DAO.DashboardDAO.getTotalBooks());
        System.out.println("🟢 Active Members: " + DAO.DashboardDAO.getTotalActiveMembers());
        System.out.println("🔴 Expired Members: " + DAO.DashboardDAO.getTotalExpiredMembers());
        System.out.println("⚠️ Overdue Books: " + DAO.DashboardDAO.getTotalOverdueBooks());
        System.out.println("=====================\n");


        // MEMBER OPERATIONS
        System.out.println("===== MEMBER TEST =====");
//        MemberDAO.insertMember(5, "Tom", "David", "1234567890", "2026-12-31", "Faculty", 55555555, "76 Norfeld Blvd", "Elmont", "NY", "11003", "50 Street", "Maspeth", "NY", "11375");
//        MemberDAO.updateMember(5, "Tom", "David", "1234567890", "2026-12-31", "Student", 55555555, "76 Norfeld Blvd", "Elmont", "NY", "11003", "50 Street", "Maspeth", "NY", "11375");
        DAO.MemberDAO.getMemberById(3);
        DAO.MemberDAO.getAllMembers();
        DAO.MemberDAO.getAllFaculty();
//        MemberDAO.deleteMember(10);
        System.out.println("=======================\n");
//
//
//        // BOOK OPERATIONS
//        System.out.println("===== BOOK TEST =====");
//        BookDAO.insertBook("ISBN001", "Clean Code", "Paperback", "English", "1st", "Programming", "Robert C. Martin", "Available");
//        BookDAO.updateBook("ISBN001", "Clean Code (Updated)", "Checked Out");
//        BookDAO.getBookByISBN("ISBN001");
//        BookDAO.getAllBooks();
//        BookDAO.deleteBook("ISBN001");
//        System.out.println("=====================\n");
//
//
//        // STAFF OPERATIONS
//        System.out.println("===== STAFF TEST =====");
//        StaffDAO.insertStaff(101, "Sarah", "Brown", "Librarian", "456 Campus Rd", "Queens", "NY", "11367");
//        StaffDAO.updateStaff(101, "Admin", "789 Admin St", "Brooklyn", "NY", "11230");
//        StaffDAO.getStaffById(101);
//        StaffDAO.getAllStaff();
//        StaffDAO.deleteStaff(101);
//        System.out.println("======================\n");
//
//
//        // BORROW OPERATIONS
//        System.out.println("===== BORROW / RETURN TEST =====");
//        BorrowDAO.borrowBook(2001, 2, "ISBN002", "2025-04-01", "2025-04-10");
//        BorrowDAO.returnBook(2001, "2025-04-15");
//        BorrowDAO.getAllBorrows();
//        System.out.println("===============================\n");
//
//
//        // REPORT OPERATIONS
//        System.out.println("===== REPORTS TEST =====");
//        ReportDAO.getOverdueBooks();
//        ReportDAO.getBorrowHistoryByMember(2);
//        ReportDAO.getBorrowHistoryByDateRange("2025-04-01", "2025-04-20");
//        ReportDAO.getActiveAndExpiredMembers();
//        System.out.println("========================\n");
//
//
//        // USER AUTH / LOGIN SYSTEM
//        System.out.println("===== LOGIN SYSTEM TEST =====");
//        UserDAO.signUp("afwan", "1234", "Admin");
//        String role = UserDAO.login("afwan", "1234");
//
//        if (role != null) {
//            System.out.println("Logged in as: " + role);
//            switch (role) {
//                case "Admin":
//                    System.out.println("Admin Dashboard Access");
//                    break;
//                case "Faculty":
//                    System.out.println("Faculty Dashboard Access");
//                    break;
//                case "Staff":
//                    System.out.println("Staff Dashboard Access");
//                    break;
//                case "Student":
//                    System.out.println("Student Dashboard Access");
//                    break;
//                default:
//                    ErrorHandler.showWarning("Unknown Role Detected");
//            }
//        }
//        System.out.println("=============================\n");
//
//
//        // SEARCH FUNCTIONALITY TEST
//        System.out.println("===== SEARCH TEST =====");
//        SearchUtilityDAO.searchMember("afwan");
//        SearchUtilityDAO.searchBook("clean");
//        SearchUtilityDAO.searchStaff("librarian");
//        System.out.println("=======================\n");
//
//
//        // SORT FUNCTIONALITY TEST
//        System.out.println("===== SORT TEST =====");
//        SortUtilityDAO.sortMembers("MemberFName");
//        SortUtilityDAO.sortBooks("TitleAuthors");
//        SortUtilityDAO.sortBorrowRecords("BorrowDate");
//        SortUtilityDAO.sortStaffByRole();
//        System.out.println("=====================\n");

        System.out.println("======== END OF TEST ========");

    }
}

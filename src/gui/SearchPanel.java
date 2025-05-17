package gui;
import DAO.SortUtilityDAO;
import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JFrame {

    public SearchPanel() {
        setTitle("Search & Sort Utility");
        setSize(300, 300);
        setLayout(new GridLayout(6, 1, 10, 10));
        setLocationRelativeTo(null);

        JButton searchMemberBtn = new JButton("Search Members");
        JButton searchBookBtn = new JButton("Search Books");
        JButton searchStaffBtn = new JButton("Search Staff");
        JButton sortMembersBtn = new JButton("Sort Members by Name");
        JButton sortBooksBtn = new JButton("Sort Books by Author");
        JButton sortBorrowsBtn = new JButton("Sort Borrows by Date");

        add(searchMemberBtn);
        add(searchBookBtn);
        add(searchStaffBtn);
        add(sortMembersBtn);
        add(sortBooksBtn);
        add(sortBorrowsBtn);

        searchMemberBtn.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Enter keyword:");
            if (keyword != null) util.SearchUtilityDAO.searchMember(keyword);
        });

        searchBookBtn.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Enter keyword:");
            if (keyword != null) util.SearchUtilityDAO.searchBook(keyword);
        });

        searchStaffBtn.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Enter keyword:");
            if (keyword != null) util.SearchUtilityDAO.searchStaff(keyword);
        });

        sortMembersBtn.addActionListener(e -> SortUtilityDAO.sortMembers("MemberFName"));
        sortBooksBtn.addActionListener(e -> SortUtilityDAO.sortBooks("TitleAuthors"));
        sortBorrowsBtn.addActionListener(e -> SortUtilityDAO.sortBorrowRecords("BorrowDate"));

        setVisible(true);
    }
}

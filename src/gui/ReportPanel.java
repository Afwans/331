package gui;
import DAO.ReportDAO;
import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JFrame {

    public ReportPanel() {
        setTitle("Reports");
        setSize(300, 250);
        setLayout(new GridLayout(5, 1, 10, 10));
        setLocationRelativeTo(null);

        JButton overdueBtn = new JButton("Overdue Books");
        JButton memberHistoryBtn = new JButton("Member Borrow History");
        JButton dateRangeBtn = new JButton("Borrow History by Date");
        JButton activeExpiredBtn = new JButton("Active vs Expired Members");

        add(overdueBtn);
        add(memberHistoryBtn);
        add(dateRangeBtn);
        add(activeExpiredBtn);

        overdueBtn.addActionListener(e -> ReportDAO.getOverdueBooks());
        memberHistoryBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Member ID:");
            if (id != null) ReportDAO.getBorrowHistoryByMember(Integer.parseInt(id));
        });
        dateRangeBtn.addActionListener(e -> {
            String start = JOptionPane.showInputDialog("Start Date (YYYY-MM-DD):");
            String end = JOptionPane.showInputDialog("End Date (YYYY-MM-DD):");
            if (start != null && end != null) ReportDAO.getBorrowHistoryByDateRange(start, end);
        });
        activeExpiredBtn.addActionListener(e -> ReportDAO.getActiveAndExpiredMembers());

        setVisible(true);
    }
}

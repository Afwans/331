package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(String role) {
        setTitle("Library Management Dashboard - Role: " + role);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JButton memberBtn = new JButton("Manage Members");
        JButton bookBtn = new JButton("Manage Books");
        JButton staffBtn = new JButton("Manage Staff");
        JButton borrowBtn = new JButton("Borrow / Return");
        JButton reportBtn = new JButton("Reports");
        JButton searchBtn = new JButton("Search / Sort");
        JButton logoutBtn = new JButton("Logout");

        // Add listeners for navigation
        memberBtn.addActionListener(e -> new MemberPanel());
        bookBtn.addActionListener(e -> new BookPanel());
        staffBtn.addActionListener(e -> new StaffPanel());
        borrowBtn.addActionListener(e -> new BorrowPanel());
        reportBtn.addActionListener(e -> new ReportPanel());
        searchBtn.addActionListener(e -> new SearchPanel());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        // Add buttons based on role
        if (role.equals("Admin")) {
            add(memberBtn);
            add(bookBtn);
            add(staffBtn);
            add(borrowBtn);
            add(reportBtn);
            add(searchBtn);
            add(logoutBtn);
        } else if (role.equals("Staff") || role.equals("Faculty")) {
            add(memberBtn);
            add(bookBtn);
            add(borrowBtn);
            add(searchBtn);
            add(logoutBtn);
        } else if (role.equals("Student")) {
            add(searchBtn);
            add(logoutBtn);
        }

        setVisible(true);
    }
}

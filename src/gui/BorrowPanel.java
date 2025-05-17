package gui;

import DAO.BorrowDAO;
import javax.swing.*;
import java.awt.*;

public class BorrowPanel extends JFrame {

    public BorrowPanel() {
        setTitle("Borrow & Return");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField bookIDField = new JTextField();
        JTextField memberIDField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField borrowDateField = new JTextField();
        JTextField dueDateField = new JTextField();
        JTextField returnDateField = new JTextField();

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; panel.add(bookIDField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Member ID:"), gbc);
        gbc.gridx = 1; panel.add(memberIDField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1; panel.add(isbnField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Borrow Date:"), gbc);
        gbc.gridx = 1; panel.add(borrowDateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Due Date:"), gbc);
        gbc.gridx = 1; panel.add(dueDateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Return Date:"), gbc);
        gbc.gridx = 1; panel.add(returnDateField, gbc);

        // Buttons
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton viewBtn = new JButton("View All Records");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(borrowBtn);
        buttonPanel.add(returnBtn);
        buttonPanel.add(viewBtn);

        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);

        // Button Actions
        borrowBtn.addActionListener(e -> {
            try {
                BorrowDAO.borrowBook(
                        Integer.parseInt(bookIDField.getText()),
                        Integer.parseInt(memberIDField.getText()),
                        isbnField.getText(),
                        borrowDateField.getText(),
                        dueDateField.getText()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        returnBtn.addActionListener(e -> {
            try {
                BorrowDAO.returnBook(
                        Integer.parseInt(bookIDField.getText()),
                        returnDateField.getText()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        viewBtn.addActionListener(e -> BorrowDAO.getAllBorrows());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BorrowPanel::new);
    }
}

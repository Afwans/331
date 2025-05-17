package gui;

import DAO.StaffDAO;
import javax.swing.*;
import java.awt.*;

public class StaffPanel extends JFrame {

    public StaffPanel() {
        setTitle("Staff Management");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ✅ Doesn't exit app

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField idField = new JTextField();
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField streetField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField stateField = new JTextField();
        JTextField zipField = new JTextField();

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("Staff ID:"), gbc);
        gbc.gridx = 1; panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; panel.add(fnameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1; panel.add(lnameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1; panel.add(roleField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Street:"), gbc);
        gbc.gridx = 1; panel.add(streetField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("City:"), gbc);
        gbc.gridx = 1; panel.add(cityField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("State:"), gbc);
        gbc.gridx = 1; panel.add(stateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Zip:"), gbc);
        gbc.gridx = 1; panel.add(zipField, gbc);

        // Buttons
        JButton insertBtn = new JButton("Add Staff");
        JButton updateBtn = new JButton("Update Staff");
        JButton viewBtn = new JButton("View All Staff");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(insertBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(viewBtn);

        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);

        // Button Actions
        insertBtn.addActionListener(e -> {
            try {
                StaffDAO.insertStaff(
                        Integer.parseInt(idField.getText()),
                        fnameField.getText(), lnameField.getText(), roleField.getText(),
                        streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            try {
                StaffDAO.updateStaff(
                        Integer.parseInt(idField.getText()), roleField.getText(),
                        streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        viewBtn.addActionListener(e -> StaffDAO.getAllStaff());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StaffPanel::new);
    }
}

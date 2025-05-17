package gui;

import DAO.MemberDAO;
import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JFrame {

    private JTextField idField, fnameField, lnameField, phoneField, expDateField, ssnField;
    private JComboBox<String> typeBox;
    private JTextField homeStreetField, homeCityField, homeStateField, homeZipField;
    private JTextField campusStreetField, campusCityField, campusStateField, campusZipField;

    public MemberPanel() {
        setTitle("Member Management");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField();
        fnameField = new JTextField();
        lnameField = new JTextField();
        phoneField = new JTextField();
        expDateField = new JTextField();
        ssnField = new JTextField();
        typeBox = new JComboBox<>(new String[]{"Student", "Faculty", "Staff"});

        homeStreetField = new JTextField();
        homeCityField = new JTextField();
        homeStateField = new JTextField();
        homeZipField = new JTextField();

        campusStreetField = new JTextField();
        campusCityField = new JTextField();
        campusStateField = new JTextField();
        campusZipField = new JTextField();

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("Member ID:"), gbc);
        gbc.gridx = 1; panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; panel.add(fnameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1; panel.add(lnameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Expiration Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; panel.add(expDateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Member Type:"), gbc);
        gbc.gridx = 1; panel.add(typeBox, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("SSN (9-digit):"), gbc);
        gbc.gridx = 1; panel.add(ssnField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Home Street:"), gbc);
        gbc.gridx = 1; panel.add(homeStreetField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Home City:"), gbc);
        gbc.gridx = 1; panel.add(homeCityField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Home State:"), gbc);
        gbc.gridx = 1; panel.add(homeStateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Home Zip:"), gbc);
        gbc.gridx = 1; panel.add(homeZipField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Campus Street:"), gbc);
        gbc.gridx = 1; panel.add(campusStreetField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Campus City:"), gbc);
        gbc.gridx = 1; panel.add(campusCityField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Campus State:"), gbc);
        gbc.gridx = 1; panel.add(campusStateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Campus Zip:"), gbc);
        gbc.gridx = 1; panel.add(campusZipField, gbc);

        // Buttons
        JButton addBtn = new JButton("Add Member");
        JButton updateBtn = new JButton("Update Member");
        JButton deleteBtn = new JButton("Delete Member");
        JButton viewBtn = new JButton("View All Members");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(viewBtn);

        gbc.gridx = 0; gbc.gridy = ++row;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);

        // Button Actions
        addBtn.addActionListener(e -> handleInsert());
        updateBtn.addActionListener(e -> handleUpdate());
        deleteBtn.addActionListener(e -> handleDelete());
        viewBtn.addActionListener(e -> MemberDAO.getAllMembers());

        setVisible(true);
    }

    private void handleInsert() {
        try {
            MemberDAO.insertMember(
                    Integer.parseInt(idField.getText()),
                    fnameField.getText(),
                    lnameField.getText(),
                    phoneField.getText(),
                    expDateField.getText(),
                    (String) typeBox.getSelectedItem(),
                    Integer.parseInt(ssnField.getText()),
                    homeStreetField.getText(),
                    homeCityField.getText(),
                    homeStateField.getText(),
                    homeZipField.getText(),
                    campusStreetField.getText(),
                    campusCityField.getText(),
                    campusStateField.getText(),
                    campusZipField.getText()
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            MemberDAO.updateMember(
                    Integer.parseInt(idField.getText()),
                    fnameField.getText(),
                    lnameField.getText(),
                    phoneField.getText(),
                    expDateField.getText(),
                    (String) typeBox.getSelectedItem(),
                    Integer.parseInt(ssnField.getText()),
                    homeStreetField.getText(),
                    homeCityField.getText(),
                    homeStateField.getText(),
                    homeZipField.getText(),
                    campusStreetField.getText(),
                    campusCityField.getText(),
                    campusStateField.getText(),
                    campusZipField.getText()
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int memberId = Integer.parseInt(idField.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete Member ID " + memberId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                MemberDAO.deleteMember(memberId);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new MemberPanel();
    }
}

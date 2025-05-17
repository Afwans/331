package gui;

import DAO.BookDAO;
import javax.swing.*;
import java.awt.*;

public class BookPanel extends JFrame {

    private JTextField isbnField, titleField, bindingField, languageField, editionField;
    private JTextField subjectField, authorsField;
    private JComboBox<String> statusBox;

    public BookPanel() {
        setTitle("Book Management");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        isbnField = new JTextField();
        titleField = new JTextField();
        bindingField = new JTextField();
        languageField = new JTextField();
        editionField = new JTextField();
        subjectField = new JTextField();
        authorsField = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Available", "Checked Out"});

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1; panel.add(isbnField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; panel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Binding:"), gbc);
        gbc.gridx = 1; panel.add(bindingField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Language:"), gbc);
        gbc.gridx = 1; panel.add(languageField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Edition:"), gbc);
        gbc.gridx = 1; panel.add(editionField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1; panel.add(subjectField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Authors:"), gbc);
        gbc.gridx = 1; panel.add(authorsField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; panel.add(statusBox, gbc);

        // Buttons
        JButton insertBtn = new JButton("Add Book");
        JButton updateBtn = new JButton("Update Book");
        JButton viewBtn = new JButton("View All Books");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(insertBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(viewBtn);

        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);

        // Button Actions
        insertBtn.addActionListener(e -> {
            BookDAO.insertBook(
                    isbnField.getText(), titleField.getText(), bindingField.getText(),
                    languageField.getText(), editionField.getText(), subjectField.getText(),
                    authorsField.getText(), (String) statusBox.getSelectedItem()
            );
        });

        updateBtn.addActionListener(e -> {
            BookDAO.updateBook(
                    isbnField.getText(), titleField.getText(),
                    (String) statusBox.getSelectedItem()
            );
        });

        viewBtn.addActionListener(e -> BookDAO.getAllBooks());

        setVisible(true);
    }

    public static void main(String[] args) {
        new BookPanel();
    }
}

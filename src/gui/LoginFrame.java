package gui;
import DAO.UserDAO;
import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Library System Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 160, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        String role = UserDAO.login(username, password);
        if (role != null) {
            JOptionPane.showMessageDialog(this, "Login successful as " + role);
            this.dispose(); // close login window

            // Load dashboard
            new DashboardFrame(role);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

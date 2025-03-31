package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Fortis Bank - Connexion");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Nom utilisateur:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Connexion");

        loginButton.addActionListener(e -> {
            // Authentification simplifiée
            if (userText.getText().equals("admin") && new String(passwordText.getPassword()).equals("1234")) {
                dispose();
                new MainMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants incorrects");
            }
        });

        panel.add(userLabel); panel.add(userText);
        panel.add(passwordLabel); panel.add(passwordText);
        panel.add(new JLabel()); panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}

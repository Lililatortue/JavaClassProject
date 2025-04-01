package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Main_V2 extends JFrame {
	
	private static final long serialVersionUID = -8127836578271881936L;

	public Main_V2() {
        setTitle("Fortis Bank - Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitre = new JLabel("Bienvenue dans Fortis Bank", SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnGestionnaire = new JButton("Connexion Gestionnaire");
        JButton btnClient = new JButton("Connexion Client");
        JButton btnQuitter = new JButton("Quitter");

        panel.add(labelTitre);
        panel.add(btnGestionnaire);
        panel.add(btnClient);
        panel.add(btnQuitter);

        add(panel);

        // Action pour quitter
        btnQuitter.addActionListener(_ -> System.exit(0));

        // Gestionnaire
        btnGestionnaire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginGestionnaire login = new LoginGestionnaire();
                login.setVisible(true);
            }
        });
        
      
        // Client
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginClient login = new LoginClient();
                login.setVisible(true);
            }
        });
        
        
    }
	
	  public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new Main_V2().setVisible(true);
	        });
	  }
}

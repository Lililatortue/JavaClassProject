package ui;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import business.Client;
import business.FileManager;

public class LoginClient extends JFrame {
	private static final long serialVersionUID = 6121083974946195897L;
	private JTextField txtId;
    private JPasswordField txtNip;
    private JLabel lblStatus;
    private List<Client> clients;

    public LoginClient() {
        setTitle("Connexion Client");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitre = new JLabel("Connexion Client");
        lblTitre.setBounds(100, 20, 200, 20);
        getContentPane().add(lblTitre);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(30, 60, 100, 20);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 60, 180, 25);
        getContentPane().add(txtId);

        JLabel lblNip = new JLabel("NIP :");
        lblNip.setBounds(30, 100, 100, 20);
        getContentPane().add(lblNip);

        txtNip = new JPasswordField();
        txtNip.setBounds(100, 100, 180, 25);
        getContentPane().add(txtNip);

        JButton btnConnexion = new JButton("Connexion");
        btnConnexion.setBounds(100, 140, 120, 30);
        getContentPane().add(btnConnexion);

        lblStatus = new JLabel("");
        lblStatus.setBounds(30, 180, 300, 20);
        getContentPane().add(lblStatus);

        // Charger les clients
        clients = FileManager.chargerClients("data/clients.dat");

        btnConnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nip = new String(txtNip.getPassword());

                    for (Client c : clients) {
                        if (c.getId() == id && c.verifierNIP(nip)) {
                            lblStatus.setText("Bienvenue " + c.getNom());
                            new MenuClient(c, clients).setVisible(true);
                            dispose();
                            return;
                        }
                    }

                    lblStatus.setText("Identifiants invalides");

                } catch (NumberFormatException ex) {
                    lblStatus.setText("ID invalide (doit être un nombre)");
                }
            }
        });
    }
}

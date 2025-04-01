package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.*;
import java.util.List;
import business.Gestionnaire;
import business.Client;
import business.FileManager;

public class LoginGestionnaire extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JPasswordField txtNip;
	private JButton btnConnexion;
	private JLabel lblStatus;

	private List<Gestionnaire> gestionnaires;
	private List<Client> clients;

	public LoginGestionnaire() {
		setTitle("Connexion Gestionnaire");
		setBounds(100, 100, 350, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblTitre = new JLabel("Connexion Gestionnaire");
		lblTitre.setBounds(90, 20, 200, 20);
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

		btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(100, 140, 120, 30);
		getContentPane().add(btnConnexion);

		lblStatus = new JLabel("");
		lblStatus.setBounds(30, 180, 300, 20);
		getContentPane().add(lblStatus);

		
		gestionnaires = FileManager.chargerGestionnaires("data/gestionnaires.dat");
		clients = FileManager.chargerClients("data/clients.dat");


		// Action du bouton
		btnConnexion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int id = Integer.parseInt(txtId.getText());
		            String nip = new String(txtNip.getPassword());

		            for (Gestionnaire g : gestionnaires) {
		                if (g.getId() == id && g.verifierNIP(nip)) {
		                    lblStatus.setText("Bienvenue " + g.getNom());
		                    clients = FileManager.chargerClients("data/clients.dat");
		                    new MenuGestionnaire(g, clients).setVisible(true);

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

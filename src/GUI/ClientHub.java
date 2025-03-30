package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.buisness.client.Utilisateur;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;

public class ClientHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ClientHub(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClienthub = new JLabel("ClientHub");
		lblClienthub.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClienthub.setBounds(12, 13, 154, 16);
		contentPane.add(lblClienthub);
		
		JLabel label_welcome = new JLabel("welcome: ");
		label_welcome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_welcome.setBounds(12, 48, 154, 16);
		contentPane.add(label_welcome);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 105, 251, 143);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_CompteOption = new JLabel("Compte option");
		label_CompteOption.setBounds(12, 13, 133, 22);
		label_CompteOption.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(label_CompteOption);
		
		JList List_Compte_disponible = new JList();
		List_Compte_disponible.setBounds(12, 64, 115, 22);
		panel.add(List_Compte_disponible);
		
		JLabel Label_list_Compte = new JLabel("Comptes:");
		Label_list_Compte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_list_Compte.setBounds(12, 35, 154, 16);
		panel.add(Label_list_Compte);
		
		JButton btnCompteConsulter = new JButton("consulter");
		btnCompteConsulter.setBounds(142, 61, 97, 25);
		panel.add(btnCompteConsulter);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 254, 251, 143);
		contentPane.add(panel_1);
		
		JLabel label_Compte = new JLabel("Ouverture de compte");
		label_Compte.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_Compte.setBounds(12, 13, 227, 22);
		panel_1.add(label_Compte);
		
		JList List_Compte_disponible_1 = new JList();
		List_Compte_disponible_1.setBounds(12, 64, 115, 22);
		panel_1.add(List_Compte_disponible_1);
		
		JLabel Label_list_Compte_1 = new JLabel("Comptes:");
		Label_list_Compte_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_list_Compte_1.setBounds(12, 35, 154, 16);
		panel_1.add(Label_list_Compte_1);
		
		JButton btnAppliquerLaDemande = new JButton("appliquer la demande");
		btnAppliquerLaDemande.setBounds(142, 61, 97, 25);
		panel_1.add(btnAppliquerLaDemande);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(346, 105, 303, 279);
		contentPane.add(panel_2);
		
		JLabel label_Event = new JLabel("Event");
		label_Event.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(label_Event);
	}
}

package com.Ui.ClientHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Utilisateur;
import com.Bus.Model.Compte.*;
import com.Bus.Service.CompteManagement.CompteRequestManagement;
import com.Bus.Service.UserManagement.ClientManagement;
import com.DAL.Repository.Exception.KeyConstraintException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompteRequestManagement request = new CompteRequestManagement();
	private ClientManagement _client = new ClientManagement();
	/**
	 * Create the frame.
	 */
	public ClientHub(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 436);
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
		panel.setBounds(12, 105, 309, 143);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_CompteOption = new JLabel("Compte option");
		label_CompteOption.setBounds(12, 13, 133, 22);
		label_CompteOption.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(label_CompteOption);
		
		JLabel Label_list_Compte = new JLabel("Comptes:");
		Label_list_Compte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_list_Compte.setBounds(12, 35, 154, 16);
		panel.add(Label_list_Compte);
		
		JComboBox<Compte> comboBox_compteDisponible = new JComboBox<Compte>();
		comboBox_compteDisponible.setBounds(12, 64, 176, 22);
		panel.add(comboBox_compteDisponible);
		for(var item : _client.readAccounts(user) ) {
			comboBox_compteDisponible.addItem(item);
		}
		
		JButton btnCompteConsulter = new JButton("consulter");
		btnCompteConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompteDetail frame = new CompteDetail((Compte)comboBox_compteDisponible.getSelectedItem());
				frame.setVisible(true);
			}
		});
		btnCompteConsulter.setBounds(200, 63, 97, 25);
		panel.add(btnCompteConsulter);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 254, 309, 143);
		contentPane.add(panel_1);
		
		JLabel label_Compte = new JLabel("Ouverture de compte");
		label_Compte.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_Compte.setBounds(12, 13, 227, 22);
		panel_1.add(label_Compte);
		
		JLabel Label_list_Compte_1 = new JLabel("Comptes:");
		Label_list_Compte_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_list_Compte_1.setBounds(12, 35, 154, 16);
		panel_1.add(Label_list_Compte_1);
		
		JComboBox<CompteType> comboBox_ouvertureCompte = new JComboBox<CompteType>();
		comboBox_ouvertureCompte.setBounds(12, 61, 176, 22);
		panel_1.add(comboBox_ouvertureCompte);
		
		//elle ajoute le type a la combo box et si le client detient le compte elle s'enleve
		for(var types: CompteType.values()) {
			
			comboBox_ouvertureCompte.addItem(types);
			for(var item : _client.readAccounts(user)) {
				if(item.getType() == types) {
					comboBox_ouvertureCompte.removeItem(types);
				}	
			}
		}

		JButton btnAppliquerLaDemande = new JButton("appliquer la demande");
		btnAppliquerLaDemande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compte item = null;
				switch((CompteType)comboBox_ouvertureCompte.getSelectedItem()) {
					case CRED: 	 item = new CompteCredit(user.getId(),0.05,5000);break;
					case LGNCRED:item = new LigneDeCredit(user.getId(),0.05);break;
					case DEV:	 item = new CompteDevise(user.getId(),0,Devise.EUR);break;
					case EPRGN:	 item = new CompteEpargne(user.getId(),0.05,2000.0);break;
					default:
				}
				try {
					request.AccountRequest(item);
				} catch (KeyConstraintException e1) {
					
				}
			}	
		});
		btnAppliquerLaDemande.setBounds(200, 61, 97, 25);
		panel_1.add(btnAppliquerLaDemande);
	}
	
}

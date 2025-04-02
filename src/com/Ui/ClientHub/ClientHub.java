package com.Ui.ClientHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Client.Utilisateur;
import com.Bus.Model.Compte.*;
import com.DAL.Repository.CompteRepository;
import com.DAL.Repository.Connection.SerializeRecord;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompteRepository _repo = new CompteRepository(
			new SerializeRecord<Compte>(".\\src\\data\\user\\AccountRequest.ser"));

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
		
		JButton btnCompteConsulter = new JButton("consulter");
		btnCompteConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompteConsulter.setBounds(200, 63, 97, 25);
		panel.add(btnCompteConsulter);
		
		JComboBox<Compte> comboBox = new JComboBox();
		setUserComboBox((Client)user,comboBox);
		comboBox.setBounds(12, 64, 176, 22);
		panel.add(comboBox);
		
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
		
		
		
		JComboBox<CompteType> comboBox_1 = new JComboBox();
		comboBox_1.setBounds(12, 61, 176, 22);
		panel_1.add(comboBox_1);
		setCompteDisponible((Client) user,comboBox_1);
		
		
		
		
		JButton btnAppliquerLaDemande = new JButton("appliquer la demande");
		btnAppliquerLaDemande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compte item = null;
				switch((Compte)comboBox_1.getSelectedItem()) {
					case CRED: 	 item = new CompteCredit((Client) user,0.05,5000.0);break;
					case LGNCRED:item = new LigneDeCredit((Client) user,0.05);break;
					case DEV:	 item = new CompteDevise((Client) user,0,Devise.EUR);break;
					case EPRGN:	 item = new CompteEpargne((Client) user,0.05,2000.0);break;
					default:
				}
				
				_repo.create(item);
			}	
		});
		btnAppliquerLaDemande.setBounds(200, 61, 97, 25);
		panel_1.add(btnAppliquerLaDemande);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(346, 105, 303, 279);
		contentPane.add(panel_2);
		
		
		
		JLabel label_Event = new JLabel("Event");
		label_Event.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(label_Event);
	}
	
	public void setUserComboBox(Client user, JComboBox<Compte> combobox) {
		for(var compte : user.getComptes()) {
			combobox.addItem(compte);
		}
	}
	public void setCompteDisponible(Client user, JComboBox<CompteType> combobox) {

		for(var t : CompteType.values()) {	
			combobox.addItem(t);
		}
		for(var compte : user.getComptes()) {
				combobox.removeItem(compte.getType());	
		}
	}
}

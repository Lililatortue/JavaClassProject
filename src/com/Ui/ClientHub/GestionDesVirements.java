package com.Ui.ClientHub;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.TransactionType;
import com.Bus.Model.Transaction.Virement;
import com.Bus.Model.Transaction.Validation.VirementManagement;
import com.Bus.Service.UserManagement.ClientManagement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GestionDesVirements extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_motdepasse;
	private JTextField textField_CreationDuMotsDePasse;
	private ClientManagement _client = new ClientManagement();
	private VirementManagement _virements = new VirementManagement();
	private JTextField textField_montant;
	/**
	 * Create the frame.
	 */
	public GestionDesVirements(Compte compte) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Virement> comboBox_virementDisponible = new JComboBox<Virement>();
		comboBox_virementDisponible.setBounds(12, 62, 194, 24);
		contentPane.add(comboBox_virementDisponible);
		for(var item: _virements.getVirement(compte.getClientId())) {
			comboBox_virementDisponible.addItem(item);
		}
		
		JLabel lbl_virementtag = new JLabel("virement disponible");
		lbl_virementtag.setBounds(12, 45, 134, 16);
		contentPane.add(lbl_virementtag);
		
		JLabel lbl_pswtag2 = new JLabel("mot de passe");
		lbl_pswtag2.setBounds(12, 147, 134, 16);
		contentPane.add(lbl_pswtag2);
		
		JLabel lbl_Title = new JLabel("accepter un virement");
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_Title.setBounds(12, 13, 194, 24);
		contentPane.add(lbl_Title);
		
		JLabel lbl_Title_1 = new JLabel("envoyer un virement");
		lbl_Title_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_Title_1.setBounds(290, 13, 194, 24);
		contentPane.add(lbl_Title_1);
		
		JLabel lbl_usertag = new JLabel("user");
		lbl_usertag.setBounds(290, 45, 134, 16);
		contentPane.add(lbl_usertag);
		
		JComboBox<Compte> comboBox_choixDecompte = new JComboBox<Compte>();
		comboBox_choixDecompte.setBounds(12, 121, 194, 24);
		contentPane.add(comboBox_choixDecompte);
		
		
		for(var types: _client.getClientCompte(compte.getClientId())) {
			comboBox_choixDecompte.addItem(types);
		}
		
		
		JLabel lbl_pswtag = new JLabel("mot de passe");
		lbl_pswtag.setBounds(290, 147, 134, 16);
		contentPane.add(lbl_pswtag);
		
		textField_motdepasse = new JTextField();
		textField_motdepasse.setBounds(12, 165, 194, 22);
		contentPane.add(textField_motdepasse);
		textField_motdepasse.setColumns(10);
		
		textField_montant = new JTextField();
		textField_montant.setColumns(10);
		textField_montant.setBounds(290, 122, 194, 22);
		contentPane.add(textField_montant);
		
		
		JButton btnAccepterVirement = new JButton("accepter");
		btnAccepterVirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Compte compte =(Compte)comboBox_choixDecompte.getSelectedItem();
				try {
					_virements.recevoirVirement((Virement)comboBox_virementDisponible.getSelectedItem(),
												textField_motdepasse.getText(),
												compte.getType()
												 );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAccepterVirement.setBounds(12, 189, 194, 25);
		contentPane.add(btnAccepterVirement);
		
		textField_CreationDuMotsDePasse = new JTextField();
		textField_CreationDuMotsDePasse.setColumns(10);
		textField_CreationDuMotsDePasse.setBounds(290, 165, 194, 22);
		contentPane.add(textField_CreationDuMotsDePasse);
		
		JComboBox<Client> comboBox_user = new JComboBox<Client>();
		comboBox_user.setBounds(290, 62, 194, 24);
		for(var item: _client.read()) {
			comboBox_user.addItem(item);
		}
		contentPane.add(comboBox_user);
		
		JButton btnEnvoyer = new JButton("envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creation du client
				Client client =(Client) comboBox_user.getSelectedItem();
				
				Transaction transaction = 
						new Transaction(client.getId(), 
								CompteType.VRMNT,  
								Double.parseDouble(textField_montant.getText()), 
								TransactionType.depot);
								
				Virement virement = new Virement(compte,
											     transaction,
												 textField_CreationDuMotsDePasse.getText()
												 );
				
				try {
					_virements.faireVirement(virement);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnEnvoyer.setBounds(290, 189, 194, 25);
		contentPane.add(btnEnvoyer);
		
		
		JLabel lbl_pswtag_1 = new JLabel("montant");
		lbl_pswtag_1.setBounds(290, 99, 134, 16);
		contentPane.add(lbl_pswtag_1);
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setBounds(12, 227, 472, 38);
		contentPane.add(lbl_error_stream);
		
		
		JLabel lbl_pswtag_1_1 = new JLabel("compte a deposer");
		lbl_pswtag_1_1.setBounds(12, 99, 134, 16);
		contentPane.add(lbl_pswtag_1_1);
	}

}

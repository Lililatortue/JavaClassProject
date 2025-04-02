package com.Ui.GestionnaireHub;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Client.Utilisateur;
import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.CompteRepository;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AccountApprouval extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompteRepository _CompteRepo = new CompteRepository(
			new SerializeRecord <Compte> (".\\src\\data\\user\\AccountRequest.ser"));
	private UserRepository _UserRepo = new UserRepository(
			new SerializeRecord <Utilisateur> (".\\src\\data\\user\\UserList.ser"));
	/**
	 * Create the frame.
	 */
	public AccountApprouval() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox_approuvedAccount = new JComboBox();
		comboBox_approuvedAccount.setBounds(10, 156, 526, 22);
		contentPane.add(comboBox_approuvedAccount);
		
		setUserComboBox(comboBox_approuvedAccount);
		
		JLabel lblApprouveOfThe = new JLabel("Approuve of the account");
		lblApprouveOfThe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblApprouveOfThe.setBounds(10, 11, 283, 22);
		contentPane.add(lblApprouveOfThe);
		
		JButton btnNewButton = new JButton("APPROUVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client user = new Client((Client) _UserRepo.findFirst(
						(u)->u.getId()== ((Compte)comboBox_approuvedAccount.getSelectedItem()).getClientId()));
				user.ajouterCompte((Compte)comboBox_approuvedAccount.getSelectedItem());
				_UserRepo.create(user);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 82, 244, 47);
		contentPane.add(btnNewButton);
		
		JButton btnRefuse = new JButton("REFUSE");
		btnRefuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//besoin de rajouter une fonction pour delete 
			}
		});
		btnRefuse.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRefuse.setBounds(292, 82, 244, 47);
		contentPane.add(btnRefuse);
		
		
	}
	
	public void setUserComboBox(JComboBox<Compte> combobox) {
		for(var compte : _CompteRepo.read()) {
			combobox.addItem(compte);
		}
	}
}

package com.Ui.GestionnaireHub;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Compte.Compte;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.Bus.Service.CompteManagement.CompteRequestManagement;
import com.Bus.Service.GestionRapports.RapportGenerator;
import com.Bus.Service.GestionRapports.TXTStrategy;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GestionnaireHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompteRequestManagement request = new CompteRequestManagement();
	private CompteManagement management = new CompteManagement((c)-> c.getSolde()<100);
	
	public GestionnaireHub()  {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNoRequestTo = new JLabel("no request to approuve");
		lblNoRequestTo.setBounds(12, 43, 323, 16);
		contentPane.add(lblNoRequestTo);
		if(request.read().size()>0) {
			lblNoRequestTo.setText("request to accomplish");
		}
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setBounds(12, 385, 234, 16);
		contentPane.add(lbl_error_stream);
		
		JLabel Label_hub = new JLabel("GESTIONNAIRE HUB  ");
		Label_hub.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_hub.setBounds(12, 13, 224, 16);
		contentPane.add(Label_hub);
		
		JPanel panel = new JPanel();
		panel.setBounds(347, 13, 279, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnApprouveClientAccount = new JButton("Approuve account request");
		btnApprouveClientAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountApprouval frame = new AccountApprouval();
				frame.setVisible(true);
			}
		});
		btnApprouveClientAccount.setBounds(39, 177, 207, 25);
		panel.add(btnApprouveClientAccount);
		
		JButton btnCloseAccount = new JButton("Close account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffacerCompte frame = new EffacerCompte();
				frame.setVisible(true);
			}
		});
		btnCloseAccount.setBounds(39, 213, 207, 25);
		panel.add(btnCloseAccount);
		
		JButton btnAddClient = new JButton("Add client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreationClient frame = new CreationClient();
				frame.setVisible(true);
			}
		});
		btnAddClient.setBounds(39, 63, 207, 25);
		panel.add(btnAddClient);
		
		JButton btnRemoveClient = new JButton("remove client");
		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffacerClient frame = new EffacerClient();
				frame.setVisible(true);
				
			}
		});
		btnRemoveClient.setBounds(39, 101, 207, 25);
		panel.add(btnRemoveClient);
		
		JLabel Label_task = new JLabel("TASK: ");
		Label_task.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_task.setBounds(12, 13, 224, 16);
		panel.add(Label_task);
		
		JLabel Label_ClientManagement = new JLabel("CLIENT MANAGEMENT: ");
		Label_ClientManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_ClientManagement.setBounds(12, 34, 224, 16);
		panel.add(Label_ClientManagement);
		
		JLabel Label_AccountManagement = new JLabel("ACCOUNT MANAGEMENT: ");
		Label_AccountManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_AccountManagement.setBounds(12, 150, 255, 16);
		panel.add(Label_AccountManagement);
		
		JLabel Label_AccountManagement_1 = new JLabel("Creation de gestionnaire");
		Label_AccountManagement_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_AccountManagement_1.setBounds(12, 249, 255, 25);
		panel.add(Label_AccountManagement_1);
		
		JButton btnAddGestionnaire = new JButton("Add gestionnaire");
		btnAddGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreationGestionnaire frame = new CreationGestionnaire();
				frame.setVisible(true);
			}
		});
		btnAddGestionnaire.setBounds(39, 273, 207, 25);
		panel.add(btnAddGestionnaire);
		
		JButton btnRemoveGestionnaire = new JButton("remove gestionnaire");
		btnRemoveGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffacerGestionnaire frame = new EffacerGestionnaire();
				frame.setVisible(true);
			}
		});
		btnRemoveGestionnaire.setBounds(39, 309, 207, 25);
		panel.add(btnRemoveGestionnaire);
		
		JButton btnCompteUrgence = new JButton("compte urgence");
		btnCompteUrgence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RapportGenerator<Compte> _rapport =new RapportGenerator<Compte>(
							new TXTStrategy<Compte>("compte urgence"),"./src/Printer/rapportCompteUrgence.txt");
					_rapport.createDocument(management.getAccount());
				} catch (Exception e1) {
					lbl_error_stream.setText(e1.getMessage());
				}
			}
		});
		btnCompteUrgence.setBounds(39, 347, 207, 25);
		panel.add(btnCompteUrgence);

		RapportGeneratorPanel rapportPanel = new RapportGeneratorPanel();
		rapportPanel.setBounds(10, 70, 279, 322);
		contentPane.add(rapportPanel);
		rapportPanel.setLayout(null);
		
	}
}

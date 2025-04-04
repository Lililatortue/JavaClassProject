package com.Ui.GestionnaireHub;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionnaireHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	
	public GestionnaireHub()  {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Label_welcome = new JLabel("Welcome: ");
		Label_welcome.setBounds(12, 43, 152, 16);
		contentPane.add(Label_welcome);
		
		JLabel Label_hub = new JLabel("GESTIONNAIRE HUB  ");
		Label_hub.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_hub.setBounds(12, 13, 224, 16);
		contentPane.add(Label_hub);
		
		JPanel panel = new JPanel();
		panel.setBounds(347, 43, 279, 352);
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

		RapportGeneratorPanel rapportPanel = new RapportGeneratorPanel();
		rapportPanel.setBounds(10, 70, 279, 322);
		contentPane.add(rapportPanel);
		rapportPanel.setLayout(null);
		
		
		
	}
}

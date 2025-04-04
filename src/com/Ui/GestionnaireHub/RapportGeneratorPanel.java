package com.Ui.GestionnaireHub;

import javax.swing.JPanel;

import com.Bus.Model.Client.Client;

import com.Bus.Service.GestionRapports.*;
import com.Bus.Service.UserManagement.ClientManagement;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class RapportGeneratorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClientManagement _clients = new ClientManagement();
	private RapportGenerator<Client> _rapport;

	public RapportGeneratorPanel()  {
		setLayout(null);
		
		JLabel Label_RapportGeneration = new JLabel("GENERER UN RAPPORT");
		Label_RapportGeneration.setBounds(10, 11, 224, 16);
		add(Label_RapportGeneration);
		Label_RapportGeneration.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel Label_clientInfo = new JLabel("CLIENT INFO: ");
		Label_clientInfo.setBounds(10, 38, 224, 16);
		add(Label_clientInfo);
		Label_clientInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblClientId = new JLabel("client id:");
		lblClientId.setBounds(10, 59, 152, 16);
		add(lblClientId);
		
		JComboBox<Client> comboBox_ClientList = new JComboBox<Client>();
		comboBox_ClientList.setBounds(10, 86, 224, 22);
		add(comboBox_ClientList);
		for(var client : _clients.read()) {
			comboBox_ClientList.addItem(client);
		}
		JLabel Label_AccountInfo = new JLabel("RAPPORT INFO");
		Label_AccountInfo.setBounds(10, 119, 255, 16);
		add(Label_AccountInfo);
		Label_AccountInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JCheckBox chckbxAccountLog = new JCheckBox("Account log");
		chckbxAccountLog.setBounds(20, 142, 111, 25);
		add(chckbxAccountLog);
		
		JCheckBox chckbxTransactionLog = new JCheckBox("Transaction log");
		chckbxTransactionLog.setBounds(20, 170, 111, 25);
		add(chckbxTransactionLog);
		
		JButton btnGenererRapport = new JButton("generer rapport");
		btnGenererRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client _client=(Client)comboBox_ClientList.getSelectedItem();
				
				try {
					_rapport = new RapportGenerator<Client>(new CSVStrategy(_client),"./src/Printer/default.csv");
					_rapport.createDocument((Client)comboBox_ClientList.getSelectedItem());
				} catch(Exception e1) {
					System.out.print(e1.getMessage());
				} 
				
			}
		});
		btnGenererRapport.setBounds(20, 243, 207, 25);
		add(btnGenererRapport);

	}
}

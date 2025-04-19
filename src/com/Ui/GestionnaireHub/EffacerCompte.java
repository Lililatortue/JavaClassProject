package com.Ui.GestionnaireHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Compte.Compte;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.Bus.Service.UserManagement.ClientManagement;
import com.DAL.Repository.Exception.InvariantException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EffacerCompte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClientManagement _client = new ClientManagement();
	private CompteManagement _compte = new CompteManagement();
	public EffacerCompte() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setBounds(121, 182, 183, 16);
		contentPane.add(lbl_error_stream);
		
		JComboBox<Client> comboBox = new JComboBox<Client>();
		comboBox.setBounds(12, 64, 194, 24);
		contentPane.add(comboBox);
		for(var item : _client.read()) {
			comboBox.addItem(item);
		}
		
		JLabel lblNewLabel = new JLabel("search for client");
		lblNewLabel.setBounds(12, 35, 102, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSelectAccount = new JLabel("select account");
		lblSelectAccount.setBounds(12, 101, 102, 16);
		contentPane.add(lblSelectAccount);
		
		JComboBox<Compte> comboBox_1 = new JComboBox<Compte>();
		comboBox_1.setBounds(12, 130, 194, 24);
		contentPane.add(comboBox_1);
		JButton btnChoisir = new JButton("choisir");
		btnChoisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.removeAllItems();
				Client client = (Client)comboBox.getSelectedItem();
				for(var item: _client.readAccounts(client))
				comboBox_1.addItem(item);
			}
		});
		btnChoisir.setBounds(218, 64, 97, 25);
		contentPane.add(btnChoisir);
		
		
		JButton btnNewButton = new JButton("delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					_compte.DeleteAccount((Compte) comboBox_1.getSelectedItem());
					lbl_error_stream.setText("Compte Effacer");
				} catch (InvariantException e1) {
					lbl_error_stream.setText(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(12, 178, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
                comboBox_1.removeAllItems();
                loadClients(comboBox);
			}
		});
		btnRefresh.setBounds(218, 97, 97, 25);
		contentPane.add(btnRefresh);
		
		
		
	}
	private void loadClients(JComboBox<Client> comboBox) {
		_client = new ClientManagement();
		_compte = new CompteManagement();
        for (var item : _client.read()) {
            comboBox.addItem(item);
        }
    }

}

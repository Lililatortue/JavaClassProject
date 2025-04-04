package com.Ui.GestionnaireHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Client.Utilisateur;
import com.Bus.Service.UserManagement.ClientManagement;
import com.Bus.Service.UserManagement.UserValidationException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EffacerClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClientManagement management = new ClientManagement();


	/**
	 * Create the frame.
	 */
	public EffacerClient() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 84, 409, 22);
		contentPane.add(comboBox);
		for(var compte : management.read()) {
			comboBox.addItem(compte);
		}
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					management.DeleteClient((Client) comboBox.getSelectedItem());
				} catch (UserValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnNewButton.setBounds(12, 48, 69, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblDeleteClient = new JLabel("Delete Client");
		lblDeleteClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDeleteClient.setBounds(12, 10, 132, 16);
		contentPane.add(lblDeleteClient);
		
		
	}
}

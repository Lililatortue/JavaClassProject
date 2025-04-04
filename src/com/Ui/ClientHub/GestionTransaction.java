package com.Ui.ClientHub;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.Bus.Model.Compte.Compte;

import java.awt.Font;

public class GestionTransaction extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public GestionTransaction(Compte compte) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("faire un depot");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 44, 169, 22);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(10, 77, 268, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 210, 268, 30);
		add(textField_1);
		
		JLabel lblFaireUnRetrait = new JLabel("faire un retrait");
		lblFaireUnRetrait.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFaireUnRetrait.setBounds(10, 169, 169, 30);
		add(lblFaireUnRetrait);
		
		JLabel lbl_error_stream = new JLabel("info ");
		lbl_error_stream.setBounds(10, 302, 268, 36);
		add(lbl_error_stream);
		
		JLabel lbl_transactionTitle = new JLabel("Transaction");
		lbl_transactionTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_transactionTitle.setBounds(84, 11, 126, 22);
		add(lbl_transactionTitle);
		
		JButton btnApprouverDepot = new JButton("Deposer");
		btnApprouverDepot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnApprouverDepot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApprouverDepot.setBounds(10, 118, 268, 40);
		add(btnApprouverDepot);

		JButton btnRetrait = new JButton("Retrait");
		btnRetrait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetrait.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRetrait.setBounds(10, 251, 268, 40);
		add(btnRetrait);
	}
}

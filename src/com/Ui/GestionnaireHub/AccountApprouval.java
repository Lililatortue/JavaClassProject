package com.Ui.GestionnaireHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.Bus.Model.Compte.Compte;
import com.Bus.Service.CompteManagement.CompteRequestManagement;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.Exception.InvariantException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AccountApprouval extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	CompteRequestManagement requestManagement = new CompteRequestManagement();
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
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_error_stream.setBounds(10, 268, 45, 13);
		contentPane.add(lbl_error_stream);
		
		JComboBox<Compte> comboBox_approuvedAccount = new JComboBox<Compte>();
		comboBox_approuvedAccount.setBounds(10, 156, 526, 22);
		contentPane.add(comboBox_approuvedAccount);
		for(var item : requestManagement.read()) {
			comboBox_approuvedAccount.addItem(item);
		}
		
		JLabel lblApprouveOfThe = new JLabel("Approuve of the account");
		lblApprouveOfThe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblApprouveOfThe.setBounds(10, 11, 283, 22);
		contentPane.add(lblApprouveOfThe);
		
		JButton btnNewButton = new JButton("APPROUVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					requestManagement.ApprouveRequestCompte(
							(Compte)comboBox_approuvedAccount.getSelectedItem());
				} catch (UserValidationException | InvariantException e1) {
					lbl_error_stream.setText(e1.getMessage());
				} 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 82, 244, 47);
		contentPane.add(btnNewButton);
		
		JButton btnRefuse = new JButton("REFUSE");
		btnRefuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					requestManagement.DenyRequestCompte(
							(Compte)comboBox_approuvedAccount.getSelectedItem());
				} catch (InvariantException e1) {
					lbl_error_stream.setText(e1.getMessage());
				}
			}
		});
		btnRefuse.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRefuse.setBounds(292, 82, 244, 47);
		contentPane.add(btnRefuse);
		
		
		
	}
}

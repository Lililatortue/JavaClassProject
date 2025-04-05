package com.Ui.GestionnaireHub;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Gestionnaire;
import com.Bus.Service.UserManagement.GestionnaireManagement;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.Exception.InvariantException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EffacerGestionnaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Delete Gestionnaire");
	GestionnaireManagement management = new GestionnaireManagement();
	
	/**
	 * Create the frame.
	 */
	public EffacerGestionnaire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 142, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_error_stream.setBounds(10, 159, 45, 13);
		contentPane.add(lbl_error_stream);
		
		JComboBox<Gestionnaire> comboBox = new JComboBox<Gestionnaire>();
		comboBox.setBounds(10, 74, 414, 22);
		contentPane.add(comboBox);
		for(var user : management.read()) {
			comboBox.addItem(user);
		}
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					management.DeleteGestionnaire((Gestionnaire) comboBox.getSelectedItem());
				} catch (UserValidationException | InvariantException e1) {
					lbl_error_stream.setText(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(10, 40, 89, 23);
		contentPane.add(btnNewButton);
		

		
		
	}

}

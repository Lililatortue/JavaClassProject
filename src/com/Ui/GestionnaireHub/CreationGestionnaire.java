package com.Ui.GestionnaireHub;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.Bus.Model.Client.Gestionnaire;
import com.Bus.Service.UserManagement.GestionnaireManagement;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class CreationGestionnaire extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_adresse;
	private JTextField textField_psw;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_email;
	private GestionnaireManagement management = new GestionnaireManagement();
	/**
	 * Create the frame.
	 */
	public CreationGestionnaire() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 403, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateGestionnaire = new JLabel("Create Gestionnaire");
		lblCreateGestionnaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreateGestionnaire.setBounds(12, 13, 194, 16);
		contentPane.add(lblCreateGestionnaire);
		
		JLabel Label_id = new JLabel("id");
		Label_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_id.setBounds(12, 42, 116, 16);
		contentPane.add(Label_id);
		
		JLabel Label_Nom = new JLabel("nom");
		Label_Nom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_Nom.setBounds(12, 90, 116, 16);
		contentPane.add(Label_Nom);
		
		JLabel Label_prenom = new JLabel("prenom");
		Label_prenom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_prenom.setBounds(12, 137, 116, 16);
		contentPane.add(Label_prenom);
		
		JLabel Label_adresse = new JLabel("adresse");
		Label_adresse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_adresse.setBounds(234, 137, 116, 16);
		contentPane.add(Label_adresse);
		
		JLabel Label_psw = new JLabel("password");
		Label_psw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_psw.setBounds(234, 42, 116, 16);
		contentPane.add(Label_psw);
		
		textField_id = new JTextField();
		textField_id.setBounds(12, 57, 116, 22);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_adresse = new JTextField();
		textField_adresse.setColumns(10);
		textField_adresse.setBounds(234, 158, 116, 22);
		contentPane.add(textField_adresse);
		
		textField_psw = new JTextField();
		textField_psw.setColumns(10);
		textField_psw.setBounds(234, 57, 116, 22);
		contentPane.add(textField_psw);
		
		textField_nom = new JTextField();
		textField_nom.setColumns(10);
		textField_nom.setBounds(12, 105, 116, 22);
		contentPane.add(textField_nom);
		
		textField_prenom = new JTextField();
		textField_prenom.setColumns(10);
		textField_prenom.setBounds(12, 158, 116, 22);
		contentPane.add(textField_prenom);
		
		JLabel lblPlsInputGestionnaire = new JLabel("pls input gestionnaire info");
		lblPlsInputGestionnaire.setBounds(12, 254, 338, 58);
		contentPane.add(lblPlsInputGestionnaire);
		
		JButton btn_create = new JButton("Create");
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestionnaire gestionnaire = new Gestionnaire(Integer.parseInt(textField_id.getText()),
						   									 textField_nom.getText(),
						   									 textField_prenom.getText(),
						   									 textField_adresse.getText(),
						   									 textField_psw.getText(),
						   									 textField_email.getText()
															);
				
				try {
					management.ADDGestionnaire(gestionnaire);
					lblPlsInputGestionnaire.setText("Gestionnaire created.");
				} catch (UserValidationException | KeyConstraintException e1) {	
					lblPlsInputGestionnaire.setText(e1.getMessage());
				}
			}
		});
		btn_create.setBounds(234, 200, 116, 43);
		contentPane.add(btn_create);
		
		
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(234, 105, 116, 22);
		contentPane.add(textField_email);
		
		JLabel Label_email = new JLabel("email");
		Label_email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_email.setBounds(234, 90, 116, 16);
		contentPane.add(Label_email);
	}

}

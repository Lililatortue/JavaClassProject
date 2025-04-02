package com.Ui.GestionnaireHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreationClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_adresse;
	private JTextField textField_psw;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_email;
	private JTextField textField_telephone;
	private UserRepository _repo= new UserRepository(
			new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
	/**
	 * Create the frame.
	 */
	public CreationClient() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateClient = new JLabel("Create Client");
		lblCreateClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreateClient.setBounds(12, 13, 135, 16);
		contentPane.add(lblCreateClient);
		
		JLabel Label_id = new JLabel("id");
		Label_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_id.setBounds(12, 42, 135, 16);
		contentPane.add(Label_id);
		
		JLabel Label_Nom = new JLabel("nom");
		Label_Nom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_Nom.setBounds(12, 90, 135, 16);
		contentPane.add(Label_Nom);
		
		JLabel Label_prenom = new JLabel("prenom");
		Label_prenom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_prenom.setBounds(12, 137, 135, 16);
		contentPane.add(Label_prenom);
		
		JLabel Label_adresse = new JLabel("adresse");
		Label_adresse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_adresse.setBounds(12, 200, 135, 16);
		contentPane.add(Label_adresse);
		
		JLabel Label_psw = new JLabel("password");
		Label_psw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_psw.setBounds(234, 42, 135, 16);
		contentPane.add(Label_psw);
		
		textField_id = new JTextField();
		textField_id.setBounds(12, 57, 116, 22);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_adresse = new JTextField();
		textField_adresse.setColumns(10);
		textField_adresse.setBounds(12, 219, 116, 22);
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
		
		JButton btn_create = new JButton("Create");
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client(Integer.parseInt(textField_id.getText()),
										   textField_nom.getText(),
										   textField_prenom.getText(),
										   textField_adresse.getText(),
										   textField_psw.getText(),
										   textField_email.getText(),
										   textField_telephone.getText()
										   );
				
				_repo.create(client);
					
				
			}
		});
		btn_create.setBounds(234, 218, 97, 25);
		contentPane.add(btn_create);
		
		JLabel lblPlsInputClient = new JLabel("pls input client info");
		lblPlsInputClient.setBounds(12, 329, 396, 43);
		contentPane.add(lblPlsInputClient);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(234, 105, 116, 22);
		contentPane.add(textField_email);
		
		JLabel Label_email = new JLabel("email");
		Label_email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_email.setBounds(234, 90, 135, 16);
		contentPane.add(Label_email);
		
		textField_telephone = new JTextField();
		textField_telephone.setColumns(10);
		textField_telephone.setBounds(234, 158, 116, 22);
		contentPane.add(textField_telephone);
		
		JLabel Label_telephone = new JLabel("telephone");
		Label_telephone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Label_telephone.setBounds(234, 137, 135, 16);
		contentPane.add(Label_telephone);
	}

}

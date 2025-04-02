package com.Ui.GestionnaireHub;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_psw;
	private UserRepository _repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));


	/**
	 * Create the frame.
	 */
	public RemoveClient() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var user=_repo.findFirst((u)->u.getId() == Integer.parseInt(textField_id.getText()));
				_repo.delete(user);
			}
		});
		
		
		btnNewButton.setBounds(255, 77, 69, 25);
		contentPane.add(btnNewButton);
		
		JLabel Label_Id = new JLabel("id");
		Label_Id.setBounds(12, 39, 56, 16);
		contentPane.add(Label_Id);
		
		JLabel Label_password = new JLabel("password");
		Label_password.setBounds(12, 81, 56, 16);
		contentPane.add(Label_password);
		
		textField_id = new JTextField();
		textField_id.setBounds(12, 57, 205, 22);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_psw = new JTextField();
		textField_psw.setColumns(10);
		textField_psw.setBounds(12, 110, 205, 22);
		contentPane.add(textField_psw);
		
		JLabel lblDeleteClient = new JLabel("Delete Client");
		lblDeleteClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDeleteClient.setBounds(12, 10, 132, 16);
		contentPane.add(lblDeleteClient);
		
		JLabel Label_error = new JLabel("pls_input_ifo");
		Label_error.setBounds(12, 181, 407, 16);
		contentPane.add(Label_error);
	}

}

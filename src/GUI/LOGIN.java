package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.buisness.ConnectionHandler.*;
import com.buisness.ConnectionHandler.handler.*;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LOGIN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TextB_name;
	private JTextField TextB_psw;
	private JButton btnNewButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//set chain of responsability
	
	Authentification authentification = new Authentification();
	Authorisation authorisation = new Authorisation();
	ConnectionHandler connHandler= new ConnectionHandler();
	
	
	connHandler.setNext(authentification);
	authentification.setNext(authorisation);						
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN frame = new LOGIN(connHandler);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LOGIN(ConnectionHandler h) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 13, 93, 16);
		contentPane.add(lblNewLabel);
		
		TextB_name = new JTextField();
		TextB_name.setBounds(12, 82, 272, 22);
		contentPane.add(TextB_name);
		TextB_name.setColumns(10);
		
		JLabel label_Name = new JLabel("Name");
		label_Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_Name.setBounds(12, 53, 93, 16);
		contentPane.add(label_Name);
		
		JLabel label_psw = new JLabel("password");
		label_psw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_psw.setBounds(12, 117, 93, 16);
		contentPane.add(label_psw);
		
		TextB_psw = new JTextField();
		TextB_psw.setColumns(10);
		TextB_psw.setBounds(12, 146, 272, 22);
		contentPane.add(TextB_psw);
		
		btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				h.Handle(new Request(Integer.parseInt(TextB_name.getText()),TextB_psw.getText()));
			}
		});
		btnNewButton.setBounds(12, 181, 97, 25);
		contentPane.add(btnNewButton);
	}

}

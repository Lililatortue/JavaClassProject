package com.Ui.ClientHub;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Service.LoginValidation.*;
import com.Bus.Service.LoginValidation.handler.*;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LOGIN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textB_name;
	private JTextField textB_psw;
	private JButton btnNewButton;
	
	/**

     * Point d’entrée principal de l’application.
     * Initialise la chaîne de responsabilité pour la gestion des connexions.
     */

public static void main(String[] args) {
		// Création de la chaîne de responsabilité pour la connexiom
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
     * Constructeur de la fenêtre de connexion.
     * @param h Gestionnaire de connexion (chaîne de responsabilité)
     */
	public LOGIN(ConnectionHandler h) {
		setTitle("FORTIS BANK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("WELCOME TO FORTIS BANK");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 18));
		lblLogin.setBounds(23, 21, 252, 41);
		contentPane.add(lblLogin);
		
		textB_name = new JTextField();
		textB_name.setBounds(23, 122, 260, 22);
		contentPane.add(textB_name);
		textB_name.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Cascadia Mono", Font.PLAIN, 14));
		lblUsername.setBounds(37, 95, 93, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Cascadia Mono", Font.PLAIN, 14));
		lblPassword.setBounds(37, 155, 93, 16);
		contentPane.add(lblPassword);
		
		textB_psw = new JTextField();
		textB_psw.setColumns(10);
		textB_psw.setBounds(23, 182, 260, 22);
		contentPane.add(textB_psw);
		
		// Bouton de connexion
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				h.Handle(new Request(Integer.parseInt(textB_name.getText()),textB_psw.getText()));
			}
		});
		btnNewButton.setBounds(97, 231, 101, 34);
		contentPane.add(btnNewButton);
	}

}

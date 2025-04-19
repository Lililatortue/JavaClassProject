package com.Ui.ClientHub;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Bus.Model.Compte.*;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.TransactionType;
import com.Bus.Model.Transaction.Validation.TransactionManagement;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompteDetail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TransactionManagement _transactions = new TransactionManagement();
	private JTextField textField_montantTransaction;
	/**
	 * Create the frame.
	 */
	public CompteDetail(Compte compte) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_frameTitle = new JLabel("Compte "+compte.getType());
		lbl_frameTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_frameTitle.setBounds(10, 11, 206, 28);
		contentPane.add(lbl_frameTitle);
		
		JLabel lbl_error_stream = new JLabel("input");
		lbl_error_stream.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_error_stream.setBounds(12, 175, 206, 28);
		contentPane.add(lbl_error_stream);
		
		JButton btnDeposer = new JButton("Deposer");
		btnDeposer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction transaction = new Transaction(compte.getClientId(),
						  								  compte.getType(),
						  								  Double.parseDouble(textField_montantTransaction.getText()),
						  								  TransactionType.depot);

				try {
					_transactions.ADDTransaction(transaction);
					lbl_error_stream.setText("Deposer avec succes");
				} catch (Exception e1) {
					//lbl_error_stream.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDeposer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeposer.setBounds(10, 134, 95, 28);
		contentPane.add(btnDeposer);
		
		JButton btnRetirer = new JButton("Retirer");
		btnRetirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction transaction = new Transaction(compte.getClientId(),
														  compte.getType(),
														  Double.parseDouble(textField_montantTransaction.getText()),
														  TransactionType.retrait);

				try {
					_transactions.ADDTransaction(transaction);
					lbl_error_stream.setText("retirer avec succes");
				} catch (Exception e1) {
					lbl_error_stream.setText(e1.getMessage());
				}
			}
		});
		btnRetirer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRetirer.setBounds(117, 134, 99, 28);
		contentPane.add(btnRetirer);
		
		JList<Transaction> list = new JList<Transaction>();
		DefaultListModel<Transaction> model = new DefaultListModel<Transaction>();
	    list = new JList<>(model);
	    list.setBounds(10, 300, 523, 152);
	    //JScrollPane scrollPane = new JScrollPane(list);
	   // scrollPane.setBounds(10, 300, 609, 152);
	    contentPane.add(list);
		populateList(list,compte);
		
		
		JLabel lbl_transactionList = new JLabel("list des transactions");
		lbl_transactionList.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_transactionList.setBounds(10, 277, 184, 13);
		contentPane.add(lbl_transactionList);
		
		JLabel lbl_CompteSolde = new JLabel("Solde: "+compte.getSolde());
		lbl_CompteSolde.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_CompteSolde.setBounds(254, 52, 118, 13);
		contentPane.add(lbl_CompteSolde);
		
		JLabel lbl_dateOuverture = new JLabel("date ouverture: "+compte.getDateOuverture());
		lbl_dateOuverture.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_dateOuverture.setBounds(254, 78, 263, 13);
		contentPane.add(lbl_dateOuverture);
		
		JLabel lblInformation = new JLabel("Information: ");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInformation.setBounds(254, 11, 206, 28);
		contentPane.add(lblInformation);
		
		JLabel lbl_dateOuverture_1 = new JLabel("additional information");
		lbl_dateOuverture_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_dateOuverture_1.setBounds(254, 112, 204, 13);
		contentPane.add(lbl_dateOuverture_1);
		
		JButton btnVirement = new JButton("virement");
		btnVirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionDesVirements frame = new GestionDesVirements(compte);
				frame.setVisible(true);
			}
		});
		btnVirement.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVirement.setBounds(10, 216, 206, 48);
		contentPane.add(btnVirement);
		
		textField_montantTransaction = new JTextField();
		textField_montantTransaction.setBounds(10, 109, 206, 22);
		contentPane.add(textField_montantTransaction);
		textField_montantTransaction.setColumns(10);
		
		JLabel lbl_transactionTitle = new JLabel("Transaction");
		lbl_transactionTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_transactionTitle.setBounds(54, 52, 125, 28);
		contentPane.add(lbl_transactionTitle);
		
		JLabel lbl_error_stream_1 = new JLabel("montant");
		lbl_error_stream_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_error_stream_1.setBounds(10, 80, 206, 22);
		contentPane.add(lbl_error_stream_1);
		
		JLabel lbl_infoAdditionnel = new JLabel(CreateAdditionnalInfo(compte));
		lbl_infoAdditionnel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_infoAdditionnel.setBounds(254, 141, 279, 77);
		contentPane.add(lbl_infoAdditionnel);
		
		
	}
	
	public void populateList(JList<Transaction> list, Compte compte) {
		
		 DefaultListModel<Transaction> model =
				 (DefaultListModel<Transaction>) list.getModel();
	    
		    ArrayList<Transaction> newTransactions = 
		    		_transactions.getSpecifiqueTransactions(compte.getClientId(), compte.getType());
		    for (Transaction transaction : newTransactions) {
		        model.addElement(transaction);
		    }
		    
	}
	public String CreateAdditionnalInfo(Compte compte) {
		StringBuilder string = new StringBuilder("<html>");
		if(compte instanceof CompteCheque) {
			CompteCheque c = (CompteCheque)compte;
			string.append("Frais de transaction: "+c.getFraisTransaction()+"<br>");
			string.append("transaction gratuite restante: "+c.getTransactionsRestante()+"<br>");
	
		} 
		else if(compte instanceof CompteCredit) {
			CompteCredit c = (CompteCredit)compte;
			string.append("Taux interet annuel: "+c.getTauxInteretAnnuel()+"<br>");
			string.append("limite: "+c.getLimite()+"<br>");
			string.append("Taux Mensuel du: "+c.getInteretDu()+"<br>");
		} 
		else if(compte instanceof LigneDeCredit) {
			LigneDeCredit c = (LigneDeCredit)compte;
			string.append("Taux interet annuel: "+c.getTauxInteretAnnuel()+"<br>");
			string.append("Taux Mensuel du: "+c.getInteretDu()+"<br>");
			
		} 
		else if(compte instanceof CompteEpargne) {
			CompteEpargne c = (CompteEpargne)compte;
			string.append("Taux interet annuel: "+c.getTauxInteretAnnuel()+"<br>");
			string.append("Taux Mensuel du: "+c.getInteretDu()+"<br>");
		} 
		else {
			CompteDevise c = (CompteDevise)compte;
			string.append("devise: "+c.getDevise() +"<br>");
			
		} 
		
		  string.append("</html>");  // End with the closing HTML tag
		    return string.toString();
	}
}

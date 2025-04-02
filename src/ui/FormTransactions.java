package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.Scrollbar;
import javax.swing.JToggleButton;

import com.Bus.Model.Transaction.*;

import javax.swing.JComboBox;

import java.util.Date;

public class FormTransactions {

	private JFrame frmFortisBankSystem;
	private JTextField textFieldAmountDeposit;
	private JTextField textFieldAmountWithdrawal;
	private JTextField textFieldAmountTransfer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTransactions window = new FormTransactions();
					window.frmFortisBankSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormTransactions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFortisBankSystem = new JFrame();
		frmFortisBankSystem.setTitle("FORTIS BANK SYSTEM");
		frmFortisBankSystem.setBounds(100, 100, 675, 409);
		frmFortisBankSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFortisBankSystem.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("FORTIS BANK");
		lblTitle.setFont(new Font("Cascadia Mono", Font.BOLD, 18));
		lblTitle.setBounds(10, 11, 144, 32);
		frmFortisBankSystem.getContentPane().add(lblTitle);
		
		JLabel lblTitle2 = new JLabel("BANK SERVICES");
		lblTitle2.setFont(new Font("Cascadia Mono", Font.BOLD, 14));
		lblTitle2.setBounds(20, 54, 238, 26);
		frmFortisBankSystem.getContentPane().add(lblTitle2);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogout.setBounds(552, 14, 88, 30);
		frmFortisBankSystem.getContentPane().add(btnLogout);
		
		JLabel lblDeposit = new JLabel("Make a deposit");
		lblDeposit.setFont(new Font("Cascadia Mono", Font.PLAIN, 12));
		lblDeposit.setBounds(42, 91, 144, 26);
		frmFortisBankSystem.getContentPane().add(lblDeposit);
		
		JLabel lblWithdrawal = new JLabel("Make a withdrawal");
		lblWithdrawal.setFont(new Font("Cascadia Mono", Font.PLAIN, 12));
		lblWithdrawal.setBounds(42, 165, 144, 26);
		frmFortisBankSystem.getContentPane().add(lblWithdrawal);
		
		JLabel lblTransferFundsBetween = new JLabel("Transfer funds between accounts");
		lblTransferFundsBetween.setFont(new Font("Cascadia Mono", Font.PLAIN, 12));
		lblTransferFundsBetween.setBounds(42, 234, 245, 26);
		frmFortisBankSystem.getContentPane().add(lblTransferFundsBetween);
		
		JLabel lblSelectAmount1 = new JLabel("Select amount :");
		lblSelectAmount1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSelectAmount1.setBounds(64, 128, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblSelectAmount1);
		
		JLabel lblSelectAmount2 = new JLabel("Select amount :");
		lblSelectAmount2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSelectAmount2.setBounds(64, 197, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblSelectAmount2);
		
		JLabel lblSelectAmount3 = new JLabel("Select amount :");
		lblSelectAmount3.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSelectAmount3.setBounds(64, 271, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblSelectAmount3);
		
		textFieldAmountDeposit = new JTextField();
		textFieldAmountDeposit.setBounds(164, 128, 96, 20);
		frmFortisBankSystem.getContentPane().add(textFieldAmountDeposit);
		textFieldAmountDeposit.setColumns(10);
		
		textFieldAmountWithdrawal = new JTextField();
		textFieldAmountWithdrawal.setColumns(10);
		textFieldAmountWithdrawal.setBounds(164, 198, 96, 20);
		frmFortisBankSystem.getContentPane().add(textFieldAmountWithdrawal);
		
		textFieldAmountTransfer = new JTextField();
		textFieldAmountTransfer.setColumns(10);
		textFieldAmountTransfer.setBounds(162, 271, 96, 20);
		frmFortisBankSystem.getContentPane().add(textFieldAmountTransfer);
		
		JComboBox comboBoxAccounts1 = new JComboBox();
		comboBoxAccounts1.setBounds(359, 129, 144, 20);
		frmFortisBankSystem.getContentPane().add(comboBoxAccounts1);
		
		JLabel lblSelectAccount1 = new JLabel("Into account :");
		lblSelectAccount1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSelectAccount1.setBounds(270, 128, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblSelectAccount1);
		
		JButton btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setBounds(515, 128, 125, 23);
		frmFortisBankSystem.getContentPane().add(btnDeposit);
		
		JButton btnReturnToMenu = new JButton("RETURN TO MENU");
		btnReturnToMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReturnToMenu.setBounds(392, 14, 150, 30);
		frmFortisBankSystem.getContentPane().add(btnReturnToMenu);
		
		JLabel lblFromAccount = new JLabel("From account :");
		lblFromAccount.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblFromAccount.setBounds(270, 197, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblFromAccount);
		
		JLabel lblFromAccount_1 = new JLabel("From account :");
		lblFromAccount_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblFromAccount_1.setBounds(270, 271, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblFromAccount_1);
		
		JLabel lblFromAccount_1_1 = new JLabel("To account :");
		lblFromAccount_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblFromAccount_1_1.setBounds(270, 326, 90, 26);
		frmFortisBankSystem.getContentPane().add(lblFromAccount_1_1);
		
		JComboBox comboBoxAccounts2 = new JComboBox();
		comboBoxAccounts2.setBounds(359, 197, 144, 20);
		frmFortisBankSystem.getContentPane().add(comboBoxAccounts2);
		
		JComboBox comboBoxAccounts3 = new JComboBox();
		comboBoxAccounts3.setBounds(359, 271, 144, 20);
		frmFortisBankSystem.getContentPane().add(comboBoxAccounts3);
		
		JComboBox comboBoxAccounts4 = new JComboBox();
		comboBoxAccounts4.setBounds(359, 327, 144, 20);
		frmFortisBankSystem.getContentPane().add(comboBoxAccounts4);
		
		JButton btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.setBounds(515, 197, 125, 23);
		frmFortisBankSystem.getContentPane().add(btnWithdraw);
		
		JButton btnTransfer = new JButton("TRANSFER");
		btnTransfer.setBounds(515, 271, 125, 23);
		frmFortisBankSystem.getContentPane().add(btnTransfer);
	}
}

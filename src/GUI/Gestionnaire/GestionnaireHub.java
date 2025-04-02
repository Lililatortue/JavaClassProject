package GUI.Gestionnaire;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionnaireHub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	
	public GestionnaireHub()  {
		
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Label_welcome = new JLabel("Welcome: ");
		Label_welcome.setBounds(12, 43, 152, 16);
		contentPane.add(Label_welcome);
		
		JLabel Label_hub = new JLabel("GESTIONNAIRE HUB  ");
		Label_hub.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_hub.setBounds(12, 13, 224, 16);
		contentPane.add(Label_hub);
		
		JPanel panel = new JPanel();
		panel.setBounds(328, 57, 279, 338);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnApprouveClientAccount = new JButton("Approuve account request");
		btnApprouveClientAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountApprouval frame = new AccountApprouval();
				frame.setVisible(true);
			}
		});
		btnApprouveClientAccount.setBounds(39, 177, 207, 25);
		panel.add(btnApprouveClientAccount);
		
		JButton btnCloseAccount = new JButton("Close account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCloseAccount.setBounds(39, 213, 207, 25);
		panel.add(btnCloseAccount);
		
		JButton btnAddClient = new JButton("Add client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreationClient frame = new CreationClient();
				frame.setVisible(true);
			}
		});
		btnAddClient.setBounds(39, 63, 207, 25);
		panel.add(btnAddClient);
		
		JButton btnRemoveClient = new JButton("remove client");
		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveClient frame = new RemoveClient();
				frame.setVisible(true);
				
			}
		});
		btnRemoveClient.setBounds(39, 101, 207, 25);
		panel.add(btnRemoveClient);
		
		JLabel Label_task = new JLabel("TASK: ");
		Label_task.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_task.setBounds(12, 13, 224, 16);
		panel.add(Label_task);
		
		JLabel Label_ClientManagement = new JLabel("CLIENT MANAGEMENT: ");
		Label_ClientManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_ClientManagement.setBounds(12, 34, 224, 16);
		panel.add(Label_ClientManagement);
		
		JLabel Label_AccountManagement = new JLabel("ACCOUNT MANAGEMENT: ");
		Label_AccountManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_AccountManagement.setBounds(12, 150, 255, 16);
		panel.add(Label_AccountManagement);
		
		JLabel Label_AccountManagement_1 = new JLabel("Creation de gestionnaire");
		Label_AccountManagement_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_AccountManagement_1.setBounds(12, 260, 255, 25);
		panel.add(Label_AccountManagement_1);
		
		JButton btnAddGestionnaire = new JButton("Add gestionnaire");
		btnAddGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGestionnaire frame = new CreateGestionnaire();
				frame.setVisible(true);
			}
		});
		btnAddGestionnaire.setBounds(39, 296, 207, 25);
		panel.add(btnAddGestionnaire);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(22, 84, 279, 311);
		contentPane.add(panel_1);
		
		JLabel Label_RapportGeneration = new JLabel("GENERER UN RAPPORT");
		Label_RapportGeneration.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_RapportGeneration.setBounds(12, 13, 224, 16);
		panel_1.add(Label_RapportGeneration);
		
		JLabel Label_clientInfo = new JLabel("CLIENT INFO: ");
		Label_clientInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_clientInfo.setBounds(12, 34, 224, 16);
		panel_1.add(Label_clientInfo);
		
		JLabel Label_AccountInfo = new JLabel("RAPPORT INFO");
		Label_AccountInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		Label_AccountInfo.setBounds(12, 127, 255, 16);
		panel_1.add(Label_AccountInfo);
		
		textField = new JTextField();
		textField.setBounds(12, 87, 255, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel Label_welcome_1 = new JLabel("client id");
		Label_welcome_1.setBounds(12, 58, 152, 16);
		panel_1.add(Label_welcome_1);
		
		JButton btnGenererRapport = new JButton("generer rapport");
		btnGenererRapport.setBounds(35, 273, 207, 25);
		panel_1.add(btnGenererRapport);
		
		JCheckBox chckbxClientLog = new JCheckBox("client log");
		chckbxClientLog.setBounds(12, 152, 111, 25);
		panel_1.add(chckbxClientLog);
		
		JCheckBox chckbxAccountLog = new JCheckBox("Account log");
		chckbxAccountLog.setBounds(12, 188, 111, 25);
		panel_1.add(chckbxAccountLog);
		
		JCheckBox chckbxTransactionLog = new JCheckBox("Transaction log");
		chckbxTransactionLog.setBounds(12, 218, 111, 25);
		panel_1.add(chckbxTransactionLog);
	}
}

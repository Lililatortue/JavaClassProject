package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class RemoveClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;



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
		btnNewButton.setBounds(255, 77, 69, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(12, 39, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(12, 81, 56, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(12, 57, 205, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 110, 205, 22);
		contentPane.add(textField_1);
		
		JLabel lblDeleteClient = new JLabel("Delete Client");
		lblDeleteClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDeleteClient.setBounds(12, 10, 132, 16);
		contentPane.add(lblDeleteClient);
		
		JLabel Label_error = new JLabel("pls_input_ifo");
		Label_error.setBounds(12, 181, 407, 16);
		contentPane.add(Label_error);
	}

}

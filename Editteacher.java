package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Editteacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField lastname;
	private JTextField mail;
	private JTextField phone;
	private JPasswordField password1;
	private JTextField id1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editteacher frame = new Editteacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void editteacher(int id1,String firstname, String lastname, String email, String password) {
		   String url = "jdbc:mysql://localhost:3306/cmss";
		   String dbUsername = "root";
		   String dbPassword = "";

		   try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		       PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname=?, lastname=?, email=?, password=? WHERE id=?");
		       preparedStatement.setInt(5, id1);
		       preparedStatement.setString(1, firstname);
		       preparedStatement.setString(2, lastname);
		       preparedStatement.setString(3, email);
		       preparedStatement.setString(4, password);
		       preparedStatement.executeUpdate();

		       JOptionPane.showMessageDialog(null, "Edit successfully");

		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		}

	/**
	 * Create the frame.
	 */
	public Editteacher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(64, 128, 128));
		lblNewLabel.setBounds(64, 81, 46, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnEditName = new JButton("Edit name");
		btnEditName.setBackground(new Color(128, 128, 192));
		btnEditName.setBounds(0, 0, 438, 55);
		btnEditName.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(btnEditName);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(64, 118, 115, 26);
		contentPane.add(lblNewLabel_1);
		
		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(178, 116, 175, 28);
		contentPane.add(fname);
		
		JLabel lblNewLabel_1_2 = new JLabel("Lastname");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(64, 160, 103, 23);
		contentPane.add(lblNewLabel_1_2);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(178, 155, 175, 28);
		contentPane.add(lastname);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(178, 194, 175, 30);
		contentPane.add(mail);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(178, 235, 175, 30);
		contentPane.add(phone);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id1.getText();
				String firstN = fname.getText();
				String lastN = lastname.getText();
				String email = mail.getText();
				String p = password1.getText();
				String ph = phone.getText();
				if(!id.isEmpty()&&!firstN.isEmpty() && !lastN.isEmpty() && !email.isEmpty()&& !p.isEmpty() && !ph.isEmpty()){
					int idd= Integer.parseInt(id);

					editteacher(idd,firstN,lastN,email,ph);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				}
			
		});
		btnNewButton.setBounds(64, 345, 89, 32);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();			}

		});
		btnBack.setBounds(264, 344, 89, 33);
		contentPane.add(btnBack);
		
		password1 = new JPasswordField();
		password1.setBounds(178, 276, 175, 28);
		contentPane.add(password1);
		
		id1 = new JTextField();
		id1.setBounds(178, 75, 56, 28);
		contentPane.add(id1);
		id1.setColumns(10);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Email");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(64, 201, 103, 23);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Phone");
		lblNewLabel_1_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_2.setBounds(64, 242, 103, 23);
		contentPane.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Password");
		lblNewLabel_1_2_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_3.setBounds(64, 281, 103, 23);
		contentPane.add(lblNewLabel_1_2_3);
	}

}

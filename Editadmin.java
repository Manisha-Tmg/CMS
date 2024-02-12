package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Editadmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField fname;
	private JTextField mail;
	private JTextField lname;
	private JTextField phone;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editadmin frame = new Editadmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void editadmin(int id,String firstname, String lastname, String email, String password) {
		   String url = "jdbc:mysql://localhost:3306/cmss";
		   String dbUsername = "root";
		   String dbPassword = "";

		   try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		       PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname=?, lastname=?, email=?, password=? WHERE id=?");
		       preparedStatement.setInt(5, id);
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
	public Editadmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(158, 84, 96, 24);
		contentPane.add(id);
		
		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(159, 119, 202, 28);
		contentPane.add(fname);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(159, 193, 202, 24);
		contentPane.add(mail);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(159, 158, 202, 28);
		contentPane.add(lname);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(158, 234, 203, 24);
		contentPane.add(phone);
		
		password = new JPasswordField();
		password.setBounds(158, 269, 203, 24);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id1 = id.getText();
				String firstN = fname.getText();
				String lastN = lname.getText();
				String email = mail.getText();
				String p = password.getText();
				String ph = phone.getText();
				if(!id1.isEmpty()&&!firstN.isEmpty() && !lastN.isEmpty() && !email.isEmpty()&& !p.isEmpty() && !ph.isEmpty()){
					int idd= Integer.parseInt(id1);

					editadmin(idd,firstN,lastN,email,ph);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				
			}
		});
		btnNewButton.setBounds(60, 334, 89, 35);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(269, 332, 89, 35);
		contentPane.add(btnBack);
		
		JButton btnEditName = new JButton("Edit name");
		btnEditName.setBackground(new Color(128, 128, 192));
		btnEditName.setFont(new Font("Arial", Font.BOLD, 30));
		btnEditName.setBounds(0, 0, 426, 55);
		contentPane.add(btnEditName);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(64, 128, 128));
		lblNewLabel.setBounds(48, 84, 46, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Firstname");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(48, 121, 115, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Lastname");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(46, 159, 103, 23);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Email");
		lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(46, 200, 103, 23);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Phone");
		lblNewLabel_1_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_2.setBounds(48, 235, 103, 23);
		contentPane.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Password");
		lblNewLabel_1_2_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_3.setBounds(48, 270, 103, 23);
		contentPane.add(lblNewLabel_1_2_3);
	}

}

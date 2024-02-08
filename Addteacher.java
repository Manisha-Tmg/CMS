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
import java.awt.Color;
import javax.swing.JPasswordField;

public class Addteacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField phone;
	private JTextField mail;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addteacher frame = new Addteacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	private void Addteacher(String firstname, String lastname,String email,String password,String phone) {
        String url = "jdbc:mysql://localhost:3306/cms";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname,email,password,phone,role) VALUES (?,?,?,?,?,'Teacher');");
//             preparedStatement.setInt(1,id);
             preparedStatement.setString(1,firstname);
             preparedStatement.setString(2, lastname);
             preparedStatement.setString(3,email);
             preparedStatement.setString(4, password);
             preparedStatement.setString(5, phone);
             preparedStatement.executeUpdate();
             
             JOptionPane.showMessageDialog(null, "Added successfully");
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public Addteacher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Firstname");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(61, 56, 126, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnAddName = new JButton("Add name");
		btnAddName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddName.setBackground(new Color(128, 128, 192));
		btnAddName.setBounds(0, 0, 458, 45);
		btnAddName.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(btnAddName);
		
		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(233, 56, 164, 30);
		contentPane.add(fname);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(233, 97, 162, 30);
		contentPane.add(lname);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(233, 179, 164, 30);
		contentPane.add(phone);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(233, 138, 164, 30);
		contentPane.add(mail);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstN = fname.getText();
				String lastN = lname.getText();
				String email = mail.getText();
				String passw = password.getText();
				String ph = phone.getText();

				if(!firstN.isEmpty() && !lastN.isEmpty() && !email.isEmpty()&& !passw.isEmpty()&& !passw.isEmpty()){
				Addteacher (firstN,lastN,email,passw,ph);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				}
			
		});
		btnNewButton_1.setBounds(61, 310, 89, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(308, 310, 89, 33);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Arial", Font.BOLD, 20));
		lblLastname.setBounds(61, 97, 126, 30);
		contentPane.add(lblLastname);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword_1.setBounds(61, 228, 126, 30);
		contentPane.add(lblPassword_1);
		
		JLabel lblPassword_1_1 = new JLabel("Phone");
		lblPassword_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword_1_1.setBounds(61, 179, 126, 30);
		contentPane.add(lblPassword_1_1);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.BOLD, 20));
		Email.setBounds(61, 138, 126, 30);
		contentPane.add(Email);
		
		password = new JPasswordField();
		password.setBounds(233, 226, 164, 30);
		contentPane.add(password);
	}
}

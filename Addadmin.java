package finalproject;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Addadmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JTextField phone;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addadmin frame = new Addadmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void Addadmin(String firstname, String lastname,String email,String password,String phone) {
        String url = "jdbc:mysql://localhost:3306/cmss";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname,email,password,phone,role) VALUES (?,?,?,?,?,'Admin');");
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
	public Addadmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(207, 83, 186, 30);
		contentPane.add(fname);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(207, 124, 186, 27);
		contentPane.add(lname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(207, 204, 186, 30);
		contentPane.add(email);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(207, 163, 186, 30);
		contentPane.add(phone);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstN = fname.getText();
				String lastN = lname.getText();
				String emailN = email.getText();
				String passw = password.getText();
				String ph = phone.getText();

				if(!firstN.isEmpty() && !lastN.isEmpty() && !emailN.isEmpty()&& !passw.isEmpty()&& !passw.isEmpty()){
				Addadmin(firstN,lastN,emailN,passw,ph);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				}
		
		});
		btnNewButton_1.setBounds(81, 316, 97, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(302, 316, 91, 36);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnAddName = new JButton("Add name");
		btnAddName.setFont(new Font("Arial", Font.BOLD, 30));
		btnAddName.setBackground(new Color(128, 128, 192));
		btnAddName.setBounds(0, 2, 461, 45);
		contentPane.add(btnAddName);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(81, 79, 126, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPassword_1_1 = new JLabel("Phone");
		lblPassword_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword_1_1.setBounds(81, 162, 126, 30);
		contentPane.add(lblPassword_1_1);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Arial", Font.BOLD, 20));
		lblLastname.setBounds(81, 123, 126, 30);
		contentPane.add(lblLastname);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(83, 245, 103, 30);
		contentPane.add(lblPassword);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.BOLD, 20));
		Email.setBounds(81, 204, 126, 30);
		contentPane.add(Email);
		
		password = new JPasswordField();
		password.setBounds(207, 245, 186, 28);
		contentPane.add(password);
	}
}

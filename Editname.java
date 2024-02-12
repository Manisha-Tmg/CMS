package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Editname extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField ID;
	private JTextField email;
	private JTextField phone;
	private JComboBox level1;
	private JComboBox courses;
	private JPasswordField password1;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editname frame = new Editname();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//update studentname
	private void editname(int ID, String firstname, String lastname, String email, String password,int level,String courses) {
		   String url = "jdbc:mysql://localhost:3306/cmss";
		   String dbUsername = "root";
		   String dbPassword = "";

		   try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		       PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname=?, lastname=?, email=?, password=?,course=?,level=? WHERE id=?");
		       preparedStatement.setString(1, firstname);
		       preparedStatement.setString(2, lastname);
		       preparedStatement.setString(3, email);
		       preparedStatement.setString(4, password);
		       preparedStatement.setInt(5, ID);
		       preparedStatement.setString(6, courses);
		       preparedStatement.setInt(7, level);


		       preparedStatement.executeUpdate();

		       JOptionPane.showMessageDialog(null, "Edit successfully");

		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		}

	/**
	 * Create the frame.
	 */
	public Editname() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(141, 99, 268, 27);
		contentPane.add(firstname);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String std = ID.getText();
				String firstN = firstname.getText();
				String lastN = lastname.getText();
				String emailN = email.getText();
				String p = password1.getText();
				String ema = email.getText();
				String level = (String)level1.getSelectedItem();
				String c = (String)courses.getSelectedItem();



				if(!std.isEmpty()&&!firstN.isEmpty() && !lastN.isEmpty() && !emailN.isEmpty()&& !p.isEmpty()&&!level.isEmpty() && !p.isEmpty()&&!c.isEmpty()){
					int l= Integer.parseInt(level);
					int st= Integer.parseInt(std);


					editname(st,firstN,lastN,p,ema,l,c);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				}
		});
		btnNewButton.setBounds(72, 405, 89, 27);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(320, 405, 89, 27);
		contentPane.add(btnBack);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(142, 136, 267, 27);
		contentPane.add(lastname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(141, 174, 268, 30);
		contentPane.add(email);
		
		ID = new JTextField();
		ID.setBounds(142, 65, 68, 25);
		contentPane.add(ID);
		
		phone = new JTextField();
		phone.setBounds(141, 216, 268, 27);
		contentPane.add(phone);
		phone.setColumns(10);
		
		level1 = new JComboBox();
		level1.setFont(new Font("Arial", Font.BOLD, 20));
		level1.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		level1.setBounds(142, 327, 78, 27);
		contentPane.add(level1);
		
		courses = new JComboBox();
		courses.setFont(new Font("Arial", Font.BOLD, 15));
		courses.setModel(new DefaultComboBoxModel(new String[] {"BSc (Hons) Computer Science", "BSc (Hons) in International Business Management", "International MBA courses."}));
		courses.setBounds(141, 254, 268, 26);
		contentPane.add(courses);
		
		password1 = new JPasswordField();
		password1.setBounds(141, 291, 208, 25);
		contentPane.add(password1);
		
		JButton btnEditName = new JButton("Edit name");
		btnEditName.setFont(new Font("Arial", Font.BOLD, 30));
		btnEditName.setBackground(new Color(128, 128, 192));
		btnEditName.setBounds(-3, 0, 444, 55);
		contentPane.add(btnEditName);
		
		JLabel lblNewLabel_2 = new JLabel("Id");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBackground(new Color(64, 128, 128));
		lblNewLabel_2.setBounds(25, 61, 46, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Firstname");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(25, 94, 126, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Lastname");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(25, 135, 103, 23);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.BOLD, 20));
		Email.setBounds(25, 171, 126, 30);
		contentPane.add(Email);
		
		JLabel lblPassword_1_1 = new JLabel("Phone");
		lblPassword_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword_1_1.setBounds(25, 211, 126, 30);
		contentPane.add(lblPassword_1_1);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Arial", Font.BOLD, 20));
		lblLevel.setBounds(28, 325, 82, 30);
		contentPane.add(lblLevel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(25, 286, 103, 30);
		contentPane.add(lblPassword);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setFont(new Font("Arial", Font.BOLD, 20));
		lblCourses.setBounds(25, 248, 89, 30);
		contentPane.add(lblCourses);
	}



		
	}






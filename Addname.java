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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Addname extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstname;
	private JTextField email;
	private JTextField phone1;
	private JPasswordField password;
	private JComboBox courses;
	private JComboBox level1;
	private JTextField lastname;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addname frame = new Addname();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//add student 
	private void addname(String firstname, String lastname,String email,String course,String password,String phone,int level) {
        String url = "jdbc:mysql://localhost:3306/cms";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname,email,password,course,phone,level,role) VALUES (?,?,?,?,?,?,?,'Student');");
//             preparedStatement.setInt(1,id);
             preparedStatement.setString(1,firstname);
             preparedStatement.setString(2, lastname);
             preparedStatement.setString(3,email);
             preparedStatement.setString(4, password);
             preparedStatement.setString(5, course);
             preparedStatement.setString(6, phone);
             preparedStatement.setInt(7, level);
             preparedStatement.executeUpdate();
             
             JOptionPane.showMessageDialog(null, "Added successfully");
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public Addname() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddName = new JButton("Add name");
		btnAddName.setBackground(new Color(128, 128, 192));
		btnAddName.setBounds(0, 0, 485, 45);
		btnAddName.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(btnAddName);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(145, 56, 328, 30);
		contentPane.add(firstname);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String I = ID.getText();
				String firstN = firstname.getText();
				String lastN = lastname.getText();
				String emailN = email.getText();
				String passw = password.getText();
				String cours = (String)courses.getSelectedItem();
				String p = phone1.getText();
				String level = (String)level1.getSelectedItem();
			
				
				if(!firstN.isEmpty() && !lastN.isEmpty() && !emailN.isEmpty()&& !passw.isEmpty() && !cours.isEmpty() && !p.isEmpty()&& !level.isEmpty()){
					int l= Integer.parseInt(level);
					addname (firstN,lastN,emailN,passw,cours,p,l);
				}else {
				JOptionPane.showMessageDialog(null,"This message can't be print");
				}
				}
	

		});
		btnNewButton_1.setBounds(60, 384, 89, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(333, 386, 89, 27);
		contentPane.add(btnNewButton_1_1);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(145, 138, 328, 30);
		contentPane.add(email);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(12, 217, 103, 30);
		contentPane.add(lblPassword);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setFont(new Font("Arial", Font.BOLD, 20));
		lblCourses.setBounds(12, 259, 89, 30);
		contentPane.add(lblCourses);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Arial", Font.BOLD, 20));
		lblLevel.setBounds(12, 308, 82, 30);
		contentPane.add(lblLevel);
		
		level1 = new JComboBox();
		level1.setFont(new Font("Arial", Font.BOLD, 20));
		level1.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		level1.setToolTipText("456");
		level1.setBounds(145, 308, 82, 30);
		contentPane.add(level1);
		
		courses = new JComboBox();
		courses.setFont(new Font("Arial", Font.BOLD, 15));
		courses.setModel(new DefaultComboBoxModel(new String[] {"BSc (Hons) Computer Science", "BSc (Hons) in International Business Management", "International MBA courses."}));
		courses.setBounds(145, 261, 328, 36);
		contentPane.add(courses);
		
		phone1 = new JTextField();
		phone1.setBounds(145, 179, 328, 30);
		contentPane.add(phone1);
		phone1.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(145, 220, 328, 30);
		contentPane.add(password);
		
		JLabel lblNewLabel = new JLabel("Firstname");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 56, 126, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Arial", Font.BOLD, 20));
		lblLastname.setBounds(10, 101, 126, 30);
		contentPane.add(lblLastname);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(145, 97, 328, 30);
		contentPane.add(lastname);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.BOLD, 20));
		Email.setBounds(10, 135, 126, 30);
		contentPane.add(Email);
		
		JLabel lblPassword_1_1 = new JLabel("Phone");
		lblPassword_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword_1_1.setBounds(10, 176, 126, 30);
		contentPane.add(lblPassword_1_1);
	}
}

package finalproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import Swing.Results.ResultDisplay;
import first.report.editMarks;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Dashborad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private course coursedisplay;
	private Teachers teacherdisplay;
	private students studentdisplay;
	private Admin Admindisplay;
	private JTextField firstname;
	private JTextField textField_8;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField lastname;
	private JTextField email;
	private JTextField oldpassword;
	private JTextField newpassword;
	
	private ResultDisplay displayResult;


	private static String username;
	public String getUsername() {
		return username;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashborad frame = new Dashborad(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String url = "jdbc:mysql://localhost:3306/cms";
	String dbUsername = "root";
	String dbPassword = "";

	//fetch the userdata
	public Map<String, String> getUserData(String userEmail) {
        Map<String, String> userData = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set
                    userData.put("firstname", resultSet.getString("firstname"));
                    userData.put("surname", resultSet.getString("lastname"));
                    userData.put("email", resultSet.getString("email"));
                    userData.put("password", resultSet.getString("password"));
                    userData.put("role", resultSet.getString("role"));
                   
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
	
	// for setting
    public int updateUserData(String oldEmail, String newFirstName, String newSurname, String newEmail, String newPassword) {
        String url = "jdbc:mysql://localhost:3306/CMS";
        String dbUsername = "root";
        String dbPassword = "";

        String query = "UPDATE users SET firstname = ?, lastname = ?, email = ?, password = ? WHERE  email = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newSurname);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setString(5, oldEmail);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Display success message
                JOptionPane.showMessageDialog(null,"Profile updated successfully. Please re-login !!");
                Loginform logIn = new Loginform();
                logIn.setVisible(true);
                dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Failed to update profile !!");
            }

            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return 0; // Return 0 if something went wrong
    }
	/**
	 * Create the frame.
	 */
	public Dashborad(String username) {
		
		this.username = username;
		
		Map<String, String> userData = getUserData(username);

        String fname = userData.get("firstname");
        String lname = userData.get("surname");
        String eMail = userData.get("email");
        String rOle = userData.get("role");
        String pAssw = userData.get("password");
        
        
//        DatabaseOperations databaseOperations = new DatabaseOperations();
//        int totalCourses = databaseOperations.getTotalCourseCount();
//        String totalCourse = String.valueOf(totalCourses);


//        int totalStds = getTotalCount("Student");
//        int totalA = getTotalCount("Admin");
//        int totalT = getTotalCount("Teacher");

//        String totalAdmins = String.valueOf(totalA);
//        String totalTeachers = String.valueOf(totalT);
//        String totalStudents = String.valueOf(totalStds);
		
		System.out.println(username);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1118, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 11, 203, 588);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.setBounds(33, 77, 127, 28);
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		panel.setLayout(null);
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Courses");
		btnNewButton.setBounds(33, 116, 127, 28);
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);

			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Students");
		btnNewButton_2.setBounds(33, 155, 127, 28);
		btnNewButton_2.setBackground(new Color(64, 128, 128));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);

			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Teachers");
		btnNewButton_3.setBounds(33, 194, 127, 28);
		btnNewButton_3.setBackground(new Color(64, 128, 128));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);

			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Logout");
		btnNewButton_4.setBounds(33, 530, 127, 28);
		btnNewButton_4.setBackground(new Color(64, 128, 128));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginform lf = new Loginform();
        		lf.setVisible(true);
        		dispose(); 
			}
			
		});
		btnNewButton_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("CMS");
		btnNewButton_5.setBounds(0, 0, 203, 66);
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setBackground(new Color(128, 128, 192));
		btnNewButton_5.setFont(new Font("Arial", Font.BOLD, 40));
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_10 = new JButton("Setting");
		btnNewButton_10.setBounds(33, 272, 127, 28);
		btnNewButton_10.setBackground(new Color(64, 128, 128));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);

				
			}
		});
		btnNewButton_10.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("Admin");
		btnNewButton_11.setBounds(33, 233, 127, 28);
		btnNewButton_11.setBackground(new Color(64, 128, 128));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);

			}
		});
		btnNewButton_11.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_11);
		
		JButton btnNewButton_10_1 = new JButton("Refresh");
		btnNewButton_10_1.setBounds(33, 311, 127, 28);
		btnNewButton_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_10_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1.setBackground(new Color(64, 128, 128));
		panel.add(btnNewButton_10_1);
		
		JButton btnNewButton_10_1_2 = new JButton("Show Std");
		btnNewButton_10_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1_2.setBackground(new Color(64, 128, 128));
		btnNewButton_10_1_2.setBounds(33, 423, 127, 28);
		panel.add(btnNewButton_10_1_2);
		
		JButton btnNewButton_10_1_2_1 = new JButton("Result");
		btnNewButton_10_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_10_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1_2_1.setBackground(new Color(64, 128, 128));
		btnNewButton_10_1_2_1.setBounds(33, 462, 127, 28);
		panel.add(btnNewButton_10_1_2_1);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(64, 128, 128));
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 22));
		tabbedPane.setBounds(223, 11, 872, 596);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Arial", Font.BOLD, 25));
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Home", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 11, 124, 124);
		panel_1.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(192, 11, 124, 124);
		panel_1.add(panel_8);
		
		JPanel panel_8_1 = new JPanel();
		panel_8_1.setBounds(363, 11, 124, 124);
		panel_1.add(panel_8_1);
		
		JPanel panel_8_2 = new JPanel();
		panel_8_2.setBounds(530, 11, 124, 124);
		panel_1.add(panel_8_2);
		
		JPanel panel_8_3 = new JPanel();
		panel_8_3.setBounds(690, 11, 124, 124);
		panel_1.add(panel_8_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Courses", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setBounds(10, 84, 160, 39);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		panel_2.add(lblNewLabel);
		
		JButton btnNewButton_6 = new JButton("Course Details");
		btnNewButton_6.setBounds(0, 0, 867, 55);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_6.setBackground(new Color(64, 0, 0));
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setFont(new Font("Arial", Font.BOLD, 40));
		panel_2.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Add ");
		btnNewButton_7.setBounds(418, 84, 88, 33);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse courseAdd =new AddCourse();
				courseAdd.setVisible(true);
			}
		});	
		btnNewButton_7.setBackground(new Color(64, 128, 128));
		btnNewButton_7.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Edit");
		btnNewButton_8.setBounds(527, 84, 88, 33);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCourse courseE = new EditCourse();
				courseE.setVisible(true);
			}
		});
		btnNewButton_8.setBackground(new Color(64, 128, 128));
		btnNewButton_8.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Del ");
		btnNewButton_9.setBounds(635, 84, 89, 33);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delcourse courseD = new Delcourse();
				courseD.setVisible(true);
			}
		});
		btnNewButton_9.setBackground(new Color(64, 128, 128));
		btnNewButton_9.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(btnNewButton_9);
//		
		coursedisplay=new course();
		coursedisplay.setBounds(10, 174, 839, 360);
		coursedisplay.setBackground(new Color(255, 255, 255));
		coursedisplay.setForeground(new Color(0,0,0));
		panel_2.add(coursedisplay);
		coursedisplay.setLayout(null);
		coursedisplay.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(152, 85, 246, 33);
		textField.setColumns(10);
		panel_2.add(textField);
		
		JButton btnNewButton_17_1_2_3 = new JButton("Refresh");
		btnNewButton_17_1_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursedisplay=new course();
				coursedisplay.setBounds(10, 174, 839, 360);
				coursedisplay.setBackground(new Color(255, 255, 255));
				coursedisplay.setForeground(new Color(0,0,0));
				panel_2.add(coursedisplay);
				coursedisplay.setLayout(null);
				coursedisplay.setLayout(null);
			}
		});
		btnNewButton_17_1_2_3.setBackground(new Color(64, 128, 128));
		btnNewButton_17_1_2_3.setBounds(734, 84, 114, 33);
		btnNewButton_17_1_2_3.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(btnNewButton_17_1_2_3);
		
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Students", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		
		JButton btnNewButton_12 = new JButton("Add");
		btnNewButton_12.setBackground(new Color(64, 128, 128));
		btnNewButton_12.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addname studentAdd =new Addname();
				studentAdd.setVisible(true);
				
			}
		});
		btnNewButton_12.setBounds(434, 99, 89, 35);
		panel_3.add(btnNewButton_12);
		
		

		studentdisplay = new students();
		studentdisplay.setBackground(new Color(255, 255, 255));
		studentdisplay.setBounds(10, 192, 847, 353);
		studentdisplay.setForeground(new Color(0, 0, 0));
		panel_3.add(studentdisplay);
		studentdisplay.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1.setBounds(10, 97, 138, 35);
		panel_3.add(lblNewLabel_1);
		
		JButton btnNewButton_15 = new JButton("Student Details");
		btnNewButton_15.setForeground(new Color(255, 255, 255));
		btnNewButton_15.setBackground(new Color(64, 0, 0));
		btnNewButton_15.setFont(new Font("Arial", Font.BOLD, 40));
		btnNewButton_15.setBounds(0, 0, 867, 56);
		panel_3.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("Edit");
		btnNewButton_16.setBackground(new Color(64, 128, 128));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Editname studentname =new Editname();
			studentname.setVisible(true);
			}
		});
		btnNewButton_16.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_16.setBounds(546, 99, 89, 35);
		panel_3.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("Del");
		btnNewButton_17.setBackground(new Color(64, 128, 128));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delname studentD = new Delname();
				studentD.setVisible(true);
			}
		});
		btnNewButton_17.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_17.setBounds(645, 100, 89, 33);
		panel_3.add(btnNewButton_17);
		
			
				
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Teachers", null, panel_4, null);
		panel_4.setLayout(null);
		
		teacherdisplay = new Teachers();
        teacherdisplay.setBackground(new Color(255, 255, 255));
        teacherdisplay.setBounds(10, 175, 837, 370);
        panel_4.add(teacherdisplay);
		teacherdisplay.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 97, 258, 33);
		panel_3.add(textField_1);
		
		JButton btnNewButton_17_1_2_2 = new JButton("Refresh");
		btnNewButton_17_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentdisplay = new students();
				studentdisplay.setBackground(new Color(255, 255, 255));
				studentdisplay.setBounds(10, 192, 847, 353);
				studentdisplay.setForeground(new Color(0, 0, 0));
				panel_3.add(studentdisplay);
				studentdisplay.setLayout(null);
				
			}
		});
		btnNewButton_17_1_2_2.setBackground(new Color(64, 128, 128));
		btnNewButton_17_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_17_1_2_2.setBounds(743, 99, 114, 33);
		panel_3.add(btnNewButton_17_1_2_2);
		
		JButton btnNewButton_15_1 = new JButton("Teacher Details");
		btnNewButton_15_1.setForeground(new Color(255, 255, 255));
		btnNewButton_15_1.setBackground(new Color(64, 0, 0));
		btnNewButton_15_1.setFont(new Font("Arial", Font.BOLD, 40));
		btnNewButton_15_1.setBounds(0, 0, 867, 58);
		panel_4.add(btnNewButton_15_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Teacher name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(10, 84, 147, 35);
		panel_4.add(lblNewLabel_1_1);
		
		JButton btnNewButton_12_1 = new JButton("Add");
		btnNewButton_12_1.setBackground(new Color(64, 128, 128));
		btnNewButton_12_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addteacher TeacherAdd =new Addteacher();
				TeacherAdd.setVisible(true);
				
			}
		});
		btnNewButton_12_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_12_1.setBounds(435, 86, 89, 35);
		panel_4.add(btnNewButton_12_1);
		
		JButton btnNewButton_16_1 = new JButton("Edit");
		btnNewButton_16_1.setBackground(new Color(64, 128, 128));
		btnNewButton_16_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editteacher teacheredit =new Editteacher();
				teacheredit.setVisible(true);
			}
		});
		btnNewButton_16_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_16_1.setBounds(534, 86, 89, 35);
		panel_4.add(btnNewButton_16_1);
		
		JButton btnNewButton_17_1 = new JButton("Del");
		btnNewButton_17_1.setBackground(new Color(64, 128, 128));
		btnNewButton_17_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delteacher teacherD = new Delteacher();
				teacherD.setVisible(true);
			}
		});
		btnNewButton_17_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_17_1.setBounds(633, 88, 89, 33);
		panel_4.add(btnNewButton_17_1);
		
		JButton btnNewButton_17_1_2 = new JButton("Refresh");
		btnNewButton_17_1_2.setBackground(new Color(64, 128, 128));
		btnNewButton_17_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherdisplay = new Teachers();
		        teacherdisplay.setBackground(new Color(255, 255, 255));
		        teacherdisplay.setBounds(10, 175, 837, 370);
		        panel_4.add(teacherdisplay);
				teacherdisplay.setLayout(null);
						}
		});
		btnNewButton_17_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_17_1_2.setBounds(733, 87, 114, 33);
		panel_4.add(btnNewButton_17_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 86, 258, 33);
		panel_4.add(textField_2);
		
				
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Admin", null, panel_5, null);
		panel_5.setLayout(null);
		
		Admindisplay = new Admin();
		Admindisplay.setBackground(new Color(255, 255, 255));
		Admindisplay.setBounds(20, 166, 816, 379);
        panel_5.add(Admindisplay);
        Admindisplay.setLayout(null);
        
        JButton btnNewButton_15_2 = new JButton("Admin Details");
        btnNewButton_15_2.setForeground(Color.WHITE);
        btnNewButton_15_2.setFont(new Font("Arial", Font.BOLD, 40));
        btnNewButton_15_2.setBackground(new Color(64, 0, 0));
        btnNewButton_15_2.setBounds(0, 0, 867, 56);
        panel_5.add(btnNewButton_15_2);
        
        JButton btnNewButton_12_1_1 = new JButton("Add");
        btnNewButton_12_1_1.setBackground(new Color(64, 128, 128));
        btnNewButton_12_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Addadmin adminAdd =new Addadmin();
				adminAdd.setVisible(true);
        		
        	}
        });
        btnNewButton_12_1_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_12_1_1.setBounds(446, 81, 89, 35);
        panel_5.add(btnNewButton_12_1_1);
        
        JButton btnNewButton_17_1_1 = new JButton("Del");
        btnNewButton_17_1_1.setBackground(new Color(64, 128, 128));
        btnNewButton_17_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Deladmin admindel =new Deladmin();
				admindel.setVisible(true);
        	}
        });
        btnNewButton_17_1_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_17_1_1.setBounds(644, 82, 89, 33);
        panel_5.add(btnNewButton_17_1_1);
        
        JButton btnNewButton_16_1_1 = new JButton("Edit");
        btnNewButton_16_1_1.setBackground(new Color(64, 128, 128));
        btnNewButton_16_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Editadmin adminedit =new Editadmin();
				adminedit.setVisible(true);
        		
        	}
        });
        btnNewButton_16_1_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_16_1_1.setBounds(545, 81, 89, 35);
        panel_5.add(btnNewButton_16_1_1);
        
        JButton btnNewButton_17_1_2_1 = new JButton("Refresh");
        btnNewButton_17_1_2_1.setBackground(new Color(64, 128, 128));
        btnNewButton_17_1_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Admindisplay = new Admin();
        		Admindisplay.setBackground(new Color(255, 255, 255));
        		Admindisplay.setBounds(20, 166, 816, 379);
                panel_5.add(Admindisplay);
                Admindisplay.setLayout(null);
        	}
        });
        btnNewButton_17_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_17_1_2_1.setBounds(743, 82, 114, 33);
        panel_5.add(btnNewButton_17_1_2_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Admin name");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 19));
        lblNewLabel_1_1_1.setBounds(20, 81, 147, 35);
        panel_5.add(lblNewLabel_1_1_1);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(156, 83, 268, 33);
        panel_5.add(textField_8);
        
        JPanel panel_6 = new JPanel();
        tabbedPane.addTab("Setting", null, panel_6, null);
        panel_6.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel("Firstname");
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3.setBounds(37, 135, 142, 33);
        panel_6.add(lblNewLabel_3);
        
        firstname = new JTextField();
        firstname.setBounds(206, 138, 238, 33);
        panel_6.add(firstname);
        firstname.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("Lastname");
        lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_1.setBounds(37, 179, 119, 33);
        panel_6.add(lblNewLabel_3_1);
        
        JButton btnNewButton_15_2_1 = new JButton("Setting");
        btnNewButton_15_2_1.setForeground(Color.WHITE);
        btnNewButton_15_2_1.setFont(new Font("Arial", Font.BOLD, 40));
        btnNewButton_15_2_1.setBackground(new Color(64, 0, 0));
        btnNewButton_15_2_1.setBounds(0, 0, 867, 56);
        panel_6.add(btnNewButton_15_2_1);
        
        JLabel lblNewLabel_2 = new JLabel("General");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_2.setBounds(0, 57, 867, 45);
        panel_6.add(lblNewLabel_2);
        
        JButton btnNewButton_14 = new JButton("Update Profile");
        btnNewButton_14.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String Ofname = firstname.getText();
                String Olname = lastname.getText();
                String Oemail = email.getText();
                String Opass = oldpassword.getText();
                String Npass = newpassword.getText();
                
                if(!Ofname.isEmpty()) {
                	if(Opass.equals(pAssw)){
                		updateUserData(username,Ofname,Olname,Oemail,Npass);
                	}else{
                		JOptionPane.showMessageDialog(null, "Old password is incorrect !!");
                	}
                }
        	}
        	
        });
        btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton_14.setBounds(368, 441, 148, 33);
        panel_6.add(btnNewButton_14);
        
        lastname = new JTextField();
        lastname.setColumns(10);
        lastname.setBounds(206, 179, 238, 33);
        panel_6.add(lastname);
        
        JLabel lblNewLabel_3_1_3 = new JLabel("Email");
        lblNewLabel_3_1_3.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_1_3.setBounds(37, 223, 119, 33);
        panel_6.add(lblNewLabel_3_1_3);
        
        email = new JTextField();
        email.setColumns(10);
        email.setBounds(206, 223, 238, 33);
        panel_6.add(email);
        
        JLabel lblNewLabel_2_1 = new JLabel("Security");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_2_1.setBounds(0, 283, 867, 45);
        panel_6.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3_2 = new JLabel("Old password");
        lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_2.setBounds(37, 339, 142, 33);
        panel_6.add(lblNewLabel_3_2);
        
        oldpassword = new JTextField();
        oldpassword.setColumns(10);
        oldpassword.setBounds(206, 339, 238, 33);
        panel_6.add(oldpassword);
        
        newpassword = new JTextField();
        newpassword.setColumns(10);
        newpassword.setBounds(206, 383, 238, 33);
        panel_6.add(newpassword);
        
        JLabel lblNewLabel_3_2_1 = new JLabel("New password");
        lblNewLabel_3_2_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_2_1.setBounds(37, 379, 159, 33);
        panel_6.add(lblNewLabel_3_2_1);
        
        JPanel panel_9 = new JPanel();
        tabbedPane.addTab("showStd", null, panel_9, null);
        panel_9.setLayout(null);
        
        JButton btnNewButton_19 = new JButton("Add");
        btnNewButton_19.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addMarks markadd = new addMarks();
        		markadd.setVisible(true);
        	}
        });
        btnNewButton_19.setBounds(623, 0, 117, 40);
        panel_9.add(btnNewButton_19);
        
        JButton btnNewButton_19_1 = new JButton("Update");
        btnNewButton_19_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editMarks markEdit = new editMarks();
        		markEdit.setVisible(true);
        	}
        });
        btnNewButton_19_1.setBounds(750, 0, 117, 40);
        panel_9.add(btnNewButton_19_1);
		
        
        displayResult = new ResultDisplay();
        displayResult.setBackground(new Color(255, 255, 255));
        displayResult.setBounds(10, 192, 847, 353);
        displayResult.setForeground(new Color(0, 0, 0));
		panel_9.add(displayResult);
		displayResult.setLayout(null);
		
		
	}
}

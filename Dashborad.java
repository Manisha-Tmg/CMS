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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Dashborad extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final Object std_id = null;
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
    String url = "jdbc:mysql://localhost:3306/cmss";
	String dbUsername = "root";
	String dbPassword = "";
	private JTextField textField_3;
	private JButton deleteC;
	protected int ID;

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
                    userData.put("userId", resultSet.getString("id"));
                   
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
	//
	//total number of admin,teacher,student
    public int getTotalCount(String role) {
        String url = "jdbc:mysql://localhost:3306/cmss";
        String dbUsername = "root";
        String dbPassword = "";

        String query = "SELECT COUNT(*) FROM users WHERE role = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, role);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return 0; // Return 0 if something went wrong
    }
    
    //to get total courses
    public class DatabaseOperations {

        // Your existing database connection parameters
        String url = "jdbc:mysql://localhost:3306/cmss";
        private String dbUsername = "root";
        private String dbPassword = "";

        // Method to get the total number of courses
        public int getTotalCourseCount() {
            String query = "SELECT COUNT(*) FROM courses";

            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                
            }

            return 0; // Return 0 if something went wrong
        }
    }


	
	// for setting
    public int updateUserData(String oldEmail, String newFirstName, String newSurname, String newEmail, String newPassword) {
        String url = "jdbc:mysql://localhost:3306/cmss";
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
        String userI = userData.get("userId");
        
        int idd = Integer.parseInt(userI);
                DatabaseOperations databaseOperations = new DatabaseOperations();
        int totalCourses = databaseOperations.getTotalCourseCount();
        String totalCourse = String.valueOf(totalCourses);

        int totalStds = getTotalCount("Student");
        int totalA = getTotalCount("Admin");
        int totalT = getTotalCount("Teacher");


        String totalAdmins = String.valueOf(totalA);
        String totalTeachers = String.valueOf(totalT);
        String totalStudents = String.valueOf(totalStds);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 11, 203, 534);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.setBounds(33, 116, 127, 28);
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
		btnNewButton.setBounds(33, 155, 127, 28);
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);

			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Students");
		btnNewButton_2.setBounds(33, 194, 127, 28);
		btnNewButton_2.setBackground(new Color(64, 128, 128));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);

			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Teachers");
		btnNewButton_3.setBounds(33, 233, 127, 28);
		btnNewButton_3.setBackground(new Color(64, 128, 128));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);

			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Logout");
		btnNewButton_4.setBounds(32, 490, 127, 28);
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
		btnNewButton_10.setBounds(33, 311, 127, 28);
		btnNewButton_10.setBackground(new Color(64, 128, 128));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);

				
			}
		});
		btnNewButton_10.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("Admin");
		btnNewButton_11.setBounds(33, 272, 127, 28);
		btnNewButton_11.setBackground(new Color(64, 128, 128));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);

			}
		});
		btnNewButton_11.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(btnNewButton_11);
		
		JButton btnNewButton_10_1 = new JButton("Refresh");
		btnNewButton_10_1.setBounds(33, 451, 127, 28);
		btnNewButton_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_10_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1.setBackground(new Color(64, 128, 128));
		panel.add(btnNewButton_10_1);
		
		JLabel lblNewLabel_6 = new JLabel(rOle);
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6.setBounds(46, 77, 127, 28);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_10_1_2_1 = new JButton("Result");
		btnNewButton_10_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultId stdid = new resultId();
				stdid.setVisible(true);
			}
		});
		btnNewButton_10_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1_2_1.setBackground(new Color(64, 128, 128));
		btnNewButton_10_1_2_1.setBounds(33, 413, 127, 28);
		panel.add(btnNewButton_10_1_2_1);
		
		JButton btnNewButton_10_1_2_1_1 = new JButton("Markss");
		btnNewButton_10_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Displayresult result = new Displayresult(idd);
				result.setVisible(true);
			}
		});
		btnNewButton_10_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1_2_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_10_1_2_1_1.setBounds(33, 376, 127, 28);
		panel.add(btnNewButton_10_1_2_1_1);
		
		JButton btnNewButton_10_1_2_1_2 = new JButton("Result");
		btnNewButton_10_1_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);

				
			}
		});
		btnNewButton_10_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10_1_2_1_2.setBackground(new Color(64, 128, 128));
		btnNewButton_10_1_2_1_2.setBounds(34, 344, 127, 28);
		panel.add(btnNewButton_10_1_2_1_2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(64, 128, 128));
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 22));
		tabbedPane.setBounds(223, 11, 859, 534);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Arial", Font.BOLD, 25));
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Home", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_8_2 = new JPanel();
		panel_8_2.setBackground(new Color(255, 128, 192));
		panel_8_2.setBounds(245, 142, 166, 157);
		panel_1.add(panel_8_2);
		panel_8_2.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel(totalStudents);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(0, 69, 166, 88);
		panel_8_2.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Student");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(0, 0, 166, 36);
		panel_8_2.add(lblNewLabel_4_1);
		
		JPanel panel_8_2_1 = new JPanel();
		panel_8_2_1.setBackground(new Color(128, 128, 0));
		panel_8_2_1.setBounds(26, 142, 166, 157);
		panel_1.add(panel_8_2_1);
		panel_8_2_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Admin");
		lblNewLabel_4.setBounds(0, 0, 166, 36);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel_8_2_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(totalAdmins);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(0, 47, 166, 110);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8_2_1.add(lblNewLabel_5);
		
		JPanel panel_8_2_2 = new JPanel();
		panel_8_2_2.setBackground(new Color(255, 128, 128));
		panel_8_2_2.setBounds(461, 142, 166, 157);
		panel_1.add(panel_8_2_2);
		panel_8_2_2.setLayout(null);
		
		JLabel lblNewLabel_4_2 = new JLabel("Teacher");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_2.setBounds(0, 0, 166, 36);
		panel_8_2_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_5_2 = new JLabel(totalTeachers);
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2.setBounds(0, 68, 166, 89);
		panel_8_2_2.add(lblNewLabel_5_2);
		
		JPanel panel_8_2_3 = new JPanel();
		panel_8_2_3.setBackground(new Color(128, 128, 255));
		panel_8_2_3.setBounds(659, 142, 166, 157);
		panel_1.add(panel_8_2_3);
		panel_8_2_3.setLayout(null);
		
		JLabel lblNewLabel_5_3 = new JLabel(totalCourse);
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_3.setBounds(0, 71, 166, 86);
		panel_8_2_3.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_4_3 = new JLabel("Course");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_3.setBounds(0, 0, 166, 36);
		panel_8_2_3.add(lblNewLabel_4_3);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(128, 0, 128));
		panel_8.setBounds(0, 0, 854, 76);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Dashboard");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(0, 0, 854, 76);
		panel_8.add(lblNewLabel_8);
		lblNewLabel_8.setBackground(new Color(128, 0, 128));
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Courses", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setBounds(10, 66, 160, 39);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		panel_2.add(lblNewLabel);
		
		JButton Cadd = new JButton("Add ");
		Cadd.setBounds(417, 71, 88, 33);
		Cadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse courseAdd =new AddCourse();
				courseAdd.setVisible(true);
			}
		});	
		Cadd.setBackground(new Color(64, 128, 128));
		Cadd.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(Cadd);
		
		JButton Cedit = new JButton("Edit");
		Cedit.setBounds(526, 71, 88, 33);
		Cedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCourse courseE = new EditCourse();
				courseE.setVisible(true);
			}
		});
		Cedit.setBackground(new Color(64, 128, 128));
		Cedit.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(Cedit);
		
		deleteC = new JButton("Del ");
		deleteC.setBounds(624, 71, 89, 33);
		deleteC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delcourse courseD = new Delcourse();
				courseD.setVisible(true);
			}
		});
		deleteC.setBackground(new Color(64, 128, 128));
		deleteC.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(deleteC);
//		
		coursedisplay=new course();
		coursedisplay.setBounds(10, 119, 839, 337);
		coursedisplay.setBackground(new Color(255, 255, 255));
		coursedisplay.setForeground(new Color(0,0,0));
		panel_2.add(coursedisplay);
		coursedisplay.setLayout(null);
//		coursedisplay.setLayout(null);
//		coursedisplay.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(151, 72, 246, 33);
		textField.setColumns(10);
		panel_2.add(textField);
		
		JButton Crefresh = new JButton("Refresh");
		Crefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursedisplay=new course();
				coursedisplay.setBounds(10, 174, 839, 360);
				coursedisplay.setBackground(new Color(255, 255, 255));
				coursedisplay.setForeground(new Color(0,0,0));
				panel_2.add(coursedisplay);
				coursedisplay.setLayout(null);
			}
		});
		Crefresh.setBackground(new Color(64, 128, 128));
		Crefresh.setBounds(735, 71, 114, 33);
		Crefresh.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(Crefresh);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(64, 0, 0));
		panel_7.setBounds(2, 0, 851, 55);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Course Details");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(0, 0, 851, 55);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 35));
		panel_7.add(lblNewLabel_7);
		
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Students", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		
		JButton Sadd = new JButton("Add");
		Sadd.setBackground(new Color(64, 128, 128));
		Sadd.setFont(new Font("Arial", Font.BOLD, 15));
		Sadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addname studentAdd =new Addname();
				studentAdd.setVisible(true);
				
			}
		});
		Sadd.setBounds(409, 69, 89, 35);
		panel_3.add(Sadd);
		
		

		studentdisplay = new students();
		studentdisplay.setBackground(new Color(255, 255, 255));
		studentdisplay.setBounds(8, 118, 834, 368);
		studentdisplay.setForeground(new Color(0, 0, 0));
		panel_3.add(studentdisplay);
		studentdisplay.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1.setBounds(10, 67, 138, 35);
		panel_3.add(lblNewLabel_1);
		
		JButton Sedit = new JButton("Edit");
		Sedit.setBackground(new Color(64, 128, 128));
		Sedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Editname studentname =new Editname();
			studentname.setVisible(true);
			}
		});
		Sedit.setFont(new Font("Arial", Font.BOLD, 15));
		Sedit.setBounds(519, 69, 89, 35);
		panel_3.add(Sedit);
		
		JButton Sdelete = new JButton("Del");
		Sdelete.setBackground(new Color(64, 128, 128));
		Sdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delname studentD = new Delname();
				studentD.setVisible(true);
			}
		});
		Sdelete.setFont(new Font("Arial", Font.BOLD, 15));
		Sdelete.setBounds(629, 70, 89, 33);
		panel_3.add(Sdelete);
		
			
				
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Teachers", null, panel_4, null);
		panel_4.setLayout(null);
		
		teacherdisplay = new Teachers();
        teacherdisplay.setBackground(new Color(255, 255, 255));
        teacherdisplay.setBounds(7, 114, 837, 370);
        panel_4.add(teacherdisplay);
		teacherdisplay.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 71, 250, 33);
		panel_3.add(textField_1);
		
		JButton Srefresh = new JButton("Refresh");
		Srefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentdisplay = new students();
				studentdisplay.setBackground(new Color(255, 255, 255));
				studentdisplay.setBounds(10, 192, 847, 353);
				studentdisplay.setForeground(new Color(0, 0, 0));
				panel_3.add(studentdisplay);
				studentdisplay.setLayout(null);
				
			}
		});
		Srefresh.setBackground(new Color(64, 128, 128));
		Srefresh.setFont(new Font("Arial", Font.BOLD, 15));
		Srefresh.setBounds(730, 70, 114, 33);
		panel_3.add(Srefresh);
		
		JButton btnNewButton_15_2_1_1 = new JButton("Student Details");
		btnNewButton_15_2_1_1.setForeground(Color.WHITE);
		btnNewButton_15_2_1_1.setFont(new Font("Arial", Font.BOLD, 40));
		btnNewButton_15_2_1_1.setBackground(new Color(64, 0, 0));
		btnNewButton_15_2_1_1.setBounds(0, 1, 852, 56);
		panel_3.add(btnNewButton_15_2_1_1);
		
		JButton btnNewButton_15_1 = new JButton("Teacher Details");
		btnNewButton_15_1.setForeground(new Color(255, 255, 255));
		btnNewButton_15_1.setBackground(new Color(64, 0, 0));
		btnNewButton_15_1.setFont(new Font("Arial", Font.BOLD, 40));
		btnNewButton_15_1.setBounds(0, 0, 854, 58);
		panel_4.add(btnNewButton_15_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Teacher name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(10, 69, 147, 35);
		panel_4.add(lblNewLabel_1_1);
		
		JButton Tadd = new JButton("Add");
		Tadd.setBackground(new Color(64, 128, 128));
		Tadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addteacher TeacherAdd =new Addteacher();
				TeacherAdd.setVisible(true);
				
			}
		});
		Tadd.setFont(new Font("Arial", Font.BOLD, 15));
		Tadd.setBounds(435, 69, 89, 35);
		panel_4.add(Tadd);
		
		JButton Tedit = new JButton("Edit");
		Tedit.setBackground(new Color(64, 128, 128));
		Tedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editteacher teacheredit =new Editteacher();
				teacheredit.setVisible(true);
			}
		});
		Tedit.setFont(new Font("Arial", Font.BOLD, 15));
		Tedit.setBounds(534, 69, 89, 35);
		panel_4.add(Tedit);
		
		JButton Tdel = new JButton("Del");
		Tdel.setBackground(new Color(64, 128, 128));
		Tdel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delteacher teacherD = new Delteacher();
				teacherD.setVisible(true);
			}
		});
		Tdel.setFont(new Font("Arial", Font.BOLD, 15));
		Tdel.setBounds(634, 69, 89, 33);
		panel_4.add(Tdel);
		
		JButton Trefresh = new JButton("Refresh");
		Trefresh.setBackground(new Color(64, 128, 128));
		Trefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherdisplay = new Teachers();
		        teacherdisplay.setBackground(new Color(255, 255, 255));
		        teacherdisplay.setBounds(10, 175, 837, 370);
		        panel_4.add(teacherdisplay);
				teacherdisplay.setLayout(null);
						}
		});
		Trefresh.setFont(new Font("Arial", Font.BOLD, 15));
		Trefresh.setBounds(733, 69, 114, 33);
		panel_4.add(Trefresh);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 69, 258, 33);
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
        btnNewButton_15_2.setBounds(0, 0, 852, 56);
        panel_5.add(btnNewButton_15_2);
        
        JButton Aadd = new JButton("Add");
        Aadd.setBackground(new Color(64, 128, 128));
        Aadd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Addadmin adminAdd =new Addadmin();
				adminAdd.setVisible(true);
        		
        	}
        });
        Aadd.setFont(new Font("Arial", Font.BOLD, 15));
        Aadd.setBounds(436, 70, 89, 35);
        panel_5.add(Aadd);
        
        JButton Adel = new JButton("Del");
        Adel.setBackground(new Color(64, 128, 128));
        Adel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Deladmin admindel =new Deladmin();
				admindel.setVisible(true);
        	}
        });
        Adel.setFont(new Font("Arial", Font.BOLD, 15));
        Adel.setBounds(638, 70, 89, 33);
        panel_5.add(Adel);
        
        JButton Aedit = new JButton("Edit");
        Aedit.setBackground(new Color(64, 128, 128));
        Aedit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Editadmin adminedit =new Editadmin();
				adminedit.setVisible(true);
        		
        	}
        });
        Aedit.setFont(new Font("Arial", Font.BOLD, 15));
        Aedit.setBounds(539, 69, 89, 35);
        panel_5.add(Aedit);
        
        JButton Arefresh = new JButton("Refresh");
        Arefresh.setBackground(new Color(64, 128, 128));
        Arefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Admindisplay = new Admin();
        		Admindisplay.setBackground(new Color(255, 255, 255));
        		Admindisplay.setBounds(20, 166, 816, 379);
                panel_5.add(Admindisplay);
                Admindisplay.setLayout(null);
        	}
        });
        Arefresh.setFont(new Font("Arial", Font.BOLD, 15));
        Arefresh.setBounds(736, 72, 114, 33);
        panel_5.add(Arefresh);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Admin name");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 19));
        lblNewLabel_1_1_1.setBounds(10, 67, 147, 35);
        panel_5.add(lblNewLabel_1_1_1);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(144, 70, 268, 33);
        panel_5.add(textField_8);
        
        JPanel panel_6 = new JPanel();
        tabbedPane.addTab("Setting", null, panel_6, null);
        panel_6.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel("Firstname");
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3.setBounds(37, 115, 142, 33);
        panel_6.add(lblNewLabel_3);
        
        firstname = new JTextField();
        firstname.setBounds(206, 115, 238, 33);
        panel_6.add(firstname);
        firstname.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("Lastname");
        lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_1.setBounds(37, 159, 119, 33);
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
        lblNewLabel_2.setBounds(0, 57, 867, 33);
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
        btnNewButton_14.setBounds(369, 414, 148, 33);
        panel_6.add(btnNewButton_14);
        
        lastname = new JTextField();
        lastname.setColumns(10);
        lastname.setBounds(206, 159, 238, 33);
        panel_6.add(lastname);
        
        JLabel lblNewLabel_3_1_3 = new JLabel("Email");
        lblNewLabel_3_1_3.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_1_3.setBounds(37, 203, 119, 33);
        panel_6.add(lblNewLabel_3_1_3);
        
        email = new JTextField();
        email.setColumns(10);
        email.setBounds(206, 203, 238, 33);
        panel_6.add(email);
        
        JLabel lblNewLabel_2_1 = new JLabel("Security");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_2_1.setBounds(0, 247, 867, 33);
        panel_6.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3_2 = new JLabel("Old password");
        lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_2.setBounds(37, 306, 142, 33);
        panel_6.add(lblNewLabel_3_2);
        
        oldpassword = new JTextField();
        oldpassword.setColumns(10);
        oldpassword.setBounds(206, 306, 238, 33);
        panel_6.add(oldpassword);
        
        newpassword = new JTextField();
        newpassword.setColumns(10);
        newpassword.setBounds(206, 350, 238, 33);
        panel_6.add(newpassword);
        
        JLabel lblNewLabel_3_2_1 = new JLabel("New password");
        lblNewLabel_3_2_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_3_2_1.setBounds(37, 350, 159, 33);
        panel_6.add(lblNewLabel_3_2_1);
        
        JPanel panel_9 = new JPanel();
        tabbedPane.addTab("Result", null, panel_9, null);
        panel_9.setLayout(null);
		
        
        displayResult = new ResultDisplay();
        displayResult.setBackground(new Color(255, 255, 255));
        displayResult.setBounds(6, 133, 838, 299);
        displayResult.setForeground(new Color(0, 0, 0));
		panel_9.add(displayResult);
//		displayResult.setLayout(null);
		
		JButton Rrefresh = new JButton("Refresh");
		Rrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayResult = new ResultDisplay();
		        displayResult.setBackground(new Color(255, 255, 255));
		        displayResult.setBounds(6, 133, 838, 299);
		        displayResult.setForeground(new Color(0, 0, 0));
				panel_9.add(displayResult);
//				displayResult.setLayout(null);
			}
		});
		Rrefresh.setFont(new Font("Arial", Font.BOLD, 15));
		Rrefresh.setBackground(new Color(64, 128, 128));
		Rrefresh.setBounds(734, 78, 114, 33);
		panel_9.add(Rrefresh);
		
		JButton Rupdate = new JButton("Update");
		Rupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMarks markEdit = new editMarks();
        		markEdit.setVisible(true);
			}
		});
		Rupdate.setFont(new Font("Arial", Font.BOLD, 15));
		Rupdate.setBackground(new Color(64, 128, 128));
		Rupdate.setBounds(586, 77, 114, 33);
		panel_9.add(Rupdate);
		
		JButton Radd = new JButton("Add");
		Radd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMarks markadd = new addMarks();
        		markadd.setVisible(true);
			}
			
		});
		Radd.setFont(new Font("Arial", Font.BOLD, 15));
		Radd.setBackground(new Color(64, 128, 128));
		Radd.setBounds(437, 77, 114, 33);
		panel_9.add(Radd);
		
		JButton btnNewButton_15_2_2 = new JButton("Marks Details");
		btnNewButton_15_2_2.setForeground(Color.WHITE);
		btnNewButton_15_2_2.setFont(new Font("Arial", Font.BOLD, 40));
		btnNewButton_15_2_2.setBackground(new Color(64, 0, 0));
		btnNewButton_15_2_2.setBounds(0, 0, 852, 56);
		panel_9.add(btnNewButton_15_2_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1_1_1_1.setBounds(10, 77, 147, 35);
		panel_9.add(lblNewLabel_1_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 77, 268, 33);
		panel_9.add(textField_3);
		System.out.println(rOle);
		if("Student".equals(rOle)) {
			Cadd.setVisible(false);
			Cadd.setVisible(false);
			Cadd.setVisible(false);
			Sadd.setVisible(false);
			Tadd.setVisible(false);
			Cadd.setVisible(false);
			Radd.setVisible(false);



			Cedit.setVisible(false);
			Sedit.setVisible(false);
			Tedit.setVisible(false);
			Aedit.setVisible(false);
			Rupdate.setVisible(false);

				
			Sdelete.setVisible(false);
			deleteC.setVisible(false);
			Tdel.setVisible(false);
			Adel.setVisible(false);
			Rupdate.setVisible(false);

			Crefresh.setVisible(false);
			Srefresh.setVisible(false);
			Trefresh.setVisible(false);
			Arefresh.setVisible(false);
			Rrefresh.setVisible(false);
			Cadd.setVisible(false);
			Sadd.setVisible(false);
			Tadd.setVisible(false);
			Aadd.setVisible(false);
			Radd.setVisible(false);
//			btnNewButton_10_1_2_1.setVisible(false);
			}
	    else {
	    	Cadd.setVisible(true);
			Tadd.setVisible(true);
			Sadd.setVisible(true);
			Cadd.setVisible(true);
			Radd.setVisible(true);



			Cedit.setVisible(true);
			Cedit.setVisible(true);
			Tedit.setVisible(true);
			Aedit.setVisible(true);
			Rupdate.setVisible(true);

			
			deleteC.setVisible(true);
			Sdelete.setVisible(true);
			Tdel.setVisible(true);
			Adel.setVisible(true);
			Rupdate.setVisible(true);

			Crefresh.setVisible(true);
			Srefresh.setVisible(true);
			Trefresh.setVisible(true);
			Arefresh.setVisible(true);
			Rrefresh.setVisible(true);
			
			Cadd.setVisible(true);
			Sadd.setVisible(true);
			Tadd.setVisible(true);
			Aadd.setVisible(true);
			Radd.setVisible(true);
//			btnNewButton_10_1_2_1.setVisible(true);



			}
		if("Teacher".equals(rOle)) {
//			btnNewButton_10_1_2_1.setVisible(true);

			Cadd.setVisible(true);
			Tadd.setVisible(true);
			Sadd.setVisible(true);
			Cadd.setVisible(true);
			Radd.setVisible(true);



			Cedit.setVisible(true);
			Cedit.setVisible(true);
			Tedit.setVisible(true);
			Aedit.setVisible(true);
			Rupdate.setVisible(true);

			
			deleteC.setVisible(true);
			Sdelete.setVisible(true);
			Tdel.setVisible(true);
			Adel.setVisible(true);
			Rupdate.setVisible(true);

			Crefresh.setVisible(true);
			Srefresh.setVisible(true);
			Trefresh.setVisible(true);
			Arefresh.setVisible(true);
			Rrefresh.setVisible(true);
			
			Cadd.setVisible(true);
			Sadd.setVisible(true);
			Tadd.setVisible(true);
			Aadd.setVisible(true);
			Radd.setVisible(true);
			


			}else {
			Cadd.setVisible(false);
			Cadd.setVisible(false);
			Cadd.setVisible(false);
			Tadd.setVisible(false);
			Sadd.setVisible(false);
			Cadd.setVisible(false);
			Radd.setVisible(false);



			Cedit.setVisible(false);
			Sedit.setVisible(false);
			Tedit.setVisible(false);
			Aedit.setVisible(false);
			Rupdate.setVisible(false);

			
			Sdelete.setVisible(false);
			deleteC.setVisible(false);
			Tdel.setVisible(false);
			Adel.setVisible(false);
			Rupdate.setVisible(false);

			Crefresh.setVisible(false);
			Srefresh.setVisible(false);
			Trefresh.setVisible(false);
			Arefresh.setVisible(false);
			Rrefresh.setVisible(false);
			Cadd.setVisible(false);
			Sadd.setVisible(false);
			Tadd.setVisible(false);
			Aadd.setVisible(false);
			Radd.setVisible(false);
//			btnNewButton_10_1_2_1.setVisible(false);


      if("Student".equals(rOle)) {
    	  btnNewButton_10_1_2_1_1.setVisible(true);
    	  btnNewButton_10_1_2_1.setVisible(false);
    	  
      }
      else {
    	  btnNewButton_10_1_2_1_1.setVisible(false);

      }
      if("Teacher".equals(rOle)) {
//    	  btnNewButton_10_1_2_1_1.setVisible(true);
    	  btnNewButton_10_1_2_1.setVisible(true);
    	        }
      else {
    	  btnNewButton_10_1_2_1_1.setVisible(false);

      }
      if("Admin".equals(rOle)) {
          btnNewButton_10_1_2_1.setVisible(true);

//    	  btnNewButton_10_1_2_1_1.setVisible(true);
//    	  btnNewButton_10_1_2_1.setVisible(true);
    	        }
      else {
//    	  btnNewButton_10_1_2_1_1.setVisible(false);
          btnNewButton_10_1_2_1.setVisible(false);


      }
		}}	
}
	

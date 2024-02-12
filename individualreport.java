package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class individualreport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static int stdId;
	public int getStdId() {
		return stdId;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Displayresult frame = new Displayresult(stdId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//get std data
	private Map<String, String> getStudentData(int id) {
        Map<String, String> studentData = new HashMap<>();

        String url = "jdbc:mysql://localhost:3306/cmss";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.firstname, u.lastname, u.email,u.course,u.level, r.module_1, r.mark_1,r.module_2, r.mark_2,r.module_3, r.mark_3, r.percentage, r.result FROM users u JOIN results r ON u.id=r.std_id WHERE u.id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set and putting it into the HashMap
                    studentData.put("firstname", resultSet.getString("firstname"));
                    studentData.put("surname", resultSet.getString("lastname"));
                    studentData.put("email", resultSet.getString("email"));
                    studentData.put("course", resultSet.getString("course"));
                    studentData.put("level", resultSet.getString("level"));
                    studentData.put("module_1", resultSet.getString("module_1"));
                    studentData.put("mark_1", resultSet.getString("mark_1"));
                    studentData.put("module_2", resultSet.getString("module_2"));
                    studentData.put("mark_2", resultSet.getString("mark_2"));
                    studentData.put("module_3", resultSet.getString("module_3"));
                    studentData.put("mark_3", resultSet.getString("mark_3"));
                    studentData.put("percentage", resultSet.getString("percentage"));
                    studentData.put("result", resultSet.getString("result"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentData;
    }


	/**
	 * Create the frame.
	 */
	public individualreport(int stdId) {
		

		Map<String, String> studentData = getStudentData(stdId);

        String fname = studentData.get("firstname");
        String lname = studentData.get("surname");
        String email = studentData.get("email");
        String course = studentData.get("course");
        String module1 = studentData.get("module_1");
        String mark1 = studentData.get("mark_1");
        String module2 = studentData.get("module_2");
        String mark2 = studentData.get("mark_2");
        String module3 = studentData.get("module_3");
        String mark3 = studentData.get("mark_3");
        String percent = studentData.get("percentage");
        String result = studentData.get("result");
        String lvl = studentData.get("level");

        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(fname);
		lblNewLabel_1.setBounds(126, 70, 121, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(lname);
		lblNewLabel_2.setBounds(374, 80, 124, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(email);
		lblNewLabel_2_1.setBounds(106, 120, 121, 24);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblEmail_6 = new JLabel(course);
		lblEmail_6.setBounds(126, 165, 283, 24);
		contentPane.add(lblEmail_6);
		
		JLabel lblEmail_7 = new JLabel(module1);
		lblEmail_7.setBounds(122, 212, 84, 24);
		contentPane.add(lblEmail_7);
		
		JLabel lblEmail_8 = new JLabel(lvl);
		lblEmail_8.setBounds(338, 115, 71, 24);
		contentPane.add(lblEmail_8);
		
		JLabel lblEmail_9 = new JLabel(mark2);
		lblEmail_9.setBounds(346, 242, 63, 24);
		contentPane.add(lblEmail_9);
		
		JLabel lblResult_1 = new JLabel(result);
		lblResult_1.setBounds(106, 354, 79, 24);
		contentPane.add(lblResult_1);
		
		JLabel lblEmail_11 = new JLabel(percent);
		lblEmail_11.setBounds(143, 319, 63, 24);
		contentPane.add(lblEmail_11);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 192));
		panel.setBounds(0, 0, 534, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Report Card");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 534, 52);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel(result);
		lblNewLabel_3.setBounds(294, 12, 0, 0);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnNewButton.setForeground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(424, 371, 89, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblModules_3 = new JLabel(module2);
		lblModules_3.setBounds(122, 242, 84, 24);
		contentPane.add(lblModules_3);
		
		JLabel lblModules_1 = new JLabel(module3);
		lblModules_1.setBounds(122, 274, 84, 24);
		contentPane.add(lblModules_1);
		
		JLabel lblEmail_9_1 = new JLabel(mark2);
		lblEmail_9_1.setBounds(346, 212, 63, 24);
		contentPane.add(lblEmail_9_1);
		
		JLabel lblEmail_9_2 = new JLabel(mark3);
		lblEmail_9_2.setBounds(346, 277, 63, 24);
		contentPane.add(lblEmail_9_2);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Arial", Font.BOLD, 20));
		lblFirstname.setBounds(23, 80, 110, 24);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Arial", Font.BOLD, 20));
		lblLastname.setBounds(257, 80, 107, 24);
		contentPane.add(lblLastname);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail_1.setBounds(23, 115, 110, 38);
		contentPane.add(lblEmail_1);
		
		JLabel lblLevel_1 = new JLabel("Level");
		lblLevel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblLevel_1.setBounds(257, 115, 71, 24);
		contentPane.add(lblLevel_1);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setFont(new Font("Arial", Font.BOLD, 20));
		lblCourses.setBounds(23, 155, 110, 34);
		contentPane.add(lblCourses);
		
		JLabel lblMarks = new JLabel("Marks:");
		lblMarks.setFont(new Font("Arial", Font.BOLD, 20));
		lblMarks.setBounds(275, 212, 80, 24);
		contentPane.add(lblMarks);
		
		JLabel lblModules = new JLabel("Modules:");
		lblModules.setFont(new Font("Arial", Font.BOLD, 20));
		lblModules.setBounds(23, 212, 110, 24);
		contentPane.add(lblModules);
		
		JLabel lblResult_2 = new JLabel("Result");
		lblResult_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblResult_2.setBounds(23, 354, 110, 24);
		contentPane.add(lblResult_2);
		
		JLabel lblPercentage = new JLabel("Percentage");
		lblPercentage.setFont(new Font("Arial", Font.BOLD, 20));
		lblPercentage.setBounds(23, 319, 110, 24);
		contentPane.add(lblPercentage);
	}
}

package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Delcourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delcourse frame = new Delcourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//for delete
	public void deleteCourse1(int id) {
		String url = "jdbc:mysql://localhost:3306/cmss";
        String dbUsername = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM courses WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Deleted Sucessfully");
            } else {
                System.out.println("Course not found with ID: " + id);
//                JOptionPane.showMessageDialog(null, "Course not deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public Delcourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Delete Course");
		btnNewButton.setBackground(new Color(128, 128, 192));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 30));
		btnNewButton.setBounds(0, 0, 447, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseI = courseId.getText();
				if(!courseI.isEmpty()) {
					int Id = Integer.parseInt(courseI);
					deleteCourse1(Id);
					
				}
			}
		});
		btnNewButton_1.setBounds(85, 165, 113, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(264, 165, 113, 39);
		contentPane.add(btnNewButton_1_1);
		
		courseId = new JTextField();
		courseId.setBounds(168, 80, 176, 30);
		contentPane.add(courseId);
		courseId.setColumns(10);
		
		JLabel lblStudentid = new JLabel("ID");
		lblStudentid.setFont(new Font("Arial", Font.BOLD, 20));
		lblStudentid.setBounds(116, 88, 70, 22);
		contentPane.add(lblStudentid);
	}

}

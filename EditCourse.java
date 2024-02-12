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
import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;

public class EditCourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseid;
	private JTextField coursename;
	private JTextField courseyear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCourse frame = new EditCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//update course
	String url = "jdbc:mysql://localhost:3306/cmss";
    String dbUsername = "root";
    String dbPassword = "";
    
    public void updateCourse1(String Name, int years ,int id) {
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE courses SET Name = ?, years = ? WHERE id = ?")) {

            preparedStatement.setString(1, Name);
            preparedStatement.setInt(2, years);
            preparedStatement.setInt(3, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course Updated successfully!");
                JOptionPane.showMessageDialog(null, "Course Updated Sucessfully");
            } else {
                System.out.println("Course not found with ID: " + id);
                JOptionPane.showMessageDialog(null, "Failed to update Course !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public EditCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		courseid = new JTextField();
		courseid.setBounds(205, 66, 77, 36);
		contentPane.add(courseid);
		courseid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(57, 172, 141, 34);
		contentPane.add(lblNewLabel_1);
		
		coursename = new JTextField();
		coursename.setColumns(10);
		coursename.setBounds(205, 165, 237, 41);
		contentPane.add(coursename);
		
		courseyear = new JTextField();
		courseyear.setColumns(10);
		courseyear.setBounds(205, 120, 96, 34);
		contentPane.add(courseyear);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseI = courseid.getText();
				String courseN = coursename.getText();
				String courseY = courseyear.getText();
				
				if(!courseI.isEmpty() && !courseN.isEmpty() && !courseY.isEmpty()) {
					int id = Integer.parseInt(courseI);
					int year = Integer.parseInt(courseY);
					updateCourse1(courseN,year,id);
					
				}else {
					JOptionPane.showMessageDialog(null, "Input cannot be emty");
				}
					
			}
		});
		btnNewButton.setBounds(73, 238, 89, 33);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(312, 238, 89, 34);
		contentPane.add(btnBack);
		
		JButton btnEditName = new JButton("Edit name");
		btnEditName.setFont(new Font("Arial", Font.BOLD, 30));
		btnEditName.setBackground(new Color(128, 128, 192));
		btnEditName.setBounds(0, 0, 470, 55);
		contentPane.add(btnEditName);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(64, 128, 128));
		lblNewLabel.setBounds(57, 73, 46, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("Course Year");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_2.setBounds(57, 117, 141, 34);
		contentPane.add(lblNewLabel_1_2);
	}
}

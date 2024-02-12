package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddCourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField coursename;
	private JTextField courseyear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//add course 
	private void addCourse1( String Name, int years) {
        String url = "jdbc:mysql://localhost:3306/cmss";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (Name, years) VALUES (?, ?);");
             preparedStatement.setString(1,Name);
              preparedStatement.setInt(2, years);
              
              preparedStatement.executeUpdate();
              
             JOptionPane.showMessageDialog(null, "Added successfully");
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public AddCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		coursename = new JTextField();
		coursename.setColumns(10);
		coursename.setBounds(148, 78, 186, 30);
		contentPane.add(coursename);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(54, 78, 89, 30);
		contentPane.add(lblNewLabel);
		
		courseyear = new JTextField();
		courseyear.setColumns(10);
		courseyear.setBounds(148, 141, 186, 27);
		contentPane.add(courseyear);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseN = coursename.getText();
				String courseY = courseyear.getText();
				if(!courseN.isEmpty() && !courseY.isEmpty()) {
					int courseYear = Integer.parseInt(courseY);
					addCourse1(courseN, courseYear);
					
				}
			}
		});
		btnNewButton_1.setBounds(54, 212, 89, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(245, 212, 89, 30);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnAddName = new JButton("Add name");
		btnAddName.setFont(new Font("Arial", Font.BOLD, 30));
		btnAddName.setBackground(new Color(128, 128, 192));
		btnAddName.setBounds(0, 0, 385, 45);
		contentPane.add(btnAddName);
		
		JLabel lblYear_1 = new JLabel("Year");
		lblYear_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblYear_1.setBounds(54, 136, 89, 30);
		contentPane.add(lblYear_1);
	}

}

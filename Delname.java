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
import java.awt.Color;

public class Delname extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField StudentId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delname frame = new Delname();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//del name
	public void deletename1(int id) {
		String url = "jdbc:mysql://localhost:3306/cmss";
        String dbUsername = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

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
	public Delname() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = StudentId.getText();

				if(!name1.isEmpty()) {
					int Id = Integer.parseInt(name1);
					deletename1(Id);
					
				}
			}
		});
		btnNewButton_1.setBounds(95, 151, 93, 33);
		contentPane.add(btnNewButton_1);
		
		JLabel lblStudentid = new JLabel("ID");
		lblStudentid.setFont(new Font("Arial", Font.BOLD, 20));
		lblStudentid.setBounds(100, 86, 70, 22);
		contentPane.add(lblStudentid);
		
		StudentId = new JTextField();
		StudentId.setBounds(159, 79, 160, 29);
		StudentId.setColumns(10);
		contentPane.add(StudentId);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(257, 151, 93, 33);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnDeleteName = new JButton("Delete Name");
		btnDeleteName.setFont(new Font("Arial", Font.BOLD, 30));
		btnDeleteName.setBackground(new Color(128, 128, 192));
		btnDeleteName.setBounds(0, 0, 406, 47);
		contentPane.add(btnDeleteName);
	}

}

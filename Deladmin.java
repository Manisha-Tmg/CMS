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
import java.awt.Font;
import java.awt.Color;

public class Deladmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deladmin frame = new Deladmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
	public Deladmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(153, 76, 160, 33);
		contentPane.add(id);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id1 = id.getText();

				if(!id1.isEmpty()) {
					int Id = Integer.parseInt(id1);
					deletename1(Id);}
			}
		});
		btnNewButton_1.setBounds(40, 150, 106, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(224, 150, 89, 33);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnDeleteName = new JButton("Delete Name");
		btnDeleteName.setBackground(new Color(128, 128, 192));
		btnDeleteName.setFont(new Font("Arial", Font.BOLD, 30));
		btnDeleteName.setBounds(0, 0, 431, 53);
		contentPane.add(btnDeleteName);
		
		JLabel lblStudentid = new JLabel("ID");
		lblStudentid.setFont(new Font("Arial", Font.BOLD, 20));
		lblStudentid.setBounds(90, 87, 70, 22);
		contentPane.add(lblStudentid);
	}

}

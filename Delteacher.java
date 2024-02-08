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
import java.awt.Color;
import java.awt.Font;

public class Delteacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delteacher frame = new Delteacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void deletename1(int id) {
		String url = "jdbc:mysql://localhost:3306/cms";
        String dbUsername = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Deleted Sucessfully");
            } else {
//                System.out.println("Course not found with ID: " + id);
                JOptionPane.showMessageDialog(null, "Course not deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
	 * Create the frame.
	 */
	public Delteacher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(154, 75, 135, 28);
		contentPane.add(ID);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id1 = ID.getText();

				if(!id1.isEmpty()) {
					int Id = Integer.parseInt(id1);
					deletename1(Id);}
			}
		});
		btnNewButton_1.setBounds(50, 151, 101, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(233, 153, 101, 31);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnDeleteName = new JButton("Delete Name");
		btnDeleteName.setFont(new Font("Arial", Font.BOLD, 30));
		btnDeleteName.setBackground(new Color(128, 128, 192));
		btnDeleteName.setBounds(0, 0, 406, 47);
		contentPane.add(btnDeleteName);
		
		JLabel lblStudentid = new JLabel("ID");
		lblStudentid.setFont(new Font("Arial", Font.BOLD, 20));
		lblStudentid.setBounds(98, 81, 70, 22);
		contentPane.add(lblStudentid);
	}

}

package finalproject;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Loginform extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Loginform frame = new Loginform();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/cms";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Loginform() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 510, 476);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblLogin = new JLabel("Login ");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(new Color(0, 0, 0));
        lblLogin.setBounds(180, 0, 118, 43);
        lblLogin.setBackground(new Color(255, 255, 255));
        lblLogin.setFont(new Font("Arial", Font.BOLD, 35));
        contentPane.add(lblLogin);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(26, 127, 88, 35);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 25));
        contentPane.add(lblEmail);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(26, 196, 159, 35);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
        contentPane.add(lblPassword);
        
        textField = new JTextField();
        textField.setBounds(180, 115, 246, 36);
        textField.setBackground(new Color(255, 255, 255));
        contentPane.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 199, 246, 36);
        contentPane.add(passwordField);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(164, 278, 118, 43);
        btnLogin.setBackground(new Color(255, 255, 255));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String username =textField.getText();
        		String password =passwordField.getText();
        		if(checkLogin(username,password)) {
        			JOptionPane.showMessageDialog(null,"Login sucessful");
        			Dashborad db = new Dashborad(username);
        			db.setVisible(true);
        			dispose();
        		}
        		
            }
        });
        btnLogin.setFont(new Font("Dialog", Font.BOLD, 25));
        contentPane.add(btnLogin);
        
        JLabel lblCreateANew = new JLabel("Create a new account ?");
        lblCreateANew.setBounds(10, 364, 218, 28);
        lblCreateANew.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        contentPane.add(lblCreateANew);
        
        JButton btnNewButton = new JButton("Sign up");
        btnNewButton.setBounds(225, 360, 117, 36);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Loginform lf = new Loginform();
        		lf.setVisible(true);
        		dispose();     		

        	}
        });
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
        contentPane.add(btnNewButton);
        
        JLabel label = new JLabel("");
        label.setBounds(61, 243, 70, 15);
        contentPane.add(label);
    }
}
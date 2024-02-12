
package finalproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Registrationform extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel confirmPassword;
    private JTextField firstName;
    private JTextField surname;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField passwordField_1;
    // private int select_Level;
    // private JTextField select_courses;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private JComboBox comboBox_2;
    private JTextField phone1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registrationform frame = new Registrationform();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void registerUser(String email, String password, String role, String first_name, String last_name,
            String level, String course, String phone) {
        String url = "jdbc:mysql://localhost:3306/cmss";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            switch (role) {
            case "Student":
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users (firstname, lastname, email, password, role, level, course, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, first_name);
                    preparedStatement.setString(2, last_name);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, password);
                    preparedStatement.setString(5, role);
                    preparedStatement.setString(6, level);
                    preparedStatement.setString(7, course);
                    preparedStatement.setString(8, phone);
                    preparedStatement.executeUpdate();
                }
                break;
                case "Teacher":
                    try (PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO users (firstname, lastname, email, password, phone, role) VALUES (?, ?, ?, ?, ?, ?)")) {
                        preparedStatement.setString(1, first_name);
                        preparedStatement.setString(2, last_name);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, password);
                        preparedStatement.setString(5, phone);
                        preparedStatement.setString(6, role);
                        preparedStatement.executeUpdate();
                    }
                    break;
                case "Admin":
                    try (PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO users (firstname, lastname, email, password, phone, role) VALUES (?, ?, ?, ?, ?, ?)")) {
                        preparedStatement.setString(1, first_name);
                        preparedStatement.setString(2, last_name);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, password);
                        preparedStatement.setString(5, phone);
                        preparedStatement.setString(6, role);
                        preparedStatement.executeUpdate();
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid role during registration");
            }
            JOptionPane.showMessageDialog(null, "Signed in as " + role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Registrationform() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 609, 599);
        confirmPassword = new JPanel();
        confirmPassword.setBackground(new Color(255, 255, 255));
        confirmPassword.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(confirmPassword);

        JLabel lblRegistration = new JLabel("REGISTRATION");
        lblRegistration.setFont(new Font("Dialog", Font.BOLD, 27));
        lblRegistration.setBounds(194, 21, 290, 55);
        confirmPassword.setLayout(null);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(21, 94, 130, 25);
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblFirstName);

        JLabel lblSurname = new JLabel("Last name");
        lblSurname.setBounds(21, 145, 101, 25);
        lblSurname.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblSurname);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(21, 199, 101, 25);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(21, 243, 171, 38);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setBounds(21, 292, 206, 35);
        lblConfirmPassword.setBackground(new Color(240, 240, 240));
        lblConfirmPassword.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblConfirmPassword);

        firstName = new JTextField();
        firstName.setBounds(223, 92, 263, 35);
        confirmPassword.add(firstName);
        firstName.setColumns(10);

        surname = new JTextField();
        surname.setBounds(223, 143, 263, 35);
        surname.setColumns(10);
        confirmPassword.add(surname);

        email = new JTextField();
        email.setBounds(223, 189, 263, 35);
        email.setColumns(10);
        confirmPassword.add(email);

        password = new JPasswordField();
        password.setBounds(223, 240, 263, 35);
        confirmPassword.add(password);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(223, 289, 263, 35);
        confirmPassword.add(passwordField_1);
        
        JLabel lblNewLabel_2 = new JLabel("Go to Login Page-->");
        lblNewLabel_2.setBounds(231, 502, 240, 35);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
        confirmPassword.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Level");
        lblNewLabel_3.setBounds(341, 352, 130, 35);
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblNewLabel_3);

        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(418, 348, 69, 33);
        comboBox_1.setBackground(new Color(255, 255, 255));
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "4", "5", "6" }));
        comboBox_1.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(comboBox_1);

        JLabel lblNewLabel_4 = new JLabel("Courses");
        lblNewLabel_4.setBounds(21, 435, 101, 38);
        lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblNewLabel_4);

        comboBox_2 = new JComboBox();
        comboBox_2.setBounds(148, 437, 333, 35);
        comboBox_2.setBackground(new Color(255, 255, 255));
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"BSc (Hons) Computer Science", "BSc (Hons) in International Business Management", "International MBA courses."}));
        comboBox_2.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(comboBox_2);

        JLabel Phone = new JLabel("Phone");
        Phone.setBounds(21, 389, 101, 35);
        Phone.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(Phone);

        phone1 = new JTextField();
        phone1.setBounds(150, 394, 246, 32);
        confirmPassword.add(phone1);
        phone1.setColumns(10);

        JButton registerBtn = new JButton("Sign Up");
        registerBtn.setBounds(89, 502, 130, 35);
        registerBtn.setBackground(new Color(64, 128, 128));
        registerBtn.setFont(new Font("Arial", Font.BOLD, 15));
        registerBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String firstname = firstName.getText();
                String lastname = surname.getText();
                String eMail = email.getText();
                String Phone1 = phone1.getText();
                String newPass = password.getText();
                String confirmPass = passwordField_1.getText();
                String selectedMode = (String) comboBox.getSelectedItem();
                String selectlevel = (String) comboBox_1.getSelectedItem();
                String select_courses = (String) comboBox_2.getSelectedItem();

                // Regex for FirstName
                String regexFN = "[a-zA-Z]+";
                Pattern Fname = Pattern.compile(regexFN);

                Matcher FN = Fname.matcher(firstname);
                boolean fname = FN.matches();

                // Regex for LastName
                String regexLN = "[a-zA-Z]+";
                Pattern Lname = Pattern.compile(regexLN);

                Matcher LN = Fname.matcher(lastname);
                boolean lname = LN.matches();

                // for emailverification

                String regexEmail = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern verifyEmail = Pattern.compile(regexEmail);

                Matcher eM = verifyEmail.matcher(eMail);
                boolean checkEmail = eM.matches();

                // Regex for New password
                String regexP = "[a-zA-Z]+";
                Pattern passN = Pattern.compile(regexP);

                Matcher pN = passN.matcher(newPass);
                boolean passNew = pN.matches();

                if (!firstname.equals("") && !lastname.equals("") && !eMail.equals("") && !newPass.equals("")
                        && !confirmPass.equals("") && !Phone1.equals("")) {
                    if (fname==true && lname==true && checkEmail==true && passNew==true && newPass.equals(confirmPass)) {
                        registerUser(eMail, newPass, selectedMode, firstname, lastname, selectlevel, select_courses,
                                Phone1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Inputs cannot be empty !!");
                }

            }
        });
        confirmPassword.add(registerBtn);

        comboBox = new JComboBox();
        comboBox.setBounds(181, 347, 120, 35);
        comboBox.setBackground(new Color(255, 255, 255));
        comboBox.setFont(new Font("Arial", Font.BOLD, 20));
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "Student", "Teacher", "Admin" }));

        confirmPassword.add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("Registered As");
        lblNewLabel_1.setBounds(21, 352, 148, 25);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPassword.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBounds(382, 502, 105, 35);
        btnNewButton.setBackground(new Color(64, 128, 128));
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Loginform rf = new Loginform();
                rf.setVisible(true);
                dispose();
            }
        });
        confirmPassword.add(btnNewButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 192));
        panel.setBounds(0, 0, 595, 58);
        confirmPassword.add(panel);
        
                JLabel lblNewLabel = new JLabel("Registration form");
                panel.add(lblNewLabel);
                lblNewLabel.setBackground(new Color(128, 128, 0));
                lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedMode = (String) comboBox.getSelectedItem();
                    if (selectedMode.equals("Student")) {
                        comboBox_1.setVisible(true); // Show Level comboBox
                        comboBox_2.setVisible(true); // Show Course comboBox
                        lblNewLabel_3.setVisible(true);
                        lblNewLabel_4.setVisible(true);
                    } else {
                        comboBox_1.setVisible(false); // Hide Level comboBox
                        comboBox_2.setVisible(false); // Hide Course comboBox
                        lblNewLabel_3.setVisible(false);
                        lblNewLabel_4.setVisible(false);

                    }
                }
            }
        });
    }
}

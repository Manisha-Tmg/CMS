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
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class resultId extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField studentid;

    private static int stdId; // Corrected declaration

    public int getstdId() {
        return stdId;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    resultId frame = new resultId(); // Corrected class name
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
    public resultId() { // Corrected class name
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 433, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Enter Student ID:");
        lblNewLabel.setBounds(60, 78, 192, 23);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contentPane.add(lblNewLabel);
        
        studentid = new JTextField();
        studentid.setBounds(233, 73, 96, 38);
        contentPane.add(studentid);
        studentid.setColumns(10);
        
        JButton btnNewButton = new JButton("Print");
        btnNewButton.setBounds(60, 187, 96, 38);
        btnNewButton.setBackground(new Color(64, 128, 128));
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = studentid.getText();
                if (!id.isEmpty()) {
                    stdId = Integer.parseInt(id); // Assign the value to stdId
                    Displayresult result = new Displayresult(stdId);
                    result.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty!!");
                }
            }
        });
        contentPane.add(btnNewButton);
        
        JButton btnBack_1 = new JButton("Back");
        btnBack_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnBack_1.setBounds(233, 187, 96, 38);
        btnBack_1.setFont(new Font("Arial", Font.BOLD, 20));
        btnBack_1.setBackground(new Color(64, 128, 128));
        contentPane.add(btnBack_1);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 419, 55);
        panel.setBackground(new Color(128, 128, 192));
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Show Details");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
        lblNewLabel_1.setBounds(0, 0, 419, 55);
        panel.add(lblNewLabel_1);
    }
}

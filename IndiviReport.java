package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.awt.event.ActionEvent;



public class IndiviReport extends JFrame {



    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JLabel lblFullName;

    private JLabel lblLevel;

    private JLabel lblCourse;

    private JLabel[] moduleLabels = new JLabel[3];

    private JLabel[] markLabels = new JLabel[3];

    private JLabel lblPercentage;

    private JLabel lblResult;

    private static int id;



    /**

     * Launch the application.

     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                	IndiviReport frame = new IndiviReport(id);

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

    public IndiviReport(int ID) {

    	id=ID;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 550, 422);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));



        setContentPane(contentPane);

        contentPane.setLayout(null);



        JLabel lblNewLabel = new JLabel("Report Card:");

        lblNewLabel.setFont(new Font("Cochin", Font.PLAIN, 24));

        lblNewLabel.setBounds(201, 6, 156, 32);

        contentPane.add(lblNewLabel);



        JLabel lblNewLabel_1 = new JLabel("Full Name:");

        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblNewLabel_1.setBounds(22, 51, 90, 16);

        contentPane.add(lblNewLabel_1);



        lblFullName = new JLabel("");

        lblFullName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblFullName.setBounds(124, 51, 217, 16);

        contentPane.add(lblFullName);



        JLabel lblNewLabel_2 = new JLabel("Level:");

        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblNewLabel_2.setBounds(353, 51, 61, 16);

        contentPane.add(lblNewLabel_2);



        lblLevel = new JLabel("");

        lblLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblLevel.setBounds(420, 51, 124, 16);

        contentPane.add(lblLevel);



        JLabel lblNewLabel_3 = new JLabel("Course:");

        lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblNewLabel_3.setBounds(22, 79, 61, 16);

        contentPane.add(lblNewLabel_3);



        lblCourse = new JLabel("");

        lblCourse.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        lblCourse.setBounds(95, 79, 449, 16);

        contentPane.add(lblCourse);



        JPanel panel = new JPanel();

        panel.setBounds(6, 107, 538, 172);

        contentPane.add(panel);

        panel.setLayout(null);



        JPanel panel_1 = new JPanel();

        panel_1.setBackground(new Color(128, 128, 128));

        panel_1.setBounds(6, 6, 526, 38);

        panel.add(panel_1);

        panel_1.setLayout(null);



        JLabel lblNewLabel_4 = new JLabel("Modules");

        lblNewLabel_4.setForeground(new Color(240, 248, 255));

        lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        lblNewLabel_4.setBounds(79, 6, 104, 26);

        panel_1.add(lblNewLabel_4);



        JLabel lblNewLabel_5 = new JLabel("Marks");

        lblNewLabel_5.setForeground(new Color(240, 248, 255));

        lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        lblNewLabel_5.setBounds(391, 7, 69, 25);

        panel_1.add(lblNewLabel_5);



        JPanel panel_2 = new JPanel();

        panel_2.setBackground(new Color(211, 211, 211));

        panel_2.setBounds(6, 48, 526, 38);

        panel.add(panel_2);

        panel_2.setLayout(null);



        moduleLabels[0] = new JLabel("Module_1");

        moduleLabels[0].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        moduleLabels[0].setBounds(43, 6, 143, 26);

        panel_2.add(moduleLabels[0]);



        markLabels[0] = new JLabel("Mark_1");

        markLabels[0].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        markLabels[0].setBounds(351, 6, 143, 26);

        panel_2.add(markLabels[0]);



        JPanel panel_3 = new JPanel();

        panel_3.setBackground(new Color(211, 211, 211));

        panel_3.setBounds(6, 89, 526, 38);

        panel.add(panel_3);

        panel_3.setLayout(null);



        moduleLabels[1] = new JLabel("Module_2");

        moduleLabels[1].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        moduleLabels[1].setBounds(42, 6, 143, 26);

        panel_3.add(moduleLabels[1]);



        markLabels[1] = new JLabel("Mark_2");

        markLabels[1].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        markLabels[1].setBounds(350, 6, 143, 26);

        panel_3.add(markLabels[1]);



        JPanel panel_4 = new JPanel();

        panel_4.setBackground(new Color(211, 211, 211));

        panel_4.setBounds(6, 129, 526, 38);

        panel.add(panel_4);

        panel_4.setLayout(null);



        moduleLabels[2] = new JLabel("Module_3");

        moduleLabels[2].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        moduleLabels[2].setBounds(42, 6, 143, 26);

        panel_4.add(moduleLabels[2]);



        markLabels[2] = new JLabel("Mark_3");

        markLabels[2].setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        markLabels[2].setBounds(349, 6, 143, 26);

        panel_4.add(markLabels[2]);



        JPanel panel_5 = new JPanel();

        panel_5.setBackground(new Color(169, 169, 169));

        panel_5.setBounds(16, 291, 178, 40);

        contentPane.add(panel_5);

        panel_5.setLayout(null);



        JLabel lblNewLabel_7 = new JLabel("Percentage:");

        lblNewLabel_7.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        lblNewLabel_7.setBounds(16, 0, 117, 40);

        panel_5.add(lblNewLabel_7);



        lblPercentage = new JLabel("P1");

        lblPercentage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        lblPercentage.setBounds(111, 8, 61, 23);

        panel_5.add(lblPercentage);



        JLabel lblNewLabel_9 = new JLabel("Result:");

        lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        lblNewLabel_9.setBounds(338, 299, 71, 32);

        contentPane.add(lblNewLabel_9);



        lblResult = new JLabel("Pass");

        lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        lblResult.setBounds(407, 295, 61, 40);

        contentPane.add(lblResult);



        JButton btnNewButton = new JButton("Return");

        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });

        btnNewButton.setBounds(199, 332, 136, 52);}}
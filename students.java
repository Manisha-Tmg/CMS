package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;

public class students extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    /**
     * Create the panel.
     */
    public students() {

        DefaultTableModel model = new DefaultTableModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmss", "root", "");
            Statement stmt = con.createStatement();
            String query = "SELECT id,firstname, lastname, email, course, phone, level FROM users WHERE role='Student'";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 840, 384);
        add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);
        table.setRowSelectionAllowed(false);
        table.setFont(new Font("Niramit", Font.PLAIN, 10));
        table.setForeground(new Color(0, 0, 0));
        table.setBackground(new Color(238, 238, 238));

        // Set preferred widths after setting the model
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10); 
        columnModel.getColumn(1).setPreferredWidth(10); 
        columnModel.getColumn(2).setPreferredWidth(20); 
        columnModel.getColumn(3).setPreferredWidth(20); 
        columnModel.getColumn(4).setPreferredWidth(20); 
        columnModel.getColumn(5).setPreferredWidth(20); 
        columnModel.getColumn(6).setPreferredWidth(10); 



        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(40);

        JTableHeader header = table.getTableHeader();
        header.setVisible(true);
        table.setIntercellSpacing(new java.awt.Dimension(3, 9));
    }
}

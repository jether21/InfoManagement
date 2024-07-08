package accountlogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.*;

public class Viewing extends JFrame implements ActionListener {
    private JFrame tblinfo;
    private DefaultTableModel model;
    private JTable table;
    private JTableHeader tblHeader;
    private JScrollPane scrollPane;
    private JPanel hdrPanel, bodyPanel;
    private JLabel lblHead, lblSearch;
    private JButton btnLogout, btnSearch;
    private JTextField textfSearch;
    private Connection conn;
    private final String[] columnHeader = {"Student No.", "First Name", "Middle Name", "Last Name", "Contact No.", "Address", "Birthday", "Course", "Year Level", "Position", "Organization"};

    public Viewing() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tblinfo = new JFrame("Student Information");
        tblinfo.setSize(1300, 800);
        tblinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tblinfo.setLocationRelativeTo(null);
        tblinfo.setLayout(new BorderLayout());

        lblHead = new JLabel("Polytechnic University of the Philippines", SwingConstants.CENTER);
        lblHead.setFont(new Font("Bookman Old Style", Font.BOLD, 36));
        lblHead.setForeground(Color.BLACK);
        hdrPanel = new JPanel(new BorderLayout());
        hdrPanel.add(lblHead, BorderLayout.CENTER);
        hdrPanel.setBackground(Color.LIGHT_GRAY);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        lblSearch = new JLabel("User ID:");
        lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textfSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnSearch.addActionListener(this);
        searchPanel.add(lblSearch);
        searchPanel.add(textfSearch);
        searchPanel.add(btnSearch);
        hdrPanel.add(searchPanel, BorderLayout.SOUTH);

        model = new DefaultTableModel(columnHeader, 0);
        table = new JTable(model);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setFillsViewportHeight(true);

        tblHeader = table.getTableHeader();
        tblHeader.setBackground(Color.BLACK);
        tblHeader.setForeground(Color.WHITE);
        tblHeader.setFont(new Font("Times New Roman", Font.BOLD, 13));
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 1));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnLogout = new JButton("Logout Account");
        btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnLogout.setBackground(Color.BLACK);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(this);
        buttonPanel.add(btnLogout);

        tblinfo.add(hdrPanel, BorderLayout.NORTH);
        tblinfo.add(scrollPane, BorderLayout.CENTER);
        tblinfo.add(buttonPanel, BorderLayout.SOUTH);

        tblinfo.getContentPane().setBackground(Color.WHITE);
        tblinfo.setVisible(true);

        displayData(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you really want to Logout?");
            if (choice == JOptionPane.YES_OPTION) {
                tblinfo.dispose();
                new Login();
            }
        } else if (e.getSource() == btnSearch) {
            String searchQuery = textfSearch.getText().trim();
            if (!searchQuery.isEmpty()) {
                searchData(searchQuery);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter user ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
           
        
    }

    private void displayData(DefaultTableModel model) {
        String query = "SELECT * FROM tbl_cite";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String[] rowData = new String[columnHeader.length];
                for (int i = 0; i < columnHeader.length; i++) {
                    rowData[i] = rs.getString(i + 1);
                }
                model.addRow(rowData);
            }
            table.setBackground(new Color(255, 236, 178));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchData(String studentNumber) {
        String query = "SELECT * FROM tbl_cite WHERE stud_ID LIKE '%" + studentNumber + "%'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                String message =
                    "Student ID: " + rs.getString(1) + "\n" +
                    "Last Name: " + rs.getString(2) + "\n" +
                    "First Name: " + rs.getString(3) + "\n" +
                    "Middle Name: " + rs.getString(4) + "\n" +
                    "Course: " + rs.getString(5) + "\n" +
                    "Year: " + rs.getString(6) + "\n" +
                    "Address: " + rs.getString(7) + "\n" +
                    "Contact Number: " + rs.getString(8) + "\n" +
                    "Birthday: " + rs.getString(9) + "\n" +
                    "Position: " + rs.getString(10) + "\n" +
                    "Affiliation: " + rs.getString(11);
                JOptionPane.showMessageDialog(this, message, "Student Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.", "Student Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


   
    }


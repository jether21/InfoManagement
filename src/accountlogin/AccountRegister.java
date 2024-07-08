package accountlogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class AccountRegister extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel header, footer, contentPanel;
    private JButton btnSubmit, btnLogin;
    private JTextField fldUser, fldMname, fldLname, fldPhnum, fldEmail, fldPass, fldAddress, fldCourse, fldYr, fldPos, fldOrg, fldBday;
    private JLabel lblHead, lblWelcome, lblUser, lblPass, lblStudnum, lblAdd, lblPhnum, lblLN, lblMN, lblFN, lblCourse, lblyl, lblBd, lblPos, lblOrg, lblLogo2;
    private ImageIcon Logo2;

    public AccountRegister() {
        frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Header Panel
        header = new JPanel();
        header.setBackground(new Color(204, 0, 0));
        header.setPreferredSize(new Dimension(1000, 80));
        frame.add(header, BorderLayout.NORTH);

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(255, 255, 255));
        frame.add(contentPanel, BorderLayout.CENTER);

        Logo2 = new ImageIcon("PUPBC_Logo.png");
        Image ResizedLogo = Logo2.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon FinalLogo = new ImageIcon(ResizedLogo);
        lblLogo2 = new JLabel(FinalLogo);
        lblLogo2.setBounds(35, 20, 120, 120);
        contentPanel.add(lblLogo2);

        lblHead = new JLabel("Polytechnic University of the Philippines");
        lblHead.setBounds(220, 10, 700, 50);
        lblHead.setFont(new Font("Bookman Old Style", Font.BOLD, 24));
        lblHead.setForeground(Color.WHITE);
        header.add(lblHead);

        lblWelcome = new JLabel("Registration Info");
        lblWelcome.setBounds(400, 90, 200, 30);
        lblWelcome.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        lblWelcome.setForeground(Color.BLACK);
        contentPanel.add(lblWelcome);

        // Labels and TextFields
        int labelX = 50, labelWidth = 140, labelHeight = 30;
        int fieldX = 190, fieldWidth = 200, fieldHeight = 30;
        int yOffset = 50;

        lblFN = new JLabel("First Name:");
        lblFN.setBounds(labelX, 150, labelWidth, labelHeight);
        lblFN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblFN);

        fldUser = new JTextField();
        fldUser.setBounds(fieldX, 150, fieldWidth, fieldHeight);
        contentPanel.add(fldUser);

        lblMN = new JLabel("Middle Name:");
        lblMN.setBounds(labelX, 150 + yOffset, labelWidth, labelHeight);
        lblMN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblMN);

        fldMname = new JTextField();
        fldMname.setBounds(fieldX, 150 + yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldMname);

        lblLN = new JLabel("Last Name:");
        lblLN.setBounds(labelX, 150 + 2 * yOffset, labelWidth, labelHeight);
        lblLN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblLN);

        fldLname = new JTextField();
        fldLname.setBounds(fieldX, 150 + 2 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldLname);

        lblPhnum = new JLabel("Contact No.");
        lblPhnum.setBounds(labelX, 150 + 3 * yOffset, labelWidth, labelHeight);
        lblPhnum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblPhnum);

        fldPhnum = new JTextField();
        fldPhnum.setBounds(fieldX, 150 + 3 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldPhnum);

        lblStudnum = new JLabel("Student No.");
        lblStudnum.setBounds(labelX, 150 + 4 * yOffset, labelWidth, labelHeight);
        lblStudnum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblStudnum);

        fldEmail = new JTextField();
        fldEmail.setBounds(fieldX, 150 + 4 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldEmail);

        lblPass = new JLabel("Password:");
        lblPass.setBounds(labelX, 150 + 5 * yOffset, labelWidth, labelHeight);
        lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblPass);

        fldPass = new JTextField();
        fldPass.setBounds(fieldX, 150 + 5 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldPass);

        lblAdd = new JLabel("Address:");
        lblAdd.setBounds(labelX, 150 + 6 * yOffset, labelWidth, labelHeight);
        lblAdd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblAdd);

        fldAddress = new JTextField();
        fldAddress.setBounds(fieldX, 150 + 6 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldAddress);

        lblCourse = new JLabel("Course:");
        lblCourse.setBounds(520, 150, labelWidth, labelHeight);
        lblCourse.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblCourse);

        fldCourse = new JTextField();
        fldCourse.setBounds(640, 150, fieldWidth, fieldHeight);
        contentPanel.add(fldCourse);

        lblyl = new JLabel("Year Lvl:");
        lblyl.setBounds(520, 150 + yOffset, labelWidth, labelHeight);
        lblyl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblyl);

        fldYr = new JTextField();
        fldYr.setBounds(640, 150 + yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldYr);

        lblBd = new JLabel("Birthday:");
        lblBd.setBounds(520, 150 + 2 * yOffset, labelWidth, labelHeight);
        lblBd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblBd);

        fldBday = new JTextField();
        fldBday.setBounds(640, 150 + 2 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldBday);

        lblPos = new JLabel("Position:");
        lblPos.setBounds(520, 150 + 3 * yOffset, labelWidth, labelHeight);
        lblPos.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblPos);

        fldPos = new JTextField();
        fldPos.setBounds(640, 150 + 3 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldPos);

        lblOrg = new JLabel("Organization:");
        lblOrg.setBounds(500, 150 + 4 * yOffset, labelWidth + 30, labelHeight);
        lblOrg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contentPanel.add(lblOrg);

        fldOrg = new JTextField();
        fldOrg.setBounds(640, 150 + 4 * yOffset, fieldWidth, fieldHeight);
        contentPanel.add(fldOrg);

        btnSubmit = new JButton("Register Now");
        btnSubmit.setBounds(750, 500, 170, 40);
        btnSubmit.setBackground(Color.RED);
        btnSubmit.setForeground(Color.WHITE);
        contentPanel.add(btnSubmit);
        btnSubmit.addActionListener(this);

        btnLogin = new JButton("Back to Login");
        btnLogin.setBounds(550, 500, 170, 40);
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.WHITE);
        contentPanel.add(btnLogin);
        btnLogin.addActionListener(this);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            frame.dispose();
            new Login();
        } else if (e.getSource() == btnSubmit) {
            register();
        }
    }

    private void register() {
        String firstName = fldUser.getText();
        String middleName = fldMname.getText();
        String lastName = fldLname.getText();
        String contactNum = fldPhnum.getText();
        String studentNum = fldEmail.getText();
        String password = fldPass.getText();
        String address = fldAddress.getText();
        String course = fldCourse.getText();
        String yearLevel = fldYr.getText();
        String birthday = fldBday.getText(); 
        String position = fldPos.getText();
        String organization = fldOrg.getText();

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || contactNum.isEmpty() || studentNum.isEmpty() || password.isEmpty() || address.isEmpty() || course.isEmpty() || yearLevel.isEmpty() || birthday.isEmpty() || position.isEmpty() || organization.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Incorrect Inputs.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "INSERT INTO `tbl_cite` (`stud_ID`,`stud_LastName`,`stud_FirstName`,`stud_MiddleName`,`stud_Course`,`stud_Year`,`stud_Address`,`stud_ContactNum`,`stud_Birthday`,`stud_Position`,`stud_Organization`)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, studentNum);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setString(4, middleName);
            ps.setString(5, course);
            ps.setString(6, yearLevel);
            ps.setString(7, address);
            ps.setString(8, contactNum);
            ps.setString(9, birthday);
            ps.setString(10, position);
            ps.setString(11, organization);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registered Successfully");

            fldUser.setText("");
            fldMname.setText("");
            fldLname.setText("");
            fldPhnum.setText("");
            fldEmail.setText("");
            fldPass.setText("");
            fldAddress.setText("");
            fldCourse.setText("");
            fldYr.setText("");
            fldBday.setText(""); 
            fldPos.setText("");
            fldOrg.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}

package accountlogin;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Login implements ActionListener {
    private JFrame frame;
    private JPanel head, footer;
    private JButton btnLogin, btnRegis, btnReset;
    private JLabel lblHead, lblWelcome, lblUser, lblPass, lblNoAcc, lblResults, lblLogo;
    private JTextField fldUser;
    private JPasswordField fldPass;        
    private JCheckBox showPass;
    private Connection conn;
    private int logattempt = 2;
    private long cdend = 5000;
    private final long cd_duration = 5000; 
    private Timer cooldownTimer;
    private JLabel lblCooldown;
    private ImageIcon Logo;

    public Login() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        frame = new JFrame("PUPBC");
        frame.setSize(1000, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        Logo = new ImageIcon("PUPBC_Logo.png");
        Image ResizedLogo = Logo.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        ImageIcon FinalLogo = new ImageIcon(ResizedLogo);
        lblLogo = new JLabel(FinalLogo);
        lblLogo.setBounds(35,80,100,100);
        frame.add(lblLogo);
        
        head = new JPanel();
        head.setBounds(0, 0, 1000, 80);
        head.setBackground(Color.YELLOW);
        frame.add(head);

        lblHead = new JLabel("Polytechnic University of the Philippines");
        lblHead.setFont(new Font("Bookman Old Style", Font.BOLD, 24));
        lblHead.setForeground(Color.RED);
        head.add(lblHead);

        footer = new JPanel();
        footer.setBounds(0, 570, 1000, 100);
        footer.setBackground(Color.YELLOW);
        frame.add(footer);

        lblWelcome = new JLabel("Welcome!");
        lblWelcome.setBounds(380, 120, 250, 30);
        lblWelcome.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        lblWelcome.setForeground(Color.BLACK);
        frame.add(lblWelcome);

        lblUser = new JLabel("Student ID:");
        lblUser.setBounds(200, 200, 200, 30);
        lblUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblUser.setForeground(Color.RED);
        frame.add(lblUser);

        fldUser = new JTextField();
        fldUser.setBounds(350, 200, 200, 30);
        frame.add(fldUser);

        lblPass = new JLabel("Password:");
        lblPass.setBounds(200, 300, 200, 30);
        lblPass.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblPass.setForeground(Color.RED);
        frame.add(lblPass); 

        fldPass = new JPasswordField();
        fldPass.setBounds(350, 300, 200, 30);
        frame.add(fldPass);

        btnLogin = new JButton("Sign In");
        btnLogin.setBounds(550, 400, 170, 40);
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.WHITE);
        frame.add(btnLogin);
        btnLogin.addActionListener(this);

        lblNoAcc = new JLabel("Don't have an account?");
        lblNoAcc.setBounds(555, 450, 250, 30);
        lblNoAcc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNoAcc.setForeground(Color.BLACK);
        frame.add(lblNoAcc);

        btnRegis = new JButton("Register");
        btnRegis.setBounds(550, 500, 170, 40);
        btnRegis.setBackground(Color.RED);
        btnRegis.setForeground(Color.WHITE);
        frame.add(btnRegis);
        btnRegis.addActionListener(e -> {
            frame.dispose();
            new AccountRegister();
        });

        showPass = new JCheckBox("Show Password");
        showPass.setBounds(350, 340, 150, 30);
        showPass.setBackground(Color.WHITE);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) {
                fldPass.setEchoChar((char) 0);
            } else {
                fldPass.setEchoChar('*');
            }
        });
        frame.add(showPass);

        btnReset = new JButton("Reset");
        btnReset.setBounds(200, 400, 100, 40);
        btnReset.setBackground(Color.DARK_GRAY);
        btnReset.setForeground(Color.WHITE);
        frame.add(btnReset);
        btnReset.addActionListener(e -> {
            fldUser.setText("");
            fldPass.setText("");
        });

        lblResults = new JLabel("");
        lblResults.setBounds(200, 360, 200, 30);
        lblResults.setFont(new Font("Arial", Font.PLAIN, 12));
        lblResults.setForeground(new Color(250, 20, 50));
        frame.add(lblResults);

        lblCooldown = new JLabel("");
        lblCooldown.setBounds(200, 440, 200, 30);
        lblCooldown.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCooldown.setForeground(new Color(250, 20, 50));
        frame.add(lblCooldown);

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            if (System.currentTimeMillis() < cdend) {
                JOptionPane.showMessageDialog(frame, "Please wait for the cooldown period.", "Cooldown", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String userID = fldUser.getText().trim();
            String password = new String(fldPass.getPassword());

            if (userID.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter User ID and Password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT * FROM tbl_user WHERE userID = ? AND PASSWORD = ?";

            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, userID);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    logattempt = 1;
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    Loading load = new Loading();
                    load.setVisible(true);
                    load.setResizable(false);
                } else {
                    logattempt++;
                    if (logattempt >= 3) {
                        cdend = System.currentTimeMillis() + cd_duration;
                        JOptionPane.showMessageDialog(frame, "Too many failed attempts. Please wait for the cooldown period.", "Cooldown", JOptionPane.WARNING_MESSAGE);
                        lblCooldown.setText("Cooldown period active.");
                        startCooldownTimer();
                        btnLogin.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid User ID or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database query error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void startCooldownTimer() {
        cooldownTimer = new Timer(1000, e -> {
            long remaining = cdend - System.currentTimeMillis();
            if (remaining <= 0) {
                lblCooldown.setText("");
                btnLogin.setEnabled(true);
                cooldownTimer.stop();
            } else {
                lblCooldown.setText("Cooldown active: " + (remaining / 1000) + " seconds remaining.");
            }
        });
        cooldownTimer.start();
    }

    
}

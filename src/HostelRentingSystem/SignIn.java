package HostelRentingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class SignIn extends JDialog {
    private JTextField txtPhone;
    private JPasswordField txtPass;
    private JButton btnSignIn;
    private JLabel lblRegister;
    SqlQuery sqlquery = new SqlQuery();
    String[] queryData = new String[3];

    public SignIn(String route, String ownername, String roomno, int price, String ownerPhone, String roomId) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        setTitle("");
        setBounds(600, 200, 400, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(150, 20, 100, 100);
        ImageIcon icon = new ImageIcon(getClass().getResource("/login.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        panel.add(lblLogo);

        JLabel lblTitle = new JLabel("Sign In");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(150, 120, 100, 30);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitle);

        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(50, 170, 300, 20);
        panel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(50, 190, 300, 30);
        panel.add(txtPhone);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50, 230, 300, 20);
        panel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(50, 250, 300, 30);
        panel.add(txtPass);

        btnSignIn = new JButton("Log In");
        btnSignIn.setFont(new Font("Arial", Font.BOLD, 16));
        btnSignIn.setBounds(50, 300, 300, 40);
        btnSignIn.setBackground(new Color(0, 120, 215));
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setFocusPainted(false);
        btnSignIn.setBorder(BorderFactory.createEmptyBorder());
        btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnSignIn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSignIn.setBackground(new Color(0, 100, 190));
            }
            public void mouseExited(MouseEvent e) {
                btnSignIn.setBackground(new Color(0, 120, 215));
            }
        });

        panel.add(btnSignIn);

        lblRegister = new JLabel("No account? Sign up");
        lblRegister.setForeground(Color.BLUE.darker());
        lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegister.setBounds(125, 360, 150, 30);
        lblRegister.setHorizontalAlignment(SwingConstants.CENTER);

        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblRegister.setForeground(new Color(100, 100, 255));
                new RegisterType().setVisible(true);
                dispose();
            }
            public void mouseEntered(MouseEvent e) {
                lblRegister.setForeground(new Color(100, 100, 255));
            }
            public void mouseExited(MouseEvent e) {
                lblRegister.setForeground(Color.BLUE.darker());
            }
        });

        panel.add(lblRegister);
        getContentPane().add(panel);

        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phoneno = txtPhone.getText().trim();
                String password = new String(txtPass.getPassword()).trim();

                if (phoneno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your phone number.");
                    txtPhone.requestFocus();
                    return;
                }

                if (!Checking.IsPhoneNo(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number should be at least 6 number");
                    txtPhone.requestFocus();
                    return;
                }
                if (Checking.IsLetter(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number must be Numbers");
                    txtPhone.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your password.");
                    txtPass.requestFocus();
                    return;
                }

                if (password.length() <= 8 || password.length()>=20) {
                    JOptionPane.showMessageDialog(null, "Password must be at betweem 8 and 20 characters.");
                    txtPass.requestFocus();
                    return;
                }
                if (!Checking.IsPassNo(txtPass.getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid password format.");
                    txtPass.requestFocus();
                    return;
                }
                queryData = sqlquery.getUserInfo(phoneno, password);

                if (queryData[0] != null && queryData[1] != null) {
                    if (queryData[2].equals("2")) {
                        if (route.equals("rent")) {
                            new Renting(queryData[4], ownername, roomno, price, phoneno, ownerPhone, roomId).setVisible(true);
                        } else {
                            String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
                            if (querySeeker[0] == null) {
                                JOptionPane.showMessageDialog(null, "Please rent a hostel to view your profile.");
                            } else {
                                new Seeker(phoneno, password).setVisible(true);
                            }
                        }
                        setVisible(false);
                    } else if (queryData[2].equals("3")) {
                        new HostelRegistration(queryData[3]).setVisible(true);
                        setVisible(false);
                    } else if (queryData[2].equals("1")) {
                        new Admin().setVisible(true);
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found. Please sign up.");
                    txtPhone.requestFocus();
                    txtPhone.selectAll();
                    txtPass.selectAll();
                }
            }
        });
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            new SignIn("", "", "", 0, "", "").setVisible(true);
//        });
//    }
}

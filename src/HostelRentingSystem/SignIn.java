package HostelRentingSystem;

import javax.swing.*;

import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class SignIn extends JDialog {
    private JTextField txtPhone;
    private JPasswordField txtPass;
    private JButton btnSignIn;
    private JLabel lblRegister;
    private ImageIcon eyeOpen;
    private ImageIcon eyeClosed;
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
        txtPhone.setBounds(50, 190, 275, 30);
        PromptSupport.setPrompt("Enter your Phone Number(e.g.0754312/09777777777)", txtPhone);
		PromptSupport.setForeground(Color.GRAY, txtPhone);
        panel.add(txtPhone);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50, 230, 300, 20);
        panel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(50, 250, 275, 30);
        PromptSupport.setPrompt("Enter your Password(e.g.kyaw12345@)", txtPass);
		PromptSupport.setForeground(Color.GRAY, txtPass);
        panel.add(txtPass);
        
     // Load Eye Icons
         eyeOpen = new ImageIcon(getClass().getResource("/eye-open.png"));   // 👁 Show Icon
         eyeClosed = new ImageIcon(getClass().getResource("/eye-closed.png")); // ❌ Hide Icon

        // Scale Icons
        Image eyeOpenImg = eyeOpen.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image eyeClosedImg = eyeClosed.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        eyeOpen = new ImageIcon(eyeOpenImg);
        eyeClosed = new ImageIcon(eyeClosedImg);
        
     // Toggle Button
        JButton btnToggle = new JButton(eyeClosed);
        btnToggle.setBounds(330, 250, 30, 30);
        btnToggle.setBorder(null);
        btnToggle.setContentAreaFilled(false);
        panel.add(btnToggle);

        // Store Default Echo Char
        char defaultEchoChar = txtPass.getEchoChar();

        // Toggle Logic
        btnToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtPass.getEchoChar() != (char) 0) {
                    txtPass.setEchoChar((char) 0);  // Show password
                    btnToggle.setIcon(eyeOpen);
                } else {
                    txtPass.setEchoChar(defaultEchoChar);  // Hide password
                    btnToggle.setIcon(eyeClosed);
                }
            }
        });

        //for btn signin
        btnSignIn = new JButton("Log In");
        btnSignIn.setFont(new Font("Arial", Font.BOLD, 16));
        btnSignIn.setBounds(89, 300, 202, 40);
        btnSignIn.setBackground(new Color(0, 120, 215));
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setFocusPainted(false);
        //btnSignIn.setBorder(BorderFactory.createEmptyBorder());
        btnSignIn.setBorderPainted(false);
        btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnSignIn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	//btnSignIn.setBackground(new Color(0, 100, 190));
                btnSignIn.setBackground(Color.decode("#f0f0f0"));
                btnSignIn.setForeground(new Color(0, 120, 215));
            }
            public void mouseExited(MouseEvent e) {
                btnSignIn.setBackground(new Color(0, 120, 215));
                btnSignIn.setForeground(Color.WHITE);
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
                if (!Checking.IsAllDigit(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number must be Numbers");
                    txtPhone.requestFocus();
                    return;
                }
                if (!Checking.IsPhoneNo(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number should be between 6 and 11 numbers");
                    txtPhone.requestFocus();
                    return;
                }
                
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your password.");
                    txtPass.requestFocus();
                    return;
                }

                if (password.length() <= 8 || password.length()>=20) {
                    JOptionPane.showMessageDialog(null, "Password must be at between 8 and 20 characters.");
                    txtPass.requestFocus();
                    return;
                }
                if (!Checking.IsPassNo(txtPass.getText())) {
                    JOptionPane.showMessageDialog(null, "Your password should be at least 8, 1 number,1 uppercase,1 lowercase and 1 special character");
                    txtPass.requestFocus();
                    return;
                }
                queryData = sqlquery.getUserInfo(phoneno, password);

                if (queryData[0] != null && queryData[1] != null) {
                    if (queryData[2].equals("2")) { // role seeker
                    	
                    	//for room renting login
                        if (route.equals("rent")) {
                            String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
                        	if(querySeeker[0] != null && ("Pending".equals(querySeeker[9])||"Accept".equals(querySeeker[9]))) {
                        		int result = 0;
                        		if("Pending".equals(querySeeker[9])){
                        			result = JOptionPane.showConfirmDialog(
                        		            null, 
                        		            "Your renting room is currently pending. You want to view your room list?",
                        		            "Pending Status",
                        		            JOptionPane.OK_CANCEL_OPTION,
                        		            JOptionPane.INFORMATION_MESSAGE
                        		        );
                        			
                        			  if (result == JOptionPane.OK_OPTION) {
                      		            System.out.println("User clicked OK.");
                      		            String userId = sqlquery.getUserId(phoneno);
//                      		        	UserHostelList userhostellist;
//                      					try {
//                      						userhostellist = new UserHostelList(userId,phoneno,password);
//                      						userhostellist.setVisible(true);
//                      					} catch (SQLException e1) {
//                      						// TODO Auto-generated catch block
//                      						e1.printStackTrace();
//                      					}
                      		          NoRoomUserHostelList noroomuserhostellist;
                    					try {
                    						noroomuserhostellist = new NoRoomUserHostelList(userId);
                    						noroomuserhostellist.setVisible(true);
                    						dispose();
                    					} catch (SQLException e1) {
                    						// TODO Auto-generated catch block
                    						e1.printStackTrace();
                    					}
                      		        } else {
                      		            System.out.println("User clicked Cancel or closed the dialog.");
                      		        }
                        	  	}else if("Accept".equals(querySeeker[9])) {
                        	  		result = JOptionPane.showConfirmDialog(
                        		            null, 
                        		            "Your are currently renting can't rent other room. You want to view your room",
                        		            "Pending Status",
                        		            JOptionPane.OK_CANCEL_OPTION,
                        		            JOptionPane.INFORMATION_MESSAGE
                        		        );
                        	  		
                        	  	  if (result == JOptionPane.OK_OPTION) {
                  		            System.out.println("User clicked OK.");
                  		            dispose();
                  		        	 new Seeker(phoneno, password).setVisible(true);

	                  		        } else {
	                  		            System.out.println("User clicked Cancel or closed the dialog.");
	                  		        }
                        	  	}
                    		      
                    		        return;
                        	}
                            new Renting(queryData[4], ownername, roomno, price, phoneno, ownerPhone, roomId).setVisible(true);
                        } 
                        //for direct login 
                        
                        else {
                            String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
                            
                            if (querySeeker[0] == null) {
                                JOptionPane.showMessageDialog(null, "Please rent a hostel to view your profile.");
                            } else if ("Pending".equals(querySeeker[9])) {
                            	int result = JOptionPane.showConfirmDialog(
                    		            null, 
                    		            "Your renting room is currently pending. You want to view your room list?",
                    		            "Pending Status",
                    		            JOptionPane.OK_CANCEL_OPTION,
                    		            JOptionPane.INFORMATION_MESSAGE
                    		        );
                    		        if (result == JOptionPane.OK_OPTION) {
                    		            System.out.println("User clicked OK.");
                    		            String userId = sqlquery.getUserId(phoneno);
//                    		        	UserHostelList userhostellist;
//                    					try {
//                    						userhostellist = new UserHostelList(userId,phoneno, password);
//                    						userhostellist.setVisible(true);
//                    					} catch (SQLException e1) {
//                    						// TODO Auto-generated catch block
//                    						e1.printStackTrace();
//                    					}
                    		            
                    		            NoRoomUserHostelList noroomuserhostellist;
                    					try {
                    						noroomuserhostellist = new NoRoomUserHostelList(userId);
                    						noroomuserhostellist.setVisible(true);
                    					} catch (SQLException e1) {
                    						// TODO Auto-generated catch block
                    						e1.printStackTrace();
                    					}
                    		        } else {
                    		            System.out.println("User clicked Cancel or closed the dialog.");
                    		        }
                            } 
                            else if("Accept".equals(querySeeker[9])) {
                            	new Seeker(phoneno, password).setVisible(true);
                            }
                            
                            else {
                            	String userId = sqlquery.getUserId(phoneno);
                                Boolean reverseRoom = sqlquery.getReverseData(userId,querySeeker[10],querySeeker[6]);
                                
                                System.out.println("user id is "+userId);
                                System.out.println("room id is "+querySeeker[10]);
                                System.out.println("end date is"+querySeeker[6]);
                            	if (reverseRoom) {
                                   	int result = JOptionPane.showConfirmDialog(
                        		            null, 
                        		            "Your not renting any room. You want to view your room list?",
                        		            "No Renting Room",
                        		            JOptionPane.OK_CANCEL_OPTION,
                        		            JOptionPane.INFORMATION_MESSAGE
                        		        );
                        		        if (result == JOptionPane.OK_OPTION) {
                        		            System.out.println("User clicked OK.");
//                        		            String userId = sqlquery.getUserId(phoneno);
//                        		        	UserHostelList userhostellist;
//                        					try {
//                        						userhostellist = new UserHostelList(userId,phoneno, password);
//                        						userhostellist.setVisible(true);
//                        					} catch (SQLException e1) {
//                        						// TODO Auto-generated catch block
//                        						e1.printStackTrace();
//                        					}
                        		            NoRoomUserHostelList noroomuserhostellist;
                        					try {
                        						noroomuserhostellist = new NoRoomUserHostelList(userId);
                        						noroomuserhostellist.setVisible(true);
                        					} catch (SQLException e1) {
                        						// TODO Auto-generated catch block
                        						e1.printStackTrace();
                        					}
                        		            
                        		        } else {
                        		            System.out.println("User clicked Cancel or closed the dialog.");
                        		        }
                            	} else {
                                    new Seeker(phoneno, password).setVisible(true);
                            	}
                            }
                        }
                        setVisible(false);
                    } else if (queryData[2].equals("3")) { // role owner
                        new HostelRegistration(queryData[3]).setVisible(true);
                        setVisible(false);
                    } else if (queryData[2].equals("1")) { // role administrator
                        new Admin(queryData[3]).setVisible(true);
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Phone Number or Password !");
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

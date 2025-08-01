package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SignIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPhone;
	private JPasswordField txtPass;
	private JLabel lblphoneno;
	private JLabel lblpass;
	private JButton btnSingin;
	private JLabel lbllogo;
	private JLabel lblregistertyep;
	private JLabel noaccount;
	private JPanel panel;
	SqlQuery sqlquery = new SqlQuery();
	String[] queryData = new String[3];

	/**
	 * Create the dialog.
	 */
	public SignIn(String route,String ownername,String roomno,int price,String ownerPhone,String roomId) {
		setTitle("");
		setBounds(500, 120, 324, 382);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		//panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sign In Form", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 318, 349);
		contentPanel.add(panel);
		panel.setLayout(null);
				
		//for img logo
		//for img resized
		Image imglogo=new ImageIcon(this.getClass().getResource("/login.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedlogo = new ImageIcon(imglogo);
        //log for image
        lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon(imglogo));
		lbllogo.setBounds(125, 14, 57, 44);
		panel.add(lbllogo);	

		//for phone number 
		
		lblphoneno = new JLabel("Phone no");
		lblphoneno.setBackground(new Color(102, 255, 0));
		lblphoneno.setFont(new Font("Arial", Font.PLAIN, 18));
		lblphoneno.setForeground(new Color(10,255,0));
		lblphoneno.setBounds(21, 102, 95, 36);
		panel.add(lblphoneno);
		
		//for password
		lblpass = new JLabel("Password");
		lblpass.setFont(new Font("Arial", Font.PLAIN, 18));
		lblpass.setForeground(new Color(10,255,0));
		lblpass.setBounds(21, 152, 109, 36);
		panel.add(lblpass);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(117, 109, 161, 29);
		panel.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(117, 159, 161, 29);
		panel.add(txtPass);
		
		//  Default colors
				Color defaultBg = new Color(10, 255, 0);
				Color defaultFg = new Color(255, 255, 255);

				//  Hover colors
				Color hoverBg = new Color(230, 230, 250);
				Color hoverFg = new Color(10, 255, 0);
			
		
		//for Sign In button
		btnSingin = new JButton("Log in");
		btnSingin.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSingin.setOpaque(true);                      // allow background painting
		btnSingin.setContentAreaFilled(true);           // MUST be true to fill background
		btnSingin.setBorderPainted(false);              // optional: remove border
		btnSingin.setFocusPainted(false);               // optional: remove focus outline
		btnSingin.setBackground(new Color(10, 255, 0));  // green
		btnSingin.setForeground(new Color(255, 255, 255));
		
		btnSingin.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnSingin.setBackground(hoverBg);    // Change background
		    	btnSingin.setForeground(hoverFg);    // Change text color
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnSingin.setBackground(defaultBg);  // Reset background
		    	btnSingin.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnSingin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Checking.IsNull(txtPhone.getText()) || Checking.IsLetter(txtPhone.getText()) || !Checking.IsPhoneNo(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Phone Number");
						txtPhone.requestFocus();
						txtPhone.selectAll();
					} else if(!Checking.IsPassNo(txtPass.getText())) {			
						JOptionPane.showMessageDialog(null, "Your password should be at least 8");
						txtPass.requestFocus();
						txtPass.selectAll();				
					} else {
					String phoneno = txtPhone.getText();
					String password = txtPass.getText();
					queryData = sqlquery.getUserInfo(phoneno,password);
					
					System.out.println(queryData[0]+"\n"+queryData[1]+"\n"+queryData[2]);
					if(queryData[0] != null && queryData[1] != null) {
						System.out.println("User data already exist");
						if(queryData[2].equals("2")) {
							if(route.equals("rent")) {
								Renting renting = new Renting(queryData[4],ownername,roomno,price,phoneno,ownerPhone,roomId);
								renting.setVisible(true);
							} else {
								String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
								if(querySeeker[0] == null) {
									JOptionPane.showMessageDialog(null, "Please Rent Hostel to see your profile");
								} else {
									Seeker seeker = new Seeker(phoneno,password);
									seeker.setVisible(true);
								}
								
							}
							setVisible(false);
						} else if(queryData[2].equals("3")) {
							//send owner user id to hostel
							HostelRegistration register = new HostelRegistration(queryData[3]);
							register.setVisible(true);
							setVisible(false);
						} else if(queryData[2].equals("1")) {
							//JOptionPane.showMessageDialog(null, "Admin Panel");
							System.out.println("Admin Panel");
							Admin admin = new Admin();
							admin.setVisible(true);
							
							setVisible(false);
						}
					} else {
						System.out.print(phoneno);
						System.out.print(password);
						System.out.print(queryData[0]);
						System.out.print(queryData[1]);
						JOptionPane.showMessageDialog(null, "Please Sign Up");
						txtPhone.requestFocus();
						txtPass.requestFocus();
						txtPhone.selectAll();
						txtPass.selectAll();		
					}
				}
			}
		});
		btnSingin.setBounds(52, 226, 204, 36);		
		panel.add(btnSingin);
		
		//for Registertype(Sign up)		
		JLabel noaccount= new JLabel("No account?");
		noaccount.setForeground(Color.LIGHT_GRAY);
		noaccount.setBounds(65, 271, 75, 30);
		panel.add(noaccount);
		
		JLabel lblregistertype = new JLabel("<html><span style='color:#0AFF00; text-decoration:none; cursor:pointer;'>Sign up</span>");
		lblregistertype.setCursor(new Cursor(Cursor.HAND_CURSOR));				
		lblregistertype.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Open Sign Up form
		        RegisterType signUp = new RegisterType(); // your class name
		        signUp.setVisible(true);
		        dispose();
 
		    }
		    public void mouseEntered(MouseEvent e) {
				lblregistertype.setText("<html><span style='color:#ccff99; text-decoration:none;'>Sign up</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblregistertype.setText("<html><span style='color:#0AFF00; text-decoration:none;'>Sign up</span></html>");
			}	
		});

		lblregistertype.setBounds(150, 271, 50, 30); // adjust position
		panel.add(lblregistertype);		
		
	}

	private int EtchedBorder(Font font) {
		// TODO Auto-generated method stub
		return 0;
	}
}

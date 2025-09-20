package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPhone;
	private JPasswordField txtPass;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnSingin;
	private JLabel lbllogo;
	private JPanel panel;
	SqlQuery sqlquery = new SqlQuery();
	String[] queryData = new String[3];

	/**
	 * Create the dialog.
	 */
	public SignIn(String route,String ownername,String roomno,int price,String ownerPhone,String roomId) {
		setTitle("Sign In");
		setBounds(400, 120, 600, 500);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sign In Form", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 564, 439);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Phone No");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		//lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setBounds(95, 170, 89, 23);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(95, 216, 109, 36);
		panel.add(lblNewLabel_1);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(312, 164, 215, 29);
		panel.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(312, 222, 215, 29);
		panel.add(txtPass);
		
		btnSingin = new JButton("Sign In");
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
		btnSingin.setBounds(235, 297, 97, 36);
		panel.add(btnSingin);
		
		lbllogo = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lbllogo.setIcon(new ImageIcon(img));
		lbllogo.setBounds(249, 48, 65, 66);
		panel.add(lbllogo);	
		
	}

	private int EtchedBorder(Font font) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

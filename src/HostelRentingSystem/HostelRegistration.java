package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import com.formdev.flatlaf.FlatIntelliJLaf;
import HostelRentingSystem.Checking;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import org.jdesktop.swingx.prompt.PromptSupport;

public class HostelRegistration extends JDialog {
	private JTextField txtHostelName;
	private JTextField txtBuildingNo;
	private JTextField txtMainRoom;
	private JTextField txtRoomCount;
	private JTextField txtState;
	private JTextField txtCity;
	private JTextField txtStreet;
	private JComboBox cboGender;
	SqlQuery sqlquery = new SqlQuery();
	//private SqlQuery sqlquery = new SqlQuery();
	
	/**
	 * Create the dialog.
	 */
	public HostelRegistration(String userId) {
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Hostel Registration Form");
		setBounds(50, 50, 1200, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNew = new JLabel("Hostel Name");
		lblNew.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew.setForeground(Color.decode("#3f3b3b"));
		lblNew.setBounds(277, 37, 102, 30);
		getContentPane().add(lblNew);
		
		JLabel lblBuildingNo = new JLabel("Building No:");
		lblBuildingNo.setFont(new Font("Arial", Font.BOLD, 15));
		lblBuildingNo.setForeground(Color.decode("#3f3b3b"));
		lblBuildingNo.setBounds(277, 95, 102, 30);
		getContentPane().add(lblBuildingNo);
		
		JLabel lblMainRoomno = new JLabel("Main RoomNo:");
		lblMainRoomno.setFont(new Font("Arial", Font.BOLD, 15));
		lblMainRoomno.setForeground(Color.decode("#3f3b3b"));
		lblMainRoomno.setBounds(277, 147, 143, 30);
		getContentPane().add(lblMainRoomno);
		
		JLabel lblSmallRoomCount = new JLabel("Small Room Count");
		lblSmallRoomCount.setFont(new Font("Arial", Font.BOLD, 15));
		lblSmallRoomCount.setForeground(Color.decode("#3f3b3b"));
		lblSmallRoomCount.setBounds(277, 198, 143, 30);
		getContentPane().add(lblSmallRoomCount);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Arial", Font.BOLD, 15));
		lblState.setForeground(Color.decode("#3f3b3b"));
		lblState.setBounds(277, 249, 102, 30);
		getContentPane().add(lblState);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Arial", Font.BOLD, 15));
		lblCity.setForeground(Color.decode("#3f3b3b"));
		lblCity.setBounds(277, 300, 102, 30);
		getContentPane().add(lblCity);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setFont(new Font("Arial", Font.BOLD, 15));
		lblStreet.setForeground(Color.decode("#3f3b3b"));
		lblStreet.setBounds(277, 358, 102, 30);
		getContentPane().add(lblStreet);
		
		JLabel lblGenderType = new JLabel("Gender Type");
		lblGenderType.setFont(new Font("Arial", Font.BOLD, 15));
		lblGenderType.setForeground(Color.decode("#3f3b3b"));
		lblGenderType.setBounds(277, 418, 102, 30);
		getContentPane().add(lblGenderType);
		

		txtHostelName = new JTextField();
		txtHostelName.setBounds(537, 36, 357, 37);
		getContentPane().add(txtHostelName);
		//PromptSupport.setPrompt("Enter Hostel Name you will rent(e.g.Pan Cheery)", txtHostelName);
		//PromptSupport.setForeground(Color.GRAY, txtHostelName);
		txtHostelName.setColumns(10);
		
		txtBuildingNo = new JTextField();
		txtBuildingNo.setColumns(10);
		txtBuildingNo.setBounds(537, 94, 357, 37);
		PromptSupport.setPrompt("Enter Building No. you will rent.(e.g.No-7)", txtBuildingNo);
		PromptSupport.setForeground(Color.GRAY, txtBuildingNo);
		getContentPane().add(txtBuildingNo);
		
		txtMainRoom = new JTextField();
		txtMainRoom.setColumns(10);
		txtMainRoom.setBounds(537, 146, 357, 37);
		PromptSupport.setPrompt("Enter MainRoom No. you will rent.(e.g.102 / A11)", txtMainRoom);
		PromptSupport.setForeground(Color.GRAY, txtMainRoom);
		getContentPane().add(txtMainRoom);
		
		txtRoomCount = new JTextField();
		txtRoomCount.setColumns(10);
		txtRoomCount.setBounds(537, 197, 357, 37);
		PromptSupport.setPrompt("Enter the number of rooms you will rent(e.g.1)", txtRoomCount);
		PromptSupport.setForeground(Color.GRAY, txtRoomCount);
		getContentPane().add(txtRoomCount);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(537, 248, 357, 37);
		PromptSupport.setPrompt("Enter State of the hostel your renting(e.g.Yangon)", txtState);
		PromptSupport.setForeground(Color.GRAY, txtState);
		getContentPane().add(txtState);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(537, 299, 357, 37);
		txtCity.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Enter City of the hostel your renting(e.g.Kamayut)", txtCity);
		PromptSupport.setForeground(Color.GRAY, txtCity);
		getContentPane().add(txtCity);
		
		txtStreet = new JTextField();
		txtStreet.setColumns(10);
		txtStreet.setBounds(537, 357, 357, 37);
		PromptSupport.setPrompt("Enter Street of the hostel your renting(e.g.SanYeikNyein)", txtStreet);
		PromptSupport.setForeground(Color.GRAY, txtStreet);
		getContentPane().add(txtStreet);
		
		cboGender = new JComboBox();
		cboGender.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Male", "Female"}));
		cboGender.setBounds(537, 417, 357, 37);
		getContentPane().add(cboGender);
		
		//for image label
		ImageIcon icon = new ImageIcon(getClass().getResource("/User-Profile.png"));
		Image img = icon.getImage().getScaledInstance(173, 170, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		JLabel lbluserimg = new JLabel(icon);
		
		lbluserimg.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				UserUpdateForm userallinfo;
				
					userallinfo = new UserUpdateForm(userId);
					userallinfo.setVisible(true);
					//dispose();
				
			}
		});
		lbluserimg.setBounds(22,63,173,171);
		getContentPane().add(lbluserimg);
		
		//for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;

		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
		
		//For btn Next
		JButton btnNext = new JButton("Next");
		btnNext.setBackground( new Color(0, 120, 215));  // green
		btnNext.setForeground(Color.WHITE);
		
		btnNext.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnNext.setBackground(hoverBg);    // Change background
		    	btnNext.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnNext.setBackground(defaultBg);  // Reset background
		    	btnNext.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[5];
				arr[0] = txtState.getText();
				arr[1] = txtCity.getText();
				arr[2] = txtStreet.getText();
				arr[3] = txtBuildingNo.getText();
				arr[4] = txtMainRoom.getText();
				
				boolean duplicate = sqlquery.isDuplicateRoomno(arr);
				
				if(Checking.IsNull(txtHostelName.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Hostel Name");
					txtHostelName.requestFocus();
					txtHostelName.selectAll();
				} else if(Checking.IsNull(txtBuildingNo.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Building No:");
					txtBuildingNo.requestFocus();
					txtBuildingNo.selectAll();
				} else if(Checking.IsNull(txtMainRoom.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Main Room No:");
					txtMainRoom.requestFocus();
					txtMainRoom.selectAll();
				} else if(Checking.IsNull(txtRoomCount.getText()) || !Checking.IsAllDigit(txtRoomCount.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Room Count");
					txtRoomCount.requestFocus();
					txtRoomCount.selectAll();
				} else if(Integer.parseInt(txtRoomCount.getText())==0|| Integer.parseInt(txtRoomCount.getText()) > 5 ) {
					JOptionPane.showMessageDialog(null, "Room count should be between 1 and 5");
					txtRoomCount.requestFocus();
					txtRoomCount.selectAll();
				} else if(Checking.IsNull(txtState.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid State Name");
					txtState.requestFocus();
					txtState.selectAll();
				} else if(!Checking.IsLetter(txtState.getText())) {
					JOptionPane.showMessageDialog(null, "State should be only words");
					txtState.requestFocus();
					txtState.selectAll();
				} else if(Checking.IsNull(txtCity.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid City Name");
					txtCity.requestFocus();
					txtCity.selectAll();
				} else if(!Checking.IsLetter(txtCity.getText())) {
					JOptionPane.showMessageDialog(null, "City should be only words");
					txtCity.requestFocus();
					txtCity.selectAll();
				} else if(Checking.IsNull(txtStreet.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Street Name");
					txtStreet.requestFocus();
					txtStreet.selectAll();
				} else if(cboGender.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "You must Choose Gender Type");
					cboGender.requestFocus();
				} else if(duplicate) {
					JOptionPane.showMessageDialog(null, "Hostel is already exist in this address!!");
				} else {
					String hostelName = txtHostelName.getText();
					String buildingNo = txtBuildingNo.getText();
					String roomNo = txtMainRoom.getText();
					String roomCount = txtRoomCount.getText();
					String state = txtState.getText();
					String city = txtCity.getText();
					String street = txtStreet.getText();
					String gender = (String) cboGender.getSelectedItem();
					new RoomRegistration(hostelName,buildingNo,roomNo,roomCount,state,city,street,gender,userId);
					//clear();
					//setVisible(false);
				} 
			}
		});
		btnNext.setBounds(448, 490, 114, 39);
		getContentPane().add(btnNext);
		
		
		
		
		//For btn Clear
		JButton btnCancel = new JButton("Clear");
		btnCancel.setBackground( new Color(0, 120, 215));  // green
		btnCancel.setForeground(Color.WHITE);
		
		btnCancel.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnCancel.setBackground(hoverBg);    // Change background
		    	btnCancel.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnCancel.setBackground(defaultBg);  // Reset background
		    	btnCancel.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(702, 490, 114, 39);
		getContentPane().add(btnCancel);
		
		//For btn Logout
		JButton btnClose = new JButton("Logout");
		btnClose.setBackground( new Color(0, 120, 215));  // green
		btnClose.setForeground(Color.WHITE);
		
		btnClose.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnClose.setBackground(hoverBg);    // Change background
		    	btnClose.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnClose.setBackground(defaultBg);  // Reset background
		    	btnClose.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to LogOut?","Confirm Exist",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnClose.setBounds(102, 490, 114, 39);
		getContentPane().add(btnClose);
		
		
		//For btn HostList
		JButton btnSkip = new JButton("HostelList");
		btnSkip.setBackground( new Color(0, 120, 215));  // green
		btnSkip.setForeground(Color.WHITE);
		
		btnSkip.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnSkip.setBackground(hoverBg);    // Change background
		    	btnSkip.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnSkip.setBackground(defaultBg);  // Reset background
		    	btnSkip.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = new Owner(userId);
				owner.setVisible(true);
				dispose();
			}
		});
		btnSkip.setBounds(1003, 490, 102, 39);
		getContentPane().add(btnSkip);
		
		

		// Load data from DB
        String[] userData = sqlquery.getUserallInfo(userId);
		//for User Name
		JLabel lblNewLabel = new JLabel(userData[0]);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.decode("#085f63"));
		lblNewLabel.setBounds(64, 248, 163, 30);
		getContentPane().add(lblNewLabel);
	}
	
	public void clear() {
		txtHostelName.setText("");
		txtBuildingNo.setText("");
		txtMainRoom.setText("");
		txtRoomCount.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtStreet.setText("");
		cboGender.setSelectedIndex(0);
	}
}

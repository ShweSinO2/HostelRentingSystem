package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import HostelRentingSystem.Checking;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	/**
	 * Create the dialog.
	 */
	public HostelRegistration(String userId) {
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Hostel Registration Form");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNew = new JLabel("Hostel Name");
		lblNew.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew.setBounds(45, 36, 102, 30);
		getContentPane().add(lblNew);
		
		JLabel lblBuildingNo = new JLabel("Building No:");
		lblBuildingNo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuildingNo.setBounds(45, 90, 102, 30);
		getContentPane().add(lblBuildingNo);
		
		JLabel lblMainRoomno = new JLabel("Main RoomNo:");
		lblMainRoomno.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMainRoomno.setBounds(45, 147, 102, 30);
		getContentPane().add(lblMainRoomno);
		
		JLabel lblSmallRoomCount = new JLabel("Small Room Count");
		lblSmallRoomCount.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSmallRoomCount.setBounds(41, 205, 143, 30);
		getContentPane().add(lblSmallRoomCount);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Arial", Font.PLAIN, 15));
		lblState.setBounds(45, 260, 102, 30);
		getContentPane().add(lblState);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCity.setBounds(45, 316, 102, 30);
		getContentPane().add(lblCity);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStreet.setBounds(45, 364, 102, 30);
		getContentPane().add(lblStreet);
		
		JLabel lblGenderType = new JLabel("Gender Type");
		lblGenderType.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGenderType.setBounds(45, 418, 102, 30);
		getContentPane().add(lblGenderType);
		

		txtHostelName = new JTextField();
		txtHostelName.setBounds(261, 40, 357, 37);
		getContentPane().add(txtHostelName);
		txtHostelName.setColumns(10);
		
		txtBuildingNo = new JTextField();
		txtBuildingNo.setColumns(10);
		txtBuildingNo.setBounds(261, 97, 357, 37);
		getContentPane().add(txtBuildingNo);
		
		txtMainRoom = new JTextField();
		txtMainRoom.setColumns(10);
		txtMainRoom.setBounds(261, 145, 357, 37);
		getContentPane().add(txtMainRoom);
		
		txtRoomCount = new JTextField();
		txtRoomCount.setColumns(10);
		txtRoomCount.setBounds(261, 198, 357, 37);
		getContentPane().add(txtRoomCount);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(261, 247, 357, 37);
		getContentPane().add(txtState);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(261, 300, 357, 37);
		getContentPane().add(txtCity);
		
		txtStreet = new JTextField();
		txtStreet.setColumns(10);
		txtStreet.setBounds(261, 357, 357, 37);
		getContentPane().add(txtStreet);
		
		System.out.println("User ID in Hostel Registration=> " + userId);
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[5];
				arr[0] = txtState.getText();
				arr[1] = txtCity.getText();
				arr[2] = txtStreet.getText();
				arr[3] = txtBuildingNo.getText();
				arr[4] = txtMainRoom.getText();
				
				boolean duplicate = sqlquery.isDuplicateRoomno(arr);
				System.out.println("Room Duplicate => "+duplicate);
				
				if(Checking.IsNull(txtHostelName.getText()) || !Checking.IsLetter(txtHostelName.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Hostel Name");
					txtHostelName.requestFocus();
					txtHostelName.selectAll();
				} else if(Checking.IsNull(txtBuildingNo.getText()) || Checking.IsLetter(txtBuildingNo.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Building No:");
					txtBuildingNo.requestFocus();
					txtBuildingNo.selectAll();
				} else if(Checking.IsNull(txtMainRoom.getText()) || Checking.IsLetter(txtMainRoom.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid Main Room No:");
					txtMainRoom.requestFocus();
					txtMainRoom.selectAll();
				} else if(Checking.IsNull(txtRoomCount.getText()) || Checking.IsLetter(txtRoomCount.getText()) || Integer.parseInt(txtRoomCount.getText())==0) {
					JOptionPane.showMessageDialog(null, "You must enter valid Room Count");
					txtRoomCount.requestFocus();
					txtRoomCount.selectAll();
				} else if(Checking.IsNull(txtState.getText()) || !Checking.IsLetter(txtState.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid State Name");
					txtState.requestFocus();
					txtState.selectAll();
				} else if(Checking.IsNull(txtCity.getText()) || !Checking.IsLetter(txtCity.getText())) {
					JOptionPane.showMessageDialog(null, "You must enter valid City Name");
					txtCity.requestFocus();
					txtCity.selectAll();
				} else if(Checking.IsNull(txtStreet.getText()) || !Checking.IsLetter(txtStreet.getText())) {
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
					clear();
					//setVisible(false);
				} 
			}
		});
		btnNext.setBounds(212, 480, 102, 42);
		getContentPane().add(btnNext);
		
		
		cboGender = new JComboBox();
		cboGender.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Male", "Female"}));
		cboGender.setBounds(261, 418, 357, 37);
		getContentPane().add(cboGender);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(382, 480, 102, 42);
		getContentPane().add(btnCancel);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnClose.setBounds(45, 480, 102, 42);
		getContentPane().add(btnClose);
		
		JButton btnSkip = new JButton("Skip");
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner owner = new Owner(userId);
				owner.setVisible(true);
			}
		});
		btnSkip.setBounds(553, 480, 102, 42);
		getContentPane().add(btnSkip);
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

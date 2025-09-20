package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import HostelRentingSystem.Checking;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HostelDetail extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);

	/**
	 * Create the dialog.
	 */
	public HostelDetail(String hostelName,String roomno,String address,String genderType,int price,String ownerName,String ownerPhone,String roomId) {
		System.out.println("Data => "+hostelName+roomno+address+genderType+price+ownerName+ownerPhone);
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Hostel Detail Form");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNew = new JLabel("Owner Name");
		lblNew.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew.setBounds(43, 90, 102, 30);
		getContentPane().add(lblNew);
		
		JLabel lblNew_1 = new JLabel("Owner PhoneNo");
		lblNew_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_1.setBounds(43, 157, 143, 30);
		getContentPane().add(lblNew_1);
		
		JLabel lblNew_2 = new JLabel("Room No");
		lblNew_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2.setBounds(43, 214, 102, 30);
		getContentPane().add(lblNew_2);
		
		JLabel lblNew_3 = new JLabel("Price");
		lblNew_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_3.setBounds(43, 276, 143, 30);
		getContentPane().add(lblNew_3);
		
		JLabel lblNew_4 = new JLabel("Address");
		lblNew_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_4.setBounds(43, 344, 102, 30);
		getContentPane().add(lblNew_4);
		
		JLabel lblGenderType = new JLabel("Gender Type");
		lblGenderType.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGenderType.setBounds(43, 412, 102, 30);
		getContentPane().add(lblGenderType);
		
		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signin = new SignIn("rent",ownerName,roomno,price,ownerPhone,roomId);
				signin.setVisible(true);
				setVisible(false);
			}
		});
		btnRent.setBounds(408, 488, 102, 42);
		getContentPane().add(btnRent);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnClose.setBounds(165, 488, 102, 42);
		getContentPane().add(btnClose);
		
		JLabel lblOwnerName = new JLabel(ownerName);
		lblOwnerName.setBounds(280, 91, 357, 30);
		lblOwnerName.setBorder(blackline);
		getContentPane().add(lblOwnerName);
		
		JLabel lblPhoneNo = new JLabel(ownerPhone);
		lblPhoneNo.setBorder(blackline);
		lblPhoneNo.setBounds(280, 150, 357, 37);
		getContentPane().add(lblPhoneNo);
		
		JLabel lblRoomNo = new JLabel(roomno);
		lblRoomNo.setBorder(blackline);
		lblRoomNo.setBounds(280, 215, 357, 30);
		getContentPane().add(lblRoomNo);
		
		JLabel lblPrice = new JLabel(price+"");
		lblPrice.setBorder(blackline);
		lblPrice.setBounds(280, 275, 357, 35);
		getContentPane().add(lblPrice);
		
		JLabel lblAddress = new JLabel(address);
		lblAddress.setBorder(blackline);
		lblAddress.setBounds(280, 339, 357, 42);
		getContentPane().add(lblAddress);
		
		JLabel lblGender = new JLabel(genderType);
		lblGender.setBorder(blackline);
		lblGender.setBounds(280, 413, 357, 30);
		getContentPane().add(lblGender);
		
		JLabel lblHostelName = new JLabel(hostelName);
		lblHostelName.setBorder(blackline);
		lblHostelName.setBounds(280, 35, 357, 30);
		getContentPane().add(lblHostelName);
		
		JLabel lblHostelName_1 = new JLabel("Hostel Name");
		lblHostelName_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblHostelName_1.setBounds(43, 34, 102, 30);
		getContentPane().add(lblHostelName_1);
	}
}

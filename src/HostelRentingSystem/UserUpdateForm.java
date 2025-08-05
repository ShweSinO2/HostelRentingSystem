package HostelRentingSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUpdateForm extends JFrame {
	
    private JTextField txtName, txtPhone, txtState, txtCity, txtStreet, txtNRCno;
    private JPasswordField txtPass;
    private JComboBox cboCode, cboCity, cboNation;
    private JRadioButton rdoMale, rdoFemale;
    private JButton btnUpdate, btnCancel;
    private String gender, nrc;
    private SqlQuery sqlquery = new SqlQuery();
   private Map<Integer, List<String>> map = new HashMap<>();
    private ArrayList<String> listCode = new ArrayList<>();
    private CityShortName shortName = new CityShortName();

    public UserUpdateForm(String userid) {
    	
    	try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
    	
        setTitle("Update Profile");
        setBounds(350, 50, 700, 616);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 0, 664, 561);
        panel.setBorder(new TitledBorder("Update Form"));
        getContentPane().add(panel);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(22, 61, 100, 20);
        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(213, 58, 424, 31);
        panel.add(txtName);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(22, 109, 100, 20);
        panel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(213, 103, 424, 31);
        panel.add(txtPhone);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(22, 160, 100, 20);
        panel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(213, 154, 424, 31);
        panel.add(txtPass);

        JLabel lblNrc = new JLabel("NRC:");
        lblNrc.setBounds(22, 216, 100, 20);
        panel.add(lblNrc);

        cboCode = new JComboBox(new String[] {"", "1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/", "9/", "10/", "11/", "12/", "13/", "14/"});
        cboCode.setBounds(214, 208, 82, 31);
        cboCode.addActionListener(e -> fillCode());
        panel.add(cboCode);

        cboCity = new JComboBox();
        cboCity.setBounds(320, 208, 95, 31);
        panel.add(cboCity);

        cboNation = new JComboBox(new String[] {"", "N", "E", "P"});
        cboNation.setBounds(439, 208, 93, 31);
        panel.add(cboNation);

        txtNRCno = new JTextField();
        txtNRCno.setBounds(542, 210, 95, 31);
        panel.add(txtNRCno);

        JLabel lblState = new JLabel("State:");
        lblState.setBounds(22, 268, 100, 20);
        panel.add(lblState);

        txtState = new JTextField();
        txtState.setBounds(213, 262, 424, 31);
        panel.add(txtState);

        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(22, 319, 100, 20);
        panel.add(lblCity);

        txtCity = new JTextField();
        txtCity.setBounds(213, 316, 424, 31);
        panel.add(txtCity);

        JLabel lblStreet = new JLabel("Street:");
        lblStreet.setBounds(22, 382, 100, 20);
        panel.add(lblStreet);

        txtStreet = new JTextField();
        txtStreet.setBounds(213, 376, 424, 31);
        panel.add(txtStreet);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(22, 432, 100, 20);
        panel.add(lblGender);

        rdoMale = new JRadioButton("Male");
        rdoMale.setBounds(259, 430, 82, 23);
        panel.add(rdoMale);

        rdoFemale = new JRadioButton("Female");
        rdoFemale.setBounds(465, 430, 82, 23);
        panel.add(rdoFemale);

        ButtonGroup group = new ButtonGroup();
        group.add(rdoMale);
        group.add(rdoFemale);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(305, 492, 97, 36);
        panel.add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(493, 492, 97, 36);
        btnCancel.addActionListener(e -> {
        	Seeker userallinfo;
			
			userallinfo = new Seeker(txtPhone.getText(),txtPass.getText());
			userallinfo.setVisible(true);
        });
        panel.add(btnCancel);

        // Load data from DB
        String[] userData = sqlquery.getUserallInfo(userid);
        txtName.setText(userData[0]);
        txtPhone.setText(userData[1]);
        txtPass.setText(userData[6]);
        txtState.setText(userData[3]);
        txtCity.setText(userData[4]);
        txtStreet.setText(userData[5]);

        if (userData[7].equalsIgnoreCase("Male")) rdoMale.setSelected(true);
        else rdoFemale.setSelected(true);
        
//        if (userData.length >= 9) {
//            if (userData[7].equalsIgnoreCase("Male")) rdoMale.setSelected(true);
//            else rdoFemale.setSelected(true);
//        }
//        
//        System.out.println("userData length = " + userData.length);
//        System.out.println(Arrays.toString(userData));



        // Split NRC into parts (e.g., 1/YGN(N)123456)
        String fullNRC = userData[2];
        try {
            String code = fullNRC.substring(0, fullNRC.indexOf('/')) + "/";
            String city = fullNRC.substring(fullNRC.indexOf('/') + 1, fullNRC.indexOf('('));
            String nation = fullNRC.substring(fullNRC.indexOf('(') + 1, fullNRC.indexOf(')'));
            String number = fullNRC.substring(fullNRC.indexOf(')') + 1);
            cboCode.setSelectedItem(code);
            fillCode();
            cboCity.setSelectedItem(city);
            cboNation.setSelectedItem(nation);
            txtNRCno.setText(number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnUpdate.addActionListener(e -> {
        	boolean duplicate = sqlquery.isPhonenoDuplicateProfile(txtPhone.getText(),userid);
			boolean duplicateNrc = sqlquery.isNrcDuplicateProfile(cboCode.getSelectedItem().toString(),cboCity.getSelectedItem().toString(),cboNation.getSelectedItem().toString(),txtNRCno.getText(),userid);
			
			if(Checking.IsNull(txtName.getText()) || Checking.IsValidName(txtName.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid Name");
				txtName.requestFocus();
				txtName.selectAll();
			}
			else if(Checking.IsNull(txtPhone.getText()) || Checking.IsLetter(txtPhone.getText()) || !Checking.IsPhoneNo(txtPhone.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid Phone Number");
				txtPhone.requestFocus();
				txtPhone.selectAll();
			}
			else if(!Checking.IsPassNo(txtPass.getText()))
			{
				JOptionPane.showMessageDialog(null, "Your password should be at least 8, 1 number,1 uppercase,1 lowercase and 1 special caracter ");
				txtPass.requestFocus();
				txtPass.selectAll();					
			}
			else if(Checking.IsNull(txtState.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid State");
				txtState.requestFocus();
				txtState.selectAll();
			}
			else if(Checking.IsNull(txtCity.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid City");
				txtCity.requestFocus();
				txtCity.selectAll();
			}
			else if(Checking.IsNull(txtStreet.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid Street");
				txtStreet.requestFocus();
				txtStreet.selectAll();
			}
			else if(cboCode.getSelectedIndex() == 0)
			{
				JOptionPane.showMessageDialog(null, "You must choose Code for NRC");
				cboCode.requestFocus();
			}
			else if(cboCity.getSelectedIndex() == 0)
			{
				JOptionPane.showMessageDialog(null, "You must choose City for NRC");
				cboCity.requestFocus();					
			}
			else if(cboNation.getSelectedIndex() == 0)
			{
				JOptionPane.showMessageDialog(null, "You must choose Nation for NRC");
				cboNation.requestFocus();
				
			}				
			else if(Checking.IsNull(txtNRCno.getText()) || !Checking.IsNrc(txtNRCno.getText()) || !Checking.IsAllDigit(txtNRCno.getText()))
			{
				JOptionPane.showMessageDialog(null, "You must enter valid NRC Code");
				txtNRCno.requestFocus();
				txtNRCno.selectAll();
			} else if(duplicate)
			{
				JOptionPane.showMessageDialog(null, "Registration PhoneNo is already exist!!");	
			} else if(duplicateNrc) {
				JOptionPane.showMessageDialog(null, "Registration NRC is already exist!!");	
			} 
			else {
				String nrcStr = cboCode.getSelectedItem() +""+ cboCity.getSelectedItem() + "(" + cboNation.getSelectedItem() + ")" + txtNRCno.getText();
	            String[] updatedData = new String[8];
	            updatedData[0] = txtName.getText();
	            updatedData[1] = txtPhone.getText();
	            updatedData[2] = nrcStr;
	            updatedData[3] = txtState.getText();
	            updatedData[4] = txtCity.getText();
	            updatedData[5] = txtStreet.getText();
	            updatedData[6] = new String(txtPass.getPassword());
	            updatedData[7] = rdoMale.isSelected() ? "Male" : "Female";

	            if (sqlquery.updateUser(userid, updatedData)) {
	                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(this, "Update failed. Try again.");
	            }
	            
	            }	
//            String nrcStr = cboCode.getSelectedItem() +""+ cboCity.getSelectedItem() + "(" + cboNation.getSelectedItem() + ")" + txtNRCno.getText();
//            String[] updatedData = new String[8];
//            updatedData[0] = txtName.getText();
//            updatedData[1] = txtPhone.getText();
//            updatedData[2] = nrcStr;
//            updatedData[3] = txtState.getText();
//            updatedData[4] = txtCity.getText();
//            updatedData[5] = txtStreet.getText();
//            updatedData[6] = new String(txtPass.getPassword());
//            updatedData[7] = rdoMale.isSelected() ? "Male" : "Female";
//
//            if (sqlquery.updateUser(userid, updatedData)) {
//                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "Update failed. Try again.");
//            }
        });
    }

    public void fillCode() {
        map = shortName.getCityCode();
        int index = cboCode.getSelectedIndex();
        if (index > 0) {
            listCode = (ArrayList<String>) map.get(index);
            cboCity.removeAllItems();
            for (String item : listCode) cboCity.addItem(item);
        }
    }
//    public static void main(String[] args) {
//    	/// TODO Auto-generated method stub
//    	UserUpdateForm up = new UserUpdateForm("24");
//    			up.setVisible(true);
//	}
 
} 


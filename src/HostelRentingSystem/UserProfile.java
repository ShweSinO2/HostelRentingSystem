package HostelRentingSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class UserProfile extends JDialog {
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblPassowrd;
	private JLabel lblNrc;
	private JLabel lblState;
	private JLabel lblCity;
	private JLabel lblStreet;
	private JLabel lblGender;
	private JLabel getName;
	private JLabel getPhoneno;
	private JLabel getPassword;
	private JLabel getNrc;
	private JLabel getStreet;
	private JLabel getState;
	private JLabel getCity;
	private JLabel getGender;
	//private JLabel getName;
	private JPanel panel; 
	
	Border blackline=BorderFactory.createLineBorder(Color.black);
	SqlQuery sqlquery = new SqlQuery();
	
	public UserProfile(String userid) {
		
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		System.out.print(userid);
		setTitle("");
		setBounds(350, 50, 700, 616);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		//int userid = id;
		panel = new JPanel();
		panel.setForeground(new Color(0, 191, 255));
		panel.setBackground(new Color(255,255,255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Update Profile", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20), new Color(135, 206, 235)));
		panel.setBounds(10, 0, 664, 561);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		String[] queryName = sqlquery.getUserallInfo(userid);
		
		
		//for label 
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblName.setForeground(new Color(0, 191, 255));
		lblName.setBounds(22, 61, 46, 21);
		panel.add(lblName);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPhone.setForeground(new Color(0, 191, 255));
		lblPhone.setBounds(22, 109, 46, 14);
		panel.add(lblPhone);
		
		lblPassowrd = new JLabel("Password:");
		lblPassowrd.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassowrd.setForeground(new Color(0, 191, 255));
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPhone.setForeground(new Color(0, 191, 255));
		lblPassowrd.setBounds(22, 160, 82, 14);
		panel.add(lblPassowrd);
		
		lblNrc = new JLabel("NRC:");
		lblNrc.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNrc.setForeground(new Color(0, 191, 255));
		lblNrc.setBounds(22, 216, 46, 14);
		panel.add(lblNrc);
		
		lblState = new JLabel("State:");
		lblState.setFont(new Font("Arial", Font.PLAIN, 15));
		lblState.setForeground(new Color(0, 191, 255));
		lblState.setBounds(22, 268, 46, 14);
		panel.add(lblState);
		
		lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCity.setForeground(new Color(0, 191, 255));
		lblCity.setBounds(22, 319, 46, 21);
		panel.add(lblCity);
		
		lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStreet.setForeground(new Color(0, 191, 255));
		lblStreet.setBounds(22, 382, 46, 14);
		panel.add(lblStreet);
		
		lblGender = new JLabel("Gender:");
		lblGender.setForeground(new Color(0, 191, 255));
		lblGender.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGender.setBounds(22, 432, 82, 14);
		panel.add(lblGender);
		
		getName = new JLabel(queryName[0]);
		getName.setBounds(350,61,150, 30);
		getName.setBorder(blackline);
		panel.add(getName);
		
		System.out.print(queryName[0]);
		
		getPhoneno = new JLabel(queryName[1]);
		getPhoneno.setBounds(350,109,150, 30);
		getPhoneno.setBorder(blackline);
		panel.add(getPhoneno);
		
		getPassword = new JLabel(queryName[6]);
		getPassword.setBounds(350,160,150, 30);
		getPassword.setBorder(blackline);
		panel.add(getPassword);
		
		getNrc = new JLabel(queryName[2]);
		getNrc.setBounds(350,216,150, 30);
		getNrc.setBorder(blackline);
		panel.add(getNrc);
		
		getState = new JLabel(queryName[3]);
		getState.setBounds(350,268,150, 30);
		getState.setBorder(blackline);
		panel.add(getState);
		
		getCity = new JLabel(queryName[4]);
		getCity.setBounds(350,319,150, 30);
		getCity.setBorder(blackline);
		panel.add(getCity);
		
		getStreet = new JLabel(queryName[5]);
		getStreet.setBounds(350,382,150, 30);
		getStreet.setBorder(blackline);
		panel.add(getStreet);
		
		getGender = new JLabel(queryName[7]);
		getGender.setBounds(350,432,150, 30);
		getGender.setBorder(blackline);
		panel.add(getGender);
		
	}

	
	
	
	
	
//	public static void main(String[] args) {
/// TODO Auto-generated method stub
//		UserProfile up = new UserProfile();
//		up.setVisible(true);

//	}

}

package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

import HostelRentingSystem.Hostel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
 
	private JPanel contentPane;
	SqlQuery sqlquery = new SqlQuery();
	String hostelName,roomno,address,genderType,ownerName,ownerPhone,roomId;
	int price;
	private JComboBox comboBox;
	
	JList<Hostel> hostelList;
	DefaultListModel<Hostel> listModel;
	JScrollPane scrollPane;
	ArrayList<Hostel> hostelArrList;
	private JButton btnR;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("Hostel Renting System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 50, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listModel = new DefaultListModel<Hostel>();
		
		hostelList = new JList<>(listModel);
		hostelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    hostelList.setCellRenderer(new ListCellRenderer());
	    
	    hostelList.addListSelectionListener(new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent e) {
	    		if(!e.getValueIsAdjusting()) {
	    			JList<Hostel> list = (JList<Hostel>) e.getSource();
	    			int selectedIndex = list.getSelectedIndex();
	    			//System.out.println("selectedIndex => " + selectedIndex);
	    			//System.out.println("hostelArrList.size() => " +  hostelArrList.size());
	    			if(selectedIndex < hostelArrList.size() && selectedIndex >= 0) {
	    				Hostel selectedHostel = hostelArrList.get(selectedIndex);
	    				hostelName = selectedHostel.getHostelName();
		    			roomno = selectedHostel.getRoomNo();
		    			address = selectedHostel.getAddress();
		    			genderType = selectedHostel.getGenderType();
		    			price = selectedHostel.getPrice();
		    			ownerName = selectedHostel.getOwnerName();
		    			ownerPhone = selectedHostel.getOwnerPhone();
		    			roomId = selectedHostel.getRoomId();
		    		
		    			HostelDetail detail = new HostelDetail(hostelName,roomno,address,genderType,price,ownerName,ownerPhone,roomId);
		    			detail.setVisible(true);
	    			}
	    			
	    				    		
	    		}
	    	}
	    });
	    
		scrollPane = new JScrollPane(hostelList);
		scrollPane.setBounds(10, 45, 664, 505);
		getContentPane().add(scrollPane);
 
		ArrayList<String[]> strQuery = sqlquery.getHostelData();
		bindListView(strQuery);
			
		ImageIcon icon = new ImageIcon(getClass().getResource("/search-icon.png"));
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 7, 322, 27);
		contentPane.add(comboBox);
 
		JButton btnSearch = new JButton(new ImageIcon((icon.getImage()).getScaledInstance(27, 25, java.awt.Image.SCALE_SMOOTH)));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedValue = comboBox.getSelectedItem().toString();
				if(selectedValue.equals("-Selected-")) {
					ArrayList<String[]> strQuery = sqlquery.getHostelData();
					bindListView(strQuery);
				} else {
					ArrayList<String[]> strQuery = sqlquery.getSearchData(selectedValue);				
					bindListView(strQuery);
				}
				
			}
		});
		btnSearch.setBounds(334, 7, 35, 27);
		contentPane.add(btnSearch);
 
		JButton btnSignin = new JButton("Sign In");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signin = new SignIn("","","",0,"","");
				signin.setVisible(true);
			}
		});
		btnSignin.setBounds(490, 9, 87, 25);
		contentPane.add(btnSignin);
 
		JButton btnSignup = new JButton("Sign Up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterType type = new RegisterType();
				type.setVisible(true);
			}
		});
		btnSignup.setBounds(578, 9, 87, 25);
		contentPane.add(btnSignup);
		
		btnR = new JButton("Refresh");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> strQuery = sqlquery.getHostelData();
				bindListView(strQuery);
				comboBox.removeAllItems();
				fillAddress();
			}
		});
		btnR.setBounds(402, 9, 87, 25);
		contentPane.add(btnR);
				
		fillAddress();
	}
	
	public void fillAddress() {
		// TODO Auto-generated method stub
		String[] str = sqlquery.getAddressForChoice("hostel");
		comboBox.addItem("-Selected-");
		for(int i=0;i<str.length;i++) {
			comboBox.addItem(str[i].toString());
		}
	}
	
	public void bindListView(ArrayList<String[]> strQuery) {
		System.out.println("Bind List View => " + strQuery.size());
		
		hostelArrList = new ArrayList<Hostel>();
		
		for(int i=0;i<strQuery.size();i++) {
			Hostel hostel = new Hostel();
			String[] data = strQuery.get(i);
			
			hostel.setHostelName(data[0]);
			hostel.setRoomNo(data[1]);
			hostel.setAddress(data[2]);
			hostel.setPrice(Integer.parseInt(data[3]));
			hostel.setGenderType(data[4]);
			hostel.setOwnerName(data[5]);
			hostel.setOwnerPhone(data[6]);
			hostel.setRoomId(data[7]);
			hostelArrList.add(hostel);
		}
		
		System.out.println("hostelArrList => " + hostelArrList);
		listModel.clear();
		
		for(Hostel hostelData : hostelArrList) {
			listModel.addElement(hostelData);
		}
	}
}




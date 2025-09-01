package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
import javax.swing.UIManager;
import javax.swing.JLabel;

import HostelRentingSystem.Hostel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
 
	private JPanel contentPane;
	SqlQuery sqlquery = new SqlQuery();
	String hostelName,roomno,address,genderType,ownerName,ownerPhone,roomId,imageFileName,imageUrl,description;
	int price;
	private JComboBox comboBox;
	private JScrollPane rulescrollPane;
	private JTextArea txtAgreement;
	
	
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
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }		setTitle("Hostel Renting System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(350, 50, 700, 600);
		setBounds(0, 0, 1700, 745);
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
		    			imageFileName = selectedHostel.getFileName();
		    			imageUrl = selectedHostel.getImageUrl();
		    			description = selectedHostel.getDescription();
		    			
		    		
		    			HostelDetail detail = new HostelDetail(hostelName,roomno,address,genderType,price,ownerName,ownerPhone,roomId,imageFileName,imageUrl,description);
		    			detail.setVisible(true);
	    			}
	    			
	    				    		
	    		}
	    	}
	    });
	    
	    //for system rule
	  //For System Rule
		// ===== Clickable line at bottom =====
		JLabel lineLabel = new JLabel("───── Click here to see System Rule ─────");
		lineLabel.setForeground(Color.BLUE);
		lineLabel.setBounds(1048, 651, 322, 30); // adjust bottom position
		lineLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lineLabel);

//		// ===== Agreement Text Area (hidden initially) =====
//		txtAgreement = new JTextArea();
//		txtAgreement.setText("Agreement Rules:\n"
//		        + "1. Seeker must provide real personal information.\n"
//		        + "2. Seeker must respect hostel property and rules.\n"
//		        + "3. Payments must be made on time.\n"
//		        + "4. Seeker must not misuse system data.\n"
//		        + "5. Please read carefully before proceeding.");
//		txtAgreement.setFont(new Font("Arial", Font.PLAIN, 14));
//		txtAgreement.setEditable(false);
//
//		agreementScroll = new JScrollPane(txtAgreement);
//		agreementScroll.setBounds(1048, 93, 300, 400); // <-- your green box area
//		agreementScroll.setVisible(false);
//		contentPane.add(agreementScroll);
	    
		// ===== Agreement (HTML Styled with ScrollPane) =====
		JEditorPane txtAgreement = new JEditorPane();
		txtAgreement.setContentType("text/html");  // enable HTML rendering
		txtAgreement.setEditable(false);           // read-only
		txtAgreement.setText(
		    "<html>" +
		    		"<h1 style='color:blue; text-align:center;'>System Rules</h1>" +
		    		"<p style='font-size:10px; color:gray;'>'This System is a platform that directly connects owners and seekers.The purpose is to make hostel searching faster and hostel rental advertising easier and simple.The system is not included Direct payment functionality.'</p>" +
		    		
    		        "<p style='font-size:12px; color:blue;'>Payment Responsibility</p>" +
    		        "<p style='color:gray;'>'Direct Payment: When using the system for renting, all payment processes must be handled directly between the hostel owner (Owner) and the renter (Seeker). Our system is solely a platform for communication and is not involved in any payment transactions.'</p>" +
    		        
    		        "<p style='font-size:12px; color:blue;'>Financial Issues</p>" +
    		        "<p style='color:gray;'>'Our system is not responsible for any financial problems that may arise from direct payments between the hostel owner and the renter.'</p>" +
    		        
    		        "<p style='font-size:12px; color:blue;'>Personal Verification of Hostel Information</p>" +
    		        "<p style='color:gray;'>'Responsibility: Before renting a hostel, the renter must personally verify the accuracy of the information provided, including photos and the current condition of the hostel. Our system is not responsible for any issues that arise from incorrect information.'</p>" +
    		        "<p style='color:gray;'>'Our system is not responsible for resolving hostel-related issues (e.g., poor cleanliness, insufficient services) and will not issue refunds.'</p>" +
    		        
    		        "<p style='font-size:12px; color:blue;'>Compliance with Laws</p>" +
    		        "<p style='color:gray;'>'Hostel owners must comply with all local laws, licenses, and regulations. Our system is not responsible for hostels or businesses that are not in compliance with the law.'</p>" +
//    		        "<p style='color:gray;'>'I will clearly and transparently list the room rates, service fees, and other relevant information'</p>" +
    		        
    		        "<p style='font-size:12px; color:blue;'>Data Security</p>" +
    		        "<p style='color:gray;'>'Our system will keep the information secure.'</p>" +
    		        
					"<p style='font-size:12px; color:blue;'>Support Hotline</p>" +
					"<p style='color:gray;'>'While the system will not directly resolution in major issues, we provide a 24-hour hotline for emergencies (e.g., criminal acts or security matters). Through this line, we will guide you on how to connect with the relevant authorities.'</p>" +
					"<p style='font-size:12px; color:red;'>Hotline : 09999999999</p>" +
					
					"<h1 style='color:blue; text-align:center;'>Seeker Rules</h1>" +
    		        "<p style='color:gray;' >'Seekers will not  get for a refund money  if they check out of the room earlier than the end date.'</p>" +
        		    "</html>"
		);
		// force scroll position to top
        txtAgreement.setCaretPosition(0);
        
		// Put inside JScrollPane
		 rulescrollPane = new JScrollPane(
		        txtAgreement,
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);

		// Set position + size
		 rulescrollPane.setBounds(1020, 100, 330, 500);
		 rulescrollPane.setVisible(false);

		// Optional: add border with title
		 rulescrollPane.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.GRAY, 1, true),
		        "Rule and Regulation",
		        TitledBorder.CENTER,
		        TitledBorder.TOP,
		        new Font("Arial", Font.BOLD, 14),
		        Color.BLACK
		));

		// Add to your container
		contentPane.add(rulescrollPane);


		// ===== Click event: show/hide agreement =====
		lineLabel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	rulescrollPane.setVisible(!rulescrollPane.isVisible());
		    }
		});
	    
	    
	    
	    
	    
	    
		scrollPane = new JScrollPane(hostelList);
		//scrollPane.setBounds(10, 45, 664, 505);
		scrollPane.setBounds(10, 45, 1000, 652);
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
		
		
		// for Login btn
		
		JButton btnSignin = new JButton("Login");
		
		btnSignin.setBackground(new Color(0, 120, 215));
		btnSignin.setForeground(Color.WHITE);
		btnSignin.setBorderPainted(false);
		btnSignin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	//btnSignIn.setBackground(new Color(0, 100, 190));
            	btnSignin.setBackground(Color.decode("#f0f0f0"));
            	btnSignin.setForeground(new Color(0, 120, 215));
            }
            public void mouseExited(MouseEvent e) {
            	btnSignin.setBackground(new Color(0, 120, 215));
            	btnSignin.setForeground(Color.WHITE);
            }
        });
        
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signin = new SignIn("","","",0,"","");
				signin.setVisible(true);
			}
		});
		//btnSignin.setBounds(578, 9, 87, 25);
		btnSignin.setBounds(1192, 8, 102, 39);
		contentPane.add(btnSignin);
 
//		JButton btnSignup = new JButton("Sign Up");
//		btnSignup.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				RegisterType type = new RegisterType();
//				type.setVisible(true);
//			}
//		});
//		btnSignup.setBounds(578, 9, 87, 25);
//		btnSignup.setBounds(1214, 8, 87, 25);
//		contentPane.add(btnSignup);
		
		//for Refresh button 
		btnR = new JButton("Refresh");
		btnR.setBorderPainted(false);
		btnR.setBackground(new Color(0, 120, 215));
		btnR.setForeground(Color.WHITE);
		btnR.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	//btnSignIn.setBackground(new Color(0, 100, 190));
            	btnR.setBackground(Color.decode("#f0f0f0"));
            	btnR.setForeground(new Color(0, 120, 215));
            }
            public void mouseExited(MouseEvent e) {
            	btnR.setBackground(new Color(0, 120, 215));
            	btnR.setForeground(Color.WHITE);
            }
        });
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> strQuery = sqlquery.getHostelData();
				bindListView(strQuery);
				comboBox.removeAllItems();
				fillAddress();
			}
		});
		//btnR.setBounds(490, 9, 87, 25);
		btnR.setBounds(1054, 8, 102, 39);
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
			hostel.setFileName(data[8]);
			hostel.setImageUrl(data[9]);
			hostel.setDescription(data[10]);
			hostelArrList.add(hostel);
		}
		
		listModel.clear();
		
		for(Hostel hostelData : hostelArrList) {
			listModel.addElement(hostelData);
		}
	}
}




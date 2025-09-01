package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import java.awt.*;

public class Seeker extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);
	SqlQuery sqlquery = new SqlQuery();

	/**
	 * Create the dialog.
	 */

	public Seeker(String phoneno,String password) {
		getContentPane().setBackground(Color.WHITE);
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		System.out.println("Data in Seeker => "+phoneno+"\n"+password);
		setTitle("Seeker Profile");
		setBounds(50, 50, 1200, 600);
		setResizable(false);
		getContentPane().setLayout(null);

		String[] queryName = sqlquery.getUserInfo(phoneno, password);
		String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
//		String roomId = sqlquery.getRoomId(querySeeker[3]);
		String roomId = querySeeker[10];
//		System.out.println("roomid right is "+ querySeeker[10]);
//		System.out.println("smallroomn no  is "+ querySeeker[3]);
//		System.out.println("roomid is "+ roomId );
		String userId = sqlquery.getUserId(phoneno);
		
		JLabel lblNew_1 = new JLabel("Name");
		lblNew_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_1.setForeground(Color.decode("#3f3b3b"));
		lblNew_1.setBounds(344, 48, 102, 24);
		getContentPane().add(lblNew_1);
			
		JLabel lblName = new JLabel(queryName[4]);
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblName.setBounds(586, 48, 394, 30);
		//lblName.setBorder(blackline);
		getContentPane().add(lblName);
		
		JLabel lblNew_2 = new JLabel("Phone No");
		lblNew_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_2.setForeground(Color.decode("#3f3b3b"));
		lblNew_2.setBounds(344, 94, 102, 30);
		getContentPane().add(lblNew_2);
		
		JLabel lblPhone = new JLabel(phoneno);
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblPhone.setBorder(blackline);
		lblPhone.setBounds(586, 94, 394, 30);
		getContentPane().add(lblPhone);
		
		JLabel lblNew_6 = new JLabel("Hostel Name");
		lblNew_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6.setForeground(Color.decode("#3f3b3b"));
		lblNew_6.setBounds(344, 136, 102, 30);
		getContentPane().add(lblNew_6);
		
		JLabel lblHostelName = new JLabel(querySeeker[0]);
		lblHostelName.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblHostelName.setBorder(blackline);
		lblHostelName.setBounds(586, 136, 394, 30);
		getContentPane().add(lblHostelName);
		
		JLabel lblNew_6_1 = new JLabel("Main Room No");
		lblNew_6_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_1.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_1.setBounds(344, 226, 114, 30);
		getContentPane().add(lblNew_6_1);
		
		JLabel lblNew_6_2 = new JLabel("Address");
		lblNew_6_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_2.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_2.setBounds(344, 314, 102, 30);
		getContentPane().add(lblNew_6_2);
		
		JLabel lblNew_6_3 = new JLabel("Start Date");
		lblNew_6_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_3.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_3.setBounds(344, 358, 102, 30);
		getContentPane().add(lblNew_6_3);
		
		JLabel lblNew_6_4 = new JLabel("End Date");
		lblNew_6_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_4.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_4.setBounds(344, 402, 102, 30);
		getContentPane().add(lblNew_6_4);
		
		JLabel lblNew_6_1_1 = new JLabel("Building No");
		lblNew_6_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_1_1.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_1_1.setBounds(344, 184, 102, 30);
		getContentPane().add(lblNew_6_1_1);
		
		JLabel lblNew_6_1_2 = new JLabel("Room No");
		lblNew_6_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_6_1_2.setForeground(Color.decode("#3f3b3b"));
		lblNew_6_1_2.setBounds(344, 270, 114, 30);
		getContentPane().add(lblNew_6_1_2);
		
		JLabel lblBuildingNo = new JLabel(querySeeker[1]);
		lblBuildingNo.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblBuildingNo.setBorder(blackline);
		lblBuildingNo.setBounds(586, 182, 394, 30);
		getContentPane().add(lblBuildingNo);
		
		JLabel lblRoomNo = new JLabel(querySeeker[3]);
		lblRoomNo.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblRoomNo.setBorder(blackline);
		lblRoomNo.setBounds(586, 270, 394, 30);
		getContentPane().add(lblRoomNo);
		
		JLabel lblAddress = new JLabel(querySeeker[4]);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblAddress.setBorder(blackline);
		lblAddress.setBounds(586, 314, 394, 30);
		getContentPane().add(lblAddress);
		
		JLabel lblStartDate = new JLabel(querySeeker[5]);
		lblStartDate.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblStartDate.setBorder(blackline);
		lblStartDate.setBounds(586, 358, 394, 30);
		getContentPane().add(lblStartDate);
		
		JLabel lblMainRoom = new JLabel(querySeeker[2]);
		lblMainRoom.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblMainRoom.setBorder(blackline);
		lblMainRoom.setBounds(586, 226, 394, 30);
		getContentPane().add(lblMainRoom);
		
		JLabel lblEndDate = new JLabel(querySeeker[6]);
		lblEndDate.setFont(new Font("Arial", Font.PLAIN, 15));
		//lblEndDate.setBorder(blackline);
		lblEndDate.setBounds(586, 402, 394, 30);
		getContentPane().add(lblEndDate);
		
		
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
		
		//for User Name
		JLabel lblNewLabel = new JLabel(queryName[4]);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.decode("#085f63"));
		lblNewLabel.setBounds(64, 248, 163, 30);
		getContentPane().add(lblNewLabel);

		//for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;

		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
		
		
		JButton btnStay = new JButton("Stay Again");
		btnStay.setBackground( new Color(0, 120, 215));  // green
		btnStay.setForeground(Color.WHITE);
		
		btnStay.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnStay.setBackground(hoverBg);    // Change background
		    	btnStay.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnStay.setBackground(defaultBg);  // Reset background
		    	btnStay.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] stayData = sqlquery.getOwnerData(querySeeker[7]);

				Renting renting = new Renting(queryName[4],stayData[0],querySeeker[3],Integer.parseInt(querySeeker[8]),phoneno,stayData[1],roomId);
				renting.setVisible(true);
			}
		});
		btnStay.setBounds(702, 490, 114, 39);
		getContentPane().add(btnStay);
		checkDateAndSetButtonState(btnStay,querySeeker[6]);
		
		
		//For btn hostelList
		JButton btnList = new JButton("Hostel History");
		btnList.setBackground( new Color(0, 120, 215));  // green
		btnList.setForeground(Color.WHITE);
		
		btnList.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnList.setBackground(hoverBg);    // Change background
		    	btnList.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnList.setBackground(defaultBg);  // Reset background
		    	btnList.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserHostelList userhostellist;
				try {
					userhostellist = new UserHostelList(userId,phoneno,password);
					userhostellist.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnList.setBounds(1003, 490, 102, 39);
		getContentPane().add(btnList);
		
		
		//For btn Logout
		JButton btnLogout = new JButton("Log Out");
		btnLogout.setBackground( new Color(0, 120, 215));  // green
		btnLogout.setForeground(Color.WHITE);
		
		btnLogout.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnLogout.setBackground(hoverBg);    // Change background
		    	btnLogout.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnLogout.setBackground(defaultBg);  // Reset background
		    	btnLogout.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to LogOut?","Confirm Exist",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnLogout.setBounds(102, 490, 114, 39);
		getContentPane().add(btnLogout);
		
		
		//For btn Reserve
		JButton btnReserve = new JButton("Reservation");
		btnReserve.setBackground( new Color(0, 120, 215));  // green
		btnReserve.setForeground(Color.WHITE);
		
		btnReserve.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnReserve.setBackground(hoverBg);    // Change background
		    	btnReserve.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnReserve.setBackground(defaultBg);  // Reset background
		    	btnReserve.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				if(JOptionPane.showConfirmDialog(null, "Seekers will not  get for a refund money  if they check out of the room earlier than the end date.","Confirm Reverse",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//					Reversion reserve = new Reversion(lblRoomNo.getText(),lblEndDate.getText(),roomId,userId);
////					sqlquery.updateStatus(status,roomId,bol);
//					sqlquery.updateRentAndRentingDetail(roomId,userId,"Reverse","Accept");
//					reserve.setVisible(true);
//				}
				
				Reversion reserve = new Reversion(lblRoomNo.getText(),lblEndDate.getText(),roomId,userId);
//				sqlquery.updateStatus(status,roomId,bol);
				sqlquery.updateRentAndRentingDetail(roomId,userId,"Reverse","Accept");
				reserve.setVisible(true);
			}
		});
		btnReserve.setBounds(448, 490, 114, 39);
		getContentPane().add(btnReserve);
		
		
		
		
		
//		JButton btnNewButton = new JButton("Acc info");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UserUpdateForm userallinfo;
//				
//					userallinfo = new UserUpdateForm(userId);
//					userallinfo.setVisible(true);
//				
//			}
//		});
//		btnNewButton.setBounds(42, 312, 87, 16);
//		getContentPane().add(btnNewButton);
//		
	
		
		
	}
	
	 private void checkDateAndSetButtonState(JButton myButton, String endDateString) {
		 LocalDate today = LocalDate.now();

		    try {
		        LocalDate localEndDate = LocalDate.parse(endDateString);

		        long daysUntilEnd = ChronoUnit.DAYS.between(today, localEndDate);

		        System.out.println("Days until end date: " + daysUntilEnd);

		        if (daysUntilEnd <= 7 && daysUntilEnd >= 0) {
		            myButton.setEnabled(true);
		            System.out.println("Button is ENABLED.");
		        } else {
		            myButton.setEnabled(false);
		            System.out.println("Button is DISABLED.");
		        }
		    } catch (DateTimeParseException e) {
		        System.err.println("Error: The end date string is not in 'YYYY-MM-DD' format.");
		        myButton.setEnabled(false); // Date format မှားရင် button ကို disable လုပ်ထားမယ်
		    }

	    }
}

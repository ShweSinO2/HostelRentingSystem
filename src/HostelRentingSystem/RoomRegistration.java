package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;


public class RoomRegistration {
	private JTextField txtRoomNo;
	private JTextField txtPrice;
	SqlQuery sqlquery = new SqlQuery();
	ArrayList<JTextField> txtRoomArray = new ArrayList<JTextField>();
	ArrayList<JTextField> txtPriceArray = new ArrayList<JTextField>();
	boolean isValid = false;
	boolean isDuplicate = false;
	
	public RoomRegistration(String hostelName,String buildingNo,String roomNo,String roomCount,String state,String city,String street,String gender,String userId) {
		JFrame frame = new JFrame("Room Registration");
        JPanel panel = new JPanel(new GridLayout(0, 4,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        int count = Integer.parseInt(roomCount);
        
        for(int j=0;j<count;j++) {
        	txtRoomNo = new JTextField();
        	txtPrice = new JTextField();
        	
        	txtRoomArray.add(txtRoomNo);
        	txtPriceArray.add(txtPrice);
        }
        
        for(int i=0;i<count;i++) {
        	JLabel lblNewLabel = new JLabel("Room No:");
    		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    		lblNewLabel.setPreferredSize(new Dimension(140, 30));
    		panel.add(lblNewLabel);
    		
    		
    		panel.add(txtRoomArray.get(i));
    		
    		JLabel lblNewLabel_1 = new JLabel("Price:");
    		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
    		panel.add(lblNewLabel_1);
    		
    		panel.add(txtPriceArray.get(i));
        }
        
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String[] hostelData = new String[9];
					String[] roomData = new String[3];
					
					hostelData[0] = hostelName;
					hostelData[1] = buildingNo;
					hostelData[2] = roomNo;
					hostelData[3] = roomCount;
					hostelData[4] = state;
					hostelData[5] = city;
					hostelData[6] = street;
					hostelData[7] = userId;
					hostelData[8] = gender;
										
					String[][] roomList = new String[count][3];
					// check validation
					for(int k=0;k<count;k++) {
						String roomNo = txtRoomArray.get(k).getText();
						String price = txtPriceArray.get(k).getText();
						System.out.println("roomNo => "+ roomNo + "\tprice" + price);
						
						if(Checking.IsNull(roomNo) || Checking.IsLetter(roomNo)) {
							JOptionPane.showMessageDialog(null, "You must enter valid Room Number");
							isValid = false;
							break;
						} else {
							isValid = true;
						}
						
						if(Checking.IsNull(price) || !Checking.IsAllDigit(price) || Integer.parseInt(price)==0) {
							JOptionPane.showMessageDialog(null, "You must enter valid Price");
							isValid = false;
							break;
						} else if(!Checking.IsValidPrice(price)) {
							JOptionPane.showMessageDialog(null, "Hostel Fee is Expensive!!");
							isValid = false;
							break;
						} else {
							isValid = true;
						}
						
									
					}
					
					System.out.println("Room Validate Outside => "+isValid);
					
					// check duplicate room
					if(isValid) {
						for(int k=0;k<count - 1;k++) {
							String roomNo = txtRoomArray.get(k).getText();
							
							for(int j=k+1;j<count;j++) {
								String nextRoomNo = txtRoomArray.get(j).getText();
								if(roomNo.equals(nextRoomNo)) {
									JOptionPane.showMessageDialog(null, "Duplicate Room Number!!");
									isDuplicate = true;
									break;
								}
								else {
									isDuplicate = false;
								}	
							}
							if(isDuplicate) {
								break;
							}
								
						}
						System.out.println("Room Duplicate Outside => "+isDuplicate);
					}
					
					
					// insert data
					if(isValid && !isDuplicate) {
						System.out.println("Room Validate Inside => "+isValid);
						System.out.println("Room Duplicate Inside => "+isDuplicate);
						
						boolean save = sqlquery.insertData("hostel", hostelData);
						String hostelId = sqlquery.getId("hostel");
						System.out.println("Hostel ID => "+hostelId);
						
						for(int k=0;k<count;k++) {
							String roomNo = txtRoomArray.get(k).getText();
							String price = txtPriceArray.get(k).getText();
							
							String[] room = {roomNo, price, hostelId}; // ["Room No", "Price", "HostelID"]
							roomList[k] = room;  
						}
						
						if(save) {
						// ["Room No", "Price", "HostelID"]
						// String[][] == new 
						// [
						// 	["Room No", "Price", "HostelID"],
						//  ["Room No", "Price", "HostelID"],
						//  ["Room No", "Price", "HostelID"]
						// ]
						
						System.out.println("Room List => "+Arrays.toString(roomList));
						for(String[] data: roomList) {
							sqlquery.insertData("room",data);
						}
						JOptionPane.showMessageDialog(null, "Successfully Saved Room data");
						
						// clean text box
						for(int k=0;k<count;k++) {
							txtRoomArray.get(k).setText("");
							txtPriceArray.get(k).setText("");
						}	
						frame.setVisible(false);
					}
				}	
				
			}
		});
		
        panel.add(btnConfirm);
       
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setBounds(380, 180, 550, 500);
        frame.pack();
        frame.setVisible(true);
	}
	
}


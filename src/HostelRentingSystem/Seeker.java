package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Seeker extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);
	SqlQuery sqlquery = new SqlQuery();

	/**
	 * Create the dialog.
	 */

	public Seeker(String phoneno,String password) {
		System.out.println("Data in Seeker => "+phoneno+"\n"+password);
		setTitle("Seeker Profile");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);

		String[] queryName = sqlquery.getUserInfo(phoneno, password);
		String[] querySeeker = sqlquery.getSeekerProfile(phoneno, password);
		String roomId = sqlquery.getRoomId(querySeeker[3]);
		String userId = sqlquery.getUserId(phoneno);
		
		JLabel lblNew_1 = new JLabel("Name");
		lblNew_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_1.setBounds(48, 54, 102, 24);
		getContentPane().add(lblNew_1);
			
		JLabel lblName = new JLabel(queryName[4]);
		lblName.setBounds(237, 48, 394, 30);
		lblName.setBorder(blackline);
		getContentPane().add(lblName);
		
		JLabel lblNew_2 = new JLabel("Phone No");
		lblNew_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2.setBounds(48, 99, 102, 30);
		getContentPane().add(lblNew_2);
		
		JLabel lblPhone = new JLabel(phoneno);
		lblPhone.setBorder(blackline);
		lblPhone.setBounds(237, 97, 394, 30);
		getContentPane().add(lblPhone);
		
		JLabel lblNew_6 = new JLabel("Hostel Name");
		lblNew_6.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6.setBounds(48, 140, 102, 30);
		getContentPane().add(lblNew_6);
		
		JLabel lblHostelName = new JLabel(querySeeker[0]);
		lblHostelName.setBorder(blackline);
		lblHostelName.setBounds(237, 138, 394, 30);
		getContentPane().add(lblHostelName);
		
		JLabel lblNew_6_1 = new JLabel("Main Room No");
		lblNew_6_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_1.setBounds(48, 232, 114, 30);
		getContentPane().add(lblNew_6_1);
		
		JLabel lblNew_6_2 = new JLabel("Address");
		lblNew_6_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_2.setBounds(48, 314, 102, 30);
		getContentPane().add(lblNew_6_2);
		
		JLabel lblNew_6_3 = new JLabel("Start Date");
		lblNew_6_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_3.setBounds(48, 365, 102, 30);
		getContentPane().add(lblNew_6_3);
		
		JLabel lblNew_6_4 = new JLabel("End Date");
		lblNew_6_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_4.setBounds(48, 413, 102, 30);
		getContentPane().add(lblNew_6_4);
		
		JLabel lblBuildingNo = new JLabel(querySeeker[1]);
		lblBuildingNo.setBorder(blackline);
		lblBuildingNo.setBounds(237, 179, 394, 30);
		getContentPane().add(lblBuildingNo);
		
		JLabel lblRoomNo = new JLabel(querySeeker[3]);
		lblRoomNo.setBorder(blackline);
		lblRoomNo.setBounds(237, 261, 394, 30);
		getContentPane().add(lblRoomNo);
		
		JLabel lblAddress = new JLabel(querySeeker[4]);
		lblAddress.setBorder(blackline);
		lblAddress.setBounds(237, 314, 394, 30);
		getContentPane().add(lblAddress);
		
		JLabel lblStartDate = new JLabel(querySeeker[5]);
		lblStartDate.setBorder(blackline);
		lblStartDate.setBounds(237, 365, 394, 30);
		getContentPane().add(lblStartDate);
		
		JButton btnStay = new JButton("Stay Again");
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] stayData = sqlquery.getOwnerData(querySeeker[7]);

				Renting renting = new Renting(queryName[4],stayData[0],querySeeker[3],Integer.parseInt(querySeeker[8]),phoneno,stayData[1],roomId);
				renting.setVisible(true);
			}
		});
		btnStay.setBounds(287, 490, 114, 39);
		getContentPane().add(btnStay);
		
		JButton btnList = new JButton("Hostel List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserHostelList userhostellist;
				try {
					userhostellist = new UserHostelList(userId);
					userhostellist.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnList.setBounds(529, 490, 102, 39);
		getContentPane().add(btnList);
		
		JLabel lblMainRoom = new JLabel(querySeeker[2]);
		lblMainRoom.setBorder(blackline);
		lblMainRoom.setBounds(237, 220, 394, 30);
		getContentPane().add(lblMainRoom);
		
		JLabel lblEndDate = new JLabel(querySeeker[6]);
		lblEndDate.setBorder(blackline);
		lblEndDate.setBounds(237, 413, 394, 30);
		getContentPane().add(lblEndDate);
		
		JButton btnReserve = new JButton("Reservation");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reversion reserve = new Reversion(lblRoomNo.getText(),lblEndDate.getText(),roomId,userId);
				reserve.setVisible(true);
			}
		});
		btnReserve.setBounds(60, 490, 114, 39);
		getContentPane().add(btnReserve);
		
		JLabel lblNew_6_1_1 = new JLabel("Building No");
		lblNew_6_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_1_1.setBounds(48, 181, 102, 30);
		getContentPane().add(lblNew_6_1_1);
		
		JLabel lblNew_6_1_2 = new JLabel("Room No");
		lblNew_6_1_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6_1_2.setBounds(48, 273, 114, 30);
		getContentPane().add(lblNew_6_1_2);
	}
}

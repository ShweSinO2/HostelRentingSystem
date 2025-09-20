package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Reversion extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);
	MyDate myDate = new MyDate();
	String payment;
	SqlQuery sqlquery = new SqlQuery();
	static int count = 0;

	/**
	 * Create the dialog.
	 */
	public Reversion(String roomNo,String endDate,String roomId,String userId) {
		setTitle("Reservation");
		setBounds(450, 180, 497, 371);
		getContentPane().setLayout(null);
		setResizable(false);
		LocalDate date = LocalDate.now();
		LocalDate announce = LocalDate.parse(endDate);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(296, 11, 44, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDate = new JLabel(date.toString());
		lblDate.setBorder(blackline);
		lblDate.setBounds(350, 12, 108, 32);
		getContentPane().add(lblDate);
		
		JLabel lblNew_4 = new JLabel("End Date");
		lblNew_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_4.setBounds(36, 367, 102, 30);
		getContentPane().add(lblNew_4);
		
		JLabel lblNew_2_1 = new JLabel("Room No");
		lblNew_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2_1.setBounds(22, 92, 102, 30);
		getContentPane().add(lblNew_2_1);
		
		JLabel lblRoom = new JLabel(roomNo);
		lblRoom.setBorder(blackline);
		lblRoom.setBounds(154, 92, 304, 30);
		getContentPane().add(lblRoom);
		
		JLabel lblNew_2_1_1 = new JLabel("Announce Date");
		lblNew_2_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2_1_1.setBounds(22, 145, 131, 30);
		getContentPane().add(lblNew_2_1_1);
		
		JLabel lblNew_2_1_1_1 = new JLabel("End Date");
		lblNew_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2_1_1_1.setBounds(22, 201, 99, 30);
		getContentPane().add(lblNew_2_1_1_1);
		
		JLabel lblAnnounce = new JLabel(announce.minusDays(7).toString());
		lblAnnounce.setBorder(blackline);
		lblAnnounce.setBounds(154, 145, 304, 30);
		getContentPane().add(lblAnnounce);
		
		JLabel lblEnd = new JLabel(endDate);
		lblEnd.setBorder(blackline);
		lblEnd.setBounds(154, 201, 304, 30);
		getContentPane().add(lblEnd);
		
		JButton btnReserve = new JButton("Reserve");
		if(count > 0) {
			System.out.print("Count => "+count);
			btnReserve.setEnabled(false);
		}
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				System.out.print("Count => "+count);
				if(lblDate.getText().compareTo(lblAnnounce.getText()) > 0) {
					JOptionPane.showMessageDialog(null, "Your Reverse Date has been passed the Announce Date.Please Contact to Owner.");
				} else {
					String[] reverseData = new String[4];
					reverseData[0] = roomId;
					reverseData[1] = userId;
					reverseData[2] = lblAnnounce.getText();
					reverseData[3] = lblDate.getText();
					
					boolean save = sqlquery.insertData("reversion", reverseData);
					if(save) {
						JOptionPane.showMessageDialog(null, "Reservation is Successful");
						btnReserve.setEnabled(false);
						try {
							boolean updateRoom = sqlquery.updateRoom(roomId,true);
							if(updateRoom) {
								System.out.println("Update Available at "+ roomId);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		btnReserve.setBounds(85, 273, 102, 32);
		getContentPane().add(btnReserve);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();			
				}
			}
		});
		btnClose.setBounds(284, 273, 102, 32);
		getContentPane().add(btnClose);
		
	}
}

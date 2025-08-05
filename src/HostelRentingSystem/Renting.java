package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Renting extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);
	MyDate myDate = new MyDate();
	private JTextField txtAmount;
	String payment;
	private JRadioButton rdoWave;
	private JRadioButton rdoKbz;
	SqlQuery sqlquery = new SqlQuery();
	private JLabel lblSeeker;
	private JLabel lblOwner;
	private JLabel lblRoom;
	private JLabel lblPrice;
	private JRadioButton rdoCb;

	/**
	 * Create the dialog.
	 */
	public Renting(String seekerName,String ownerName,String roomno,int price,String seekerPhone,String ownerPhone,String roomId) {
		
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		setTitle("Renting");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		LocalDate date = LocalDate.now();
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(448, 11, 44, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDate = new JLabel(date.toString());
		lblDate.setBorder(blackline);
		lblDate.setBounds(525, 12, 121, 32);
		getContentPane().add(lblDate);
		
		JLabel lblNew_1 = new JLabel("Seeker Name");
		lblNew_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_1.setBounds(67, 53, 102, 30);
		getContentPane().add(lblNew_1);
		
		lblSeeker = new JLabel(seekerName);
		lblSeeker.setBounds(291, 54, 355, 30);
		lblSeeker.setBorder(blackline);
		getContentPane().add(lblSeeker);
		
		JLabel lblNew_2 = new JLabel("Owner Name");
		lblNew_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2.setBounds(67, 105, 102, 30);
		getContentPane().add(lblNew_2);
		
		lblOwner = new JLabel(ownerName);
		lblOwner.setBorder(blackline);
		lblOwner.setBounds(291, 106, 355, 30);
		getContentPane().add(lblOwner);
		
		JLabel lblNew_6 = new JLabel("Payment Type");
		lblNew_6.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_6.setBounds(67, 256, 102, 30);
		getContentPane().add(lblNew_6);
		
		rdoCb = new JRadioButton("CB Pay");
		rdoCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoCb.isSelected()) {
					rdoKbz.setSelected(false);
					rdoWave.setSelected(false);
					payment = "CB Pay";
				}
			}
		});
		rdoCb.setBounds(291, 261, 66, 23);
		getContentPane().add(rdoCb);
		
		rdoKbz = new JRadioButton("KBZ Pay");
		rdoKbz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoKbz.isSelected()) {
					rdoCb.setSelected(false);
					rdoWave.setSelected(false);
					payment = "KBZ Pay";
				}
			}
		});
		rdoKbz.setBounds(419, 261, 89, 23);
		getContentPane().add(rdoKbz);
		
		rdoWave = new JRadioButton("Wave Pay");
		rdoWave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoWave.isSelected()) {
					rdoKbz.setSelected(false);
					rdoCb.setSelected(false);
					payment = "Wave Pay";
				}
			}
		});
		rdoWave.setBounds(547, 261, 99, 23);
		getContentPane().add(rdoWave);
		
		JLabel lblNew_5 = new JLabel("Amount");
		lblNew_5.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_5.setBounds(67, 311, 102, 30);
		getContentPane().add(lblNew_5);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(291, 311, 301, 32);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel lblNew_7 = new JLabel("Kyats");
		lblNew_7.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_7.setBounds(602, 311, 44, 30);
		getContentPane().add(lblNew_7);
		
		JLabel lblNew_3 = new JLabel("Start Date");
		lblNew_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_3.setBounds(67, 365, 102, 30);
		getContentPane().add(lblNew_3);
		
		//System.out.println("Date => "+lblDate.getText());
		JLabel lblStart = new JLabel(myDate.getStartDate(date));
		lblStart.setBorder(blackline);
		lblStart.setBounds(291, 366, 355, 30);
		getContentPane().add(lblStart);
		
		JLabel lblNew_4 = new JLabel("End Date");
		lblNew_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_4.setBounds(67, 420, 102, 30);
		getContentPane().add(lblNew_4);
		
		JLabel lblEnd = new JLabel(myDate.getEndDate(lblStart.getText()));
		lblEnd.setBorder(blackline);
		lblEnd.setBounds(291, 421, 355, 30);
		getContentPane().add(lblEnd);
		
		lblPrice = new JLabel(price+"");
		lblPrice.setBorder(blackline);
		lblPrice.setBounds(291, 216, 355, 30);
		getContentPane().add(lblPrice);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Checking.IsNull(txtAmount.getText()) || !Checking.IsAllDigit(txtAmount.getText()) || (Integer.parseInt(txtAmount.getText()) <= 0) || (Integer.parseInt(txtAmount.getText()) < Integer.parseInt(lblPrice.getText()))) {
					JOptionPane.showMessageDialog(null, "You must enter valid Amount");
					txtAmount.requestFocus();
					txtAmount.selectAll();
				} else if(payment == null) {
					JOptionPane.showMessageDialog(null, "You must choose Payment Type");
					rdoCb.requestFocus();
				} else {
					String[] rentData = new String[2];
					String[] paymentData = new String[2];
					String[] rentDetailData = new String[6];
					
					String seekerId = sqlquery.getUserId(seekerPhone);
					rentData[0] = seekerId;
					rentData[1] = lblDate.getText();
					boolean save = sqlquery.insertData("renting", rentData);
					if(save) {
						paymentData[0] = txtAmount.getText();
						paymentData[1] = payment;
						boolean savePayment = sqlquery.insertData("payment", paymentData);
						if(savePayment) {
							String rentId = sqlquery.getRentingDetailId("renting");
							String paymentId = sqlquery.getRentingDetailId("payment");
							String ownerId = sqlquery.getUserId(ownerPhone);
							String startDate = lblStart.getText();
							String endDate = lblEnd.getText();
							
							rentDetailData[0] = rentId;
							rentDetailData[1] = roomId;
							rentDetailData[2] = paymentId;
							rentDetailData[3] = ownerId;
							rentDetailData[4] = startDate;
							rentDetailData[5] = endDate;
							
							boolean saveDetail = sqlquery.insertData("rentingdetail", rentDetailData);
							if(saveDetail) {
								JOptionPane.showMessageDialog(null, "Your Hostel Renting is Successful");
								try {
									boolean updateRoom = sqlquery.updateRoom(roomId,false);
									if(updateRoom) {
//										System.out.println("Update Available at "+ roomId);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								clear();
								setVisible(false);
							}
							
						}
					}
				}
			}
		});
		btnConfirm.setBounds(419, 488, 99, 45);
		getContentPane().add(btnConfirm);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnClose.setBounds(209, 488, 99, 45);
		getContentPane().add(btnClose);
		
		JLabel lblNew_2_1 = new JLabel("Room No");
		lblNew_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2_1.setBounds(67, 158, 102, 30);
		getContentPane().add(lblNew_2_1);
		
		JLabel lblNew_2_1_1 = new JLabel("Price");
		lblNew_2_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNew_2_1_1.setBounds(67, 215, 102, 30);
		getContentPane().add(lblNew_2_1_1);
		
		lblRoom = new JLabel(roomno);
		lblRoom.setBorder(blackline);
		lblRoom.setBounds(291, 159, 355, 30);
		getContentPane().add(lblRoom);
		
	}
	
	public void clear() {
		lblSeeker.setText("");
		lblOwner.setText("");
		lblRoom.setText("");
		lblPrice.setText("");
		rdoCb.setSelected(false);
		rdoKbz.setSelected(false);
		rdoWave.setSelected(false);
		txtAmount.setText("");		
	}
}

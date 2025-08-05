package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import HostelRentingSystem.Checking;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class HostelDetail extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);

	/**
	 * Create the dialog.
	 */
	public HostelDetail(String hostelName,String roomno,String address,String genderType,int price,String ownerName,String ownerPhone,String roomId,String fileName,String imageUrl) {
		System.out.println("Data => "+hostelName+roomno+address+genderType+price+ownerName+ownerPhone);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    int screenHeight = screenSize.height;
	    
        Insets insets = toolkit.getScreenInsets(getGraphicsConfiguration());
        int contentPaneHeight = screenHeight - insets.top - insets.bottom;

        // Frame á€›á€²á€· á€¡á€€á€»á€šá€ºá€”á€²á€· x, y á€”á€±á€›á€¬á€€á€­á€¯ á€žá€�á€ºá€™á€¾á€�á€ºá€™á€šá€ºá�‹
        int frameWidth = 700;
        int frameX = 350;
        int frameY = 50;
        int frameHeight = screenHeight;
        
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Hostel Detail Form");
		setBounds(350, 50, 700, 650);
//		setBounds(frameX, frameY, frameWidth, frameHeight);
		setResizable(true);
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
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setFont(new Font("Arial", Font.PLAIN, 15));
		lblImage.setBounds(43, 480, 102, 30);
		getContentPane().add(lblImage);
		
		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signin = new SignIn("rent",ownerName,roomno,price,ownerPhone,roomId);
				signin.setVisible(true);
				setVisible(false);
			}
		});
		btnRent.setBounds(408, 550, 102, 35);
		getContentPane().add(btnRent);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnClose.setBounds(165, 550, 102, 35);
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
		
		JLabel imageLabel = new JLabel("Loading Image...");
		imageLabel.setBounds(280, 487, 150, 23);
		File imageFile = new File(imageUrl);
		if (imageFile.exists() && imageFile.isFile()) {
			
            ImageIcon originalIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(130, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            
            imageLabel.setIcon(scaledIcon);
            imageLabel.setText("");
    		imageLabel.setBounds(280, 487, 130, 100);
    		
    		//If there is image change y dimension
    		setBounds(350, 50, 700, 727);
    		btnRent.setBounds(408, 617, 102, 35);
    		btnClose.setBounds(165, 617, 102, 35);
        } else {
            imageLabel.setText("Image not found: " + imageUrl);
            imageLabel.setForeground(Color.RED);
        }
		getContentPane().add(imageLabel, BorderLayout.CENTER);
		
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

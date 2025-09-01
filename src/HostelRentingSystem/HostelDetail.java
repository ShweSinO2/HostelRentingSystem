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
import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class HostelDetail extends JDialog {
	Border blackline=BorderFactory.createLineBorder(Color.black);

	/**
	 * Create the dialog.
	 */
	public HostelDetail(String hostelName,String roomno,String address,String genderType,int price,String ownerName,String ownerPhone,String roomId,String fileName,String imageUrl, String description) {
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
        
		getContentPane().setBackground(Color.WHITE);
		setTitle("Hostel Detail Form");
		setBounds(350, 50, 700, 534);
//		setBounds(frameX, frameY, frameWidth, frameHeight);
		setResizable(true);
		getContentPane().setLayout(null);
		
		JLabel lblNew = new JLabel("Owner Name");
		lblNew.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNew.setBounds(42, 66, 102, 30);
		getContentPane().add(lblNew);
		
		JLabel lblNew_1 = new JLabel("Owner PhoneNo");
		lblNew_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNew_1.setBounds(42, 93, 143, 30);
		getContentPane().add(lblNew_1);
		
		JLabel lblNew_2 = new JLabel("Room No");
		lblNew_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNew_2.setBounds(42, 122, 102, 30);
		getContentPane().add(lblNew_2);
		
		JLabel lblNew_3 = new JLabel("Monthly Rental Fees");
		lblNew_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNew_3.setForeground(Color.decode("#085f63"));
		lblNew_3.setBounds(457, 33, 161, 30);
		getContentPane().add(lblNew_3);
		
		JLabel lblNew_4 = new JLabel("Address");
		lblNew_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNew_4.setBounds(42, 181, 102, 30);
		getContentPane().add(lblNew_4);
		
		JLabel lblGenderType = new JLabel("Gender Type");
		lblGenderType.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGenderType.setBounds(42, 150, 102, 30);
		getContentPane().add(lblGenderType);
		
//		JLabel lblImage = new JLabel("Image");
//		lblImage.setFont(new Font("Arial", Font.PLAIN, 15));
//		lblImage.setBounds(42, 351, 102, 30);
//		getContentPane().add(lblImage);
		
//		JLabel lblDescription = new JLabel("Description");
//		lblDescription.setFont(new Font("Arial", Font.PLAIN, 15));
//		lblDescription.setBounds(42, 225, 102, 30);
//		getContentPane().add(lblDescription);
		
		
		//for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;

		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
		
		JButton btnRent = new JButton("Rent");
		btnRent.setBackground( new Color(0, 120, 215));  // green
		btnRent.setForeground(Color.WHITE);
		
		btnRent.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnRent.setBackground(hoverBg);    // Change background
		    	btnRent.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnRent.setBackground(defaultBg);  // Reset background
		    	btnRent.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signin = new SignIn("rent",ownerName,roomno,price,ownerPhone,roomId);
				signin.setVisible(true);
				setVisible(false);
			}
		});
		btnRent.setBounds(371, 431, 102, 35);
		getContentPane().add(btnRent);
		
		
		
		JButton btnClose = new JButton("Close");
		
		btnClose.setBackground( new Color(0, 120, 215));  // green
		btnClose.setForeground(Color.WHITE);
		
		btnClose.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnClose.setBackground(hoverBg);    // Change background
		    	btnClose.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnClose.setBackground(defaultBg);  // Reset background
		    	btnClose.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Existing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				//}
			}
		});
		btnClose.setBounds(151, 431, 102, 35);
		getContentPane().add(btnClose);
		
		JLabel lblOwnerName = new JLabel(ownerName);
		lblOwnerName.setBounds(195, 67, 143, 30);
		//lblOwnerName.setBorder(blackline);
		getContentPane().add(lblOwnerName);
		
		JLabel lblPhoneNo = new JLabel(ownerPhone);
		//lblPhoneNo.setBorder(blackline);
		lblPhoneNo.setBounds(195, 91, 143, 37);
		getContentPane().add(lblPhoneNo);
		
		JLabel lblRoomNo = new JLabel(roomno);
		//lblRoomNo.setBorder(blackline);
		lblRoomNo.setBounds(195, 123, 143, 30);
		getContentPane().add(lblRoomNo);
		
		// add comma and kyats to price
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    	String formattedPrice = numberFormat.format(price);
    	
		JLabel lblPrice = new JLabel(formattedPrice+" Kyats");
		//lblPrice.setBorder(blackline);
		lblPrice.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPrice.setForeground(Color.decode("#000000"));
		lblPrice.setBounds(457, 90, 161, 35);
		getContentPane().add(lblPrice);
		
		JLabel lblAddress = new JLabel(address);
		//lblAddress.setBorder(blackline);
		lblAddress.setBounds(195, 176, 314, 42);
		getContentPane().add(lblAddress);
		
		JLabel lblGender = new JLabel(genderType+" Hostel");
		//lblGender.setBorder(blackline);
		lblGender.setBounds(195, 151, 143, 30);
		getContentPane().add(lblGender);
		
		JLabel imageLabel = new JLabel("Loading Image...");
		imageLabel.setBounds(243, 357, 110, 23);
		File imageFile = new File(imageUrl);
		if (imageFile.exists() && imageFile.isFile()) {
			
            ImageIcon originalIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(230, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            
            imageLabel.setIcon(scaledIcon);
            imageLabel.setText("");
    		imageLabel.setBounds(212, 365, 230, 200);
    		
    		//If there is image change y dimension
    		setBounds(350, 50, 700, 675);
    		btnRent.setBounds(408, 595, 102, 35);
    		btnClose.setBounds(165, 595, 102, 35);
        } else {
            imageLabel.setText("Image not found: " + imageUrl);
            imageLabel.setForeground(Color.RED);
        }
		getContentPane().add(imageLabel, BorderLayout.CENTER);
		
//		JLabel desc = new JLabel(description);
//		desc.setBorder(blackline);
//		desc.setBounds(196, 325, 357, 30);
//		getContentPane().add(desc);
		
		//For description use JTextArea
		JTextArea ltextdesc = new JTextArea(description);
		ltextdesc.setEditable(false); // Not editable like JLabel
		ltextdesc.setLineWrap(true);  // Wrap lines
		ltextdesc.setWrapStyleWord(true); // Wrap at word boundaries
		ltextdesc.setFont(new Font("Arial", Font.PLAIN, 14));
		ltextdesc.setBackground(null); // Make background match panel (optional)
		ltextdesc.setBorder(null);     // Remove border (optional)
		ltextdesc.setBounds(42, 235, 340, 86); // Size and position
		getContentPane().add(ltextdesc);

		
		JLabel lblHostelName = new JLabel(hostelName);
		//lblHostelName.setBorder(blackline);
		lblHostelName.setBounds(196, 35, 143, 30);
		getContentPane().add(lblHostelName);
		
		JLabel lblHostelName_1 = new JLabel("Hostel Name");
		lblHostelName_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblHostelName_1.setBounds(42, 34, 102, 30);
		getContentPane().add(lblHostelName_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 216, 640, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 351, 640, 2);
		getContentPane().add(separator_1);
	}
}

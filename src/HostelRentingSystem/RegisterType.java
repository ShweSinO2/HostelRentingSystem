package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterType extends JDialog {
	private JLabel lbllogo,lbllogo1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterType dialog = new RegisterType();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisterType() {
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Register Type");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setBounds(101, 224, 93, 43);
		lblOwner.setFont(new Font("Arial", Font.PLAIN, 18));
		getContentPane().add(lblOwner);
		
		JLabel lblSeeker = new JLabel("Seeker");
		lblSeeker.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSeeker.setBounds(489, 229, 93, 33);
		getContentPane().add(lblSeeker);
		
		JLabel lblNewLabel = new JLabel("<html><center>Owner can register for hostel information and management for room</center></html>");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 289, 250, 72);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><center>Seeker can find hostel and booking for room</center></html>");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(399, 289, 250, 72);
		getContentPane().add(lblNewLabel_1);
		
		
		
		//for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;

		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
		
		JButton btnOwner = new JButton("Owner Register");
		btnOwner.setOpaque(true);                      // allow background painting
		btnOwner.setContentAreaFilled(true);           // MUST be true to fill background
		btnOwner.setBorderPainted(false);              // optional: remove border
		btnOwner.setFocusPainted(false);               // optional: remove focus outline
		btnOwner.setBackground( new Color(0, 120, 215));  // green
		btnOwner.setForeground(Color.WHITE);
		
		btnOwner.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnOwner.setBackground(hoverBg);    // Change background
		    	btnOwner.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnOwner.setBackground(defaultBg);  // Reset background
		    	btnOwner.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UserRegistration owner = new UserRegistration("3");
//				owner.setVisible(true);
//				setVisible(false);
				
				OwnerAgreementForm owneragreement = new OwnerAgreementForm();
		        owneragreement.setVisible(true);
		        setVisible(false);
			}
		});
		btnOwner.setBounds(61, 401, 145, 38);
		getContentPane().add(btnOwner);
		
		
		
		//for Seeker btn
		
		JButton btnSeeker = new JButton("Seeker Register");
		btnSeeker.setOpaque(true);                      // allow background painting
		btnSeeker.setContentAreaFilled(true);           // MUST be true to fill background
		btnSeeker.setBorderPainted(false);              // optional: remove border
		btnSeeker.setFocusPainted(false);               // optional: remove focus outline
		btnSeeker.setBackground(new Color(0, 120, 215));  // green
		btnSeeker.setForeground(Color.WHITE);
		
		btnSeeker.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnSeeker.setBackground(hoverBg);    // Change background
		    	btnSeeker.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnSeeker.setBackground(defaultBg);  // Reset background
		    	btnSeeker.setForeground(defaultFg);  // Reset text color
		    }
		});
		

		btnSeeker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UserRegistration seeker = new UserRegistration("2");
//				seeker.setVisible(true);
//				setVisible(false);
				
				SeekerAgreementForm seekeragreement = new SeekerAgreementForm();
				seekeragreement.setVisible(true);
		        setVisible(false);
			}
		});
		btnSeeker.setBounds(455, 401, 145, 38);
		getContentPane().add(btnSeeker);
		
		lbllogo = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/hostel.png")).getImage();
		lbllogo.setIcon(new ImageIcon(img));
		lbllogo.setBounds(101, 122, 72, 91);
		getContentPane().add(lbllogo);
		
		lbllogo1 = new JLabel("");
		Image img1=new ImageIcon(this.getClass().getResource("/hostelrenter.png")).getImage();
		lbllogo1.setIcon(new ImageIcon(img1));
		lbllogo1.setBounds(467, 122, 86, 91);
		getContentPane().add(lbllogo1);

	}
}



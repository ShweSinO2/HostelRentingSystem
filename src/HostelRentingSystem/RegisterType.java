package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setTitle("Choose Register Type");
		setBounds(350, 50, 700, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setBounds(101, 224, 93, 43);
		lblOwner.setFont(new Font("Arial", Font.PLAIN, 18));
		getContentPane().add(lblOwner);
		
		JLabel lblSeeker = new JLabel("Seeker");
		lblSeeker.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSeeker.setBounds(496, 229, 93, 33);
		getContentPane().add(lblSeeker);
		
		JLabel lblNewLabel = new JLabel("<html><center>Owner can register for hostel information and management for room</center></html>");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 289, 250, 72);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><center>Seeker can find hostel and booking for room</center></html>");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(399, 289, 250, 72);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnOwner = new JButton("Owner Register");
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration owner = new UserRegistration("3");
				owner.setVisible(true);
				setVisible(false);
			}
		});
		btnOwner.setBounds(61, 401, 145, 38);
		getContentPane().add(btnOwner);
		
		JButton btnSeeker = new JButton("Seeker Register");
		btnSeeker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration seeker = new UserRegistration("2");
				seeker.setVisible(true);
				setVisible(false);
			}
		});
		btnSeeker.setBounds(455, 401, 145, 38);
		getContentPane().add(btnSeeker);
		
		lbllogo = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/owner.png")).getImage();
		lbllogo.setIcon(new ImageIcon(img));
		lbllogo.setBounds(101, 129, 86, 84);
		getContentPane().add(lbllogo);
		
		lbllogo1 = new JLabel("");
		Image img1=new ImageIcon(this.getClass().getResource("/seeker.png")).getImage();
		lbllogo1.setIcon(new ImageIcon(img1));
		lbllogo1.setBounds(496, 134, 78, 84);
		getContentPane().add(lbllogo1);

	}
}



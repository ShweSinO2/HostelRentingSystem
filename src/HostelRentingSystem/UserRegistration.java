package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

//import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import org.jdesktop.swingx.prompt.PromptSupport;



public class UserRegistration extends JDialog {
	private JLabel lblNewLabel;
	private JLabel lblPhone;
	private JLabel lblNrc;
	private JLabel lblState;
	private JLabel lblCity;
	private JLabel lblStreet;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtState;
	private JTextField txtCity;
	private JTextField txtStreet;
	private JTextField txtNRCno;
	private JComboBox cboNation;
	private JComboBox cboCode;
	private JComboBox cboCity;
	private JButton btnRegister;
	private JPanel panel;
	private ImageIcon eyeOpen;
    private ImageIcon eyeClosed;
	Map<Integer,List<String>> map = new HashMap<>();
	CityShortName shortName = new CityShortName();
	ArrayList listCode = new ArrayList();
	private JLabel lblPassowrd;
	private JPasswordField txtPass;
	private JButton btnClose;
	private JButton btnCancel;
	String[] userData = new String[10];
	String gender,nrc;
	private JRadioButton rdoFemale;
	SqlQuery sqlquery = new SqlQuery();
	private JRadioButton rdoMale;

	/**
	 * Create the dialog.
	 */
	public UserRegistration(String roleId) {
		
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		setTitle("");
		setBounds(350, 50, 700, 616);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		getContentPane().setLayout(null);
		//int roleId = id;
		panel = new JPanel();
		panel.setForeground(new Color(0, 191, 255));
		panel.setBackground(new Color(255,255,255));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Register Form", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20), Color.decode("#085f63")));
		//panel.setFont((new Font("Arial", Font.PLAIN, 20)));
		panel.setBounds(10, 0, 664, 561);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.decode("#3f3b3b"));
		lblNewLabel.setBounds(22, 61, 46, 21);
		panel.add(lblNewLabel);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPhone.setForeground(Color.decode("#3f3b3b"));
		lblPhone.setBounds(22, 109, 46, 14);
		panel.add(lblPhone);
		
		lblPassowrd = new JLabel("Password:");
		lblPassowrd.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassowrd.setForeground(Color.decode("#3f3b3b"));
		lblPassowrd.setBounds(22, 160, 82, 14);
		panel.add(lblPassowrd);
		
		lblNrc = new JLabel("NRC:");
		lblNrc.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNrc.setForeground(Color.decode("#3f3b3b"));
		lblNrc.setBounds(22, 216, 46, 14);
		panel.add(lblNrc);
		
		lblState = new JLabel("State:");
		lblState.setFont(new Font("Arial", Font.PLAIN, 15));
		lblState.setForeground(Color.decode("#3f3b3b"));
		lblState.setBounds(22, 268, 46, 14);
		panel.add(lblState);
		
		lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCity.setForeground(Color.decode("#3f3b3b"));
		lblCity.setBounds(22, 319, 46, 21);
		panel.add(lblCity);
		
		lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStreet.setForeground(Color.decode("#3f3b3b"));
		lblStreet.setBounds(22, 382, 46, 14);
		panel.add(lblStreet);
		
		txtName = new JTextField();
		txtName.setBounds(213, 58, 424, 31);
		panel.add(txtName);
		PromptSupport.setPrompt("Enter your Name(e.g.Kyaw Kyaw)", txtName);
		PromptSupport.setForeground(Color.GRAY, txtName);
		txtName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(213, 103, 424, 31);
		panel.add(txtPhone);
		PromptSupport.setPrompt("Enter your Phone Number(e.g.0754312/09777777777)", txtPhone);
		PromptSupport.setForeground(Color.GRAY, txtPhone);
		txtPhone.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(213, 154, 396, 31);
		panel.add(txtPass);
		PromptSupport.setPrompt("Enter your email(e.g.kyaw12345@)", txtPass);
		PromptSupport.setForeground(Color.GRAY, txtPass);
		txtPass.setColumns(10);
		
		// Load Eye Icons
        eyeOpen = new ImageIcon(getClass().getResource("/eye-open.png"));   // 👁 Show Icon
        eyeClosed = new ImageIcon(getClass().getResource("/eye-closed.png")); // ❌ Hide Icon

       // Scale Icons
       Image eyeOpenImg = eyeOpen.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
       Image eyeClosedImg = eyeClosed.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
       eyeOpen = new ImageIcon(eyeOpenImg);
       eyeClosed = new ImageIcon(eyeClosedImg);
       
    // Toggle Button
       JButton btnToggle = new JButton(eyeClosed);
       btnToggle.setBounds(612, 154, 30, 30);
       btnToggle.setBorder(null);
       btnToggle.setContentAreaFilled(false);
       panel.add(btnToggle);

       // Store Default Echo Char
       char defaultEchoChar = txtPass.getEchoChar();

       // Toggle Logic
       btnToggle.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (txtPass.getEchoChar() != (char) 0) {
                   txtPass.setEchoChar((char) 0);  // Show password
                   btnToggle.setIcon(eyeOpen);
               } else {
                   txtPass.setEchoChar(defaultEchoChar);  // Hide password
                   btnToggle.setIcon(eyeClosed);
               }
           }
       });
		
		txtState = new JTextField();
		txtState.setBounds(213, 262, 424, 31);
		panel.add(txtState);
		PromptSupport.setPrompt("Enter your State(e.g.Yangon)", txtState);
		PromptSupport.setForeground(Color.GRAY, txtState);
		txtState.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(213, 316, 424, 31);
		panel.add(txtCity);
		PromptSupport.setPrompt("Enter your City(e.g.Kamayut)", txtCity);
		PromptSupport.setForeground(Color.GRAY, txtCity);
		txtCity.setColumns(10);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(213, 376, 424, 31);
		panel.add(txtStreet);
		PromptSupport.setPrompt("Enter your Street(e.g.SanYeikNyein)", txtStreet);
		PromptSupport.setForeground(Color.GRAY, txtStreet);
		txtStreet.setColumns(10);
		
		cboCode = new JComboBox();
		cboCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				fillCode();				
			}
		});
		cboCode.setModel(new DefaultComboBoxModel(new String[] {"","1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/", "9/", "10/", "11/", "12/", "13/", "14/"}));
		cboCode.setMaximumRowCount(5);
		cboCode.setBackground(Color.WHITE);
		cboCode.setForeground(Color.BLACK);
		cboCode.setFont(new Font("Arial", Font.PLAIN, 14));
		cboCode.setBounds(214, 208, 73, 31);
		panel.add(cboCode);
		
		cboCity = new JComboBox();
		cboCity.setModel(new DefaultComboBoxModel(new String[] {""}));
		cboCity.setMaximumRowCount(5);
		cboCity.setBackground(Color.WHITE);
		cboCity.setForeground(Color.BLACK);
		cboCity.setFont(new Font("Arial", Font.PLAIN, 14));
		cboCity.setBounds(305, 208, 122, 31);
		panel.add(cboCity);
		
		cboNation = new JComboBox();
		cboNation.setModel(new DefaultComboBoxModel(new String[] {"", "N", "E", "P"}));
		cboNation.setMaximumRowCount(5);
		cboNation.setBackground(Color.WHITE);
		cboNation.setForeground(Color.BLACK);
		cboNation.setFont(new Font("Arial", Font.PLAIN, 14));
		cboNation.setBounds(450, 208, 82, 31);
		panel.add(cboNation);
		
		txtNRCno = new JTextField();
		txtNRCno.setBounds(542, 210, 95, 31);
		panel.add(txtNRCno);
		txtNRCno.setColumns(10);
		
	//  Default colors
			Color defaultBg = new Color(0, 120, 215);
			Color defaultFg = Color.WHITE;

			//  Hover colors
			Color hoverBg = Color.decode("#f0f0f0");
			Color hoverFg = new Color(0, 120, 215);
		
		
		btnRegister = new JButton("Register");
		btnRegister.setOpaque(true);                      // allow background painting
		btnRegister.setContentAreaFilled(true);           // MUST be true to fill background
		btnRegister.setBorderPainted(false);              // optional: remove border
		btnRegister.setFocusPainted(false);               // optional: remove focus outline
		btnRegister.setBackground(new Color(0, 120, 215));  // green
		btnRegister.setForeground(Color.WHITE);
		
		btnRegister.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnRegister.setBackground(hoverBg);    // Change background
		    	btnRegister.setForeground(hoverFg);    // Change text color
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnRegister.setBackground(defaultBg);  // Reset background
		    	btnRegister.setForeground(defaultFg);  // Reset text color
		    }
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String phoneno = txtPhone.getText().trim();
				boolean duplicate = sqlquery.isPhonenoDuplicate(txtPhone.getText());
				boolean duplicateNrc = sqlquery.isNrcDuplicate(cboCode.getSelectedItem().toString(),cboCity.getSelectedItem().toString(),cboNation.getSelectedItem().toString(),txtNRCno.getText());
				
				if(Checking.IsNull(txtName.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter valid Name");
					txtName.requestFocus();
					txtName.selectAll();
				}
				/////
				else if(Checking.IsValidName(txtName.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter///// valid Phone Number");
					txtName.requestFocus();
					txtName.selectAll();
				}
//				else if(Checking.IsNull(txtPhone.getText()) || Checking.IsLetter(txtPhone.getText()) || !Checking.IsPhoneNo(txtPhone.getText()))
//				{
//					JOptionPane.showMessageDialog(null, "You must enter valid Phone Number");
//					txtPhone.requestFocus();
//					txtPhone.selectAll();
//				}
				/////
				else if (phoneno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your phone number.");
                    txtPhone.requestFocus();
                    txtPhone.selectAll();
                }
				else if (!Checking.IsAllDigit(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number must be Numbers");
                    txtPhone.requestFocus();
                    txtPhone.selectAll();
                }
				else if (!Checking.IsPhoneNo(phoneno)) {
                    JOptionPane.showMessageDialog(null, "Phone Number should be between 6 and 11 numbers");
                    txtPhone.requestFocus();
                    txtPhone.selectAll();
                }
			    else if(duplicate)
				{
					JOptionPane.showMessageDialog(null, "Registration PhoneNo is already exist!!");	
				}
				else if(txtPass.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter your password");
					txtPass.requestFocus();
					txtPass.selectAll();					
				}
				
				else if(!Checking.IsPassNo(txtPass.getText()))
				{
					JOptionPane.showMessageDialog(null, "Your password should be at least 8, 1 number,1 uppercase,1 lowercase and 1 special character ");
					txtPass.requestFocus();
					txtPass.selectAll();					
				}
				else if(cboCode.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "You must choose Code for NRC");
					cboCode.requestFocus();
				}
				else if(cboCity.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "You must choose City for NRC");
					cboCity.requestFocus();					
				}
				else if(cboNation.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "You must choose Nation for NRC");
					cboNation.requestFocus();
					
				}				
				else if(Checking.IsNull(txtNRCno.getText()) || !Checking.IsNrc(txtNRCno.getText()) || !Checking.IsAllDigit(txtNRCno.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter valid NRC Code");
					txtNRCno.requestFocus();
					txtNRCno.selectAll();
				} 
				else if(duplicateNrc) {
					JOptionPane.showMessageDialog(null, "Registration NRC is already exist!!");	
				} 
				else if(Checking.IsNull(txtState.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter valid State");
					txtState.requestFocus();
					txtState.selectAll();
				}
				else if(!Checking.IsLetter(txtState.getText()))
				{
					JOptionPane.showMessageDialog(null, "State should be only words");
					txtState.requestFocus();
					txtState.selectAll();
				}
				else if(Checking.IsNull(txtCity.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter valid City");
					txtCity.requestFocus();
					txtCity.selectAll();
				}
				else if(!Checking.IsLetter(txtCity.getText()))
				{
					JOptionPane.showMessageDialog(null, "City should be only words");
					txtCity.requestFocus();
					txtCity.selectAll();
				}
				else if(Checking.IsNull(txtStreet.getText()))
				{
					JOptionPane.showMessageDialog(null, "You must enter valid Street");
					txtStreet.requestFocus();
					txtStreet.selectAll();
				}
				else if(!rdoMale.isSelected() && !rdoFemale.isSelected())
				{
					JOptionPane.showMessageDialog(null, "You must Choose Gender Type");
					//txtStreet.requestFocus();
					//txtStreet.selectAll();
				}
				
				else {
					String code,city,nation,number;
					code = (String) cboCode.getSelectedItem();
					city = (String) cboCity.getSelectedItem();
					nation = (String) cboNation.getSelectedItem();
					number = txtNRCno.getText();
					nrc = code + city + "(" + nation + ")" + number;
					
					//System.out.println("NRC => "+ nrc);
					userData[0] = txtName.getText();
					userData[1] = txtPhone.getText();
					userData[2] = nrc;
					userData[3] = txtState.getText();
					userData[4] = txtCity.getText();
					userData[5] = txtStreet.getText();
					userData[6] = txtPass.getText();
					userData[7] = roleId;
					userData[8] = "pending";
					userData[9] = gender;//gender
										
					boolean save = sqlquery.insertData("user", userData);
					if(save) {
						JOptionPane.showMessageDialog(null, "Thank You for Registration. Admin will approve account in a few day!!!");
						clear();
					}				
				}				
			}
			private Object String(String text) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnRegister.setBounds(305, 492, 97, 36);
		panel.add(btnRegister);
		
		
		
		//for cancel btn
		btnCancel = new JButton("Cancel");
		
		btnCancel.setOpaque(true);                      // allow background painting
		btnCancel.setContentAreaFilled(true);           // MUST be true to fill background
		btnCancel.setBorderPainted(false);              // optional: remove border
		btnCancel.setFocusPainted(false);               // optional: remove focus outline
		btnCancel.setBackground(new Color(0, 120, 215));  // green
		btnCancel.setForeground(Color.WHITE);
		
		btnCancel.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnCancel.setBackground(hoverBg);    // Change background
		    	btnCancel.setForeground(hoverFg);    // Change text color
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnCancel.setBackground(defaultBg);  // Reset background
		    	btnCancel.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				
			}
		});
		btnCancel.setBounds(108, 492, 97, 36);
		panel.add(btnCancel);
		
		
		//for close btn
		btnClose = new JButton("Close");
		
		btnClose.setOpaque(true);                      // allow background painting
		btnClose.setContentAreaFilled(true);           // MUST be true to fill background
		btnClose.setBorderPainted(false);              // optional: remove border
		btnClose.setFocusPainted(false);               // optional: remove focus outline
		btnClose.setBackground(new Color(0, 120, 215));  // green
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
				//if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				//{	
					dispose();
				//}
			}
		});
		btnClose.setBounds(493, 492, 89, 36);
		panel.add(btnClose);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setForeground(Color.decode("#3f3b3b"));
		lblGender.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGender.setBounds(22, 432, 82, 14);
		panel.add(lblGender);
		
		rdoMale = new JRadioButton("Male");
		rdoMale.setOpaque(true);                      // allow background painting
		rdoMale.setContentAreaFilled(true);           // MUST be true to fill background
		rdoMale.setBorderPainted(false);              // optional: remove border
		rdoMale.setFocusPainted(false);               // optional: remove focus outline
		rdoMale.setBackground(new Color(255, 255, 255));
		rdoMale.setForeground(Color.decode("#3f3b3b"));
		rdoMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoMale.isSelected()) {
					rdoFemale.setSelected(false);
					gender = "Male";
				}
			}
		});
		rdoMale.setBounds(259, 430, 82, 23);
		panel.add(rdoMale);
		
		rdoFemale = new JRadioButton("Female");
		rdoFemale.setOpaque(true);                      // allow background painting
		rdoFemale.setContentAreaFilled(true);           // MUST be true to fill background
		rdoFemale.setBorderPainted(false);              // optional: remove border
		rdoFemale.setFocusPainted(false);               // optional: remove focus outline
		rdoFemale.setBackground(new Color(255, 255, 255));
		rdoFemale.setForeground(Color.decode("#3f3b3b"));
		rdoFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoFemale.isSelected()) {
					rdoMale.setSelected(false);
					gender = "Female";
				}
			}
		});
		rdoFemale.setBounds(465, 430, 82, 23);
		panel.add(rdoFemale);
		
		
	}
	
	public void fillCode() {
		map = shortName.getCityCode();
		if(cboCode.getSelectedIndex() == 1)
		{
			System.out.println("Short Name => "+ map.get(1));	
			listCode = (ArrayList) map.get(1);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++) 
			{
 
				cboCity.addItem(listCode.get(i));
			}
		} 
		else if(cboCode.getSelectedIndex() == 2) {
			System.out.println("Short Name => "+ map.get(2));	
			listCode = (ArrayList) map.get(2);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 3) {
			System.out.println("Short Name => "+ map.get(3));	
			listCode = (ArrayList) map.get(3);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 4) {
			System.out.println("Short Name => "+ map.get(4));	
			listCode = (ArrayList) map.get(4);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 5) {
			System.out.println("Short Name => "+ map.get(5));	
			listCode = (ArrayList) map.get(5);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 6) {
			System.out.println("Short Name => "+ map.get(6));	
			listCode = (ArrayList) map.get(6);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 7) {
			System.out.println("Short Name => "+ map.get(7));	
			listCode = (ArrayList) map.get(7);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 8) {
			System.out.println("Short Name => "+ map.get(8));	
			listCode = (ArrayList) map.get(8);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 9) {
			System.out.println("Short Name => "+ map.get(9));	
			listCode = (ArrayList) map.get(9);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 10) {
			System.out.println("Short Name => "+ map.get(10));	
			listCode = (ArrayList) map.get(10);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 11) {
			System.out.println("Short Name => "+ map.get(11));	
			listCode = (ArrayList) map.get(11);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 12) {
			System.out.println("Short Name => "+ map.get(12));	
			listCode = (ArrayList) map.get(12);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 13) {
			System.out.println("Short Name => "+ map.get(13));	
			listCode = (ArrayList) map.get(13);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		else if(cboCode.getSelectedIndex() == 14) {
			System.out.println("Short Name => "+ map.get(14));	
			listCode = (ArrayList) map.get(14);
			cboCity.removeAllItems();
			for(int i=0;i<listCode.size();i++)
			{
 
				cboCity.addItem(listCode.get(i));
			}
			
		}
		
	}
	
	public void clear()
	{
		txtName.setText("");
		txtPhone.setText("");
		txtPass.setText("");
		cboCode.setSelectedIndex(0);
		// Reset cboCity to only blank
	    cboCity.removeAllItems();
	    cboCity.addItem("");
		//cboCity.setSelectedIndex(0);
		cboNation.setSelectedIndex(0);
		txtNRCno.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtStreet.setText("");
		txtName.requestFocus();
		rdoMale.setSelected(false);
		rdoFemale.setSelected(false);
	}
}
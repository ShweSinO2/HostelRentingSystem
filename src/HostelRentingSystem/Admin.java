package HostelRentingSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JDialog {
	private JTable tblOwner,tblSeeker;
	DefaultTableModel tblOwnerModel = new DefaultTableModel();
	DefaultTableModel tblSeekerModel = new DefaultTableModel();
	DBConnection connect = new DBConnection();
	Connection con = null;
	List<String[]> ownerTableRowList = new ArrayList<>();
	List<String[]> seekerTableRowList = new ArrayList<>();
	
    public Admin(String userId) {
        setTitle("Admin Panel");
        setSize(800, 560);
        try {
			con = connect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel2 = new JPanel();
        
        tabbedPane.addTab("Seeker", null, panel2, "Seeker");
        panel2.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 11, 759, 411);
        panel2.add(scrollPane_2);
        
        tblSeeker = new JTable();
        tblSeeker.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int row = tblSeeker.getSelectedRow();
        		String status = (String) tblSeeker.getValueAt(row, 4);
        		
        		String[] seekerData = seekerTableRowList.get(row);
        		//System.out.println("selectedRowData => " + Arrays.toString(ownerData));	
        		String userId = seekerData[5];
        		//System.out.println("User ID => "+userId);
        		     		
        		if(status.equals("pending")) {        			
        			getCustomDialog(userId);
        		}
        		
        	}   
        });
        scrollPane_2.setViewportView(tblSeeker);      
        
        //for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;
	
		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
        
        JButton lblprofile = new JButton("Acc Info");
        
        lblprofile.setBackground( new Color(0, 120, 215));  // green
        lblprofile.setForeground(Color.WHITE);
		
        lblprofile.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	lblprofile.setBackground(hoverBg);    // Change background
		    	lblprofile.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	lblprofile.setBackground(defaultBg);  // Reset background
		    	lblprofile.setForeground(defaultFg);  // Reset text color
		    }
		});
        
        lblprofile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdateForm userallinfo;
				
					userallinfo = new UserUpdateForm(userId);
					userallinfo.setVisible(true);
					//dispose();
				
			}
		});
        
        lblprofile.setBounds(20, 448, 87, 27);
        panel2.add(lblprofile);
        
        JButton btnLogout1 = new JButton("Logout");
        btnLogout1.setBackground( new Color(0, 120, 215));  // green
        btnLogout1.setForeground(Color.WHITE);
		
        btnLogout1.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnLogout1.setBackground(hoverBg);    // Change background
		    	btnLogout1.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnLogout1.setBackground(defaultBg);  // Reset background
		    	btnLogout1.setForeground(defaultFg);  // Reset text color
		    }
		});
		
        btnLogout1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to LogOut?","Confirm Exist",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
        btnLogout1.setBounds(670, 448, 87, 27);
        panel2.add(btnLogout1);

        getContentPane().add(tabbedPane);
        
                JPanel panel1 = new JPanel();
                
                        tabbedPane.addTab("Owner", null, panel1, "Owner");
                        panel1.setLayout(null);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(10, 11, 759, 411);
                        panel1.add(scrollPane);
                        
                        tblOwner = new JTable();
                        tblOwner.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mouseClicked(MouseEvent e) {
                        		int row = tblOwner.getSelectedRow();
                        		String status = (String) tblOwner.getValueAt(row, 4);
                        		
                        		String[] ownerData = ownerTableRowList.get(row);
                        		//System.out.println("selectedRowData => " + Arrays.toString(ownerData));	
                        		String userId = ownerData[5];
                        		//System.out.println("User ID => "+userId);
                        		     		
                        		if(status.equals("pending")) {        			
                        			getCustomDialog(userId);
                        		}
                        		
                        	}   
                        });
                        scrollPane.setViewportView(tblOwner);
                        //btn for Acc Info
                        JButton lblprofile1 = new JButton("Acc Info");
                        lblprofile1.setBackground( new Color(0, 120, 215));  // green
                        lblprofile1.setForeground(Color.WHITE);
                		
                        lblprofile1.addMouseListener(new MouseAdapter() {
                		    
                		    public void mouseEntered(MouseEvent e) {
                		    	lblprofile1.setBackground(hoverBg);    // Change background
                		    	lblprofile1.setForeground(hoverFg);    // Change text color
                		    }

                		    
                		    public void mouseExited(MouseEvent e) {
                		    	lblprofile1.setBackground(defaultBg);  // Reset background
                		    	lblprofile1.setForeground(defaultFg);  // Reset text color
                		    }
                		});
                        
                        lblprofile1.addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent e) {
                				UserUpdateForm userallinfo;
                				
                					userallinfo = new UserUpdateForm(userId);
                					userallinfo.setVisible(true);
                					//dispose();
                				
                			}
                		});
                        
                        lblprofile1.setBounds(20, 448, 87, 27);
                        panel1.add(lblprofile1);
                        
                        //For btn Logout
                        JButton btnLogout = new JButton("Logout");
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
                        
                        btnLogout.setBounds(670, 448, 87, 27);
                    panel1.add(btnLogout);

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        createOwnerTable();
        createSeekerTable();
        
        fillOwnerData();
        fillSeekerData();
    }
    
    void getCustomDialog(String userId) {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Confirmation for User Activation");
        panel.add(label);
            
        int choice = JOptionPane.showOptionDialog(frame,panel,"User Activation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
        System.out.println("Choice => "+choice);
        if(choice == JOptionPane.YES_OPTION) {
        	//System.out.println("You clicked YES");
        	try {
        		SqlQuery sqlquery = new SqlQuery();
            	boolean update = sqlquery.updateUserStatus(userId);
            	if(update) {
            		//JOptionPane.showMessageDialog(null, "Update User Status Successfully");
            		fillOwnerData();
            		fillSeekerData();
            	}
        	}catch(SQLException e) {
        		System.out.println(e.getMessage());
        	}       	
        } else if(choice == JOptionPane.NO_OPTION) {
        	System.out.println("You clicked NO");
        } else {
        	System.out.println("You close the dialog");
        }
	}

    public void createOwnerTable() {
    	tblOwnerModel.addColumn("Owner Name");
    	tblOwnerModel.addColumn("Phone No");
    	tblOwnerModel.addColumn("Nrc");
    	tblOwnerModel.addColumn("Password");
    	tblOwnerModel.addColumn("Status");    	
		tblOwner.setModel(tblOwnerModel);
		setColumnWidth(0,40,tblOwner);
		setColumnWidth(0,40,tblOwner);
		setColumnWidth(2,40,tblOwner);
		setColumnWidth(3,40,tblOwner);
	}
    
    public void createSeekerTable() {
    	tblSeekerModel.addColumn("Seeker Name");
    	tblSeekerModel.addColumn("Phone No");
    	tblSeekerModel.addColumn("Nrc");
    	tblSeekerModel.addColumn("Password");
    	tblSeekerModel.addColumn("Status");
		tblSeeker.setModel(tblSeekerModel);
		setColumnWidth(0,40,tblSeeker);
		setColumnWidth(0,40,tblSeeker);
		setColumnWidth(2,40,tblSeeker);
		setColumnWidth(3,40,tblSeeker);
		setColumnWidth(4,20,tblSeeker);
	}
    
	public void setColumnWidth(int index,int width,JTable tableName) {
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel)tableName.getColumnModel();
		TableColumn tc = columnModel.getColumn(index);
		tc.setPreferredWidth(width);
	}
    
	public void fillOwnerData() {	
		ownerTableRowList.clear();
		try {
			Statement ste = con.createStatement();
			String query = "select * from user where roleid=3";
			ResultSet rs = ste.executeQuery(query);
			while(rs.next()) {
				String[] ownerData = new String[6];
				ownerData[0] = rs.getString(2);//Owner Name
				ownerData[1] = rs.getString(3);//Phone No
				ownerData[2] = rs.getString(4);//Nrc
				ownerData[3] = rs.getString(8);//Password
				ownerData[4] = rs.getString(10);//Status
				ownerData[5] = rs.getString(1);//UserId
				ownerTableRowList.add(ownerData);
			}
			bindOwnerTableData(ownerTableRowList);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	void bindOwnerTableData(List<String[]> ownerTableRowList) {
	System.out.println("ownerTableRowList =>" + ownerTableRowList);
	tblOwnerModel.setRowCount(0);
		for(String[] ownerData : ownerTableRowList) {
			tblOwnerModel.addRow(ownerData);
		}
		tblOwner.setModel(tblOwnerModel);
	}
	
	public void fillSeekerData() {	
		seekerTableRowList.clear();			
		try {
			Statement ste = con.createStatement();
			String query = "select * from user where roleid=2";
			ResultSet rs = ste.executeQuery(query);
			while(rs.next()) {
				String[] seekerData = new String[6];	
				seekerData[0] = rs.getString(2);//Seeker Name
				seekerData[1] = rs.getString(3);//Phone No
				seekerData[2] = rs.getString(4);//Nrc
				seekerData[3] = rs.getString(8);//Password
				seekerData[4] = rs.getString(10);//Status
				seekerData[5] = rs.getString(1);//UserId
				seekerTableRowList.add(seekerData);
				bindSeekerTableData(seekerTableRowList);
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	void bindSeekerTableData(List<String[]> seekerTableRowList) {
	System.out.println("ownerTableRowList =>" + seekerTableRowList);
	tblSeekerModel.setRowCount(0);
		for(String[] seekerData : seekerTableRowList) {
			tblSeekerModel.addRow(seekerData);
		}
		tblSeeker.setModel(tblSeekerModel);
	}
}

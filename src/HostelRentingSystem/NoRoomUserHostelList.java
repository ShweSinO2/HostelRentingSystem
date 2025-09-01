package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class NoRoomUserHostelList extends JDialog{
	

	
	static Connection con=null;
	static Statement ste;
	static String query,queryUpdate;
	ResultSet rs;
	DBConnection connect = new DBConnection();

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel userHostelModel = new DefaultTableModel();
	SqlQuery sqlquery = new SqlQuery();
	List<String[]> userHostelList = new ArrayList<>();
	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public NoRoomUserHostelList(String userId) throws SQLException {
		
		try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
		
		setTitle("Hostel List");
		setBounds(50, 120, 1200, 600);
		
		try {
			con = connect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1147, 410);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1150, 412);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		createtable();

		filluserhostel(userId);
		
		//for owner btn
		//  Default colors
		Color defaultBg = new Color(0, 120, 215);
		Color defaultFg = Color.WHITE;

		//  Hover colors
		Color hoverBg = Color.decode("#f0f0f0");
		Color hoverFg = new Color(0, 120, 215);
		
		JButton btnNewButton = new JButton("Acc info");
		btnNewButton.setBackground( new Color(0, 120, 215));  // green
		btnNewButton.setForeground(Color.WHITE);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnNewButton.setBackground(hoverBg);    // Change background
		    	btnNewButton.setForeground(hoverFg);    // Change text color
		    }

		    
		    public void mouseExited(MouseEvent e) {
		    	btnNewButton.setBackground(defaultBg);  // Reset background
		    	btnNewButton.setForeground(defaultFg);  // Reset text color
		    }
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdateForm userallinfo;
				
					userallinfo = new UserUpdateForm(userId);
					userallinfo.setVisible(true);
					//dispose();
				
			}
		});
		btnNewButton.setBounds(23, 505, 104, 42);
		contentPanel.add(btnNewButton);
		
		//for btn logout
		JButton btnLogout = new JButton("Log Out");
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
		btnLogout.setBounds(1070, 505, 104, 42);
		contentPanel.add(btnLogout);
		
	}
	
	public void setColumnWidth(int index , int width)
	{
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)table.getColumnModel();
	    TableColumn tc = tcm.getColumn(index);
	    tc.setPreferredWidth(width);
	}
	public void createtable()
	{
		userHostelModel.addColumn("Hostel Name");
		userHostelModel.addColumn("Building No");
		userHostelModel.addColumn("Main Room No");
		userHostelModel.addColumn("Room No");
		userHostelModel.addColumn("Price(kyats)");
		userHostelModel.addColumn("State");
		userHostelModel.addColumn("City");
		userHostelModel.addColumn("Street");
		userHostelModel.addColumn("Start Date");
		userHostelModel.addColumn("End Date");
		userHostelModel.addColumn("Owner Name");
		userHostelModel.addColumn("Owner Phone No.");
		userHostelModel.addColumn("Renting Status");

	   	table.setModel(userHostelModel);
	   	setColumnWidth(0,30);
	   	setColumnWidth(1,20);
	   	setColumnWidth(2,20);
	   	setColumnWidth(3,20);
	   	setColumnWidth(4,20);
	   	setColumnWidth(5,40);
	   	setColumnWidth(6,40);
	   	setColumnWidth(7,40);
		setColumnWidth(8,40);
		setColumnWidth(9,40);
		//setColumnWidth(9,40);
		setColumnWidth(10,40);
		setColumnWidth(11,40);
		setColumnWidth(12,40);
	               
	}
	
	public void filluserhostel(String userId) {	
		userHostelList.clear();
		try {
			Statement ste = con.createStatement();
			//String query = "select hostelname,buildingno,roomno,smroomno,price,state,city,street,startdate,enddate,renting_status  from renting,rentingdetail,room,hostel where renting.rentid=rentingdetail.rentid and rentingdetail.roomid=room.roomid and room.hostelid=hostel.hostelid and renting.userid='"+userId+"' order by renting.rentid";
			String query = "select hostelname,buildingno,roomno,smroomno,price,hostel.state,hostel.city,hostel.street,startdate,enddate,renting_status,user.username,user.phoneno from renting,rentingdetail,room,hostel,user where renting.rentid=rentingdetail.rentid and rentingdetail.roomid=room.roomid and room.hostelid=hostel.hostelid and hostel.userid=user.userid and renting.userid='"+userId+"' order by renting.rentid";
			ResultSet rs = ste.executeQuery(query);
//			while(rs.next()) {
//				String[] userhostel=new String[11];
//				
//				userhostel[0] = rs.getString(1);//hostelname
//				userhostel[1] = rs.getString(2);//buildingno
//				userhostel[2] = rs.getString(3);//roomno
//				userhostel[3] = rs.getString(4);//smroomno
//				userhostel[4] = rs.getString(5);//state
//				userhostel[5] = rs.getString(6);//city
//				userhostel[6] = rs.getString(7); //street
//				userhostel[7] = rs.getString(8);//price
//				userhostel[8] = rs.getString(9);//startdate
//				userhostel[9] = rs.getString(10);//enddate
//				userhostel[10] = rs.getString(11);//renting status
//				
//				userHostelList.add(userhostel);
//				
//			}
			
			while(rs.next()) {
				String[] userhostel=new String[13];
				
				userhostel[0] = rs.getString(1);//hostelname
				userhostel[1] = rs.getString(2);//buildingno
				userhostel[2] = rs.getString(3);//roomno
				userhostel[3] = rs.getString(4);//smroomno
				userhostel[4] = rs.getString(5);//state
				userhostel[5] = rs.getString(6);//city
				userhostel[6] = rs.getString(7); //street
				userhostel[7] = rs.getString(8);//price
				userhostel[8] = rs.getString(9);//startdate
				userhostel[9] = rs.getString(10);//enddate
				userhostel[10] = rs.getString(12);//Owner Name
				userhostel[11] = rs.getString(13);//Owner Phone No
				userhostel[12] = rs.getString(11);//renting status
				
				userHostelList.add(userhostel);
				
			}
			bindUserHostelTableData(userHostelList);
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void bindUserHostelTableData(List<String[]> freeDataList) {
		userHostelModel.setRowCount(0);
		// TODO Auto-generated method stub
		for(String[] data: freeDataList) {
			userHostelModel.addRow(data);
		}
		table.setModel(userHostelModel);
	}
	
//	 public static void main(String[] args) {
//	    	/// TODO Auto-generated method stub
//		 NoRoomUserHostelList up = new NoRoomUserHostelList("25");
//	    			up.setVisible(true);
//		}
}

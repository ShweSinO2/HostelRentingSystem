package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserHostelList extends JDialog {
	
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
	public UserHostelList(String userId) throws SQLException {
		
		setTitle("Hostel List");
		setBounds(155, 120, 1000, 500);
		
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
		panel.setBounds(10, 11, 974, 450);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 964, 450);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		createtable();

		filluserhostel(userId);
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
		userHostelModel.addColumn("Price");
		userHostelModel.addColumn("State");
		userHostelModel.addColumn("City");
		userHostelModel.addColumn("Street");
		userHostelModel.addColumn("Start Date");
		userHostelModel.addColumn("End Date");

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
	               
	}
	
	public void filluserhostel(String userId) {	
		userHostelList.clear();
		try {
			Statement ste = con.createStatement();
			String query = "select hostelname,buildingno,roomno,smroomno,price,state,city,street,startdate,enddate  from renting,rentingdetail,room,hostel where renting.rentid=rentingdetail.rentid and rentingdetail.roomid=room.roomid and room.hostelid=hostel.hostelid and renting.userid='"+userId+"'";
			ResultSet rs = ste.executeQuery(query);
			while(rs.next()) {
				String[] userhostel=new String[10];
				
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
		
	 
}
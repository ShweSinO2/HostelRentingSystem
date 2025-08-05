package HostelRentingSystem;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
 
public class SqlQuery {
	static Connection con = null;
	static Statement ste;
	static String query,queryUpdate;
	ResultSet rs;
	DBConnection connect = new DBConnection();
 
	public SqlQuery() {
		try {
			con = connect.getConnection();
		} 
		catch(SQLException sqle) {
			System.out.print(sqle);
		} catch(Exception e) {
			System.out.print(e);
		} 
	}
 
	//Insert Query
	public static boolean insertData(String tableName,String[] data) {
		if(tableName.equals("hostel")) {
			query = "insert into hostel(hostelname,buildingno,roomno,smroomcount,state,city,street,userid,gendertype) values('"+data[0]+"','"+data[1]+"','"+data[2]+"',"+data[3]+",'"+data[4]+"','"+data[5]+"','"+data[6]+"',"+data[7]+",'"+data[8]+"')";
		} else if(tableName.equals("room")) {
			query = "insert into room(smroomno,available,price,hostelid,description,image_url,file_name,status) values('"+data[0]+"',"+true+","+data[1]+","+data[2]+",'"+data[3]+"','"+data[4]+"','"+data[5]+"','Free')";
		} else if(tableName.equals("user")) {
			query = "insert into user(username,phoneno,nrc,state,city,street,password,roleid,status,gender) values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"',"+data[7]+",'"+data[8]+"','"+data[9]+"')";
		} else if(tableName.equals("renting")) {
			query = "insert into renting(userid,rentDate) values("+data[0]+",'"+data[1]+"')";
		} else if(tableName.equals("payment")) {
			query = "insert into payment(amount,paymenttype) values("+data[0]+",'"+data[1]+"')";
		} else if(tableName.equals("rentingdetail")) {
			query = "insert into rentingdetail values("+data[0]+","+data[1]+","+data[2]+","+data[3]+",'"+data[4]+"','"+data[5]+"')";
		} else if(tableName.equals("reversion")) {
			query = "insert into reversion(roomid,userid,announcedate,reversedate) values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
		}
		try {
			ste = con.createStatement();
			if(ste.executeUpdate(query) == 1) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
 
	//Check PhoneNo Duplicate
	public boolean isPhonenoDuplicate(String phoneno) {
		query = "select * from user where phoneno='"+phoneno+"'"; 
		try {
			ste = con.createStatement();
			rs = ste.executeQuery(query);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	//Check PhoneNo Duplicate profile
	public boolean isPhonenoDuplicateProfile(String phoneno,String userid) {
		query = "select * from user where phoneno='"+phoneno+"' and userid!='"+userid+"'"; 
		try {
			ste = con.createStatement();
			rs = ste.executeQuery(query);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	//Check NRC Duplicate
	public boolean isNrcDuplicate(String code,String city,String nation,String nrcno) {
		
		String nrc = code + city + "(" + nation + ")" + nrcno;
		System.out.println("NRC => "+nrc);
		query = "select * from user where nrc='"+nrc+"'"; 
		try {
			ste = con.createStatement();
			rs = ste.executeQuery(query);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	//Check NRC Duplicate User Profile
	public boolean isNrcDuplicateProfile(String code,String city,String nation,String nrcno,String userid) {
		
		String nrc = code + city + "(" + nation + ")" + nrcno;
		System.out.println("NRC => "+nrc);
		query = "select * from user where nrc='"+nrc+"' and userid!='"+userid+"'"; 
		try {
			ste = con.createStatement();
			rs = ste.executeQuery(query);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
		
	//Get RoomNo for Duplicate
	public boolean isDuplicateRoomno(String[] data) {
		query = "select count(roomno) from hostel where state='"+data[0]+"' and city='"+data[1]+"' and street='"+data[2]+"' and buildingno='"+data[3]+"' and roomno='"+data[4]+"'";
		try {
			ste = con.createStatement();
			rs = ste.executeQuery(query);
			rs.next();
			int count = rs.getInt(1);
			if(count > 0) {
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
 
	//Get Address for Combobox
	public String[] getAddressForChoice(String tableName) {
		try {
			if(tableName.equals("hostel")) {
				rs = connect.sqlSelect("city", "hostel");
			}
			int rowCount = 0;
			while(rs.next()) {
				rowCount++;
			}
			String[] temp = new String[rowCount];
			rs.beforeFirst();
			int i = 0;
			while(rs.next()) {
				temp[i] = rs.getString(1);
				i++;
			}
			return temp;
		} catch(SQLException sqle) {
			System.out.print(sqle);
			return null;
		} catch(Exception e) {
			System.out.print(e);
			return null;
		}
	}
 
	//Get User ID
	public String getUserId(String phoneNo) {
		try {
			String userId;
			ste = con.createStatement();
			query = "select * from user where phoneno='"+phoneNo+"'";
			rs = ste.executeQuery(query);
			rs.next();
			userId = rs.getString(1);
			return userId;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
 
	//Get Hostel ID
	public String getId(String tableName) {
		try {
			String id;
			ste = con.createStatement();
			if(tableName.equals("hostel")) {
				query = "select hostelid from hostel";
			}
			rs = ste.executeQuery(query);
			rs.last();
			id = rs.getString(1);
			return id;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	//Get ID From Rent,Payment
	public String getRentingDetailId(String tableName) {
		String id;
		try {
			if(tableName.equals("renting")) {
				rs = connect.sqlSelect("rentid", "renting");
			} else if(tableName.equals("payment")) {
				rs = connect.sqlSelect("paymentid", "payment");
			}
				
			rs.last();
			id = rs.getString(1);
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
 
	//Get Item Data
	public String[] getItemData(String itemId) {
		try {
			String[] str = new String[6];
			ste = con.createStatement();
			query = "select * from itemdetail where itemId='"+itemId+"'";
			rs = ste.executeQuery(query);
			//System.out.println("ResultSet => "+ rs);
			if(rs.next()) {
				str[0] = rs.getString(1);//itemId
				str[1] = rs.getString(2);//merId
				str[2] = rs.getString(3);//itemName
				str[3] = rs.getString(4);//salePrice
				str[4] = rs.getString(5);//remark
				str[5] = rs.getString(6);//totalQty
			}
			return str;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	//Get User of all Information
		public String[] getUserallInfo(String userid) {
			try {
				String[] str = new String[8];
				ste = con.createStatement();
				query = "select * from user where userid='"+userid+"' and status='active'";
				rs = ste.executeQuery(query);
				
				if(rs.next()) {
					str[0] = rs.getString(2);//Name
					str[1] = rs.getString(3);//Phone
					str[2] = rs.getString(4);//NRC
					str[3] = rs.getString(5);//State
					str[4] = rs.getString(6);//City
					str[5] = rs.getString(7);//Street
					str[6] = rs.getString(8);//Password
					str[7] = rs.getString(11);//Gender
					//str[0] = rs.getString(1);
				}
				return str;
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
			
		}
	
	

 
	//Get User Info
		public String[] getUserInfo(String phoneNo,String password) {
			try {
				String[] str = new String[5];
				ste = con.createStatement();
				query = "select * from user where phoneno='"+phoneNo+"' and password='"+password+"' and status='active'";
				rs = ste.executeQuery(query);
				
				if(rs.next()) {
					str[0] = rs.getString(3);//phoneNo
					//str[3] = rs.getString(4);//nrc
					//str[4] = rs.getString(5);//state
					//str[5] = rs.getString(6);//city
					//str[6] = rs.getString(7);//street
					str[1] = rs.getString(8);//password
					str[2] = rs.getString(9);//roleid
					str[3] = rs.getString(1);//userId
					str[4] = rs.getString(2);//userName
					//str[9] = rs.getString(10);//status
					//str[10] = rs.getString(11);//gender
				}
				return str;
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
		}
		
	//Get Data for Seeker Profile
	public String[] getSeekerProfile(String phoneno,String password) {
		try {
			String[] seekerData = new String[9];
			ste = con.createStatement();
			query = "select hostelname,buildingno,roomno,smroomno,hostel.state,hostel.city,hostel.street,startdate,enddate,rentingdetail.userid,price from renting,rentingdetail,hostel,room,user where rentingdetail.roomid=room.roomid and room.hostelid=hostel.hostelid and renting.rentid=rentingdetail.rentid and renting.userid=user.userid and phoneno='"+phoneno+"' and password='"+password+"'";
			rs = ste.executeQuery(query);
			if(rs.last()) {
				seekerData[0] = rs.getString(1);//hostelname
				seekerData[1] = rs.getString(2);//buildingno
				seekerData[2] = rs.getString(3);//roomno
				seekerData[3] = rs.getString(4);//smroomno
				seekerData[4] = rs.getString(5) + " State/"+rs.getString(6)+" City/"+rs.getString(7)+"Street";//street/city/state
				seekerData[5] = rs.getString(8);//startdate
				seekerData[6] = rs.getString(9);//enddate
				seekerData[7] = rs.getString(10);//owner id
				seekerData[8] = rs.getString(11);//price
			} 
			return seekerData;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	//Get Hostel Data
	public ArrayList<String[]> getHostelData() {
		try {
			ArrayList<String[]> hostelList = new ArrayList<String[]>();
			ste = con.createStatement();
			query = "select hostelname,smroomno,hostel.street,hostel.city,hostel.state,price,gendertype,username,phoneno,room.roomid,room.file_name,room.image_url,room.description from hostel,room,user where hostel.hostelid=room.hostelid and hostel.userid=user.userid and available=true";
			rs = ste.executeQuery(query);
			
			while(rs.next()) {
				String[] str = new String[11];
				str[0] = rs.getString(1);//hostelname
				str[1] = rs.getString(2);//smroomno
				str[2] = rs.getString(3) +" Street/"+ rs.getString(4) +"/"+ rs.getString(5);//street/city/state
				str[3] = rs.getString(6);//price
				str[4] = rs.getString(7);//gendertype
				str[5] = rs.getString(8);//owner username
				str[6] = rs.getString(9);//owner password
				str[7] = rs.getString(10);//roomid
				str[8] = rs.getString(11); //room file name
				str[9] = rs.getString(12); //room image_url
				str[10] = rs.getString(13); //description
				hostelList.add(str);
				//System.out.println("ResultSet => "+ rs.getString(2));
			}
			
			return hostelList;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	//Get Search Hostel Data
		public ArrayList<String[]> getSearchData(String city) {
			try {
				ArrayList<String[]> searchList = new ArrayList<String[]>();
				ste = con.createStatement();
				query = "select hostelname,smroomno,hostel.street,hostel.city,hostel.state,price,gendertype,username,phoneno,room.roomid,room.file_name,room.image_url,room.description from hostel,room,user where hostel.hostelid=room.hostelid and hostel.userid=user.userid and available=true and hostel.city='"+city+"'";
				System.out.println("Query => "+query);
				rs = ste.executeQuery(query);
				
				while(rs.next()) {
					String[] str = new String[11];
					str[0] = rs.getString(1);//hostelname
					str[1] = rs.getString(2);//smroomno
					str[2] = rs.getString(3) +" Street/"+ rs.getString(4) +"/"+ rs.getString(5);//street/city/state
					str[3] = rs.getString(6);//price
					str[4] = rs.getString(7);//gendertype
					str[5] = rs.getString(8);//owner username
					str[6] = rs.getString(9);//owner password
					str[7] = rs.getString(10);//roomid
					str[8] = rs.getString(11); // room image file name
					str[9] = rs.getString(12); //room image url
					str[10] = rs.getString(13); // room description
					searchList.add(str);
					//System.out.println("ResultSet => "+ rs.getString(2));
				}
				
				return searchList;
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
		}
	
	//Update Room Available
	public boolean updateRoom(String roomId,boolean flag) throws SQLException {
		try {
			String query = "update room set available="+flag+", status = 'Pending' where roomid="+roomId+"";
			boolean update = connect.executeSql(query);
			if(update) {
				System.out.println("Room Update Success");
			}
			return update;
		} catch(Exception e) {
			System.out.print(e);
			return false;
		}		
	}
	
	//Update Status when admin approved
	public boolean updateUserStatus(String userId) throws SQLException {
		try {
			String query = "update user set status='active' where userid='"+userId+"'";
			boolean update = connect.executeSql(query);
			if(update) {
				System.out.println("Status Update Success");
			}
			return update;
		} catch(Exception e) {
			System.out.print(e);
			return false;
		}		
	}
	
	//Update Room Price 
		public boolean updatePrice(String price,String roomid) {
			try {
				ste = con.createStatement();
				query = "update room set price='"+price+"'where roomid='"+roomid+"'";
				if(ste.executeUpdate(query) == 1) {
					return true;
				} else {
					return false;
				}
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
		}
		
		//Update Room Status 
		public boolean updateStatus(String status,String roomid) {
			try {
				ste = con.createStatement();
				query = "update room set available=true, status='"+status+"'where roomid='"+roomid+"'";
				if(ste.executeUpdate(query) == 1) {
					return true;
				} else {
					return false;
				}
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
		}
		
	//Get OwnerName 
	public String[] getOwnerData(String userId) {
		try {
			String[] data = new String[2];
			ste = con.createStatement();
			query = "select username,phoneno from user where userid='"+userId+"'";
			rs = ste.executeQuery(query);
			if(rs.next()) {
				data[0] = rs.getString(1);//ownername
				data[1] = rs.getString(2);//phoneno
				System.out.println("Stay Data => "+data[0]+"////"+data[1]);
			}
			return data;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	//Get Room Id According to roomNo 
		public String getRoomId(String roomNo) {
			try {
				String roomId;
				ste = con.createStatement();
				query = "select roomId from room where smroomno='"+roomNo+"'";
				rs = ste.executeQuery(query);
				rs.next();
				roomId = rs.getString(1);
				return roomId;
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
		}
		
	//Get Password According to phoneNo 
		public String getPassword(String phoneNo) {
			try {
				String password;
				ste = con.createStatement();
				query = "select password from user where phoneno='"+phoneNo+"'";
				rs = ste.executeQuery(query);
				rs.next();
				password = rs.getString(1);
				return password;
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
		}

	//Delete Small Room No 
	public boolean deleteRoom(String smallroomno,String hostelid) {
		String deleteHostel = hostelid;
		try {
			int count;
			ste = con.createStatement();
			query = "delete from room where smroomno='"+smallroomno+"'";
			if(ste.executeUpdate(query) == 1) {
				queryUpdate = "select count(hostelid) from room where hostelid='"+hostelid+"'";
				rs = ste.executeQuery(queryUpdate);
				rs.next();
				count = rs.getInt(1);
				if(count == 0) {
					String queryDelete = "delete from hostel where hostelid='"+deleteHostel+"'";
					if(ste.executeUpdate(queryDelete) == 1) {
						System.out.println("Delete HostelId");					
					}
					//System.out.println("Delete HostelId");	
				} else {
					String updateCount = "update hostel set smroomcount="+count+" where hostelid="+deleteHostel+"";
					if(ste.executeUpdate(updateCount) == 1) {
						System.out.println("Update Small Room Count");	
					} 
				}
				System.out.println("Count HostelId => "+count);
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	//Get user hostel list according to userid	
	public String[] getuserhostel(String userid) {
		try {
			String[] userhostel = new String[8];
			ste = con.createStatement();
			query = "select hostelname,buildingno,roomno,smroomno,state,city,street,price,startdate,enddate  from renting,rentingdetail,room,hostel where renting.rentid=rentingdetail.rentid and rentingdetail.roomid=room.roomid and room.hostelid=hostel.hostelid and renting.userid='"+userid+"'";
			rs = ste.executeQuery(query);
			while(rs.next()) {
				userhostel[0] = rs.getString(1);//hostelname
				userhostel[1] = rs.getString(2);//buildingno
				userhostel[2] = rs.getString(3);//roomno
				userhostel[3] = rs.getString(4);//smroomno
				userhostel[4] = rs.getString(5)+"/" + rs.getString(6)+"/"+rs.getString(7);//street/city/state
				userhostel[5] = rs.getString(8);//price
				userhostel[6] = rs.getString(9);//startdate
				userhostel[7] = rs.getString(10);//enddate
			}
			return userhostel;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	// for user update
	public boolean updateUser(String userid, String[] userData) {
	    String query = "UPDATE user SET username=?, phoneno=?, nrc=?, state=?, city=?, street=?, password=?, gender=? WHERE userid=?";
	    try (PreparedStatement pst = con.prepareStatement(query)) {
	        pst.setString(1, userData[0]);  // name
	        pst.setString(2, userData[1]);  // phoneno
	        pst.setString(3, userData[2]);  // nrc
	        pst.setString(4, userData[3]);  // state
	        pst.setString(5, userData[4]);  // city
	        pst.setString(6, userData[5]);  // street
	        pst.setString(7, userData[6]);  // password
	        pst.setString(8, userData[7]);  // gender
	        pst.setString(9, userid);       // WHERE id = ?

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}

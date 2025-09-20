package HostelRentingSystem;

import java.sql.*;
import java.util.*;
 
public class DBConnection {
	public static Connection con = null;
	private final String CONNECTION = "jdbc:mysql://localhost:3307/hosteldb?characterEncoding=latin1&useConfigs=maxPerformance";
	private final String PASSWORD = "";
 
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	public Connection getConnection() throws SQLException {
		if(con == null) {
			con = DriverManager.getConnection(CONNECTION,"root",PASSWORD);
		}
		return con;
	}
 
	public Connection stopConnection() {
		return null;
	}
 
	public ResultSet sqlSelect(String field,String table) {
		ResultSet rs = null;
		try {
			Statement ste = getConnection().createStatement();
			rs = ste.executeQuery("Select distinct "+field+" "
					+ "from "+table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
 
	public boolean executeSql(String sql) throws ClassNotFoundException {
		try {
			Statement ste = getConnection().createStatement();
			ste.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			DBConnection cls = new DBConnection();
			cls.getConnection();
			System.out.println("Connect Successfully");
		}catch(Exception e) {
			System.out.print(e);
		}
	}
 
}

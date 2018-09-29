package jdbcBank.DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import jdbcBank.Connection.ConnFactory;
import jdbcBank.DAO.CustomerDAO;
import jdbcBank.Objects.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	public boolean createCustomer() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	public Customer getCustomerByName(String fname, String lname) throws SQLException {
		
		Customer C = null;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETCUSTOMERBYNAME(?,?))";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,fname);
			stmt.setString(2, lname);
			ResultSet rs = stmt.executeQuery();
			
			long tCustomerID = 0L;
			String tFName = null;
			String tLName = null;
			Date tBDate = null;
			String tPhoneNumber = null;
			long tLoginID = 0L;
			
			while(rs.next()) {
				tCustomerID =  rs.getLong(1);
				tFName = rs.getString(2);
				tLName = rs.getString(3);
				tBDate = rs.getDate(4);
				tPhoneNumber = rs.getString(5);
				tLoginID = rs.getLong(6);
			}
			
			if(tCustomerID != 0L && tFName != null && tLName != null && tBDate != null && tPhoneNumber != null && tLoginID != 0L) {
				C = new Customer(tCustomerID,tFName,tLName,tBDate,tPhoneNumber,tLoginID);
				return C;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getCustomerByUsernamePassword(String username, String password) throws SQLException {

		Customer C;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETCUSTOMERBYUSERNAMEPASSWORD(?,?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		
		long tCustomerID = 0L;
		String tFName = null;
		String tLName = null;
		Date tBDate = null;
		String tPhoneNumber = null;
		long tLoginID = 0L;
		
		while(rs.next()) {
			tCustomerID =  rs.getLong(1);
			tFName = rs.getString(2);
			tLName = rs.getString(3);
			tBDate = rs.getDate(4);
			tPhoneNumber = rs.getString(5);
			tLoginID = rs.getLong(6);
		}
		
		if(tCustomerID != 0L && tFName != null && tLName != null && tBDate != null && tPhoneNumber != null && tLoginID != 0L) {
			C = new Customer(tCustomerID,tFName,tLName,tBDate,tPhoneNumber,tLoginID);
			return C;
		}
		return null;
	}

	public long getCustomerID(String username, String password) throws SQLException {
		Customer C;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETCUSTOMERBYUSERNAMEPASSWORD(?,?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		
		long tCustomerID = 0L;
		while(rs.next()) {
			tCustomerID =  rs.getLong(1);
		}
		return tCustomerID;
	}
	
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteCustomerRecord() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateCustomerInfo(String newFName, String newLName, String newPhoneNum, 
			String newPWord, long customerID) {
		Connection conn = cf.getConnection();
		String sql = "{ call UPDATECUSTOMERINFO(?,?,?,?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setString(1,newFName);
			call.setString(2,newLName);
			call.setString(3, newPhoneNum);
			call.setString(4, newPWord);
			call.setLong(5, customerID );
			call.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}

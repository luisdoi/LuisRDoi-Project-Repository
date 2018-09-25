package jdbcBank.DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Connection.ConnFactory;
import jdbcBank.DAO.LoginInfoDAO;
import jdbcBank.Objects.Customer;
import jdbcBank.Objects.Employee;
import jdbcBank.Objects.LoginInfo;



public class LoginInfoDAOImpl implements LoginInfoDAO  {

	public static ConnFactory cf = ConnFactory.getInstance();

	// Creates record in LoginInfo AND Customer table in database
	public boolean createLoginRecord(Customer C, LoginInfo L) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTCUSTOMER(?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1,C.getFirstName());
		call.setString(2,C.getLastName());
		call.setDate(3, C.getBirthdate());
		call.setString(4, C.getPhoneNumber());
		call.setString(5, L.getUsername());
		call.setString(6, L.getPassword());
		call.executeQuery();
		return true;
	}
	
	public boolean createLoginRecord(Employee E, LoginInfo L) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTEMPLOYEE(?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1,E.getFirstName());
		call.setString(2,E.getLastName());
		call.setDate(3, E.getBirthdate());
		call.setString(4, E.getPhoneNumber());
		call.setString(5, E.getTitle());
		call.setString(6, L.getUsername());
		call.setString(7, L.getPassword());
		call.executeQuery();
		return true;
	}
	public boolean doesUsernameExist(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT DOESUSERNAMEEXIST(?) FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,username);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
		if(rs.getInt(1) == 0)	
			return false;
		else
		if(rs.getInt(1) == 1)
			return true;
		}
		return false;
	}

	public LoginInfo getLoginInfo(String username, String password) throws SQLException {
		LoginInfo tempLoginInfoObj;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETLOGININFO(?,?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		
		long tempLoginId = 0L;
		String tempUsername = "";
		String tempPassword = "";
		while(rs.next()) {
			tempLoginId =  rs.getLong(1);
			tempUsername = rs.getString(2);
			tempPassword = rs.getString(3);
		}
		
		if(tempLoginId != 0L && !tempUsername.equals("") && !tempPassword.equals("")) {
			tempLoginInfoObj = new LoginInfo(tempLoginId,username,password);
			return tempLoginInfoObj;
		}
		return null;
	}

	public ArrayList<LoginInfo> getAllLoginInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUsername() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updatePassword() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteLoginRecord() {
		// TODO Auto-generated method stub
		return false;
	}

}

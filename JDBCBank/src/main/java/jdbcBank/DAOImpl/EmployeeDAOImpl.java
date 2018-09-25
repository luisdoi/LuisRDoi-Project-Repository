package jdbcBank.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Connection.ConnFactory;
import jdbcBank.DAO.EmployeeDAO;
import jdbcBank.Objects.Customer;
import jdbcBank.Objects.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public boolean createEmployee() throws SQLException {
		return false;
	}

	public Employee getEmployee(String username, String password) throws SQLException {
		Employee E;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETEMPLOYEEBYUSERNAMEPASSWORD(?,?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		
		long tEmployeeID = 0L;
		String tFName = null;
		String tLName = null;
		Date tBDate = null;
		String tPhoneNumber = null;
		String tTitle = null;
		long tLoginID = 0L;
		
		while(rs.next()) {
			tEmployeeID =  rs.getLong(1);
			tFName = rs.getString(2);
			tLName = rs.getString(3);
			tBDate = rs.getDate(4);
			tPhoneNumber = rs.getString(5);
			tTitle = rs.getString(6);
			tLoginID = rs.getLong(7);
		}
		
		if(tEmployeeID != 0L && tFName != null && tLName != null && tBDate != null && tPhoneNumber != null && tTitle != null && tLoginID != 0L) {
			E = new Employee(tEmployeeID,tFName,tLName,tBDate,tPhoneNumber,tTitle,tLoginID);
			return E;
		}
		return null;
	}

	public ArrayList<Employee> getAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteEmployeeRecord() {
		// TODO Auto-generated method stub
		return false;
	}

}

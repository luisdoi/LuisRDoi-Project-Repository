package jdbcBank.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Objects.Customer;
import jdbcBank.Objects.Employee;
import jdbcBank.Objects.LoginInfo;

public interface LoginInfoDAO {

	// CRUD OPERATIONS - Create, Read, Update, Delete
	
	// CRUD - CREATE
	public abstract boolean createLoginRecord(Customer C, LoginInfo L) throws SQLException;
	public abstract boolean createLoginRecord(Employee E, LoginInfo L) throws SQLException;

	// CRUD - READ
	public abstract boolean doesUsernameExist(String username) throws SQLException;
	public abstract LoginInfo getLoginInfo(String username, String password) throws SQLException;
	public abstract ArrayList<LoginInfo> getAllLoginInfo() throws SQLException;

	// CRUD - UPDATE
	public abstract boolean updateUsername();
	public abstract boolean updatePassword();
	
	// CRUD - DELETE
	public abstract boolean deleteLoginRecord();
	
}

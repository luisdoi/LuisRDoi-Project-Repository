package jdbcBank.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Objects.Account;

public interface AccountDAO {
	
	// CRUD OPERATIONS - Create, Read, Update, Delete
	
	// CRUD - CREATE
	public abstract boolean createAccount(long customerID, int typeID) throws SQLException;
	
	// CRUD - READ
	public abstract Account getAccount(long accountID) throws SQLException;
	public abstract ArrayList<Account> getCustomerAccounts(long customerID) throws SQLException;
	public abstract ArrayList<Account> getAllAccounts() throws SQLException; 
	public abstract ArrayList<Account> getPendingStatusAccounts() throws SQLException;
	public abstract boolean verifyAccountActive(long accountID) throws SQLException;
	public abstract String getAccountStatus(long accountID) throws SQLException;
	public abstract boolean verifyAvailableFunds(long accoundID, double amountToWithdraw) throws SQLException;
	
	// CRUD - UPDATE
	public abstract boolean deposit(long accountID, long customerID, long employeeID, double amount) throws SQLException;
	public abstract boolean withdraw(long accountID, long customerID, long employeeID, double amount) throws SQLException;
	public abstract boolean transfer(long accountIDFrom, long accountIDTo, long customerID, long employeeID, double amount) throws SQLException;
	public abstract boolean updateAccountStatus(long accountID, int statusID) throws SQLException;
	// CRUD - DELETE
	public abstract boolean deleteAccount(long accountID) throws SQLException;
	
}

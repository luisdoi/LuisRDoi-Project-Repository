package jdbcBank.DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Connection.ConnFactory;
import jdbcBank.DAO.AccountDAO;
import jdbcBank.Objects.Account;

public class AccountDAOImpl implements AccountDAO {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public boolean createAccount(long customerID, int typeID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTACCOUNT(?,?)}";
		CallableStatement call = conn.prepareCall(sql);
		call.setLong(1, customerID);
		call.setInt(2, typeID);
		call.execute();		
		return false;
	}

	public Account getAccount(long accountID) throws SQLException {
		Account A = null;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETACCOUNT(?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, accountID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			A = new Account(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getInt(4));
		}
		return A;
	}

	public ArrayList<Account> getCustomerAccounts(long customerID) throws SQLException {
		ArrayList<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETCUSTOMERACCOUNTS(?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, customerID);
		ResultSet rs = stmt.executeQuery();
		Account A = null;
		while(rs.next()) {
			A = new Account(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getInt(4));
			accountList.add(A);
		}
		return accountList;
	}

	public ArrayList<Account> getAllAccounts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Account> getPendingStatusAccounts() throws SQLException {
		ArrayList<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETPENDINGACCOUNTS())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Account A = null;
		while(rs.next()) {
			A = new Account(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getInt(4));
			accountList.add(A);
		}
		return accountList;
	}

	public boolean verifyAccountActive(long accountID) throws SQLException {
		int isActive = 0;
		Connection conn = cf.getConnection();
		String sql = "SELECT VERIFYACCOUNTACTIVE(?) FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, accountID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			isActive = rs.getInt(1);
		}
		if(isActive == 0)
			return false;
		else
			return true;
	}

	public String getAccountStatus(long accountID) throws SQLException {
		String stat = null;
		Connection conn = cf.getConnection();
		String sql = "SELECT GETACCOUNTSTATUS(?) FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, accountID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			 stat = rs.getString(1);
		}
		return stat;
	}

	public boolean verifyAvailableFunds(long accountID, double amountToWithdraw) throws SQLException {
		int isAvailable = 0;
		Connection conn = cf.getConnection();
		String sql = "SELECT VERIFYAVAILABLEFUNDS(?,?) FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, accountID);
		stmt.setDouble(2, amountToWithdraw);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			 isAvailable = rs.getInt(1);
		}
		if(isAvailable == 0)
			return false;
		else
			return true;
	}

	public boolean deposit(long accountID, long customerID, long employeeID, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call DEPOSIT(?,?,?,?)}";
		CallableStatement call = conn.prepareCall(sql);
		call.setLong(1, accountID);
		call.setLong(2, customerID);
		call.setLong(3, employeeID);
		call.setDouble(4, amount);
		call.execute();	
		return true;
	}

	public boolean withdraw(long accountID, long customerID, long employeeID, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call WITHDRAW(?,?,?,?)}";
		CallableStatement call = conn.prepareCall(sql);
		call.setLong(1, accountID);
		call.setLong(2, customerID);
		call.setLong(3, employeeID);
		call.setDouble(4, amount);
		call.execute();	
		return true;
	}

	public boolean transfer(long accountIDFrom, long accountIDTo, long customerID, long employeeID, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call TRANSFER(?,?,?,?,?)}";
		CallableStatement call = conn.prepareCall(sql);
		call.setLong(1, accountIDFrom);
		call.setLong(2, accountIDTo);
		call.setLong(3, customerID);
		call.setLong(4, employeeID);
		call.setDouble(5, amount);
		call.execute();	
		return true;
	}

	public boolean updateAccountStatus(long accountID, int statusID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call UPDATEACCOUNTSTATUS(?,?)}";
		CallableStatement call = conn.prepareCall(sql);
		call.setLong(1, accountID);
		call.setInt(2, statusID);
		call.execute();	
		return true;
	}
	
	public boolean deleteAccount(long accountID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM BANK_ACCOUNTS WHERE BANK_ACCOUNTS.ACCOUNTID = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, accountID);
		stmt.executeQuery();
		return true;
	}

	

}

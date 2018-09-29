package jdbcBank.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Connection.ConnFactory;
import jdbcBank.DAO.TransactionDAO;
import jdbcBank.Objects.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	public boolean createTransaction() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Transaction> getEmployeeTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Transaction> getCustomerTransactions(long custID) throws SQLException {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM TABLE(GETCUSTOMERTRANSACTIONS(?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, custID);
		ResultSet rs = stmt.executeQuery();
		long transactionID = 0L;
		int transactionTypeID = 0;
		long customerID = 0L;
		long employeeID = 0L;
		double oldBalance;
		double newBalance;
		long accountID = 0L;
		
		while(rs.next()) {
			transactionID = rs.getLong(1);
			transactionTypeID = rs.getInt(2);
			customerID = rs.getLong(3);
			employeeID = rs.getLong(4);
			oldBalance = rs.getDouble(5);
			newBalance = rs.getDouble(6);
			accountID = rs.getLong(7);
			if(transactionID != 0L && transactionTypeID != 0 && customerID != 0L && accountID != 0L) {
				Transaction T = new Transaction(transactionID, transactionTypeID, customerID, employeeID, oldBalance, newBalance, accountID);
				transactionList.add(T);
			}
		}
		if(transactionList == null || transactionList.isEmpty())
			return null;
		
		return transactionList;
	}

	public ArrayList<Transaction> getAllTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteTransaction() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}

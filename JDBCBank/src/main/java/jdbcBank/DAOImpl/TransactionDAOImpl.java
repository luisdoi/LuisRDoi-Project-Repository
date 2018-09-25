package jdbcBank.DAOImpl;

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

	public ArrayList<Transaction> getCustomerTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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

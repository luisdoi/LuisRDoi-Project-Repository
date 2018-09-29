package jdbcBank.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.Objects.Transaction;

public interface TransactionDAO {
	
	// CRUD OPERATIONS - Create, Read, Update, Delete
	
	// CRUD - Create
	public abstract boolean createTransaction() throws SQLException;
	
	// CRUD - Read
	public abstract ArrayList<Transaction> getEmployeeTransactions() throws SQLException;
	public abstract ArrayList<Transaction> getCustomerTransactions(long customerID) throws SQLException;
	public abstract ArrayList<Transaction> getAllTransactions() throws SQLException;
	// CRUD - Update
	// ...
	
	// CRUD - Delete
	public abstract boolean deleteTransaction() throws SQLException;
	


}

package jdbcBank.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import jdbcBank.Objects.Customer;

public interface CustomerDAO {

	// CRUD OPERATIONS - Create, Read, Update, Delete
	
	// CRUD - Create
	public abstract boolean createCustomer() throws SQLException;
	
	// CRUD - Read
	public abstract Customer getCustomerByUsernamePassword(String username, String password) throws SQLException;
	public abstract Customer getCustomerByName(String firstName, String lastName) throws SQLException;
	public long getCustomerID(String username, String password) throws SQLException;
	public abstract ArrayList<Customer> getAllCustomers() throws SQLException;

	// CRUD - Update
	// ...
	
	// CRUD - Delete
	public abstract boolean deleteCustomerRecord() throws SQLException;
}

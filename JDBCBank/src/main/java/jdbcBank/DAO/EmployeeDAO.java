package jdbcBank.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import jdbcBank.Objects.Employee;


public interface EmployeeDAO {

	// CRUD OPERATIONS - Create, Read, Update, Delete

	// CRUD - Create
	public abstract boolean createEmployee() throws SQLException;
	
	// CRUD - Read
	public abstract Employee getEmployee(String username, String password) throws SQLException;
	public abstract ArrayList<Employee> getAllEmployees() throws SQLException;

	// CRUD - Update
	// ...
	
	// CRUD - Delete
	public abstract boolean deleteEmployeeRecord();
	
}

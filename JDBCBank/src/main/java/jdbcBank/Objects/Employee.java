package jdbcBank.Objects;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import jdbcBank.Objects.Customer;
import jdbcBank.DAOImpl.AccountDAOImpl;

public class Employee {
	
	
	private long employeeID;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String phoneNumber;
	private String title;
	private long loginID;
	
	public static AccountDAOImpl accountDAO = new AccountDAOImpl();
	
	// Constructor
	public Employee(long employeeID, String firstName, String lastName, Date birthdate, String phoneNumber,
			String title, long loginID) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.title = title;
		this.loginID = loginID;
	}

	
	public boolean viewCustomerInfo(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			Account.showCustomerAccounts(C.getCustomerID());
			return true;
		}
		return false;
	}
	
	public boolean manageOpenAppsForAccounts(Scanner SC) {
		ArrayList<Account> accountList = null;
		try {
			accountList = accountDAO.getPendingStatusAccounts();
		} catch (SQLException e) {
			System.out.println("ERROR - SQL Error attempting to get pending status accounts from database.\n");
			e.printStackTrace();
			return false;
		}
		if(accountList != null) {		
			for(Account A: accountList) {
				
				System.out.println("The following account application needs your attention:\n\n" + A + "\n");
				System.out.println("Please enter: \n '1' to APPROVE\n '2' to DENY\n 'Q' to QUIT");
				String x = SC.nextLine();
				if(x.length() == 1) {
					if(x.equals("Q")) {
						return false;
					}
					try {
						int X = Integer.valueOf(x);
						switch(X) {
						case 1:
							A.updateAccountStatus(A.getAccountID(), Account.convertToStatusID("Active"));
							System.out.println("The following account has been successfully ACTIVATED:\n\n" + A);
							break;
						case 2:
							A.updateAccountStatus(A.getAccountID(), Account.convertToStatusID("Closed"));
							System.out.println("The following account has been successfully CLOSED:\n\n" + A);
							break;
						default:
							System.out.println("ERROR - Your input was invalid. Please try again");
							System.out.println();
							break;
						}
					} catch(NumberFormatException e){
						System.out.println("ERROR - Your input was invalid. Please try again.\n");
						e.printStackTrace();
						return false;
					}
				}
				else {
					System.out.println("ERROR - Your input was invalid. Please try again.\n");
				}
			}	
		}
		else {
			System.out.println("There are no accounts left with a pending status.\n\n");
		}
		return true;
	}
	
	public boolean withdraw(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.withdraw(SC);
			// TODO LOG AS EMPLOYEE
		}
		return false;
	}
	
	public boolean deposit(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.deposit(SC);
			// TODO LOG AS EMPLOYEE
		}
		return false;
	}
	
	public boolean transfer(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.transfer(SC);
			// TODO LOG AS EMPLOYEE
		}
		return false;
	}
	
	public boolean closeAccount(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.withdraw(SC);
		}
		return false;
	}
	
	public boolean deleteAccount(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.deleteCustomerAccount(SC);
		}
		return false;	
	}
	
	public boolean updateCustomerInfo(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.updateCustomerInfo(SC);
		}
		return false;
	}
	
	public boolean viewCustomerTransactions(Scanner SC) {
		Customer C = Customer.getCustomerByName(SC);
		if(C != null) {
			return C.viewTransactionHistory(SC);
		}
		return false;
	}
	
	// Getters and setters
	public long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getLoginID() {
		return loginID;
	}
	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthdate=" + birthdate + ", phoneNumber=" + phoneNumber + ", title=" + title + ", loginID="
				+ loginID + "]";
	}
}

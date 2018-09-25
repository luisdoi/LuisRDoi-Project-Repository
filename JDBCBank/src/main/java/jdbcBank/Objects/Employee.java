package jdbcBank.Objects;

import java.sql.Date;
import java.util.Scanner;

public class Employee {
	
	
	private long employeeID;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String phoneNumber;
	private String title;
	private long loginID;
	
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
		
		return false;
	}
	
	public boolean withdraw(Scanner SC) {
		
		return false;
	}
	
	public boolean deposit(Scanner SC) {
		
		return false;
	}
	
	public boolean transfer(Scanner SC) {
		
		return false;
	}
	
	public boolean closeAccount(Scanner SC) {
		
		return false;
	}
	
	public boolean cancelAccount(Scanner SC) {
		
		return false;
	}
	
	public boolean updateCustomerInformation(Scanner SC) {
		
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

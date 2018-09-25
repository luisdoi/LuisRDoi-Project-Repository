package jdbcBank.Objects;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import jdbcBank.DAOImpl.CustomerDAOImpl;


public class Customer {
	
	private long customerID;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String phoneNumber;
	private long loginID;
	
	public static CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	
	// Constructors
	public Customer(long customerID, String firstName, String lastName, 
					Date birthdate, String phoneNumber, long loginID) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.loginID = loginID;
	}
	
	public static Customer getCustomerByName(Scanner SC) {
		while(true) {
			System.out.println("\nWhat customer are you looking for?."
					+ "\n Enter 'Q' to quit.");
			
			System.out.println("Enter customer's first name:		");
			String x = SC.nextLine();
			String username = null;
			String password = null;
			if(x.length() == 1) {
				if(x.equals("Q")) 
					break;
			}
			else
				username = x;
			System.out.println("Enter customer's last name:			");
			x = SC.nextLine();	
			if(x.length() == 1) {
				if(x.equals("Q")) 
					break;
			}
			else
				password = x;
			Customer C = null;
			try {	
				C = customerDAO.getCustomerByUsernamePassword(username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(C != null) {
				return C;
			}
			else 
				System.out.println("ERROR - Invalid username and password.");
		}
		return null;
	}
		
	public boolean withdraw(Scanner SC) {
		
		if(viewCustomerAccounts()) {
			System.out.println("Which account would you like to WITHDRAW from?\n"
			+ "Please enter corresponding digit.");
			String x = SC.nextLine();
			try {
				int X = Integer.valueOf(x)-1;
				ArrayList<Account>accountList = Account.getCustomerAccounts(this.customerID);
				if(accountList != null) {
					Account A = accountList.get(X);
					System.out.print("How much would you like to WITHDRAW?\n	$");
					try {
						double amount = Double.valueOf(SC.nextLine());
						amount = Math.floor(amount*100)/100;
						if(amount > 0.00d) {
							if(A.verifyAccountActive(A.getAccountID())) {
								if(A.verifyAvailableFunds(A.getAccountID(), amount)) {
									if(A.withdraw(A.getAccountID(),this.getCustomerID(),0L,amount))
										return true;
									else 
										return false;
								}
								else {
									System.out.println("ERROR - Insufficient funds.\n");
								}
							}
							else {
								System.out.println("ERROR - Your account is status is:\t" + A.getStatusIDAsString());
								if(A.getStatusIDAsString().equals("Pending"))
									System.out.println("Please be patient as we are in the process of reviewing "
											+ "your application for this account.\n");
							}
						}
						else {
							System.out.println("ERROR - Please only enter positive values. Do not enter zero or values less than in the hundredths (00/100).\n");
							return false;
						}
					} catch (NumberFormatException e) {
						System.out.println("ERROR - Invalid input. Please only enter digits.\n");
						e.printStackTrace();
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("ERROR - Invalid input. Please only enter digits.\n");
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e) {
				System.out.println("ERROR - Please enter a valid digit.");
			}
		}	
		return false;
	}
	
	public boolean deposit(Scanner SC) {
		
		if(viewCustomerAccounts()) {
			System.out.println("Which account would you like to DEPOSIT to?\n"
			+ "Please enter corresponding digit.");
			String x = SC.nextLine();
			try {
				int X = Integer.valueOf(x)-1;
				ArrayList<Account>accountList = Account.getCustomerAccounts(this.customerID);
				if(accountList != null) {
					Account A = accountList.get(X);
					System.out.print("How much would you like to DEPOSIT?\n		$");
					try {
						double amount = Double.valueOf(SC.nextLine());
						amount = Math.floor(amount*100)/100;
						if(amount > 0.00d) { 
							if(A.verifyAccountActive(A.getAccountID())) {
								if(A.deposit(A.getAccountID(),this.getCustomerID(),0L,amount))
									return true;
								else 
									return false;
							}
							else {
								System.out.println("ERROR - Your account is status is:\t" + A.getStatusIDAsString());
								if(A.getStatusIDAsString().equals("Pending"))
									System.out.println("Please be patient as we are in the process of reviewing "
											+ "your application for this account.\n");
							}
						}
						else {
							System.out.println("ERROR - Please only enter positive values. Do not enter zero or values less than in the hundredths (00/100).\n");
							return false;
						}
					} catch (NumberFormatException e) {
						System.out.println("ERROR - Invalid input. Please only enter digits.\n");
						e.printStackTrace();
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("ERROR - Invalid input. Please only enter digits.\n");
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e) {
				System.out.println("ERROR - Please enter a valid digit.");
			}
		}	
		return false;
	}
	
	
	// TEST FOR TRANSFER WHEN THERE'S ONLY ONE ACCOUNT AVAILABLE
	public boolean transfer(Scanner SC) {
		if(viewCustomerAccounts()) {
			System.out.println("Which account would you like to TRANSFER from?\n");
			String fr = SC.nextLine();
			try {
				int X = Integer.valueOf(fr)-1;
				ArrayList<Account>accountList = Account.getCustomerAccounts(this.customerID);
				if(accountList != null) {
					Account A = accountList.get(X);						
					System.out.println("Which account would you like to TRANSFER to?\n");
					String to = SC.nextLine();
					try {
						int Y = Integer.valueOf(to)-1;
						Account B = accountList.get(Y);
						if(A != B) {
							System.out.print("How much would you like to TRANSFER?\n 	$");
							try {
								double amount = Double.valueOf(SC.nextLine());
								amount = Math.floor(amount*100)/100;
								if(amount > 0.00d) {
									if(A.verifyAvailableFunds(A.getAccountID(), amount )) {
										if(A.verifyAccountActive(A.getAccountID())) {
											if(B.verifyAccountActive(B.getAccountID())) {
												if(A.transfer(A.getAccountID(), B.getAccountID(), this.getCustomerID(), 0L, amount))
													return true;
												else
													return false;
											}
											else {
												System.out.println("ERROR - AccountID: "+ B.getAccountID()+" status is:\t" + B.getStatusIDAsString());
												if(B.getStatusIDAsString().equals("Pending"))
													System.out.println("Please be patient as we are in the process of reviewing "
															+ "your application for this account.\n");
											}
										}
										else {
											System.out.println("ERROR - AccountID: "+ A.getAccountID() +" status is:\t" + A.getStatusIDAsString());
											if(A.getStatusIDAsString().equals("Pending"))
												System.out.println("Please be patient as we are in the process of reviewing "
														+ "your application for this account.\n");
										}
									}
									else {
										System.out.println("ERROR - Insufficient funds in account:	" + A.getAccountID());
									}
								}
								else {
									System.out.println("ERROR - Please only enter positive values. Do not enter zero or values less than in the hundredths (00/100).\n");
									return false;
								}
							} catch (NumberFormatException e) {
								System.out.println("ERROR - Invalid input. Please only enter digits.\n");
								e.printStackTrace();
							}
						}
						else
							System.out.println("ERROR - You cannot TRANSFER to the same account you are transferring from!");
					} catch (NumberFormatException e) {
						System.out.println("ERROR - Invalid input. Please only enter digits.\n");
						e.printStackTrace();
					} catch(IndexOutOfBoundsException e) {
						System.out.println("ERROR - Please enter a valid digit.");
					}
					
				}
			} catch(NumberFormatException e) {
				System.out.println("ERROR - Invalid input. Please only enter digits.\n");
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e) {
				System.out.println("ERROR - Please enter a valid digit.");
			}
		}	
		return false;
	}
	
	public boolean applyForAccount(Scanner SC) {
		
		System.out.println(this.firstName + " " + this.lastName +", you are now attempting to APPLY FOR AN ACCOUNT.");
		System.out.println("Please enter:\n '1' if you would like to APPLY for a Checking account.");
		System.out.println(" '2' if you would like to APPLY for a Savings account.");
		System.out.println(" 'Q' if you would like to exit.");
		
		String s = SC.nextLine();
		if(s.length() == 1) {
			if(s.equals("Q"))
				return false;
			try {
			int x = Integer.valueOf(s);
			switch(x) {
				case 1:
					Account.applyForAccount(this.getCustomerID(), 1);
					System.out.println("Congratulations! You have just applied for a CHECKING account.");
					//System.out.println("Your account number is: " + A.getAccountNumber() + "\n");
					//System.out.println(A);
					return true;
					//break;
				case 2:
					Account.applyForAccount(this.getCustomerID(), 2);
					System.out.println("Congratulations! You have just applied for a SAVINGS account.");
					//System.out.println("Your account number is: " + A.getAccountNumber() + "\n");
					//System.out.println(A);
					return true;
					//break;
				default:
					System.out.println("ERROR - Your input was invalid. Please try again.\n");
					System.out.println();		
					break;
			  	}
			} catch (NumberFormatException e) {
				System.out.println("ERROR - Your input was invalid. Please try again.\n");
				System.out.println();		
			}
		}
		System.out.println("ERROR - Your input was invalid. Please try again.\n");
		return false;
	}
	
	public boolean viewCustomerAccounts() {
		if(!Account.showCustomerAccounts(this.customerID)) {
			System.out.println("ERROR - Sorry, but there are no registered bank accounts for customer: " +
					this.firstName + " " + this.lastName + " Customer ID:" + this.customerID);
			return false;
		}
		return true;
	}
	
	public boolean deleteCustomerAccount(Scanner SC) {
		
		if(viewCustomerAccounts()) {
			System.out.println("Which account would you like to DELETE?\n "
					+ "Please enter corresponding digit.");
			System.out.println(" 'Q' if you would like to exit.");

			String fr = SC.nextLine();
			if(fr.length() == 1) {
				if(fr.equals("Q"))
					return false;
				try {
					int X = Integer.valueOf(fr)-1;
					ArrayList<Account>accountList = Account.getCustomerAccounts(this.customerID);
					if(accountList != null) {
						Account A = accountList.get(X);	
						if(A.verifyAccountActive(A.getAccountID())) {
							if(Math.floor(A.getBalance()*100)/100 == 0.00) {	// rounding to hundredth of a dollar
								if(A.deleteAccount(A.getAccountID()))
									System.out.println("You have successfully deleted accountID: "+ A.getAccountID());
								else
									System.out.println("ERROR - Unexpected error attempting to delete account.\n"
									+ "AccountID: " + A.getAccountID() + " was NOT deleted!");
							}
							else
								System.out.println("ERROR - You CANNOT delete an account with a balance.\n");
						}
						else {
							System.out.println("ERROR - AccountID: " + A.getAccountID() + " has a " + 
						A.getStatusIDAsString() + " status.\n You can only delete ACTIVE accounts.\n");	
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("ERROR - Invalid input.");
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean viewTransactionHistory(Scanner SC) {
		
		
		return false;
	}

	
	
	
	
	
	
	// Getters and Setters
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
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
	public long getLoginID() {
		return loginID;
	}
	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthdate=" + birthdate + ", phoneNumber=" + phoneNumber + ", loginID=" + loginID + "]";
	}
}

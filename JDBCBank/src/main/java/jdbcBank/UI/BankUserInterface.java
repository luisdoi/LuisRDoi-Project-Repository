package jdbcBank.UI;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import jdbcBank.DAOImpl.CustomerDAOImpl;
import jdbcBank.DAOImpl.EmployeeDAOImpl;
import jdbcBank.DAOImpl.LoginInfoDAOImpl;
import jdbcBank.DAOImpl.TransactionDAOImpl;
import jdbcBank.Objects.Customer;
import jdbcBank.Objects.Employee;
import jdbcBank.Objects.LoginInfo;


public class BankUserInterface {

	public CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	public EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	public LoginInfoDAOImpl logininfoDAO = new LoginInfoDAOImpl();
	public TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
	
	
	public void showCustomerMenu(Scanner SC, Customer C) {

		while(true) {
			System.out.println("\nAs a customer, would you like to: \n '1' Withdraw \n '2' Deposit \n '3' Transfer to Account\n"+
			" '4' View Accounts\n '5' Apply for an Account\n '6' Delete an Account\n '7' View Transaction History\n 'Q' Log Off\n" + 
			" Please enter choice:");
			String x = SC.nextLine();
			
			if(x.length() == 1) {
				if(x.equals("Q"))
					break;
				try {
					int X = Integer.valueOf(x);
					switch(X) {
					case 1:
						C.withdraw(SC);
						break;
					case 2:
						C.deposit(SC);
						break;
					case 3:
						C.transfer(SC);
						break;
					case 4:
						C.viewCustomerAccounts();
						break;
					case 5:
						C.applyForAccount(SC);
						break;
					case 6:
						C.deleteCustomerAccount(SC);
						break;
					case 7:
						C.viewTransactionHistory(SC);
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
						System.out.println("ERROR - Your input was invalid. Please try again");
						System.out.println();					
				}
			}
		}
	}	
	
	
	public void showEmployeeMenu(Scanner SC, Employee E) {
		// bank admin can view/edit accounts (withdraw,deposit,cancel,transferring, approve/deny accounts)
		// An Admin can view, create, update, and delete all users.
		while(true) {
			System.out.println("\nAs an bank administrator, would you like to: \n '1' View customer info \n" +
					" '2' Approve/Deny open applications for accounts\n '3' Withdraw from Customer Account\n" +
					" '4' Deposit to Customer Account\n '5' Transfer to/from Customer Accounts\n" + 
					" '6' Close Customer Account\n '7' Delete Customer Account\n" +
					" '8' Update Customer Information\n '9' View Customer Transaction History\n" +
					" 'Q' Log Off\n Please Enter Choice:\n");
			String x = SC.nextLine();
			
			if(x.length() == 1) {
				if(x.equals("Q"))
					break;
				try {
					int X = Integer.valueOf(x);
					switch(X) {
					case 1:
						E.viewCustomerInfo(SC);
						break;
					case 2:
						E.manageOpenAppsForAccounts(SC);
						break;
					case 3:
						E.withdraw(SC);
						break;
					case 4:
						E.deposit(SC);
						break;
					case 5:
						E.transfer(SC);
						break;
					case 6:
						E.closeAccount(SC);
						break;
					case 7:
						E.deleteAccount(SC);
						break;
					case 8:
						E.updateCustomerInfo(SC);
						break;
					case 9:
						E.viewCustomerTransactions(SC);
					default:
						break;
						
					}
				} catch (NumberFormatException e) {
						System.out.println("ERROR - Invalid input. Please only enter digits.");
						System.out.println();					
				}
			}
		}
	}
	
	public void loginToSystem(Scanner SC) {
		while(true) {
			System.out.println("\nLOGIN - Who are you?\n Please enter your username and password."
					+ "\n Enter 'Q' to quit.");
			
			System.out.println("Enter Username:		");
			String x = SC.nextLine();
			String username = null;
			String password = null;
			if(x.length() == 1) {
				if(x.equals("Q")) 
					break;
			}
			else
				username = x;
			System.out.println("Enter Password:		");
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
				System.out.println("Welcome! You have successfully signed in as CUSTOMER: " 
				+ C.getFirstName() + " " + C.getLastName() + " with USERNAME: " + username + "\n");
				showCustomerMenu(SC,C);
			}
			else {
				Employee E = null;
				try {
					E = employeeDAO.getEmployee(username, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(E != null)	{
					System.out.println("Welcome! You have successfully signed in as EMPLOYEE: "
					+ E.getFirstName() + " " + E.getLastName() + " with USERNAME: " + username + "\n");
					showEmployeeMenu(SC,E);
				}
				else
					System.out.println("ERROR - Invalid username and password. Try again.");
			}
		}
	}

	public void createCustomerAccount(Scanner SC) {
		String fName = null;
		String lName = null;
		String uName = null;
		String pWord = null;
		Date bdate = null;
		String phoneNumber = null;
		System.out.println("You are now creating a customer account!");

		while(true) {
					
			if(fName == null) {
				System.out.println("Please enter your first name.");
				fName = SC.nextLine();
			}
			else
			if(lName == null) {
				System.out.println("Please enter your last name.");
				lName = SC.nextLine();
			}
			else
			if(bdate == null) {
				System.out.println("Please enter your date of birth as YYYY-MM-DD.");
				String x = SC.nextLine();
				try {
					bdate = Date.valueOf(x);
				} catch(IllegalArgumentException e) {
					bdate = null;
					System.out.println("Error - Please input your date of birth as YYYY-MM-DD format.");
				}
			}
			else
			if(phoneNumber == null) {
				System.out.println("Please enter your phone number.");
				
				phoneNumber = SC.nextLine();
				try{
					Long.valueOf(phoneNumber);
				} catch (NumberFormatException e) {
					System.out.println("Error - Please only enter digits.");
					phoneNumber = null;
				}
				
			}
			else
			if(uName == null) {
				System.out.println("Please enter your new username");
				uName = SC.nextLine();				
				try {
					if(logininfoDAO.doesUsernameExist(uName)) {
						uName = null;
						System.out.println("Sorry, but username is TAKEN! Please enter a different username.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			if(pWord == null) {
				System.out.println("Please enter your new password");
				pWord = SC.nextLine();
			}
			else
			if(fName != null && lName != null && uName != null && pWord != null) {
				//long tempCustomerID = Customer.genRandomIDNum(myBank.customerIDList);
				//Customer C = new Customer(fName, lName, uName, pWord, tempCustomerID);
				Customer C = new Customer(0L,fName,lName,bdate,phoneNumber,0L);
				LoginInfo LI = new LoginInfo(0L,uName,pWord);
				try {
					logininfoDAO.createLoginRecord(C, LI);
					System.out.println("Congratulations " + fName + " " + lName + " you have created a new customer account. " +
							"Your username is: " + uName + " and your password is: " + pWord + "\n\n\n");
							//System.out.println("Your customerID is:      " + tempCustomerID + "\n");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void initialize(Scanner SC) {
		
		while(true) {
			System.out.println("**************************************\n"
					+ "Hello and Welcome to RED FEATHER BANK!\n"
					+ "**************************************\n"
					+ "**************************************\n"
					+ "\n" + "Please enter '1' if you would like to " 
		+ "log into an account in our system.\n" + "Enter '2' if you would like to register a customer account.");
			String x = SC.nextLine();

			try {
				int X = Integer.valueOf(x);
				switch(X) {
				case 1:
					loginToSystem(SC);
					break;
				case 2:
					createCustomerAccount(SC);
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("ERROR - Your input was invalid. Please try again");
				System.out.println();
			}
		}
	}
}

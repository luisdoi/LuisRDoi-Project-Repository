package jdbcBank.Objects;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbcBank.DAOImpl.AccountDAOImpl;

public class Account {
	
	public static AccountDAOImpl accountDAO = new AccountDAOImpl();
	
	private long accountID;
	private int typeID; 
	private double balance;
	private int statusID;
	
	public Account() {
		super();
	}
	
	public Account(long accountID, int typeID, double balance, int statusID) {
		super();
		this.accountID = accountID;
		this.typeID = typeID;
		this.balance = balance;
		this.statusID = statusID;
	}
	
	public static boolean showCustomerAccounts(long customerID) {
		ArrayList<Account> accountList = null;
		try {
			accountList = accountDAO.getCustomerAccounts(customerID);
			if(accountList.isEmpty())
				return false;
			else {
				System.out.println("Now showing accounts belonging to customerID:		" + customerID);
				for(int i = 0; i < accountList.size(); i++) {
					System.out.print(i+1 + " ) ");
					System.out.println(accountList.get(i).toString());
				}
				return true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error fetching customer accounts.\n");
			e.printStackTrace();
		}
		return false;
	}
	
	public Account getAccount(long accountID) {
		try {
			return accountDAO.getAccount(accountID);
		} catch (SQLException e) {
			System.out.println("ERROR - Account with ID#:	" + accountID + " does not exist.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Account> getCustomerAccounts(long customerID) {	
		ArrayList<Account> accountList = null;
		try {
			accountList = accountDAO.getCustomerAccounts(customerID);
			if(accountList.isEmpty())	// or... check if accountList is null?
				accountList = null;	
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error fetching customer accounts.\n");
			e.printStackTrace();
		}
		return accountList;
	}
	
	public boolean withdraw(long accountID, long customerID, long employeeID, double amount) {
		try {
			return accountDAO.withdraw(accountID, customerID, employeeID, amount);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error withdrawing.\n");
			e.printStackTrace();
		}
		return false;
	}
	public boolean deposit(long accountID, long customerID, long employeeID, double amount) {
		try {
			return accountDAO.deposit(accountID, customerID, employeeID, amount);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error depositing.\n");
			e.printStackTrace();
		}
		return false;
	}
	public boolean transfer(long accountIDFrom, long accountIDTo, long customerID, long employeeID, double amount) {
		try {
			return accountDAO.transfer(accountIDFrom, accountIDTo, customerID, employeeID, amount);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error performing transfer.\n");
			e.printStackTrace();
		}
		return false;
	}
	public static boolean applyForAccount(long customerID, int typeID) {
		try {
			return accountDAO.createAccount(customerID, typeID);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error creating account.\n");
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Account> getPendingStatusAccounts(){
		try {
			return accountDAO.getPendingStatusAccounts();
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error getting pending status accounts.\n");
			e.printStackTrace();
		}
		return null;
	}
	public boolean verifyAccountActive(long accountID) {
		try {
			return accountDAO.verifyAccountActive(accountID);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error verifying if account is active.\n");
			e.printStackTrace();
		}
		return false;
	}
	public String getAccountStatus(long accountID) {
		try {
			return accountDAO.getAccountStatus(accountID);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error getting account status.\n");
			e.printStackTrace();
		}
		return null;
	}
	public boolean verifyAvailableFunds(long accountID, double amountToWithdraw) {
		try {
			return accountDAO.verifyAvailableFunds(accountID, amountToWithdraw);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error verifying balance availability to withraw from account.\n");
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateAccountStatus(long accountID, int statusID) {
		try {
			return accountDAO.updateAccountStatus(accountID, statusID);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error updating account status.\n");
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteAccount(long accountID) {
		try {
			return accountDAO.deleteAccount(accountID);
		} catch (SQLException e) {
			System.out.println("ERROR - Unexpected error deleting account.\n");
			e.printStackTrace();
		}
		return false;
	}
	


	// Getters and Setters
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public static int convertToTypeID(String typeID) {
		int TID = 0;
		String t = typeID.toLowerCase();
		if(t.equals("checking"))
			TID = 1;
		else
		if(t.equals("savings"))
			TID = 2;
		return TID;
	}
	public static int convertToStatusID(String statusID) {
		int SID = 0;
		String s = statusID.toLowerCase();
		if(s.equals("pending"))
			SID = 1;
		else
		if(s.equals("active"))
			SID = 2;
		else
		if(s.equals("closed"))
			SID = 3;
		return SID;
	}
	public String getTypeIDAsString() {
		String typeAsString = null;
		switch(this.typeID) {
		case 1: 
			typeAsString = "checking";
			break;
		case 2:
			typeAsString = "savings";
			break;
		default:
			typeAsString = "";
			break;
		}
		return typeAsString;
	}
	public static String getTypeIDAsString(int typeID) {
		String typeAsString = null;
		switch(typeID) {
		case 1: 
			typeAsString = "checking";
			break;
		case 2:
			typeAsString = "savings";
			break;
		default:
			typeAsString = "";
			break;
		}
		return typeAsString;
	}
	public String getStatusIDAsString() {
		String statusAsString = null;
		switch(this.statusID) {
		case 1:
			statusAsString = "Pending";
			break;
		case 2:
			statusAsString = "Active";
			break;
		case 3:
			statusAsString = "Closed";
			break;
		default:
			statusAsString = "";
			break;
		}
		return statusAsString;
	}
	public String getStatusIDAsString(int statusID) {
		String statusAsString = null;
		switch(statusID) {
		case 1:
			statusAsString = "Pending";
			break;
		case 2:
			statusAsString = "Active";
			break;
		case 3:
			statusAsString = "Closed";
			break;
		default:
			statusAsString = "";
			break;
		}
		return statusAsString;
	}
	@Override
	public String toString() {
		return this.getTypeIDAsString().toUpperCase() + " Account [accountID : " + accountID +  "]\n "+ "	Balance : 	$" + String.format("%1$,.2f", balance) + "\n\tStatus : " + this.getStatusIDAsString();
	}
}

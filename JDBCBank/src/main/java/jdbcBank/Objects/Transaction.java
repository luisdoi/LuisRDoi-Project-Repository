package jdbcBank.Objects;

public class Transaction {

	private long transactionID;
	private int transactionTypeID;
	private long customerID;
	private long employeeID;
	private double oldBalance;
	private double newBalance;
	private long accountID;
	
	// Constructor
	public Transaction(long transactionID, int transactionTypeID, long customerID, long employeeID, double oldBalance,
			double newBalance, long accountID) {
		super();
		this.transactionID = transactionID;
		this.transactionTypeID = transactionTypeID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.accountID = accountID;
	}
	
	// Getters and setters
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public int getTransactionTypeID() {
		return transactionTypeID;
	}
	public void setTransactionTypeID(int transactionTypeID) {
		this.transactionTypeID = transactionTypeID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	public double getOldBalance() {
		return oldBalance;
	}
	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", transactionTypeID=" + transactionTypeID
				+ ", customerID=" + customerID + ", employeeID=" + employeeID + ", oldBalance=" + oldBalance
				+ ", newBalance=" + newBalance + ", accountID=" + accountID + "]";
	}
}

package jdbcBank.Objects;

public class LoginInfo {
	private long loginID;
	private String username;
	private String password;
	
	// Constructor
	public LoginInfo(long loginID, String username, String password) {
		super();
		this.loginID = loginID;
		this.username = username;
		this.password = password;
	}
	
	// Getters and setters
	public long getLoginID() {
		return loginID;
	}
	
	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginInfo [loginID=" + loginID + ", username=" + username + ", password=" + password + "]";
	}
}

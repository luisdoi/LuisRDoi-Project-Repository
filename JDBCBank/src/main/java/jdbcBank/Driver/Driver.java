package jdbcBank.Driver;

import java.sql.SQLException;
import java.util.Scanner;

import jdbcBank.DAOImpl.LoginInfoDAOImpl;
import jdbcBank.Objects.LoginInfo;
import jdbcBank.UI.BankUserInterface;


public class Driver {

	public static BankUserInterface UI = new BankUserInterface();
	
	public static void main(String [] args) {
		LoginInfoDAOImpl lidi = new LoginInfoDAOImpl();
		

		LoginInfo lg;
		try {
			lg = lidi.getLoginInfo("ldoi","111");
			System.out.println(lg.getLoginID());
			Scanner SC = new Scanner(System.in);
			UI.initialize(SC);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}

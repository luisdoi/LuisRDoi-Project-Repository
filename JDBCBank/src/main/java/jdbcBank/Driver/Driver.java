package jdbcBank.Driver;

import java.util.Scanner;

import jdbcBank.UI.BankUserInterface;


public class Driver {

	public static BankUserInterface UI = new BankUserInterface();
	
	public static void main(String [] args) {

		Scanner SC = new Scanner(System.in);
		UI.initialize(SC);
	}		
}

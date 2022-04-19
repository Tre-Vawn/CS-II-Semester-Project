package com.iffi;

import java.util.ArrayList;

/**
 * Main data driver for loading in data from a database and printing the account
 * report to the standard output.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class AccountReport {

	public static void main(String[] args) {

	//	ArrayList<Account> accounts = DatabaseLoader.loadAccountTable();

	//	AccountReportPrinter.printAccountReport(accounts);
		
		AccountData.clearDatabase();
	}
}
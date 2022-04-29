package com.iffi;

import java.util.ArrayList;

import com.iffi.comparators.CompareByManager;
import com.iffi.comparators.CompareByOwner;
import com.iffi.comparators.CompareByTotalReturnPercent;
import com.iffi.comparators.CompareByTotalValue;

/**
 * Main data driver for loading in data from a database and printing the account
 * report to the standard output.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class AccountReport {

	public static void main(String[] args) {

		ArrayList<Account> accounts = DatabaseLoader.loadAccountTable();
		

		// AccountReportPrinter.printAccountReport(accounts);

		MyList<Account> accountListByOwner = new MyList<Account>(new CompareByOwner());
		for (Account a : accounts) {
		
			accountListByOwner.addToList(a);
		}
		AccountReportPrinter.printAccountReport(accountListByOwner);
		
		MyList<Account> accountListByManager = new MyList<Account>(new CompareByManager());
		for (Account a : accounts) {
			accountListByManager.addToList(a);
		}
		AccountReportPrinter.printAccountReport(accountListByManager);
		
		MyList<Account> accountListByTotalValue = new MyList<Account>(new CompareByTotalValue());
		for (Account a : accounts) {
			accountListByTotalValue.addToList(a);
		}
		AccountReportPrinter.printAccountReport(accountListByTotalValue);
		
		MyList<Account> accountListByTotalReturnPercent = new MyList<Account>(new CompareByTotalReturnPercent());
		for (Account a : accounts) {
			accountListByTotalReturnPercent.addToList(a);
		}
		AccountReportPrinter.printAccountReport(accountListByTotalReturnPercent);
	}
}
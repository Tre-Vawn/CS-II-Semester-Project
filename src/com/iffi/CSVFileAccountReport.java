package com.iffi;

import java.util.ArrayList;

/**
 * Main data driver for loading in data from CSV files and printing the account
 * report to the standard output.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class CSVFileAccountReport {

	public static void main(String[] args) {

		ArrayList<Person> people = CSVFileLoader.loadPersonCSVFile();
		ArrayList<Asset> assets = CSVFileLoader.loadAssetCSVFile();
		ArrayList<Account> accounts = CSVFileLoader.loadAccountCSVFile(assets, people);

		//AccountReportPrinter.printAccountReport(accounts);
	}
}
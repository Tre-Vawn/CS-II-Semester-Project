package com.iffi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 * @author tre-vawnrainey, ethanwood
 */
public final class AccountData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static final void clearDatabase() {
		// TODO: Check correctness
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String dropEmailQuery = "drop table if exists Email;";
		PreparedStatement psDropEmail = null;
		ResultSet rsDropEmail = null;
		
		String dropAccountAssetQuery = "drop table if exists AccountAsset;";
		PreparedStatement psDropAccountAsset = null;
		ResultSet rsDropAccountAsset = null;
		
		String dropAccountQuery = "drop table if exists Accoun;";
		PreparedStatement psDropAccount = null;
		ResultSet rsDropAccount = null;
		
		String dropAssetQuery = "drop table if exists Asset;";
		PreparedStatement psDropAsset= null;
		ResultSet rsDropAsset = null;
		
		String dropPersonQuery = "drop table if exists Person;";
		PreparedStatement psDropPerson = null;
		ResultSet rsDropPerson = null;
		
		String dropAddressQuery = "drop table if exists Address;";
		PreparedStatement psDropAddress = null;
		ResultSet rsDropAddress = null;

		try {
			psDropEmail = conn.prepareStatement(dropEmailQuery);
			rsDropEmail = psDropEmail.executeQuery();
			
			psDropAccountAsset = conn.prepareStatement(dropAccountAssetQuery);
			rsDropAccountAsset = psDropAccountAsset.executeQuery();
			
			psDropAccount = conn.prepareStatement(dropAccountQuery);
			rsDropAccount = psDropAccount.executeQuery();
			
			psDropAsset = conn.prepareStatement(dropAssetQuery);
			rsDropAsset = psDropAsset.executeQuery();
			
			psDropPerson = conn.prepareStatement(dropPersonQuery);
			rsDropPerson = psDropPerson.executeQuery();
			
			psDropAddress = conn.prepareStatement(dropAddressQuery);
			rsDropAddress = psDropAddress.executeQuery();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}

		try {
			rsDropEmail.close();
			psDropEmail.close();
			
			rsDropAccountAsset.close();
			psDropAccountAsset.close();
			
			rsDropAccount.close();
			psDropAccount.close();
			
			rsDropAsset.close();
			psDropAsset.close();
			
			rsDropPerson.close();
			psDropPerson.close();
			
			rsDropAddress.close();
			psDropAddress .close();
			
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personCode
	 * @param lastName
	 * @param firstName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static final void addPerson(String personCode, String lastName, String firstName, String street, String city,
			String state, String zip, String country)  {
		// TODO: implement
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static final void addEmail(String personCode, String email) {
		// TODO: implement
	}

	/**
	 * Adds a cryptocurrency asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param currentExchangeRate
	 * @param exchangeFeeRate
	 */
	public static final void addCrypto(String assetCode, String label, double 
			currentExchangeRate, double exchangeFeeRate)  {
		// TODO: implement
	}

	/**
	 * Adds a property asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param appraisedValue
	 */
	public static final void addProperty(String assetCode, String label, double appraisedValue) {
		// TODO: implement
	}

	/**
	 * Adds a stock asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param symbol
	 * @param currentSharePrice
	 */
	public static final void addStock(String assetCode, String label, String symbol, double currentSharePrice) {
		// TODO: implement
	}

	/**
	 * Adds an account record to the database with the given data. If the account
	 * has no beneficiary, the <code>beneficiaryCode</code> will be
	 * <code>null</code>. The <code>accountType</code> is expected to be either
	 * <code>"N"</code> (noob) or <code>"P"</code> (pro).
	 *
	 * @param accountNumber
	 * @param accountType
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static final void addAccount(String accountNumber, String accountType, String ownerCode, String managerCode,
			String beneficiaryCode) {
		// TODO: implement
	}

	/**
	 * Adds a cryptocurrency asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to the
	 * provided <code>accountNumber</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedExchangeRate
	 * @param numCoins
	 */
	public static final void addCryptoToAccount(String accountNumber, String assetCode, String purchasedDate,
			double purchasedExchangeRate, double numCoins) {
		// TODO: implement
	}

	/**
	 * Adds a property asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to the
	 * provided <code>accountNumber</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedPrice
	 */
	public static final void addPropertyToAccount(String accountNumber, String assetCode, String purchasedDate,
			double purchasedPrice) {
		// TODO: implement
	}

	/**
	 * Adds a stock asset corresponding to <code>assetCode</code> (which is assumed
	 * to already exist in the database) to the account corresponding to the
	 * provided <code>accountNumber</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedSharePrice
	 * @param numShares
	 * @param dividendTotal
	 */
	public static final void addStockToAccount(String accountNumber, String assetCode, String purchasedDate,
			double purchasedSharePrice, double numShares, double dividendTotal) {
		// TODO: implement
	}

	/**
	 * Adds a stock option asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to
	 * the* provided <code>accountNumber</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param optionType
	 * @param strikeDate
	 * @param shareLimit
	 * @param premium
	 * @param strikePrice
	 */
	public static final void addStockOptionToAccount(String accountNumber, String assetCode, String purchasedDate,
			String optionType, String strikeDate, double shareLimit, double premium, double strikePrice) {
		// TODO: implement
	}
}

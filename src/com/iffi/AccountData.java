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
		int rsDropEmail = 0;
		
		String dropAccountAssetQuery = "drop table if exists AccountAsset;";
		PreparedStatement psDropAccountAsset = null;
		int rsDropAccountAsset = 0;
		
		String dropAccountQuery = "drop table if exists Accoun;";
		PreparedStatement psDropAccount = null;
		int rsDropAccount = 0;
		
		String dropAssetQuery = "drop table if exists Asset;";
		PreparedStatement psDropAsset= null;
		int rsDropAsset = 0;
		
		String dropPersonQuery = "drop table if exists Person;";
		PreparedStatement psDropPerson = null;
		int rsDropPerson = 0;
		
		String dropAddressQuery = "drop table if exists Address;";
		PreparedStatement psDropAddress = null;
		int rsDropAddress = 0;
		
		String createAddressQuery = "create table Address"
				+ "(addressId int not null primary key auto_increment,"
				+ "street varchar(255) not null,"
				+ "city varchar(255) not null,"
				+ "state varchar(2) not null,"
				+ "zip varchar(255) not null,"
				+ "country varchar(255) not null);";
		PreparedStatement psCreateAddress = null;
		int rsCreateAddress = 0;
		
		String createPersonQuery = "create table Person"
				+ "(personId int not null primary key auto_increment,"
				+ "personCode varchar(50) not null unique key,"
				+ "lastName varchar(255) not null,"
				+ "firstName varchar(255) not null,"
				+ "addressId int not null,"
				+ "foreign key (addressId) references Address(addressId));";
		PreparedStatement psCreatePerson = null;
		int rsCreatePerson = 0;
		
		String createEmailQuery = "create table Email"
				+ "    (emailId int not null primary key auto_increment,"
				+ "    email varchar(255),"
				+ "    personId int not null,"
				+ "    foreign key (personId) references Person(personId));";
		PreparedStatement psCreateEmail = null;
		int rsCreateEmail = 0;
		
		String createAccountQuery = "create table Account"
				+ "(accountId int not null primary key auto_increment,"
				+ "accountCode varchar(255) not null unique key,"
				+ "accountType varchar(1) not null,"
				+ "ownerId int not null,"
				+ "managerId int not null,"
				+ "beneficiaryId int,"
				+ "foreign key (ownerId) references Person(personId),"
				+ "foreign key (managerId) references Person(personId),"
				+ "foreign key (beneficiaryId) references Person(personId));";
		PreparedStatement psCreateAccount = null;
		int rsCreateAccount = 0;
		
		String createAssetQuery = "create table Asset"
				+ "(assetId int not null primary key auto_increment,"
				+ "assetCode varchar(255) not null unique key,"
				+ "assetType varchar(1) not null,"
				+ "label varchar(50) not null,"
				+ "currentAppraisedValue double,"
				+ "currentExchangeRate double,"
				+ "exchangeRateFee double,"
				+ "symbol varchar(50),"
				+ "currentSharePrice double);";
		PreparedStatement psCreateAsset = null;
		int rsCreateAsset = 0;
		
		String createAccountAssetQuery = "create table AccountAsset"
				+ "(accountAssetId int not null primary key auto_increment,"
				+ "purchasedDate varchar(10) not null,"
				+ "purchasedPrice double,"
				+ "purchasedExchangeRate double,"
				+ "numCoins double,"
				+ "purchasedSharePrice double,"
				+ "numShares double,"
				+ "dividendTotal double,"
				+ "optionType varchar(1),"
				+ "strikePrice double,"
				+ "shareLimit double,"
				+ "premium double,"
				+ "strikeDate varchar(10),"
				+ "accountId int not null,"
				+ "assetId int not null,"
				+ "foreign key (accountId) references Account(accountId),"
				+ "foreign key (assetId) references Asset(assetId),"
				+ "constraint uniquePair unique index(accountId, assetId));";
		PreparedStatement psCreateAccountAsset = null;
		int rsCreateAccountAsset = 0;
				
		try {
			psDropEmail = conn.prepareStatement(dropEmailQuery);
			rsDropEmail = psDropEmail.executeUpdate();
			
			psDropAccountAsset = conn.prepareStatement(dropAccountAssetQuery);
			rsDropAccountAsset = psDropAccountAsset.executeUpdate();
			
			psDropAccount = conn.prepareStatement(dropAccountQuery);
			rsDropAccount = psDropAccount.executeUpdate();
			
			psDropAsset = conn.prepareStatement(dropAssetQuery);
			rsDropAsset = psDropAsset.executeUpdate();
			
			psDropPerson = conn.prepareStatement(dropPersonQuery);
			rsDropPerson = psDropPerson.executeUpdate();
			
			psDropAddress = conn.prepareStatement(dropAddressQuery);
			rsDropAddress = psDropAddress.executeUpdate();
			
			psCreateAddress = conn.prepareStatement(createAddressQuery);
			rsCreateAddress = psCreateAddress.executeUpdate();

			psCreatePerson = conn.prepareStatement(createPersonQuery);
			rsCreatePerson = psCreatePerson.executeUpdate();
			
			psCreateEmail = conn.prepareStatement(createEmailQuery);
			rsCreateEmail = psCreateEmail.executeUpdate();
			
			psCreateAccount = conn.prepareStatement(createAccountQuery);
			rsCreateAccount = psCreateAccount.executeUpdate();
			
			psCreateAsset = conn.prepareStatement(createAssetQuery);
			rsCreateAsset = psCreateAsset.executeUpdate();
			
			psCreateAccountAsset = conn.prepareStatement(createAccountAssetQuery);
			rsCreateAccountAsset = psCreateAccountAsset.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot clear tables.");
			throw new RuntimeException(e);
		}

		try {
			//rsDropEmail.close();
			psDropEmail.close();
			
			//rsDropAccountAsset.close();
			psDropAccountAsset.close();
			
			//rsDropAccount.close();
			psDropAccount.close();
			
			//rsDropAsset.close();
			psDropAsset.close();
			
			//rsDropPerson.close();
			psDropPerson.close();
			
			//rsDropAddress.close();
			psDropAddress .close();
			
			//rsCreateAddress.close();
			psCreateAddress.close();
			
			//rsCreatePerson.close();
			psCreatePerson.close();
			
			//rsCreateEmail.close();
			psCreateEmail.close();
			
			//rsCreateAccount.close();
			psCreateAccount.close();
			
			//rsCreateAsset.close();
			psCreateAsset.close();
			
			//rsCreateAccountAsset.close();
			psCreateAccountAsset.close();
			
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

package com.iffi;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 * @author tre-vawnrainey, ethanwood
 */
public class AccountData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static void clearDatabase() {
		// TODO: implement
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
	public static void addPerson(String personCode, String lastName, String firstName, String street, String city,
			String state, String zip, String country) {
		// TODO: implement
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
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
	public static void addCrypto(String assetCode, String label, double currentExchangeRate, double exchangeFeeRate) {
		// TODO: implement
	}

	/**
	 * Adds a property asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param appraisedValue
	 */
	public static void addProperty(String assetCode, String label, double appraisedValue) {
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
	public static void addStock(String assetCode, String label, String symbol, double currentSharePrice) {
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
	public static void addAccount(String accountNumber, String accountType, String ownerCode, String managerCode,
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
	public static void addCryptocurrencyToAccount(String accountNumber, String assetCode, String purchasedDate,
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
	public static void addPropertyToAccount(String accountNumber, String assetCode, String purchasedDate,
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
	public static void addStockToAccount(String accountNumber, String assetCode, String purchasedDate,
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
	 * @param strikePrice
	 * @param shareLimit
	 * @param premium
	 * @param strikeDate
	 */
	public static void addStockOptionToAccount(String accountNumber, String assetCode, String purchasedDate,
			String optionType, double strikePrice, double shareLimit, double premium, String strikeDate) {
		// TODO: implement
	}
}

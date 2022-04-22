package com.iffi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String deleteEmailQuery = "delete from Email;";
		PreparedStatement psDeleteEmail = null;
		int DeleteEmail = 0;

		String deleteAccountAssetQuery = "delete from AccountAsset;";
		PreparedStatement psDeleteAccountAsset = null;
		int DeleteAccountAsset = 0;

		String deleteAccountQuery = "delete from Account;";
		PreparedStatement psDeleteAccount = null;
		int DeleteAccount = 0;

		String deleteAssetQuery = "delete from Asset;";
		PreparedStatement psDeleteAsset = null;
		int DeleteAsset = 0;

		String deletePersonQuery = "delete from Person;";
		PreparedStatement psDeletePerson = null;
		int DeletePerson = 0;

		String deleteAddressQuery = "delete from Address;";
		PreparedStatement psDeleteAddress = null;
		int DeleteAddress = 0;
		try {
			psDeleteEmail = conn.prepareStatement(deleteEmailQuery);
			DeleteEmail = psDeleteEmail.executeUpdate();

			psDeleteAccountAsset = conn.prepareStatement(deleteAccountAssetQuery);
			DeleteAccountAsset = psDeleteAccountAsset.executeUpdate();

			psDeleteAccount = conn.prepareStatement(deleteAccountQuery);
			DeleteAccount = psDeleteAccount.executeUpdate();

			psDeleteAsset = conn.prepareStatement(deleteAssetQuery);
			DeleteAsset = psDeleteAsset.executeUpdate();

			psDeletePerson = conn.prepareStatement(deletePersonQuery);
			DeletePerson = psDeletePerson.executeUpdate();

			psDeleteAddress = conn.prepareStatement(deleteAddressQuery);
			DeleteAddress = psDeleteAddress.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot clear tables.");
			throw new RuntimeException(e);
		}

		try {
			psDeleteEmail.close();
			psDeleteAccountAsset.close();
			psDeleteAccount.close();
			psDeleteAsset.close();
			psDeletePerson.close();
			psDeleteAddress.close();
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
			String state, String zip, String country) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String insertAddressQuery = "insert into Address (street, city, state, zip, country) values (?, ?, ?, ?, ?);";
		PreparedStatement psInsertAddress = null;
		ResultSet rsAddressId = null;
		int addressId = 0;

		String insertPersonQuery = "insert into Person (personCode, lastName, firstName, addressId) values (?, ?, ?, ?);";
		PreparedStatement psInsertPerson = null;
		try {
			psInsertAddress = conn.prepareStatement(insertAddressQuery, Statement.RETURN_GENERATED_KEYS);
			psInsertAddress.setString(1, street);
			psInsertAddress.setString(2, city);
			psInsertAddress.setString(3, state);
			psInsertAddress.setString(4, zip);
			psInsertAddress.setString(5, country);
			psInsertAddress.executeUpdate();
			rsAddressId = psInsertAddress.getGeneratedKeys();
			if (rsAddressId.next()) {
				addressId = rsAddressId.getInt(1);
			}

			psInsertPerson = conn.prepareStatement(insertPersonQuery);
			psInsertPerson.setString(1, personCode);
			psInsertPerson.setString(2, lastName);
			psInsertPerson.setString(3, firstName);
			psInsertPerson.setInt(4, addressId);
			psInsertPerson.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add person.");
			throw new RuntimeException(e);
		}
		try {
			psInsertAddress.close();
			psInsertPerson.close();
			rsAddressId.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static final void addEmail(String personCode, String email) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String getPersonIdQuery = "select personId from Person where personCode = ?;";
		PreparedStatement psGetPersonId = null;
		ResultSet rsGetPersonId = null;
		int personId = 0;

		String insertEmailQuery = "insert into Email (email, personId) values (?, ?);";
		PreparedStatement psInsertEmail = null;
		try {
			psGetPersonId = conn.prepareStatement(getPersonIdQuery);
			psGetPersonId.setString(1, personCode);
			rsGetPersonId = psGetPersonId.executeQuery();
			if (rsGetPersonId.next()) {
				personId = rsGetPersonId.getInt("personId");
				psInsertEmail = conn.prepareStatement(insertEmailQuery);
				psInsertEmail.setString(1, email);
				psInsertEmail.setInt(2, personId);
				psInsertEmail.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add email.");
			throw new RuntimeException(e);
		}
		try {
			rsGetPersonId.close();
			psGetPersonId.close();
			psInsertEmail.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a property asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param currentAppraisedValue
	 */
	public static final void addProperty(String assetCode, String label, double currentAppraisedValue) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String query = "insert into Asset"
				+ "(assetCode, assetType, label, currentAppraisedValue, currentExchangeRate, exchangeFeeRate, symbol, currentSharePrice)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, "P");
			ps.setString(3, label);
			ps.setDouble(4, currentAppraisedValue);
			ps.setNull(5, java.sql.Types.DOUBLE);
			ps.setNull(6, java.sql.Types.DOUBLE);
			ps.setNull(7, java.sql.Types.VARCHAR);
			ps.setNull(8, java.sql.Types.DOUBLE);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency.");
			throw new RuntimeException(e);
		}
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a cryptocurrency asset record to the database with the provided data.
	 *
	 * @param assetCode
	 * @param label
	 * @param currentExchangeRate
	 * @param exchangeFeeRate
	 */
	public static final void addCrypto(String assetCode, String label, double currentExchangeRate,
			double exchangeFeeRate) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String query = "insert into Asset"
				+ "(assetCode, assetType, label, currentAppraisedValue, currentExchangeRate, exchangeFeeRate, symbol, currentSharePrice)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, "C");
			ps.setString(3, label);
			ps.setNull(4, java.sql.Types.DOUBLE);
			ps.setDouble(5, currentExchangeRate);
			ps.setDouble(6, exchangeFeeRate);
			ps.setNull(7, java.sql.Types.VARCHAR);
			ps.setNull(8, java.sql.Types.DOUBLE);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency.");
			throw new RuntimeException(e);
		}
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
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
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String query = "insert into Asset"
				+ "(assetCode, assetType, label, currentAppraisedValue, currentExchangeRate, exchangeFeeRate, symbol, currentSharePrice)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, "S");
			ps.setString(3, label);
			ps.setNull(4, java.sql.Types.DOUBLE);
			ps.setNull(5, java.sql.Types.DOUBLE);
			ps.setNull(6, java.sql.Types.DOUBLE);
			ps.setString(7, symbol);
			ps.setDouble(8, currentSharePrice);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency.");
			throw new RuntimeException(e);
		}
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds an account record to the database with the given data. If the account
	 * has no beneficiary, the <code>beneficiaryCode</code> will be
	 * <code>null</code>. The <code>accountType</code> is expected to be either
	 * <code>"N"</code> (noob) or <code>"P"</code> (pro).
	 *
	 * @param accountCode
	 * @param accountType
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static final void addAccount(String accountCode, String accountType, String ownerCode, String managerCode,
			String beneficiaryCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String getOwnerIdQuery = "select personId from Person where personCode = ?;";
		PreparedStatement psGetOwnerId = null;
		ResultSet rsGetOwnerId = null;
		int ownerId = 0;
		String getManagerIdQuery = "select personId from Person where personCode = ?;";
		PreparedStatement psGetManagerId = null;
		ResultSet rsGetManagerId = null;
		int managerId = 0;
		String getBeneficiaryIdQuery = "select personId from Person where personCode = ?;";
		PreparedStatement psGetBeneficiaryId = null;
		ResultSet rsGetBeneficiaryId = null;
		int beneficiaryId = 0;

		String insertAccountQuery = "insert into Account"
				+ "(accountCode, accountType, ownerId, managerId, beneficiaryId) values (?, ?, ?, ?, ?);";
		PreparedStatement psInsertAccount = null;
		try {
			psGetOwnerId = conn.prepareStatement(getOwnerIdQuery);
			psGetOwnerId.setString(1, ownerCode);
			rsGetOwnerId = psGetOwnerId.executeQuery();

			psGetManagerId = conn.prepareStatement(getManagerIdQuery);
			psGetManagerId.setString(1, managerCode);
			rsGetManagerId = psGetManagerId.executeQuery();

			psGetBeneficiaryId = conn.prepareStatement(getBeneficiaryIdQuery);
			psGetBeneficiaryId.setString(1, beneficiaryCode);
			rsGetBeneficiaryId = psGetBeneficiaryId.executeQuery();
			if (rsGetOwnerId.next()) {
				ownerId = rsGetOwnerId.getInt("personId");
			}
			if (rsGetManagerId.next()) {
				managerId = rsGetManagerId.getInt("personId");
			}
			if (rsGetBeneficiaryId.next()) {
				beneficiaryId = rsGetBeneficiaryId.getInt("personId");
			} else {
				beneficiaryId = -1;
			}
			psInsertAccount = conn.prepareStatement(insertAccountQuery);
			psInsertAccount.setString(1, accountCode);
			psInsertAccount.setString(2, accountType);
			psInsertAccount.setInt(3, ownerId);
			psInsertAccount.setInt(4, managerId);
			if (beneficiaryId > 0) {
				psInsertAccount.setInt(5, beneficiaryId);
			} else {
				psInsertAccount.setNull(5, java.sql.Types.INTEGER);
			}
			psInsertAccount.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add account.");
			throw new RuntimeException(e);
		}
		try {
			rsGetOwnerId.close();
			psGetOwnerId.close();
			rsGetManagerId.close();
			psGetManagerId.close();
			rsGetBeneficiaryId.close();
			psGetBeneficiaryId.close();
			psInsertAccount.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a property asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to the
	 * provided <code>accountCode</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedPrice
	 */
	public static final void addPropertyToAccount(String accountCode, String assetCode, String purchasedDate,
			double purchasedPrice) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountIdQuery = "Select accountId from Account where accountCode = ?;";
		PreparedStatement psGetAccountId = null;
		ResultSet rsGetAccountId = null;
		int accountId = 0;

		String propertyIdQuery = "Select assetId from Asset where assetCode = ?;";
		PreparedStatement psGetPropertyId = null;
		ResultSet rsGetPropertyId = null;
		int assetId = 0;

		String insertAccountAssetQuery = "insert into AccountAsset"
				+ "(purchasedDate, purchasedPrice, purchasedExchangeRate, numCoins, purchasedSharePrice, numShares, dividendTotal, optionType, strikePrice, shareLimit, premium, strikeDate, accountId, assetId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement psInsertAccountAsset = null;
		try {
			psGetAccountId = conn.prepareStatement(accountIdQuery);
			psGetAccountId.setString(1, accountCode);
			rsGetAccountId = psGetAccountId.executeQuery();

			psGetPropertyId = conn.prepareStatement(propertyIdQuery);
			psGetPropertyId.setString(1, assetCode);
			rsGetPropertyId = psGetPropertyId.executeQuery();
			if (rsGetAccountId.next()) {
				accountId = rsGetAccountId.getInt("accountId");
			}
			if (rsGetPropertyId.next()) {
				assetId = rsGetPropertyId.getInt("assetId");
			}
			psInsertAccountAsset = conn.prepareStatement(insertAccountAssetQuery);
			psInsertAccountAsset.setString(1, purchasedDate);
			psInsertAccountAsset.setDouble(2, purchasedPrice);
			psInsertAccountAsset.setNull(3, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(4, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(5, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(6, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(7, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(8, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(9, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(10, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(11, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(12, java.sql.Types.VARCHAR);
			psInsertAccountAsset.setInt(13, accountId);
			psInsertAccountAsset.setInt(14, assetId);
			psInsertAccountAsset.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add property to account.");
			throw new RuntimeException(e);
		}
		try {
			rsGetAccountId.close();
			psGetAccountId.close();
			rsGetPropertyId.close();
			psGetPropertyId.close();
			psInsertAccountAsset.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a cryptocurrency asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to the
	 * provided <code>accountCode</code>.
	 *
	 * @param accountCode
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedExchangeRate
	 * @param numCoins
	 */
	public static final void addCryptoToAccount(String accountCode, String assetCode, String purchasedDate,
			double purchasedExchangeRate, double numCoins) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountIdQuery = "Select accountId from Account where accountCode = ?;";
		PreparedStatement psGetAccountId = null;
		ResultSet rsGetAccountId = null;
		int accountId = 0;

		String cryptocurrencyIdQuery = "Select assetId from Asset where assetCode = ?;";
		PreparedStatement psGetCryptocurrencyId = null;
		ResultSet rsGetCryptocurrencyId = null;
		int assetId = 0;

		String insertAccountAssetQuery = "insert into AccountAsset"
				+ "(purchasedDate, purchasedPrice, purchasedExchangeRate, numCoins, purchasedSharePrice, numShares, dividendTotal, optionType, strikePrice, shareLimit, premium, strikeDate, accountId, assetId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement psInsertAccountAsset = null;
		try {
			psGetAccountId = conn.prepareStatement(accountIdQuery);
			psGetAccountId.setString(1, accountCode);
			rsGetAccountId = psGetAccountId.executeQuery();

			psGetCryptocurrencyId = conn.prepareStatement(cryptocurrencyIdQuery);
			psGetCryptocurrencyId.setString(1, assetCode);
			rsGetCryptocurrencyId = psGetCryptocurrencyId.executeQuery();
			if (rsGetAccountId.next()) {
				accountId = rsGetAccountId.getInt("accountId");
			}
			if (rsGetCryptocurrencyId.next()) {
				assetId = rsGetCryptocurrencyId.getInt("assetId");
			}
			psInsertAccountAsset = conn.prepareStatement(insertAccountAssetQuery);
			psInsertAccountAsset.setString(1, purchasedDate);
			psInsertAccountAsset.setNull(2, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setDouble(3, purchasedExchangeRate);
			psInsertAccountAsset.setDouble(4, numCoins);
			psInsertAccountAsset.setNull(5, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(6, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(7, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(8, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(9, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(10, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(11, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(12, java.sql.Types.VARCHAR);
			psInsertAccountAsset.setInt(13, accountId);
			psInsertAccountAsset.setInt(14, assetId);
			psInsertAccountAsset.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency to account.");
			throw new RuntimeException(e);
		}
		try {
			rsGetAccountId.close();
			psGetAccountId.close();
			rsGetCryptocurrencyId.close();
			psGetCryptocurrencyId.close();
			psInsertAccountAsset.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a stock asset corresponding to <code>assetCode</code> (which is assumed
	 * to already exist in the database) to the account corresponding to the
	 * provided <code>accountCode</code>.
	 *
	 * @param accountNumber
	 * @param assetCode
	 * @param purchasedDate
	 * @param purchasedSharePrice
	 * @param numShares
	 * @param dividendTotal
	 */
	public static final void addStockToAccount(String accountCode, String assetCode, String purchasedDate,
			double purchasedSharePrice, double numShares, double dividendTotal) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountIdQuery = "Select accountId from Account where accountCode = ?;";
		PreparedStatement psGetAccountId = null;
		ResultSet rsGetAccountId = null;
		int accountId = 0;

		String stockIdQuery = "Select assetId from Asset where assetCode = ?;";
		PreparedStatement psGetStockId = null;
		ResultSet rsGetStockId = null;
		int assetId = 0;

		String insertAccountAssetQuery = "insert into AccountAsset"
				+ "(purchasedDate, purchasedPrice, purchasedExchangeRate, numCoins, purchasedSharePrice, numShares, dividendTotal, optionType, strikePrice, shareLimit, premium, strikeDate, accountId, assetId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement psInsertAccountAsset = null;
		try {
			psGetAccountId = conn.prepareStatement(accountIdQuery);
			psGetAccountId.setString(1, accountCode);
			rsGetAccountId = psGetAccountId.executeQuery();

			psGetStockId = conn.prepareStatement(stockIdQuery);
			psGetStockId.setString(1, assetCode);
			rsGetStockId = psGetStockId.executeQuery();
			if (rsGetAccountId.next()) {
				accountId = rsGetAccountId.getInt("accountId");
			}
			if (rsGetStockId.next()) {
				assetId = rsGetStockId.getInt("assetId");
			}
			psInsertAccountAsset = conn.prepareStatement(insertAccountAssetQuery);
			psInsertAccountAsset.setString(1, purchasedDate);
			psInsertAccountAsset.setNull(2, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(3, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(4, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setDouble(5, purchasedSharePrice);
			psInsertAccountAsset.setDouble(6, numShares);
			psInsertAccountAsset.setDouble(7, dividendTotal);
			psInsertAccountAsset.setNull(8, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(9, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(10, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(11, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(12, java.sql.Types.VARCHAR);
			psInsertAccountAsset.setInt(13, accountId);
			psInsertAccountAsset.setInt(14, assetId);
			psInsertAccountAsset.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency to account.");
			throw new RuntimeException(e);
		}
		try {
			rsGetAccountId.close();
			psGetAccountId.close();
			rsGetStockId.close();
			psGetStockId.close();
			psInsertAccountAsset.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Adds a stock option asset corresponding to <code>assetCode</code> (which is
	 * assumed to already exist in the database) to the account corresponding to
	 * the* provided <code>accountCode</code>.
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
	public static final void addStockOptionToAccount(String accountCode, String assetCode, String purchasedDate,
			String optionType, String strikeDate, double shareLimit, double premium, double strikePrice) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountIdQuery = "Select accountId from Account where accountCode = ?;";
		PreparedStatement psGetAccountId = null;
		ResultSet rsGetAccountId = null;
		int accountId = 0;

		String stockOptionIdQuery = "Select assetId from Asset where assetCode = ?;";
		PreparedStatement psGetStockOptionId = null;
		ResultSet rsGetStockOptionId = null;
		int assetId = 0;

		String insertAccountAssetQuery = "insert into AccountAsset"
				+ "(purchasedDate, purchasedPrice, purchasedExchangeRate, numCoins, purchasedSharePrice, numShares, dividendTotal, optionType, strikePrice, shareLimit, premium, strikeDate, accountId, assetId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement psInsertAccountAsset = null;
		try {
			psGetAccountId = conn.prepareStatement(accountIdQuery);
			psGetAccountId.setString(1, accountCode);
			rsGetAccountId = psGetAccountId.executeQuery();

			psGetStockOptionId = conn.prepareStatement(stockOptionIdQuery);
			psGetStockOptionId.setString(1, assetCode);
			rsGetStockOptionId = psGetStockOptionId.executeQuery();
			if (rsGetAccountId.next()) {
				accountId = rsGetAccountId.getInt("accountId");
			}
			if (rsGetStockOptionId.next()) {
				assetId = rsGetStockOptionId.getInt("assetId");
			}
			psInsertAccountAsset = conn.prepareStatement(insertAccountAssetQuery);
			psInsertAccountAsset.setString(1, purchasedDate);
			psInsertAccountAsset.setNull(2, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(3, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(4, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(5, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(6, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setNull(7, java.sql.Types.DOUBLE);
			psInsertAccountAsset.setString(8, optionType);
			psInsertAccountAsset.setDouble(9, strikePrice);
			psInsertAccountAsset.setDouble(10, shareLimit);
			psInsertAccountAsset.setDouble(11, premium);
			psInsertAccountAsset.setString(12, strikeDate);
			psInsertAccountAsset.setInt(13, accountId);
			psInsertAccountAsset.setInt(14, assetId);
			psInsertAccountAsset.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot add cryptocurrency to account.");
			throw new RuntimeException(e);
		}
		try {
			rsGetAccountId.close();
			psGetAccountId.close();
			rsGetStockOptionId.close();
			psGetStockOptionId.close();
			psInsertAccountAsset.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}
}

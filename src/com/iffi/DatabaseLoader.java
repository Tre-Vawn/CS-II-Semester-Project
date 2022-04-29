package com.iffi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Loads in the data from the database specified in the DatabaseInfo class.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 */
public final class DatabaseLoader {

	/**
	 * Creates and returns an address object from the database data by the address
	 * id.
	 * 
	 * @param addressId
	 * @return
	 */
	private static final Address getAddressByAddressId(int addressId) {
		Address a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String addressQuery = "select addressId, street, city, state, zip, country from Address where addressId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(addressQuery);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int addressIdFromDatabase = rs.getInt("addressId");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				a = new Address(addressIdFromDatabase, street, city, state, zip, country);
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}
		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return a;
	}

	/**
	 * Creates and returns an array list of emails from the database data by the
	 * personId. id.
	 * 
	 * @param addressId
	 * @return
	 */
	private static final void getPersonEmailsByPersonId(Person p, int personId) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String emailQuery = "select email from Email where personId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(emailQuery);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("email") != null) {
					p.addEmail(rs.getString("email"));
				}
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}

		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return;
	}

	/**
	 * Creates and returns a person object from the database data by the person id.
	 * 
	 * @param personId
	 * @return
	 */
	private static final Person getPersonByPersonId(int personId) {
		Person p = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String personQuery = "select personId, personCode, lastName, firstName, addressId from Person where personId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(personQuery);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personIdFromDatabase = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				Address a = getAddressByAddressId(rs.getInt("addressId"));
				p = new Person(personIdFromDatabase, personCode, new Name(lastName, firstName), a);
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}
		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return p;
	}

	/**
	 * Creates and returns an asset list from the database data by the account id.
	 * 
	 * @param accountId
	 * @return
	 */
	private static final ArrayList<Asset> getAccountAssetsByAccountId(int accountId) {
		ArrayList<Asset> accountAssetList = new ArrayList<Asset>();
		Asset a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountAssetQuery = "select a1.assetId, a1.assetCode, a1.assetType, a1.label,"
				+ "a1.currentAppraisedValue, " + "a1.currentExchangeRate, a1.exchangeFeeRate,"
				+ "a1.symbol, a1.currentSharePrice, " + "a.purchasedDate," + "a.purchasedPrice,"
				+ "a.purchasedExchangeRate, a.numCoins,"
				+ "a.purchasedSharePrice, a.numShares, a.dividendTotal, a.optionType,"
				+ "a.strikePrice, a.shareLimit, a.premium, a.strikeDate,"
				+ "a.accountId, a.assetId from AccountAsset a join Asset a1 on a.assetId = a1.assetId where accountId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(accountAssetQuery);
			ps.setInt(1, accountId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int assetIdFromDataBase = rs.getInt("assetId");
				String assetCode = rs.getString("assetCode");
				String assetType = rs.getString("assetType");
				String label = rs.getString("label");

				LocalDate purchasedDate = LocalDate.parse(rs.getString("purchasedDate"));

				if (assetType.equals("P")) {
					double currentAppraisedValue = rs.getDouble("currentAppraisedValue");
					a = new Property(assetCode, label, currentAppraisedValue);
					double purchasedPrice = rs.getDouble("purchasedPrice");
					a = new Property(assetIdFromDataBase, ((Property) a), purchasedDate, purchasedPrice);
					accountAssetList.add(a);
				} else if (assetType.equals("C")) {
					double currentExchangeRate = rs.getDouble("currentExchangeRate");
					double exchangeFeeRate = rs.getDouble("exchangeFeeRate");
					a = new Cryptocurrency(assetCode, label, currentExchangeRate, exchangeFeeRate);
					double purchasedExchangeRate = rs.getDouble("purchasedExchangeRate");
					double numCoins = rs.getDouble("numCoins");
					a = new Cryptocurrency(assetIdFromDataBase, ((Cryptocurrency) a), purchasedDate, purchasedExchangeRate,
							numCoins);
					accountAssetList.add(a);
				} else if (assetType.equals("S")) {
			
					String symbol = rs.getString("symbol");
					double currentSharePrice = rs.getDouble("currentSharePrice");
					a = new Stock(assetCode, label, symbol, currentSharePrice);
	
					String optionType = rs.getString("optionType");
					a = new Stock(((Stock) a), purchasedDate);
					if (rs.getString("optionType") == null) {
						double purchasedSharePrice = rs.getDouble("purchasedSharePrice");
						double numShares = rs.getDouble("numShares");
						double dividendTotal = rs.getDouble("dividendTotal");
						Stock s = new Stock(assetIdFromDataBase, ((Stock) a), purchasedSharePrice, numShares, dividendTotal);
						accountAssetList.add(s);
					} else if (optionType.equals("C") || optionType.equals("P")) {
						double strikePrice = rs.getDouble("strikePrice");
						double shareLimit = rs.getDouble("shareLimit");
						double premium = rs.getDouble("premium");
						LocalDate strikeDate = LocalDate.parse(rs.getString("strikeDate"));
						StockOption so = new StockOption((Stock) a, strikePrice, shareLimit, premium, strikeDate);
						if (optionType.equals("C")) {
							Call c = new Call(assetIdFromDataBase, so);
							accountAssetList.add(c);
						} else if (optionType.equals("P")) {
							Put p = new Put(assetIdFromDataBase, so);
							accountAssetList.add(p);
						}
					}
				}
			}

		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}

		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return accountAssetList;
	}

	/**
	 * Loads in the data from the account table and adds the created account objects
	 * to the returned array list of accounts.
	 * 
	 * @return
	 */
	protected static final ArrayList<Account> loadAccountTable() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Account a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountQuery = "select accountId, accountCode, accountType, ownerId, managerId, beneficiaryId from Account;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(accountQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int accountIdFromDataBase = rs.getInt("accountId");
				String accountCode = rs.getString("accountCode");
				String accountType = rs.getString("accountType");
				int ownerId = rs.getInt("ownerId");
				int managerId = rs.getInt("managerId");
				int beneficiaryId = rs.getInt("beneficiaryId");
				Person owner = getPersonByPersonId(ownerId);
				Person manager = getPersonByPersonId(managerId);
				Person beneficiary = getPersonByPersonId(beneficiaryId);
				getPersonEmailsByPersonId(owner, ownerId);
				getPersonEmailsByPersonId(manager, managerId);
				getPersonEmailsByPersonId(beneficiary, beneficiaryId);
				ArrayList<Asset> assetList = getAccountAssetsByAccountId(accountIdFromDataBase);
				if (accountType.equals("P")) {
					a = new Pro(accountIdFromDataBase, accountCode, owner, manager, beneficiary);
					for (Asset a1 : assetList) {
						a.addAsset(a1);
					}
				}
				if (accountType.equals("N")) {
					a = new Noob(accountIdFromDataBase, accountCode, owner, manager, beneficiary);
					for (Asset a1 : assetList) {
						a.addAsset(a1);
					}
				}
				accounts.add(a);
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}
		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot close for some reason.");
			throw new RuntimeException(e);
		}
		return accounts;
	}
}
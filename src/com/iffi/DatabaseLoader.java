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
	 * Creates and returns a name object from the database data by the person id.
	 * 
	 * @param personId
	 * @return
	 */
	private static final Name getNameByPersonId(int personId) {
		Name n = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String nameQuery = "select lastName, firstName from Person where personId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(nameQuery);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				n = new Name(lastName, firstName);
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
		return n;
	}

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
			while (rs.next()) {
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
				p.addEmail(rs.getString("email"));
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
	 * Loads in the data from the person table and adds the created person objects
	 * to the returned array list of people.
	 * 
	 * @return
	 */
	protected static final ArrayList<Person> loadPersonTable() {
		ArrayList<Person> people = new ArrayList<Person>();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String personQuery = "select personId, personCode, firstName, lastName, addressId from Person;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				int addressId = rs.getInt("addressId");
				Name n = getNameByPersonId(personId);
				Address a = getAddressByAddressId(addressId);
				Person p = new Person(personId, personCode, n, a);
				getPersonEmailsByPersonId(p, personId);
				people.add(p);
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
		return people;
	}

	/**
	 * Loads in the data from the asset table and adds the created asset objects to
	 * the returned array list of assets.
	 * 
	 * @return
	 */
	protected static final ArrayList<Asset> loadAssetTable() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		Asset a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String assetQuery = "select assetId, assetCode, assetType, label, currentAppraisedValue, currentExchangeRate, exchangeRateFee, symbol, currentSharePrice from Asset";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(assetQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int assetId = rs.getInt("assetId");
				String assetCode = rs.getString("assetCode");
				String assetType = rs.getString("assetType");
				String label = rs.getString("label");
				if (assetType.equals("P")) {
					double currentAppraisedValue = rs.getDouble("currentAppraisedValue");
					a = new Property(assetId, assetCode, label, currentAppraisedValue);
				}
				if (assetType.equals("C")) {
					double currentExchangeRate = rs.getDouble("currentExchangeRate");
					double exchangeFeeRate = rs.getDouble("exchangeRateFee");
					a = new Cryptocurrency(assetId, assetCode, label, currentExchangeRate, exchangeFeeRate);
				}
				if (assetType.equals("S")) {
					String symbol = rs.getString("symbol");
					double currentSharePrice = rs.getDouble("currentSharePrice");
					a = new Stock(assetId, assetCode, label, symbol, currentSharePrice);
				}
				assets.add(a);
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
		return assets;
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
		ArrayList<Asset> assetList = loadAssetTable();
		Asset a = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountAssetQuery = "select a.purchasedDate, a.purchasedPrice, a.purchasedExchangeRate, a.numCoins, a.purchasedSharePrice, a.numShares, a.dividendTotal, a.optionType, a.strikePrice, a.shareLimit, a.premium, a.strikeDate, a.accountId, a.assetId from AccountAsset a join Asset a1 on a.assetId = a1.assetId where accountId = ?;";
		PreparedStatement psAccountAsset = null;
		ResultSet rsAccountAsset = null;

		try {
			psAccountAsset = conn.prepareStatement(accountAssetQuery);
			psAccountAsset.setInt(1, accountId);
			rsAccountAsset = psAccountAsset.executeQuery();
			while (rsAccountAsset.next()) {
				LocalDate purchasedDate = LocalDate.parse(rsAccountAsset.getString("purchasedDate"));

				double purchasedPrice = rsAccountAsset.getDouble("purchasedPrice");

				double purchasedExchangeRate = rsAccountAsset.getDouble("purchasedExchangeRate");
				double numCoins = rsAccountAsset.getDouble("numCoins");

				double purchasedSharePrice = rsAccountAsset.getDouble("purchasedSharePrice");
				double numShares = rsAccountAsset.getDouble("numShares");
				double dividendTotal = rsAccountAsset.getDouble("dividendTotal");

				String optionType = rsAccountAsset.getString("optionType");
				double strikePrice = rsAccountAsset.getDouble("strikePrice");
				double shareLimit = rsAccountAsset.getDouble("shareLimit");
				double premium = rsAccountAsset.getDouble("premium");

				Integer assetId = rsAccountAsset.getInt("assetId");
				for (Asset a1 : assetList) {
					if (a1.getClass().equals(Property.class) && assetId == a1.assetId) {
						a = new Property(assetId, ((Property) a1), purchasedDate, purchasedPrice);
						accountAssetList.add(a);
						break;
					} else if (a1.getClass().equals(Cryptocurrency.class) && assetId == a1.assetId) {
						a = new Cryptocurrency(assetId, ((Cryptocurrency) a1), purchasedDate, purchasedExchangeRate,
								numCoins);
						accountAssetList.add(a);
						break;
					} else if (a1.getClass().equals(Stock.class) && assetId == a1.assetId) {

						Stock s = new Stock(a1, ((Stock) a1).getSymbol(), ((Stock) a1).getCurrentSharePrice());
						if (optionType != null && optionType.equals("P")) {
							LocalDate strikeDate = LocalDate.parse(rsAccountAsset.getString("strikeDate"));
							StockOption so = new StockOption(s, purchasedDate, strikePrice, shareLimit, premium,
									strikeDate);
							Put p = new Put(so);
							a = new Put(assetId, p);
						} else if (optionType != null && optionType.equals("C")) {
							LocalDate strikeDate = LocalDate.parse(rsAccountAsset.getString("strikeDate"));
							StockOption so = new StockOption(s, purchasedDate, strikePrice, shareLimit, premium,
									strikeDate);
							Call c = new Call(so);
							a = new Call(assetId, c);
						} else {
							a = new Stock(assetId, s, purchasedDate, purchasedSharePrice, numShares, dividendTotal);
						}
						accountAssetList.add(a);
						break;
					}

				}
			}
		} catch (SQLException e) {
			System.err.println("SQLException: Cannot get data.");
			throw new RuntimeException(e);
		}

		try {
			rsAccountAsset.close();
			psAccountAsset.close();
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
				int accountId = rs.getInt("accountId");
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
				ArrayList<Asset> assetList = getAccountAssetsByAccountId(accountId);
				if (accountType.equals("P")) {
					a = new Pro(accountId, accountCode, owner, manager, beneficiary);
					for (Asset a1 : assetList) {
						a.addAsset(a1);
					}
				}
				if (accountType.equals("N")) {
					a = new Noob(accountId, accountCode, owner, manager, beneficiary);
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

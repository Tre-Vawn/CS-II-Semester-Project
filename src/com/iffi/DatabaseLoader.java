package com.iffi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public static final Name getNameByPersonId(int personId) {
		Name n = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String nameQuery = "select lastName, firstName from Person p where personId = ?;";
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
	public static final Address getAddressByAddressId(int addressId) {
		Address a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String addressQuery = "select * from Address a where addressId = ?;";
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
	 * Loads in the data from the person table and adds the created person objects
	 * to the returned array list of people.
	 * 
	 * @return
	 */
	public static final ArrayList<Person> loadPersonTable() {
		ArrayList<Person> people = new ArrayList<Person>();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String personQuery = "select * from Person;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				int addressId = rs.getInt("addressId");
				Name n = getNameByPersonId(personId);
				Address a = getAddressByAddressId(addressId);
				Person p = new Person(personId, personCode, n, a);
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
	public static final ArrayList<Asset> loadAssetTable() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		Asset a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String assetQuery = "select * from Asset";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(assetQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int assetId = rs.getInt("assetId");
				String assetCode = rs.getString("assetCode");
				String type = rs.getString("assetType");
				String label = rs.getString("label");
				if (type == "P") {
					double currentAppraisedValue = rs.getDouble("currentAppraisedValue");
					a = new Property(assetId, assetCode, label, currentAppraisedValue);
				}
				if (type == "C") {
					double currentExchangeRate = rs.getDouble("currentExchangeRate");
					double exchangeFeeRate = rs.getDouble("exchangeFeeRate");
					a = new Cryptocurrency(assetId, assetCode, label, currentExchangeRate, exchangeFeeRate);
				}
				if (type == "S") {
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
	public static final Person getPersonByPersonId(int personId) {
		Person p = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.err.println("SQLException: Connection failed.");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String personQuery = "select * from Person a where personId = ?;";
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
				p = new Person(personIdFromDatabase, personCode, new Name(firstName, lastName), a);
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
	 * Loads in the data from the account table and adds the created account objects
	 * to the returned array list of accounts.
	 * 
	 * @return
	 */
	public static final ArrayList<Account> loadAccountTable() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Account a = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String accountQuery = "select * from Account;";
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
				if (accountType == "P") {
					a = new Pro(accountId, accountCode, owner, manager, beneficiary);
				}
				if (accountType == "N") {
					a = new Noob(accountId, accountCode, owner, manager, beneficiary);
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

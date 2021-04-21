package com.mgg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database interface class
 */
public class SalesData {

	/**
	 * executes a query when there is nothing selected from it and there's no
	 * inserted values
	 */
	public static void executeUpdate(String query, Connection conn) {

		try {
			PreparedStatement ps = null;

			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * gets the personId from the database using the personCode
	 */
	private static int getPersonId(String personCode, Connection conn) {
		String query = "select personId from Person where personCode = ?;";
		int personId = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				personId = rs.getInt("Person.personId");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return personId;
	}

	/**
	 * gets the saleId from the saleCode
	 */
	private static int getSaleId(String saleCode, Connection conn) {
		int saleId = 0;
		String query = "select saleId from Sale where saleCode = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				saleId = rs.getInt("Sale.saleId");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return saleId;
	}

	/**
	 * gets the itemId from the itemCode
	 */
	private static int getItemId(String itemCode, Connection conn) {
		int itemId = 0;
		String query = "select itemId from Item where itemCode = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				itemId = rs.getInt("Item.itemId");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return itemId;
	}

	/**
	 * Removes all sales records from the database.
	 */
	public static void removeAllSales() {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/**
		 * deletes all items sold in a sale
		 */
		String query = "set foreign_key_checks = 0;";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		/**
		 * removes all the sales
		 */
		query = "truncate table Sale;";
		try {

			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		query = "set foreign_key_checks = 1;";
		ps = null;

		try {

			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Removes the single sales record associated with the given
	 * <code>saleCode</code>
	 *
	 * @param saleCode
	 */
	public static void removeSale(String saleCode) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/**
		 * deletes the saleItems in the sale
		 */
		String query = "select saleItemId from SaleItem\n"
				+ "inner join Sale on SaleItem.saleId = Sale.saleId where Sale.saleCode = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int saleItemId = rs.getInt("SaleItem.saleItemId");
				String query2 = "delete from SaleItem where saleItemId = ?;";
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, saleItemId);
				ps2.executeUpdate();
				ps2.close();

			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		/**
		 * deletes the sale
		 */
		query = "delete from Sale where saleCode = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Clears all tables of the database of all records.
	 */
	public static void clearDatabase() {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String query = "set foreign_key_checks = 0;";
		executeUpdate(query, conn);
		query = "truncate table SaleItem;";
		executeUpdate(query, conn);
		query = "truncate table Sale;";
		executeUpdate(query, conn);
		query = "truncate table Address;";
		executeUpdate(query, conn);
		query = "truncate table Country;";
		executeUpdate(query, conn);
		query = "truncate table State;";
		executeUpdate(query, conn);
		query = "truncate table Store;";
		executeUpdate(query, conn);
		query = "truncate table Email;";
		executeUpdate(query, conn);
		query = "truncate table Person;";
		executeUpdate(query, conn);
		query = "truncate table Item;";
		executeUpdate(query, conn);
		query = "set foreign_key_checks = 1;";
		executeUpdate(query, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>type</code> will be one of "E", "G", "P" or "C" depending on the type
	 * (employee or type of customer).
	 *
	 * @param personCode
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String type, String firstName, String lastName, String street,
			String city, String state, String zip, String country) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/**
		 * insert person into person table
		 */
		String query = "alter table Person auto_increment = 1000;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		query = "insert into Person (personCode, type, firstName, lastName) values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, type);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/**
		 * get the new person's personId in order to put it into the address table
		 */
		int personId = getPersonId(personCode, conn);
		/**
		 * insert person's address into address table
		 */
		query = "alter table Address auto_increment = 1000;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		query = "insert into Address (personId, street, city, state, zip, country) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			ps.setString(2, street);
			ps.setString(3, city);
			ps.setString(4, state);
			ps.setString(5, zip);
			ps.setString(6, country);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int personId = getPersonId(personCode, conn);
		String query = "alter table Email auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into Email (email, personId) values (?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, personId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 *
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {

		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int storeId = 0;
		int personId = getPersonId(managerCode, conn);
		String query = "alter table Store auto_increment = 1000;";
		executeUpdate(query, conn);

		query = "insert into Store (storeCode, managerId) values (?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ps.setInt(2, personId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		query = "select storeId from Store where storeCode = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				storeId = rs.getInt("storeId");
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		query = "alter table Address auto_increment = 1000;";
		executeUpdate(query, conn);

		query = "insert into Address (storeId, street, city, state, zip, country) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, storeId);
			ps.setString(2, street);
			ps.setString(3, city);
			ps.setString(4, state);
			ps.setString(5, zip);
			ps.setString(6, country);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds a sales item (product, service, subscription) record to the database
	 * with the given <code>name</code> and <code>basePrice</code>. The type of item
	 * is specified by the <code>type</code> which may be one of "PN", "PU", "PG",
	 * "SV", or "SB". These correspond to new products, used products, gift cards
	 * (for which <code>basePrice</code> will be <code>null</code>), services, and
	 * subscriptions.
	 *
	 * @param itemCode
	 * @param type
	 * @param name
	 * @param basePrice
	 */
	public static void addItem(String itemCode, String type, String name, Double basePrice) {

		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String query = "alter table Item auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into Item (itemCode, itemType, itemName, cost) values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setDouble(4, basePrice);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a sales record to the database with the given data.
	 *
	 * @param saleCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 */
	public static void addSale(String saleCode, String storeCode, String customerCode, String salesPersonCode) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int storeId = 0;
		/**
		 * gets storeId
		 */
		String query = "select storeId from Store where storeCode = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				storeId = rs.getInt("Store.storeId");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		int salePersonId = getPersonId(salesPersonCode, conn);
		/**
		 * gets customerId
		 */
		int customerId = getPersonId(customerCode, conn);
		/**
		 * inserts new sale into Sale table
		 */
		query = "alter table Sale auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into Sale (saleCode, storeId, customerId, salepersonId) values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			ps.setInt(2, storeId);
			ps.setInt(3, customerId);
			ps.setInt(4, salePersonId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular product (new or used, identified by <code>itemCode</code>)
	 * to a particular sale record (identified by <code>saleCode</code>) with the
	 * specified quantity.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToSale(String saleCode, String itemCode, int quantity) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/**
		 * gets the saleId
		 */
		int saleId = getSaleId(saleCode, conn);
		/**
		 * gets the itemId
		 */
		int itemId = getItemId(itemCode, conn);
		/**
		 * inserts new saleItem into SaleItem table
		 */
		String query = "alter table SaleItem auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into SaleItem (saleId, itemId, quantity) values (?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			ps.setInt(2, itemId);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular gift card (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) in the specified
	 * amount.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addGiftCardToSale(String saleCode, String itemCode, double amount) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int saleId = getSaleId(saleCode, conn);
		int itemId = getItemId(itemCode, conn);
		String query = "alter table SaleItem auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into SaleItem (saleId, itemId, cost) values (?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			ps.setInt(2, itemId);
			ps.setDouble(3, amount);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which will be
	 * performed by the given employee for the specified number of hours.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param employeeCode
	 * @param billedHours
	 */
	public static void addServiceToSale(String saleCode, String itemCode, String employeeCode, double billedHours) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int saleId = getSaleId(saleCode, conn);
		int itemId = getItemId(itemCode, conn);
		int employeeId = getPersonId(employeeCode, conn);
		String query = "alter table SaleItem auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into SaleItem (saleId, itemId, salespersonId, numHours) values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			ps.setInt(2, itemId);
			ps.setInt(3, employeeId);
			ps.setDouble(4, billedHours);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular subscription (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which is
	 * effective from the <code>startDate</code> to the <code>endDate</code>
	 * inclusive of both dates.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addSubscriptionToSale(String saleCode, String itemCode, String startDate, String endDate) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int saleId = getSaleId(saleCode, conn);
		int itemId = getItemId(itemCode, conn);
		String query = "alter table SaleItem auto_increment = 1000;";
		executeUpdate(query, conn);
		query = "insert into SaleItem (saleId, itemId, beginDate, endDate) values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			ps.setInt(2, itemId);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
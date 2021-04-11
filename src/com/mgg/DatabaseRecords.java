package com.mgg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Vasavi Kotipalli
 * @author Madison Maloney Date: 04/09/2021 Purpose of Program: This class
 *         focuses on producing lists of the people, salespeople, stores, items,
 *         and sales with the data is found in the MySQL database. The required
 *         data is selected by the queries and is made into variables that can
 *         be used in Java. After retrieving the data form the query, the data
 *         is added to its respective list.
 * 
 */
public class DatabaseRecords {
	private static List<Integer> _personsId = new ArrayList<>();
	private static List<Integer> _storeId = new ArrayList<>();
	private static List<Integer> _saleId = new ArrayList<>();
	private static List<Integer> _itemId = new ArrayList<>();

	/**
	 * This method finds a person code and returns that code based on a given
	 * personId from the MySQL database. If no person code is found based on the
	 * personId then null is returned.
	 * 
	 * @param personId
	 * @return personCode
	 */

	public static String findPersonCodeById(int personId) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select p.personId, p.personCode from Person p;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personIdSQL = rs.getInt("p.personId");
				String personCode = rs.getString("p.personCode");
				if (personIdSQL == personId) {
					return personCode;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * This method finds a store code and returns that code based on a given storeId
	 * from the MySQL database. If no store code is found based on the storeId then
	 * null is returned.
	 * 
	 * @param storeId
	 * @return
	 */
	public static String findStoreCodeById(int storeId) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select storeId, storeCode from Store;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int storeIdSQL = rs.getInt("storeId");
				String stroreCode = rs.getString("storeCode");
				if (storeIdSQL == storeId) {
					return stroreCode;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * This method returns a list of all the email for a specific person based on
	 * the personId.
	 * 
	 * @param _personsId
	 * @return
	 */
	public static List<String> getEmails(int _personsId) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<String> emailList = new ArrayList<>();

		String query = "select p.personId, e.email from Person p  inner join Email e on e.personId = p.personId;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("p.personId");
				String email = rs.getString("e.email");

				if (_personsId == personId && !"".equals(email)) {
					emailList.add(email);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return emailList;
	}

	/**
	 * This method gets the person's data from the query and adds the data to a list
	 * of salespeople after checking if the person type is 'E' (employee).
	 * 
	 * @return List<Person> salesperson
	 */

	public static List<Person> getSalespeople() {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<Person> salespersons = new ArrayList<>();

		String query = "select p.personId, p.personCode, p.firstName, p.lastName, p.type, a.street, a.city,a.state, a.zip, a.country "
				+ "from Person p inner join Address a on a.addressId = p.addressId;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("p.personId");
				String personCode = rs.getString("p.personCode");
				String firstName = rs.getString("p.firstName");
				String lastName = rs.getString("p.lastName");
				String type = rs.getString("p.type");
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("a.state");
				String zip = rs.getString("a.zip");
				String country = rs.getString("a.country");

				if (type.equals("E")) {
					List<String> email = getEmails(personId);
					Address address = new Address(street, city, state, zip, country);
					Person newPerson = new Person(personCode, type, lastName, firstName, address, email);
					salespersons.add(newPerson);
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return salespersons;
	}

	/**
	 * This method gets the person's data from the query and adds the data to a list
	 * of persons.
	 * 
	 * @return List<Person> persons
	 */

	public static List<Person> getPeople() {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<Person> persons = new ArrayList<>();

		String query = "select p.personId, p.personCode, p.firstName, p.lastName, p.type, a.street, a.city,a.state, a.zip, a.country "
				+ "from Person p inner join Address a on a.addressId = p.addressId;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("p.personId");
				String personCode = rs.getString("p.personCode");
				String firstName = rs.getString("p.firstName");
				String lastName = rs.getString("p.lastName");
				String type = rs.getString("p.type");
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("a.state");
				String zip = rs.getString("a.zip");
				String country = rs.getString("a.country");
				List<String> email = getEmails(personId);
				Address address = new Address(street, city, state, zip, country);
				Person newPerson = new Person(personCode, type, lastName, firstName, address, email);
				persons.add(newPerson);
				_personsId.add(personId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return persons;
	}

	/**
	 * The store's data is found with the use of the query and is added to the list
	 * of stores. This method takes in the persons list within its parameter in
	 * order to get the store manager's information.
	 * 
	 * @param persons
	 * @return
	 */

	public static List<Store> getStores(List<Person> persons) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select s.storeId, s.storeCode, s.managerId, p.personId,p.personCode, p.type, p.lastName,"
				+ " p.firstName,  a.street, a.city, a.state, a.zip, a.country from Store s "
				+ "inner join Address a on a.addressId = s.addressId "
				+ "inner join Person p on p.personId = s.managerId;";

		List<Store> stores = new ArrayList<>();

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int storeId = rs.getInt("s.storeId");
				String storeCode = rs.getString("s.storeCode");
				String managerCode = rs.getString("p.personCode");
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("a.state");
				String zip = rs.getString("a.zip");
				String country = rs.getString("a.country");

				Address address = new Address(street, city, state, zip, country);
				Person manager = Person.getPerson(managerCode, persons);
				Store store = new Store(storeCode, manager, address);
				stores.add(store);
				_storeId.add(storeId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return stores;
	}

	/**
	 * The item's data is found with the use of the query and is added to the list
	 * of items. This method takes in the persons list within its parameter in order
	 * to salesperson's info for services.
	 * 
	 * @param persons
	 * @return
	 */

	public static List<Item> getItems(List<Person> persons) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<Item> items = new ArrayList<>();

		String query = "select itemId, itemCode, itemType, itemName, employeeId, numHours, beginDate, endDate, cost, quantity from Item;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int itemId = rs.getInt("itemId");
				String itemCode = rs.getString("itemCode");
				String type = rs.getString("itemType");
				String name = rs.getString("itemName");
				double price = rs.getDouble("cost");

				_itemId.add(itemId);
				if (type.equals("PN")) {
					double quantity = rs.getDouble("quantity");
					ProductNew np = new ProductNew(itemCode, type, name, price, quantity);
					items.add(np);
				} else if (type.equals("PG")) {
					ProductGiftCard gc = new ProductGiftCard(itemCode, type, name, price); // price???
					items.add(gc);
				} else if (type.equals("PU")) {
					double quantity = rs.getDouble("quantity");
					ProductUsed up = new ProductUsed(itemCode, type, name, price, quantity);
					items.add(up);
				} else if (type.equals("SB")) {
					String beginDateString = rs.getString("beginDate");
					String endDateString = rs.getString("endDate");
					LocalDate beginDate = LocalDate.parse(beginDateString);
					LocalDate endDate = LocalDate.parse(endDateString);
					Subscription sb = new Subscription(itemCode, type, name, price, beginDate, endDate);
					items.add(sb);
				} else if (type.equals("SV")) {
					int employeeId = rs.getInt("employeeId");
					double numHours = rs.getDouble("numHours");
					String employeeCode = findPersonCodeById(employeeId);
					Person serviceEmployee = Person.getPerson(employeeCode, persons);
					Service sv = new Service(itemCode, type, name, serviceEmployee, price, numHours);
					items.add(sv);
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return items;
	}

	/**
	 * This method returns a list of all the items bought on a certain sale based on
	 * the saleId.
	 * 
	 * @param _saleId
	 * @param items
	 * @return List<Item> saleItemsList
	 */

	public static List<Item> getSaleItems(int _saleId, List<Item> items) {
		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<Item> saleItemsList = new ArrayList<>();

		String query = " select si.saleId, i.itemId, i.itemCode from SaleItem si join Item i on si.itemId = i.itemId;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int saleId = rs.getInt("si.saleId");
				String itemCode = rs.getString("i.itemCode");

				if (_saleId == saleId && !"".equals(itemCode)) {
					saleItemsList.add(Item.getItem(itemCode, items));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return saleItemsList;
	}

	/**
	 * The sale's data is found with the use of the query and is added to the list
	 * of sales. This method takes in the persons list within its parameter in order
	 * to customer's and salesperson's information. It also takes in the stores list
	 * to get storeCode and the items list to use the getSaleItems() method above.
	 * 
	 * @param stores
	 * @param persons
	 * @param items
	 * @return List<Sale> sales
	 */

	public static List<Sale> getSales(List<Store> stores, List<Person> persons, List<Item> items) {

		String USERNAME = DatabaseInfo.USERNAME;
		String PASSWORD = DatabaseInfo.PASSWORD;
		String URL = DatabaseInfo.URL;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		List<Sale> sales = new ArrayList<>();

		String query = "select s.saleId, s.saleCode, st.storeCode, c.personCode, sl.personCode from Sale s  "
				+ "join Store st on st.storeId = s.storeId " 
				+ "join Person c on c.personId = s.customerId "
				+ "join Person sl on sl.personId = s.salespersonId;";

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int saleId = rs.getInt("s.saleId");
				String saleCode = rs.getString("s.saleCode");
				String storeCode = rs.getString("st.storeCode");
				String customerCode = rs.getString("c.personCode");
				String salespersonCode = rs.getString("sl.personCode");

				Person customer = Person.getPerson(customerCode, persons);
				Person salesperson = Person.getPerson(salespersonCode, persons);
				Store store = Store.getStore(storeCode, stores);

				_saleId.add(saleId);

				List<Item> itemSale = getSaleItems(saleId, items);
				Sale sale = new Sale(saleCode, customer, salesperson, store, itemSale);
				sales.add(sale);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return sales;
	}

}

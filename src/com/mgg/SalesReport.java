package com.mgg;

import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 3/03/21
 * 
 *         Purpose of Program: This is a sales report file. It computes the
 *         subtotal, tax, discount price, and grand total amount given a list of
 *         sales (and the specific sale attributes). It also produces a
 *         salesperson and store sales summary report. For the salesperson
 *         summary report this checks the number of sales a salesperson has made
 *         and the grand total amount. For the store sales summary report this
 *         checks the number of sales a store has made, its manager, and the
 *         grand total amount.
 */

public class SalesReport {

	public static void main(String[] args) {
		List<Sale> salesList = DataLoadingFile.loadSaleFile();
		List<Person> saleperson = Person.getSalesPerson();
		List<Store> storeList = DataLoadingFile.loadStoreFile();

		System.out.println(
				"+----------------------------------------------------+\n|Saleperson Report Summary\t\t\t\t|\n+----------------------------------------------------+");
		System.out.printf("%-20s %-10s %s\n", "Salesperson", "# Sales", "Grand Total");

		for (int i = 0; i < Person.getSalesPerson().size(); i++) {
			System.out.println(saleperson.get(i).getLastName() + ", " + saleperson.get(i).getFirstName() + "\t\t"
					+ saleperson.get(i).getSaleCount());
		}
		System.out.println("\n");

		System.out.println(
				"\n+----------------------------------------------------+\n|Store Sales Summary Report   \t\t\t\t|\n+----------------------------------------------------+");
		System.out.printf("%-10s %-20s %-10s %s\n", "Store", "Manager", "Sales", "Grand Total");

		for (int i = 0; i < storeList.size(); i++) {
			System.out.println(storeList.get(i).getStoreCode() + " " + storeList.get(i).getManager().getLastName()
					+ ", " + storeList.get(i).getManager().getFirstName());
		}

		for (int i = 0; i < salesList.size(); i++) {
			double subtotal = salesList.get(i).getSubTotal();
			double totalTax = salesList.get(i).getTotalTax();
			double discount = salesList.get(i).getDiscountPrice();
			double totalAmount = subtotal + totalTax - discount;
			System.out.println(salesList.get(i).toString());
			System.out.printf("Subtotal: $ %59.2f\n", subtotal);
			System.out.printf("Tax: $%64.2f\n", totalTax);
			System.out.printf("Discount: $%59.2f\n", discount);
			System.out.printf("Total: $%62.2f\n", totalAmount);
			System.out.println("\n");
		}
	}
}
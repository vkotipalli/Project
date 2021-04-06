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

	public static int countSalesPerSalesperson(String salespersonCode, List<Sale> salesList) {
		int count = 0;
		for (int i = 0; i < salesList.size(); i++) {
			if (salesList.get(i).getSalesperson().getPersonCode().equals(salespersonCode)) {
				count = count + 1;
			}
		}
		return count;
	}

	public static double priceOfSalesPerSalesperson(String salespersonCode, List<Sale> salesList) {
		double totalPrice = 0.0;
		for (int i = 0; i < salesList.size(); i++) {
			if (salesList.get(i).getSalesperson().getPersonCode().equals(salespersonCode)) {
				totalPrice = totalPrice + salesList.get(i).getTotalAmount();
			}
		}
		return Math.round(totalPrice * 100.0) / 100.0;
	}

	public static void SalespersonReport(List<Person> saleperson, List<Sale> salesList) {
		System.out.println("+-------------------------------------------------------+\n|Saleperson Report "
				+ "Summary\t\t\t\t|\n+-------------------------------------------------------+");

		System.out.printf("%-28s %-15s %s\n", "Salesperson", "# Sales", "Grand Total");

		int total = 0;
		double grandTotal = 0.0;

		for (int i = 0; i < saleperson.size(); i++) {
			System.out.print(saleperson.get(i).getLastName() + ", " + saleperson.get(i).getFirstName());

			int count = countSalesPerSalesperson(saleperson.get(i).getPersonCode(), salesList);
			System.out.print("    \t\t" + count);
			total += count;

			double price = priceOfSalesPerSalesperson(saleperson.get(i).getPersonCode(), salesList);
			System.out.println("    \t\t$" + price);
			grandTotal += price;
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t\t\t" + total + "\t\t$" + Math.round(grandTotal * 100.0) / 100.0);
	}

	public static int countSalesPerStore(String storeCode, List<Sale> salesList) {
		int count = 0;
		for (int i = 0; i < salesList.size(); i++) {
			if (salesList.get(i).getStore().getStoreCode().equals(storeCode)) {
				count = count + 1;
			}
		}
		return count;
	}

	public static double priceOfSalesPerStore(String storeCode, List<Sale> salesList) {
		double totalPrice = 0.0;
		for (int i = 0; i < salesList.size(); i++) {
			if (salesList.get(i).getStore().getStoreCode().equals(storeCode)) {
				totalPrice = totalPrice + salesList.get(i).getTotalAmount();
			}
		}
		return (Math.round(totalPrice * 100.0)) / 100.0;
	}

	public static void StoreSalesReport(List<Store> storeList, List<Sale> salesList) {
		System.out.println("\n+-------------------------------------------------------+\n|Store Sales Summary"
				+ " Report    \t\t\t\t|\n+-------------------------------------------------------+");
		System.out.printf("%-15s %-20s %-10s %s\n", "Store", "Manager", "Sales", "Grand Total");

		int total = 0;
		double grandTotal = 0.0;

		for (int i = 0; i < storeList.size(); i++) {
			System.out.print(storeList.get(i).getStoreCode() + "\t" + storeList.get(i).getManager().getLastName() + ", "
					+ storeList.get(i).getManager().getFirstName());

			int count = countSalesPerStore(storeList.get(i).getStoreCode(), salesList);
			System.out.print("   \t" + count);
			total += count;

			double price = priceOfSalesPerStore(storeList.get(i).getStoreCode(), salesList);
			System.out.println("     \t$" + price);
			grandTotal += price;
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t\t\t\t" + total + "\t$" + Math.round(grandTotal * 100.0) / 100.0);
	}

	public static void IndividualSales(List<Sale> salesList) {
		for (int i = 0; i < salesList.size(); i++) {
			double subtotal = salesList.get(i).getSubTotal();
			double totalTax = salesList.get(i).getTotalTax();
			double discount = salesList.get(i).getDiscountPrice();
			double totalAmount = salesList.get(i).getTotalAmount();
			System.out.println(salesList.get(i).toString());
			System.out.printf("Subtotal: $ %59.2f\n", subtotal);
			System.out.printf("Tax: $%64.2f\n", totalTax);
			System.out.printf("Discount: $%59.2f\n", discount);
			System.out.printf("Total: $%62.2f\n\n", totalAmount);
		}
	}

	public static void main(String[] args) {
		List<Sale> salesList = DataLoadingFile.loadSaleFile();
		List<Person> saleperson = Person.getSalesPerson();
		List<Store> storeList = DataLoadingFile.loadStoreFile();

		SalespersonReport(saleperson, salesList);
		StoreSalesReport(storeList, salesList);
		IndividualSales(salesList);

	}
}
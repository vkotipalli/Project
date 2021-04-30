package com.mgg;

import java.util.Comparator;
import java.util.List;

public class SortedSales {

	public static void main(String[] args) {
		
		Comparator<Sale> byCustomerName = new Comparator<>() {
			public int compare(Sale a, Sale b) {
				String customerA = a.getCustomer().getLastName();
				String customerB = b.getCustomer().getLastName();
				if (customerA.compareTo(customerB) != 0) {
					return customerA.compareTo(customerB);
				} else {
					return a.getCustomer().getFirstName().compareTo(b.getCustomer().getFirstName());
				}
			}
		};

		Comparator<Sale> byGrandTotal = new Comparator<>() {
			public int compare(Sale a, Sale b) {
				Double totalA = a.getTotalAmount();
				Double totalB = b.getTotalAmount();
				if (totalA.compareTo(totalB) == 0) {
					return 0;
				} else {
					return totalA.compareTo(totalB);
				}
			}
		};

		Comparator<Sale> byStoreandSalesperson = new Comparator<>() {
			public int compare(Sale a, Sale b) {
				String storeA = a.getStore().getStoreCode();
				String storeB = b.getStore().getStoreCode();
				String salespersonLastNameA = a.getSalesperson().getLastName();
				String salespersonLastNameB = b.getSalesperson().getLastName();
				String salespersonFirstNameA = a.getSalesperson().getFirstName();
				String salespersonFirstNameB = b.getSalesperson().getFirstName();
				if (storeA.compareTo(storeB) != 0) {
					return storeA.compareTo(storeB);
				} else if (salespersonLastNameA.compareTo(salespersonLastNameB) != 0) {
					return salespersonLastNameA.compareTo(salespersonLastNameB);
				} else {
					return salespersonFirstNameA.compareTo(salespersonFirstNameB);
				}
			}
		};
		
		List<Sale> salesList = DataLoadingFile.loadSaleFile();
		
		
		
		SaleList<Sale> linkedSalesByCustomer = new SaleList<>(byCustomerName); // SaleList.uploadToNewList(salesList);
		for (Sale sale : salesList) {
			linkedSalesByCustomer.add(sale);
		}
		
		

		System.out.println("Sales by Customer Name "
				+ "\n---------------------------------------------------------------------------------------\n"
				+ "Sale \t   Store \t   Customer \t   Salesperson \t   Total");
		System.out.println(linkedSalesByCustomer);

//		sortByCustomersName(linkedSales);
//		Comparator<Sale> byGrandTotal =linkedSales;
//		SaleList(byCustomerName);

//		System.out.println("Sales by GrandTotal "
//				+ "\n---------------------------------------------------------------------------------------");
//		sortByTotalAmount(linkedSales);
//		System.out.println(linkedSales.printList());

//		System.out.println("Sales by Store and Salesperson \n---------------------------------------------------------------------------------------");
//		sortByStoreandSalesperson(linkedSales);
//		System.out.println(linkedSales.printList());

//	
	}
	
	public static String printList(SaleList<Sale> salesList) {
		StringBuilder sb = new StringBuilder();
		if (salesList.isEmpty()) {
			sb.append("empty");
		} else {
			SaleListNode <Sale> current = salesList.getHead();
//			while (current != null) {
			for (int i = 0; i < salesList.getSize(); i++) {
				sb.append(current.getItem().getcode()+ "\t" + current.getItem().getStore().getStoreCode() + "\t"
						+ current.getItem().getCustomer().getLastName() + ","
						+ current.getItem().getCustomer().getFirstName() + "\t\t"
						+ current.getItem().getSalesperson().getLastName() + ","
						+ current.getItem().getSalesperson().getFirstName() + "\t\t" + "$  "
						+ current.getItem().getTotalAmount() + "\n");
				current = current.getNext();
			}
		}
		return sb.toString();
	
	}
}

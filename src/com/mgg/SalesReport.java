package com.mgg;

import java.util.List;

public class SalesReport {

	public static void main(String[] args) {

	
List<Sale> salesList = Sale.loadSaleFile();	
for(int i = 0; i<salesList.size(); i++) {
	System.out.println(salesList.get(i).toString());
}

}
}

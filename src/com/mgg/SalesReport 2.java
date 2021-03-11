package com.mgg;

import java.util.List;

public class SalesReport {
	
List<Sale> salesList = Sale.loadSaleFile();	

System.out.println(salesList);

}

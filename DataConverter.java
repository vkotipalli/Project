package com.mgg;

import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         Purpose of Program: This a data conversion program that takes in a
 *         csv file for each respective class (Person, Store, Item). Then we
 *         used the loadFile methods to get the information by reading it and
 *         tokenizing it. We then used our FileToJson method to output the
 *         information of each respective class into a json file.
 */
public class DataConverter {

	public static void main(String[] args) {

		List<Person> personsList = DataLoadingFile.loadPersonFile();
		DataLoadingFile.personFileToJson(personsList, "data/Persons.json");

		List<Store> storesList = DataLoadingFile.loadStoreFile();
		DataLoadingFile.storeFileToJson(storesList, "data/Stores.json");

		List<Item> itemsList = DataLoadingFile.loadItemFile();
		DataLoadingFile.itemsFileToJson(itemsList, "data/Items.json");

		List<Sale> salesList = DataLoadingFile.loadSaleFile();
		System.out.println(salesList);
	}
}
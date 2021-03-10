package com.mgg;

import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 *         Purpose of Program: This a data conversion program that takes in a
 *         csv file for each respective class (Person, Store, Item). Then we
 *         used the loadFile methods to get the information by reading it and
 *         tokenizing it. We then used our FileToJson method to output the
 *         information of each respective class into a json file.
 */
public class DataConverter {

	public static void main(String[] args) {

		List<Person> personsList = Person.loadPersonFile();
		Person.personFileToJson(personsList, "data/Persons.json");
		
		System.out.println("");
		
		List<Store> storesList = Store.loadStoreFile();
		Store.storeFileToJson(storesList, "data/Stores.json");
		
		System.out.println("");
		
		List<Item> itemsList = Item.loadItemFile();
		Item.itemsFileToJson(itemsList, "data/Items.json");
	}
}
package com.mgg;

import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 *         An abstract class that represents the Item object that's specified
 *         for the project
 */
public abstract class Item {
	private String code;
	private String name;
	private String type;

	public Item(String code, String name, String type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public abstract double getPrice();
	
	public abstract double getTax();

	/**
	 * This method returns a specific instance of an item (and its attributes) given a specific item code. If the given item code does
	 * not match the item codes within the item list null is returned.
	 * @param itemCode
	 * @param itemList
	 * @return specific item instance or null
	 */
	public static Item getItem(String itemCode, List<Item> itemList) {
		for(int i = 0; i<itemList.size(); i++) {
			if(itemList.get(i).getCode().equals(itemCode)) {
				return itemList.get(i);
			}
		}
		return null;
	}

}
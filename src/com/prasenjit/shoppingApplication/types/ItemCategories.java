/**
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.types;

import java.util.HashMap;
import java.util.Map;

public enum ItemCategories {

	BOOKS(){

		@Override
		public boolean isBasicTaxExempted() {
			return true;
		}
		
	},
	FOOD(){

		@Override
		public boolean isBasicTaxExempted() {
			return true;
		}
		
	},
	MEDICAL(){

		@Override
		public boolean isBasicTaxExempted() {
			return true;
		}
		
	},
	OTHERS(){

		@Override
		public boolean isBasicTaxExempted() {
			return false;
		}
		
	};
	
	public abstract boolean isBasicTaxExempted();
	
	private static Map<String, ItemCategories> itemVsItemCategories=new HashMap<String, ItemCategories>();
	
	/**
	 * 
	 * @param items
	 * @param categories
	 */
	public static void registerItems(String items,ItemCategories categories) {
		if (items!=null ) {
			String []strings=items.split(",");
			for (int i = 0; i < strings.length; i++) {
				itemVsItemCategories.put(strings[i].trim(), categories);
			}
		}
	}

	public static Map<String, ItemCategories> getItemVsItemCategories() {
		return itemVsItemCategories;
	}
}

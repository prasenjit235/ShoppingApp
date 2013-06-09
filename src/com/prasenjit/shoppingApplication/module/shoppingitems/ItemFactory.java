/**
 * This factory class creates Item using the
 * item description from file. It sets the item 
 * categories and adds {@link TaxCategories} as 
 * per eligibility of tax exemption.
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.module.shoppingitems;

import java.util.Set;

import com.prasenjit.shoppingApplication.types.ItemCategories;
import com.prasenjit.shoppingApplication.types.TaxCategories;
import com.prasenjit.shoppingApplication.utils.InvalidItemException;
import com.prasenjit.shoppingApplication.utils.ItemNotFoundException;

public class ItemFactory {
	/**
	 * 
	 * @param itemDescription - this is the line read 
	 * by ShoppingList.
	 * @return
	 * @throws InvalidItemException
	 * @throws ItemNotFoundException 
	 */
	public static Item createItem(String itemDescription)
			throws InvalidItemException, ItemNotFoundException {
		Item item = null;
		Set<String> keySet = ItemCategories.getItemVsItemCategories()
		.keySet();
		boolean itemFound=false;
		for (String string : keySet) {
			if (itemDescription.contains(string)){
				itemFound=true;
			}
		}
		if (!itemFound) {
			throw new ItemNotFoundException("Item Not Found:: " + itemDescription);
		}
		String[] strings = itemDescription.split(" ");
		try {
			item = new Item();
			item.setQuantity(Integer.parseInt(strings[0].trim())); 
			item.setPrice(Float.parseFloat(strings[strings.length - 1]));
			for (int i = 1; i < strings.length - 2; i++) {
				item
						.setDescription(item.getDescription() == null ? ""
								+ strings[i] : item.getDescription() + " "
								+ strings[i]);
			}
			if (itemDescription.indexOf("imported") > 0) {
				item.getTaxCategories().add(TaxCategories.ADDITIONAL);
			}
			
			for (String string : keySet) {
				if (itemDescription.contains(string)
						&& ItemCategories.getItemVsItemCategories().get(string) != null
						&& !ItemCategories.getItemVsItemCategories()
								.get(string).isBasicTaxExempted()) {
					item.getTaxCategories().add(TaxCategories.BASIC);
				}
			}

			for (String string : keySet) {
				if (itemDescription.contains(string)) {
					item.setCategory(ItemCategories.getItemVsItemCategories()
							.get(string));
				}
			}
		} catch (NumberFormatException e) {
			InvalidItemException invalidItemException = new InvalidItemException(
					"Invalid Item :: " + itemDescription);
			invalidItemException.setSourceException(e);
			throw invalidItemException;
		}
		return item;
	}

}

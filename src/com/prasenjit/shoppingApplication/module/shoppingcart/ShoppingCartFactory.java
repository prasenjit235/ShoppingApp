/**
 * A factory class to create ShoppingCart using
 * ShoppingList.
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.module.shoppingcart;

import java.io.IOException;

import com.prasenjit.shoppingApplication.module.shoppingitems.ItemFactory;
import com.prasenjit.shoppingApplication.utils.InvalidItemException;
import com.prasenjit.shoppingApplication.utils.ItemNotFoundException;

public class ShoppingCartFactory {

	/**
	 * 
	 * @param shoppingList
	 * @return
	 * @throws IOException
	 */
	public static ShoppingCart createShoppingCart(ShoppingList shoppingList) throws IOException {
		ShoppingCart shoppingCart = null;
		String string=null;
		try{
		while ((string=shoppingList.readNext())!=null) {
			if (shoppingCart==null) {
				shoppingCart=new ShoppingCart();
			}
			try {
				shoppingCart.addItem(ItemFactory.createItem(string));
			} catch (InvalidItemException e) {
				System.err.println(e.getMessage());
				System.err.println("Please follow the convention to write item description.(<Quantity> <item description> at <Unit Price>)");
				System.err.println("Moving to next Item!!");
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
				System.err.println("Please add the item in AppMain.properties");
				System.err.println("Moving to next Item!!");
			}
		}	
		}finally{
		if (shoppingList!=null) {
			shoppingList.listDone();
		}
		}
		return shoppingCart;
	}
}

/**
 * A service which will be used to carry out
 * typical cash counter related activities. 
 * @author prasenjit
 */
package com.prasenjit.shoppingApplication.service;

import java.io.IOException;

import com.prasenjit.shoppingApplication.module.shoppingcart.ShoppingCart;
import com.prasenjit.shoppingApplication.module.shoppingitems.Item;

public interface ICashCounterService {

	public void processShoppingcartAndGenerateReciept(ShoppingCart shoppingCart)throws IOException ;
	public void cashOutItem(Item item);
}

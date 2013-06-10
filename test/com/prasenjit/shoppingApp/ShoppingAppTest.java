/**
 * @author Prasenjit Das
 */

package com.prasenjit.shoppingApp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.prasenjit.shoppingApplication.module.shoppingcart.ShoppingCart;
import com.prasenjit.shoppingApplication.module.shoppingitems.Item;
import com.prasenjit.shoppingApplication.module.shoppingitems.ItemFactory;
import com.prasenjit.shoppingApplication.service.CashCounterServiceImpl;
import com.prasenjit.shoppingApplication.types.ItemCategories;
import com.prasenjit.shoppingApplication.utils.InvalidItemException;
import com.prasenjit.shoppingApplication.utils.ItemNotFoundException;
import com.prasenjit.shoppingApplication.utils.Utilities;

public class ShoppingAppTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ItemCategories.registerItems("book,copies", ItemCategories.BOOKS);
		ItemCategories.registerItems("chocolate bar,chocolates", ItemCategories.FOOD);
		ItemCategories.registerItems("pills", ItemCategories.MEDICAL);
		ItemCategories.registerItems("music CD", ItemCategories.OTHERS);
		Utilities.setRoundUpValue(.05f);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCase_1() throws IOException {
		ShoppingCart shoppingCart=new ShoppingCart();
		Item item=new Item();
		item.setPrice(10.0f);
		item.setQuantity(4);
		item.setDescription(" book at ");
		shoppingCart.addItem(item);
		CashCounterServiceImpl cashCounterService=new CashCounterServiceImpl();
		cashCounterService.processShoppingcartAndGenerateReciept(shoppingCart);
		List<String> strings=new ArrayList<String>();
		strings.add(item.getQuantity() + " " + item.getDescription()
				+ " : 40.00");
		strings.add("Sales Taxes: 0.00");
		strings.add("Total: 40.00");
		Assert.assertEquals(cashCounterService.getCashCounterItems(), strings);
	}
	
	@Test
	public void testCase_2() throws IOException {
		String []arr={"40.00","100.00"};
		ShoppingCart shoppingCart=new ShoppingCart();
		Item item=new Item();
		item.setPrice(10.0f);
		item.setQuantity(4);
		item.setDescription(" copies at ");
		shoppingCart.addItem(item);
		item=new Item();
		item.setPrice(20.0f);
		item.setQuantity(5);
		item.setDescription(" pills at ");
		shoppingCart.addItem(item);
		CashCounterServiceImpl cashCounterService=new CashCounterServiceImpl();
		cashCounterService.processShoppingcartAndGenerateReciept(shoppingCart);
		List<String> strings=new ArrayList<String>();
		int i=0;
		for (Item items : shoppingCart.getItems()) {
			strings.add(items.getQuantity() + " " + items.getDescription()
					+ " : "+arr[i]);
			i++;
		}
		
		strings.add("Sales Taxes: 0.00");
		strings.add("Total: 140.00");
		Assert.assertEquals(cashCounterService.getCashCounterItems(), strings);
	}
	
	@Test
	public void testCase_3() throws IOException, InvalidItemException, ItemNotFoundException {
		ShoppingCart shoppingCart=new ShoppingCart();
		Item item=ItemFactory.createItem("4 imported music CD at 11.0");
		shoppingCart.addItem(item);
		CashCounterServiceImpl cashCounterService=new CashCounterServiceImpl();
		cashCounterService.processShoppingcartAndGenerateReciept(shoppingCart);
		List<String> strings=new ArrayList<String>();
		strings.add(item.getQuantity() + " " + item.getDescription()
				+ " : 50.60");
		strings.add("Sales Taxes: 6.60");
		strings.add("Total: 50.60");
		Assert.assertEquals(cashCounterService.getCashCounterItems(), strings);
	}
	
	@Test
	public void testCase_4() throws IOException {
		ShoppingCart shoppingCart=new ShoppingCart();
		Item item;
		try {
			item = ItemFactory.createItem("4 imported pens at 11.0");
			shoppingCart.addItem(item);
			CashCounterServiceImpl cashCounterService=new CashCounterServiceImpl();
			cashCounterService.processShoppingcartAndGenerateReciept(shoppingCart);
			List<String> strings=new ArrayList<String>();
			strings.add(item.getQuantity() + " " + item.getDescription()
					+ " : 46.00");
			strings.add("Sales Taxes: 6.00");
			strings.add("Total: 46.00");
			Assert.assertEquals(cashCounterService.getCashCounterItems(), strings);
		} catch (InvalidItemException e) {

			Assert.assertTrue("Invalid Item Exception", true);
		} catch (ItemNotFoundException e) {
			Assert.assertTrue("Item Not Exception", true);
		}
		
		
	}
}
